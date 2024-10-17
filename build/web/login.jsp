<%-- 
    Document   : login
    Created on : Oct 15, 2024, 12:38:44 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LOGIN</h1>
        <%
            if(request.getAttribute("error")!= null){
            String er= (String)request.getAttribute("error");
        %>
        <h3 style="color: red "><%= er %></h3>
        <%
            }
        %> 


        <form action="login" method="post">
            UserName:<input type="text" name="user"  /><br/>
            Password:<input type="password" name="pass"  /><br/>
            <input type="submit" value="LOGIN"  />
        </form>

    </body>
</html>
