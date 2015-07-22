<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="css/table_style.css">
</head>
<body>
<table align="center" border="1">
    <tr>
        <td>id</td>
        <td>date of order</td>
        <td>client</td>
        <td>amount</td>
        <td>address from</td>
        <td>address to</td>
    </tr>
    <c:forEach var="order" items="${list}">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.dateOfOrder}"/></td>
            <td><c:out value="${order.client.name} ${order.client.surname}"/></td>
            <td><c:out value="${order.amount}"/></td>
            <td><c:out value="${order.addressFrom}"/></td>
            <td><c:out value="${order.addressTo}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="/dashboard.html" class="dashboard">DASHBOARD</a>
</body>
</html>