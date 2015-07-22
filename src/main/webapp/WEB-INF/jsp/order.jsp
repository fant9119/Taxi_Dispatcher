<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>${title} ${orderId}</title>
    <link rel="stylesheet" href="css/register_style.css">
</head>
<body>
<div class="container">
    <section class="register">
        <h1>Order's Form</h1>
        <form method="post" action="${formURL}">
            <div class="reg_section personal_info">
                <h3>Date</h3>
                <input type="text" name="dateOfOrder" placeholder="order date: YYYY-MM-DD" value="${dateOfOrder}">
                <h3>Client's id</h3>
                <input type="text" name="client" placeholder="client's id: numeric value" value="${clientId}">
                <h3>Amount</h3>
                <input type="text" name="amount" placeholder="amount: numeric value" value="${amount}">
                <h3>Address from</h3>
                <input type="text" name="addressFrom" placeholder="address from" value="${addressFrom}">
                <h3>Address to</h3>
                <input type="text" name="addressTo" placeholder="address to" value="${addressTo}">
            </div>
            <p class="submit"><input type="submit" name="commit" value="Ok"></p>
            <p class="reset"><input type="reset" name="reset" value="Clear"></p>
        </form>
    </section>
</div>
<div class="message" class="">
    <h1><c:out value="${errorMessage}"/></h1>
</div>

<div class="logIn">
    <a href="/dashboard.html">DASHBOARD</a>
</div>
</body>
</html>
