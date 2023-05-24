// A simple servlet to process get requests.

//import javax.servlet.*;
//import javax.servlet.http.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SQL_Servlet extends HttpServlet {

    // process "get" requests from clients
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException, IOException
    {

        // Database parameters
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/project4";
        String dbUsername = "root";
        String dbPassword = "root";

        // Set database parameters
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL( dbURL );
        dataSource.setUser( dbUsername );
        dataSource.setPassword( dbPassword );

        try {
            // Database connection
            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            String sqlStatement = request.getParameter("sqlStatement"); // session variable
            String message; // session variable
            int updateResult; //stores affected rows from update

            // Checks entered commands
            String checker = "SELECT";
            String insertChecker = "INTO SHIPMENTS";
            String updateChecker = "UPDATE SHIPMENTS";

            response.setContentType( "text/html" );

            statement = conn.prepareStatement( sqlStatement );

            // Check if "select" command
            if ( sqlStatement.toUpperCase().startsWith(checker) )
            {
                ResultSet resultSet = statement.executeQuery( sqlStatement );

                ResultSetMetaData metaData = resultSet.getMetaData();
                int colCount = metaData.getColumnCount(); // column count
                int i; // looping variable
                StringBuilder resultText = new StringBuilder(); // used to create table

                // START table creation
                resultText.append( "<table style=\"margin-left:auto;margin-right:auto;\">" );

                // Header for column names
                resultText.append( "<tr>" );

                for (i = 1; i <= colCount; i++)
                {
                    resultText.append( "<th>" );
                    resultText.append( metaData.getColumnName(i) );
                    resultText.append( "</th>" );
                }

                resultText.append( "</tr>" );

                // Create each row of the table
                while ( resultSet.next() )
                {
                    resultText.append( "<tr>" );

                    for (i = 1; i <= colCount; ++i)
                    {
                        resultText.append( "<td>" );
                        resultText.append( resultSet.getString(i) );
                        resultText.append( "</td>" );
                    }

                    resultText.append( "</tr>" );

                } // end of while loop

                resultText.append( "</table>" );
                // END table creation

                message = resultText.toString(); // holds entire result set table

            } // end of  if statement
            else
            {
                /* bonus
                if ( sqlStatement.toUpperCase().startsWith(updateChecker) || sqlStatement.toUpperCase().contains(insertChecker) )
                {
                    statement.executeUpdate("drop table if exists beforeShipments;create table beforeShipments like " +
                            "shipments;insert into beforeShipments select* from shipments;" );
                }
                 */

                updateResult = statement.executeUpdate( sqlStatement ); // Perform update command

                // Trigger for business logic
                if ( sqlStatement.toUpperCase().startsWith(updateChecker) || sqlStatement.toUpperCase().contains(insertChecker) )
                {
                    statement.executeUpdate("update suppliers set status = status + 5 where snum in (select snum " +
                            "from shipments where quantity >= 100)" );
                }

                /* bonus
                statement.executeUpdate("update suppliers set status = status + 5 where snum in (select distinct snum " +
                        "from shipments left join beforeShipments using (snum,pnum,jnum,quantity) where " +
                        "beforeShipments.snum is null and quantity > 100);");
                statement.executeUpdate("drop table beforeShipments;");
                */

                // Returns rows affected by command
                message = "<span style=\"color:green\">" + updateResult + "</span> row(s) affected";
            }

            // Assign session variables
            HttpSession session = request.getSession();
            session.setAttribute("message", message);
            session.setAttribute("sqlStatement", sqlStatement);

        } //end of try
        catch (SQLException e) {
            e.printStackTrace();
        }

        // Send data back to index.jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }   //end doGet() method


    public void doPost(HttpServletRequest request, HttpServletResponse response )
            throws IOException, ServletException {
        doGet(request, response);
    }

} //end SQL_Servlet class
