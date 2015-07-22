<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Operators_Workspace</title>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var roleVar = ${role};
            if (roleVar == 1) {
                document.getElementById("admin").style.display="none";
            }
        });
    </script>
    <link rel="stylesheet" href="css/dashboard_style.css">
</head>
<body>
    <a href="/logout.html" class="logout" style="font-weight: bold; color: red">logout</a>
    <div class="client">
        <form action="/client_registration_form.html" method="POST">
            <input type="submit" value="New Client"/>
        </form>
        <form action="/clients.html" method="POST">
            <input type="submit" value="All Clients"/>
            <input type="text" name="portion" placeholder="portion">
        </form>
        <form action="/clients.html" method="POST">
            <input type="submit" value="More than Sum"/>
            <input type="text" name="sum" placeholder="sum">
        </form>
        <form action="/clients.html" method="POST">
            <input type="submit" name="month" value="Last Month"/>
        </form>
    </div>
    <div class="order">
        <form action="/new_order.html" method="POST">
            <input type="submit" name="new_order" value="New Order"/>
        </form>
        <form action="/edit_order.html" method="POST">
            <input type="submit" name="edit order" value="Edit Order"/>
            <input type="text" name="orderId" placeholder="order_id">
        </form>
        <form action="/orders_from_to.html" method="POST">
            <input type="submit" name="edit order" value="Show Order's"/>
            <input type="text" name="min" placeholder="min">
            <input type="text" name="max" placeholder="max">
        </form>
        <form action="/orders.html" method="POST">
            <input type="submit" name="allOrders" value="All Orders"/>
        </form>
    </div>
    <div id="">
        <form action="/operators.html" method="POST">
            <input type="submit" name="allOperators" value="All Operators"/>
        </form>
        <form action="/edit_operator.html" method="POST">
            <input type="submit" name="editOperator" value="Edit Operator"/>
            <input type="text" name="operatorId" placeholder="operator_id">
        </form>
    </div>
    <div class="message">
        <h1><c:out value="${errorMessage}"/></h1>
    </div>
</body>
</html>
