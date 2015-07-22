<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Roman_Taxi</title>
    <link rel="stylesheet" type="text/css" href="css/index_style.css" />
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/authentication.html" method="POST">
            <h1>Hello!</h1>
            <div>
                <input type="text" placeholder="Login" name="login" id="login" />
            </div>
            <div>
                <input type="password" placeholder="Password" name="password" id="password" />
            </div>
            <div>
                <input type="submit" value="Log in" />
                <input type="reset" value="Reset">
            </div>
        </form>
        <form action="/registration.html" method="POST">
            <input type="submit" value="Register" />
        </form>
    </section>
</div>
<h1 align="middle"><c:out value="${message}"/></h1>
</body>
</html>
