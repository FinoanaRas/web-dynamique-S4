<%@ page import="model.Personne, java.util.ArrayList"%>
<%
    Personne p = (Personne)request.getAttribute("pers");
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
    <h1><%= p.getName() %></h1>
    <ul>
        <li><%= p.getGender() %></li>
        <li><%= p.getNum() %></li>
        <li><%= p.getDtn() %></li>
        <li><ul>
        <%  if(p.getLangues()!=null){
            for(int i=0;i < p.getLangues().length;i++){ %>
            <li><%= p.getLangues()[i] %></li>
        <% } }%>
        </ul></li>
    </ul>
</body>
</html>