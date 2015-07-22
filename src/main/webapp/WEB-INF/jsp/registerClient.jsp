<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration Form</title>
    <link rel="stylesheet" href="css/register_style.css">
</head>
<body>
<div class="container">
    <section class="register">
        <h1>Register of a new client</h1>
        <form method="post" action="/client_registration.html">
            <div class="reg_section personal_info">
                <h3>Client's name</h3>
                <input type="text" name="name" placeholder="name">
                <h3>Client's surname</h3>
                <input type="text" name="surname" placeholder="surname">
                <h3>Client's phone</h3>
                <input type="text" name="phone" placeholder="phone: (XXX)XXX-XX-XX">
                <h3>Client's address</h3>
                <input type="text" name="address" placeholder="address">
                <h3>Total sum</h3>
                <input type="text" name="sum" placeholder="total sum">
                <h3>The date of last order</h3>
                <input type="text" name="lastOrderDate" placeholder="last order date: YYYY-MM-DD">
            </div>
            <p class="submit"><input type="submit" name="commit" value="Register"></p>
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
