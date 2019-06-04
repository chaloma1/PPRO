<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Offcanvas template · Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/offcanvas/">

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">


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
    <link href="../../css/offcanvas.css" rel="stylesheet">
</head>
<body class="bg-light">

<jsp:include page="./static/header.jsp"/>

<main role="main" class="container">


    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0">Dochazka uzivatele ${employee.getLogin_name()}</h6>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Prichod</th>
                <th scope="col">Odchod</th>
                <th scope="col">Pocet hodin</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${attendances != null}">
                    <c:forEach var="a" items="${attendances}">
                        <tr>
                            <th scope="row">${a.getId_attendance()}</th>
                            <td>${a.getAttend_time()}</td>
                            <td>${a.getLeave_time()}</td>
                            <td>${a.compare_dates(a.getAttend_time(), a.getLeave_time())}</td>
                            <%
                                if ((int)session.getAttribute("access") == 0){
                            %>
                            <td><form method="GET", action="./editUserAttendance">
                                <button name="id_attendance" value="${a.getId_attendance()}" type="submit">Edit</button>
                            </form></td>
                            <%
                                }
                            %>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
            </tbody>
        </table>
    </div>


</main>
<script src="../../javascript/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../javascript/jquery-3.3.1.slim.min.js"><\/script>')</script>
<script src="../../javascript/bootstrap.bundle.min.js"></script>
<script src="../../javascript/offcanvas.js"></script>

</body>
</html>
