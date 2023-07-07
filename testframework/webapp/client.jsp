<%@ page import="model.Client, etu2054.framework.FileUpload" %>
<%
    Client pers = (Client) request.getAttribute("client");
    FileUpload f = pers.getBadge();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
    <body>
        <p><%= pers.getName()%></p>
        <p><%= f.getName()%></p>
        <p><%= f.getPath()%></p>
        <p><%= f.getImage()%></p>
        
    </body>
</html>