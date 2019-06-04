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
        <h6 class="border-bottom border-gray pb-2 mb-0">Dochazka uzivatele</h6>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Prichod</th>
                <th scope="col">Odchod</th>
                <th scope="col">Pocet hodin</th>
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
            </tr>
            </c:forEach>
            </c:when>
            </c:choose>
            </tbody>
        </table>


        <small class="d-block text-right mt-3">
            <a href="#">All updates</a>
        </small>
    </div>

    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0">Uzivatele pod vedoucim z oddeleni</h6>
        <c:choose>
            <c:when test="${members != null && members.size() > 0}">
                <c:forEach var="m" items="${members}">
        <div class="media text-muted pt-3">
            <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                <strong class="text-gray-dark">${m.first_name} ${m.last_name}</strong>
                <div class="d-flex justify-content-between align-items-center w-100">

                    <p>
                   <c:if test="${m.getAccess_level() == 0}"><strong>Role: Admin</strong></c:if>
                    <c:if test="${m.getAccess_level() == 1}"><strong>Role: Employee</strong></c:if>
                    </p>
                    <p>
                    ${m.getDepartment().getTitle()}:
                    ${m.getPosition()}
                    </p>
                    <form method="GET", action="./provideDetailUser">
                        <button name="userLogin" value="${m.getLogin_name()}" type="submit">Detail</button>
                    </form>
                </div>
                <span class="d-block">${m.getLogin_name()}</span>
            </div>
        </div>
                </c:forEach>
            </c:when>
        </c:choose>

        <small class="d-block text-right mt-3">
            <a href="#">All suggestions</a>
        </small>
    </div>
</main>
<script src="../../javascript/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../javascript/jquery-3.3.1.slim.min.js"><\/script>')</script>
<script src="../../javascript/bootstrap.bundle.min.js"></script>
<script src="../../javascript/offcanvas.js"></script>

</body>
</html>
