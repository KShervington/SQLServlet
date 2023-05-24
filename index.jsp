<!DOCTYPE html>


<%-- start scriplet --%>
    
<% 
   String sqlStatement = (String) session.getAttribute("sqlStatement");
   if (sqlStatement == null) sqlStatement= "select * from suppliers";
   String message = (String) session.getAttribute("message");
   if (message == null) message = " ";
%>


<!-- WelcomeServlet.html -->
<html lang="en">
    <head>
        <title>MySQL Servlet</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style type="text/css">
            <!--
            body {background-color: #35363a;
                color: white;
                text-align: center;
                justify-content: center;
                font-family: sans-serif;
                font-size: 1.5vw;}
            
            input {font-size: 1.5vw; 
                color: black;
                border: 1px;
                box-shadow: 5px 5px 5px 2px black;} 
            
            textarea {
                font-size: 1vw;
                color: lawngreen;
                background-color: black;
                border-radius: 100 px;
            }
            
            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #dbdbdb;
            }
            
            th {
                background-color: #4CAF50;
                color: white;
            }
            -->
        </style>
        
        
    </head>
    
    <body>
        <h1>Welcome to the Spring 2021 Project 4 Enterprise Database System<br>A Servlet/JSP-based Multi-tiered Enterprise Application Utilizing A Tomcat Container</h1>
        
        <h5 style="color: yellow"><b>Developed by: Kyle Shervington</b></h5>
        
        <hr>
        <br>
        <br>
        
        <p>You are connected to the Project 4 Enterprise System databse as an administrator.<br>Please enter any valid SQL query or update command.</p>
        
        <form action="/Project4/sqlServlet" method="post">
            
            <textarea name="sqlStatement" rows="20" cols="90"></textarea>
            
            <div>
                <input type="submit" value="Execute Command">
                <input type="button" value="Reset Form" onclick="reset();">
            </div>
        </form>
        
        <br>
        <br>
        
        <p>All execution will appear below.</p>
        
        <hr>
        
        <p><b>Database Results:</b></p>
        
        <h2>
            <%-- JSP expression to access servlet variable: message --%>
            <%=message%>
        </h2>  
               
            
    </body>
</html>