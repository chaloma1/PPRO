<%@ page import="java.util.Date" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <title>Checkout example · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="../../css/form-validation.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container">
    <div class="py-5 text-center">
        <h2>Attendance form</h2>
        <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
    </div>



    <div class="row">

        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Billing address</h4>
            <form class="needs-validation" method="POST" action="/sendAttendance">
                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label for="employee">Zamestnanci</label>
                        <select class="custom-select d-block w-100" name="employee" id="employee" required>
                            <option value="">Choose...</option>
                            <c:forEach var="e" items="${employees}">
                                <option>${e.login_name}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid employee.
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="day">Datum</label>
                        <input type="date" name="day" class="form-control" id="day" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid date required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="attend_time">Prichod</label>
                        <input type="time" name="attend_time" class="form-control" id="attend_time" placeholder=""
                               value="" min="00:00" max="23:59" onchange="document.getElementById('leave_time').min=this.value;"
                               required>
                        <div class="invalid-feedback">
                            Valid attend_time required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="leave_time">Odchod</label>
                        <input type="time" name="leave_time" class="form-control" id="leave_time" placeholder="" min="document.getElementById('attend_time').value"  required>
                        <div class="invalid-feedback">
                            Valid leave_time required.
                        </div>
                    </div>
                </div>




                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
        </div>
    </div>


</div>
<!--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../javascript/jquery-3.3.1.slim.min.js"><\/script>')</script><script src="../../javascript/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<script src="../../javascript/form-validation.js"></script>
-->
</body>
</html>
