<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients</title>
    <link rel="stylesheet" href="css/table_style.css">
</head>
<body>
<table align="center" border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>surname</td>
        <td>phone</td>
        <td>address</td>
        <td>total sum</td>
        <td>last order date</td>
    </tr>
<c:forEach var="client" items="${list}">
    <tr>
        <td><c:out value="${client.id}"/></td>
        <td><c:out value="${client.name}"/></td>
        <td><c:out value="${client.surname}"/></td>
        <td><c:out value="${client.phone}"/></td>
        <td><c:out value="${client.address}"/></td>
        <td><c:out value="${client.sum}"/></td>
        <td><c:out value="${client.lastOrderDate}"/></td>
    </tr>
</c:forEach>
</table>
<a href="/dashboard.html" class="dashboard">DASHBOARD</a>
</body>
</html>