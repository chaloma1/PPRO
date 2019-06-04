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

<div class="container" style="margin-top: 5%">
    <div class="py-5 text-center">
        <h2>Attendance EDIT form</h2>

    </div>



    <div class="row">
        <c:choose>
            <c:when test="${attendance != null}">
        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Uprava dochazky zamestnance ${attendance.getEmployee().getLogin_name()}</h4>
            <form class="needs-validation" method="POST" action="/alterAttendance">

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label for="id_attendance">ID dochazky</label>
                        <input type="number" name="id_attendance" class="form-control" id="id_attendance" placeholder=""
                               value="${attendance.id_attendance}" readonly = "readonly">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="attend_time">Prichod</label>
                        <input type="datetime" name="attend_time" class="form-control" id="attend_time" placeholder=""
                               value="${attendance.attend_time}" onchange="document.getElementById('leave_time').min >= (this.value);"
                               required>
                        <div class="invalid-feedback">
                            Valid attend_time required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="leave_time">Odchod</label>
                        <input type="datetime"  name="leave_time" class="form-control" id="leave_time" value="${attendance.leave_time}" placeholder="" min="document.getElementById('attend_time').value"  required>
                        <div class="invalid-feedback">
                            Valid leave_time required.
                        </div>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="toDelete">Chces smazat dochazku?</label>
                        <input type="checkbox" name="toDelete" class="form-control" id="toDelete">
                    </div>
                </div>




                <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
        </div>
            </c:when>
        </c:choose>
    </div>


</div>
<script src="../../javascript/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../javascript/jquery-3.3.1.slim.min.js"><\/script>')</script>
<script src="../../javascript/bootstrap.bundle.min.js"></script>
<script src="../../javascript/offcanvas.js"></script>
</body>
</html>
