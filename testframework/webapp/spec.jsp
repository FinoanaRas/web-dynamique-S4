<%@ page import="model.Personne" %>
<%
    Personne pers = (Personne) request.getAttribute("pers");
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
    <h1>Informations: <%= pers.getName() %></h1>
    <ul>
        <li><%= pers.getGender() %></li>
        <li><%= pers.getNum() %></li>
        <li><%= pers.getDtn() %></li>
    </ul>
</body>
</html>