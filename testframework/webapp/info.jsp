<%@ page import="model.Personne, java.util.ArrayList"%>
<%
    ArrayList<Personne> liste = (ArrayList<Personne>)request.getAttribute("lst");
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
    <h1>Informations: liste de personnes</h1>
    <ul>
        <% for(Personne p: liste) { %>
            <li><%= p.getName() %></li>
            <li><%= p.getGender() %></li>
            <li><%= p.getNum() %></li>
            <li><%= p.getDtn() %></li>
        <% } %>
    </ul>
</body>
</html>