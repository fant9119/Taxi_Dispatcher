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
        <h1>Register of new operator</h1>
        <form method="post" action="/register.html">
            <div class="reg_section personal_info">
                <h3>Your Login(more than 4 symbols without spaces)</h3>
                <input type="text" name="login" required="true" placeholder="Your Desired login">
                <h3>Your ID(only 10 numbers without other symbols)</h3>
                <input type="text" name="id" required="true" placeholder="Your Desired ID">
            </div>
            <div class="reg_section password">
                <h3>Your Password (more than 8 symbols)</h3>
                <input type="password" name="password" required="true" placeholder="Your Password">
                <input type="password" name="confirm" required="true" placeholder="Confirm Password">
            </div>
            <p class="submit"><input type="submit" required="true" name="commit" value="Register"></p>
            <p class="reset"><input type="reset" required="true" name="reset" value="Clear"></p>
        </form>
    </section>
</div>
<div class="message" class="">
    <h1><c:out value="${errorMessage}"/></h1>
</div>

<div class="logIn">
    <a href="/">LOG IN</a>
</div>
</body>
</html>
