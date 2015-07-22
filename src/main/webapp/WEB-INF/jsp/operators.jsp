<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="css/table_style.css">
</head>
<body>
<table align="center" border="1">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>password</td>
        <%--<td>blocking</td>--%>
        <td>role</td>
    </tr>
    <c:forEach var="operator" items="${operators}">
        <tr>
            <td><c:out value="${operator.id}"/></td>
            <td><c:out value="${operator.login}"/></td>
            <td><c:out value="${operator.password}"/></td>
            <%--<td><c:out value="${operator.blocking}"/></td>--%>
            <td><c:out value="${operator.role.abbreviation}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="/dashboard.html" class="dashboard">DASHBOARD</a>
</body>
</html>