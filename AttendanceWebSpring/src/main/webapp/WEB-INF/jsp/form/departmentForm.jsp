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

<jsp:include page=".././static/header.jsp"/>

<div class="container">
    <div class="py-5 text-center">
        <h2>Department form</h2>
        <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
    </div>



    <div class="row">

        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Pridani oddeleni</h4>

        <c:choose>
            <c:when test="${uprava != null && uprava == true}">
            <form class="needs-validation" method="POST" action="/sendNewDepartment">

                <div class="col-md-5 mb-3">
                    <label for="department">Upravovane oddeleni</label>
                    <select class="custom-select d-block w-100" name="department" id="department">
                        <option value="">Choose...</option>
                        <c:forEach var="d" items="${departments}">
                            <option>${d.title} - ${d.supervisor}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        Please select a valid department.
                    </div>
                </div>

            </c:when>
        </c:choose>
            <c:choose>
                <c:when test="${uprava == null || uprava == false}">
            <form class="needs-validation" method="POST" action="/sendDepartment">

                </c:when>
                    </c:choose>


                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="title">Department title</label>
                        <input type="text" name="title" class="form-control" id="title" placeholder="" value="">
                        <div class="invalid-feedback">
                            Valid department title required.
                        </div>
                    </div>


                    <div class="col-md-5 mb-3">
                        <label for="supervisor">Supervisor login</label>
                        <select class="custom-select d-block w-100" name="supervisor" id="supervisor">
                            <option value="">Choose...</option>
                            <c:forEach var="s" items="${supervisors}">
                                <option>${s.first_name} ${s.last_name} (${s.login_name})</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid supervisor.
                        </div>
                    </div>

                </div>




                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
        </div>
    </div>


</div>
<script src="../../javascript/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../javascript/jquery-3.3.1.slim.min.js"><\/script>')</script>
<script src="../../javascript/bootstrap.bundle.min.js"></script>
<script src="../../javascript/offcanvas.js"></script>
</body>
</html>
