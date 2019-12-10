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
        <h2>Primum Partus<br> Confirmation Error</h2>
        <p>Email confirmation was unsuccessful</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <div class="form-group">
                <p>Please contact our support group</p>
                <a href="mailto:primumpartushelp@gmail.com">primumpartushelp@gmail.com</a>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/login.js"/> "></script>
</body>
</html>
