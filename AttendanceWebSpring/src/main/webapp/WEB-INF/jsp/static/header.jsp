<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
    <a class="navbar-brand mr-auto mr-lg-0" href="#">Offcanvas navbar</a>
    <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Dashboard <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Notifications</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Profile</a>
            </li>
            <%
                if((int)session.getAttribute("access") == 0)
                {
            %>

            <li class="nav-item">
                <a class="nav-link" href="./addAttendance">Pridej dochazku</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="./addEmployee">Registrace uzivatele</a>
            </li>

            <%
                }
            %>
            <!--<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li> -->
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

<div class="event-messages" style="text-align: center;font-size: large">

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
