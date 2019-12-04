<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Primum Partus</title>

    <jsp:include page="../includeResources.jsp"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
</head>
<body>
    <div class="sidenav">
        <div class="login-main-text">
            <h2>Primum Partus<br> Login Page</h2>
            <p>Login here to access.</p>
        </div>
    </div>
    <div class="main">
        <div class="col-md-6 col-sm-12">
            <div class="login-form">
                <form id="loginForm">
                    <div class="alert alert-danger" role="alert" id="errorMessage" style="display: none">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" placeholder="Email" name="email" id="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                    </div>
                    <input type="button" class="btn btn-black" value="Login" onclick="login()">
                    <input type="button" class="btn btn-secondary" value="Register" onclick="redirectToRegistration()">
                </form>
            </div>
        </div>
    </div>

    <script src="<c:url value="/resources/js/login.js"/> "></script>
</body>
</html>
