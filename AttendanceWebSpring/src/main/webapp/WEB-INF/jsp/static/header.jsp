<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">


    <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">

        <div class="nav-item active">
            <a class="nav-link" style="color: white" href="./index">Home <span class="sr-only">(current)</span></a>
        </div>

        <ul class="navbar-nav mr-auto">
            <%
                if((int)session.getAttribute("access") <= 1)
                {
            %>


            <li class="nav-link nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Request</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="./viewRequests">Zobraz</a>
                    <a class="dropdown-item" href="./addRequest">Pridej</a>
                </div>
            </li>


            <li class="nav-link nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dochazka</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="./provideDetailUser">Prehled</a>
                   <%
                       if((int)session.getAttribute("access") == 0)
                       {
                   %>
                    <a class="dropdown-item" href="./addAttendance">Pridej</a>
                    <%
                        }
                    %>
                </div>
            </li>

            <%
                }
                if((int)session.getAttribute("access") == 0)
                {
            %>

            <li class="nav-link nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Zamestnanec</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
                <a class="dropdown-item" href="./addEmployee">Pridej</a>
                <!--<a class="dropdown-item" href="./alterEmployee">Uprav</a>-->
            </div>
           </li>

            <li class="nav-link nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Oddeleni</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="./addDepartment">Pridej</a>
                    <a class="dropdown-item" href="./alterDepartment">Uprav</a>
                </div>
            </li>

            <%
                }
            %>

        </ul>

                <div>
                <a class="nav-link" style="color: white;" href="./logout">Logout</a>
                </div>

        <!--
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        -->
    </div>


</nav>

<div class="event-messages" style="text-align: center;font-size: large; margin-top:1%;">

    <c:choose>
    <c:when test="${successmessage != null &&  successmessage != \"\" }">
    <div class="alert alert-success alert-dismissible">
        <span class="close" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong>Success!</strong> <c:out value="${successmessage}"/>
    </div>
    </c:when>
    </c:choose>

    <c:choose>
    <c:when test="${failuremessage != null &&  failuremessage != \"\" }">
    <div class="alert alert-danger alert-dismissible">
        <span class="close" onclick="this.parentElement.style.display='none';">&times;</span>
        <strong>Danger!</strong> <c:out value="${failuremessage}"/>
    </div>
    </c:when>
    </c:choose>

</div>


<!-- <div class="nav-scroller bg-white shadow-sm">
    <nav class="nav nav-underline">
        <a class="nav-link active" href="#">Dashboard</a>
        <a class="nav-link" href="#">
            Friends
            <span class="badge badge-pill bg-light align-text-bottom">27</span>
        </a>
        <a class="nav-link" href="#">Explore</a>
        <a class="nav-link" href="#">Suggestions</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
    </nav>
</div> -->
