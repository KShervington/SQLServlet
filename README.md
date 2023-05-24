# SQLServlet

## Description
This is a three-tier web-based application which uses servlets and JSP technology. It originally ran on a Tomcat server. This servlet was created to access and maintain a persiustent MySQL database using JDBC.

## 1st Tier
The index.jsp page allows the user to enter SQL commands and submit them, after which, the web server handles the processing.

## 2nd Tier
SQL_Servlet.java handles the server-side processing and business logic. This would live on the Tomcat server and route user-entered SQL commands to this project's MySQL database.

## 3rd Tier
The MYSQL database houses the data which needs to be manipulated

### Simple SELECT
![Command 2A](https://github.com/KShervington/SQLServlet/assets/54691558/609b6c22-ae11-40ed-8014-debe4499f9be)

### Complex SELECT
![Command 1](https://github.com/KShervington/SQLServlet/assets/54691558/3081d45b-fdc1-4c85-9e9a-9768946e9bb4)

### Multi-Row UPDATE
![Command 5B](https://github.com/KShervington/SQLServlet/assets/54691558/3d30a34b-db98-46d0-9e47-ae623e62db9f)
