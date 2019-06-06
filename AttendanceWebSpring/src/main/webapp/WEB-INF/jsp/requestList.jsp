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

    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <p>Some text in the modal.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>


    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0">Tydenni requesty uzivatele ${employee.first_name} ${employee.last_name}</h6>

            <c:choose>
                <c:when test="${requests != null}">
                    <c:forEach var="r" items="${requests}">
                        <div class="media text-muted pt-3">

                            <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                <strong class="d-block text-gray-dark">${r.getRequest_maker_name()} - ${r.getHeader()}</strong>
                                ${r.getText()}
                            </p>
                            <form method="POST", action="./completeRequest">
                                <button name="id_request" value="${r.getId_request()}" type="submit">Request splneny!</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>



        <small class="d-block text-right mt-3">
            <a href="#">All updates</a>
        </small>
    </div>

    <div class="my-3 p-3 bg-white rounded shadow-sm">
        <h6 class="border-bottom border-gray pb-2 mb-0">Moje requesty za posledni tyden</h6>
        <c:choose>
            <c:when test="${myRequests != null}">
                <c:forEach var="mR" items="${myRequests}">
                    <div class="media text-muted pt-3"
                     <c:if test="${mR.getDate_of_completion() == null}">style="background-color:Yellow" </c:if>
                         <c:if test="${mR.getDate_of_completion() != null}">style="background-color:aquamarine" </c:if>
                    >

                        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                            <strong class="d-block text-gray-dark">Poslano: ${mR.requested_employee.first_name} ${mR.requested_employee.last_name} - ${mR.getHeader()}</strong>
                                ${mR.getText()}
                        </p>
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
