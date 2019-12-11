<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Primum Partus</title>

    <jsp:include page="./includeResources.jsp"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/mainPage.css"/>">
</head>
<body>
    <div class="header">
        <div class="titleLogoP">
            P
        </div>
        <div class="titleLogoText">
            rimum
            <br/>
            artus
        </div>
        <input class="logicButton" type="button" value="Home"/>
        <input class="logicButton" type="button" value="Review"/>
        <input class="logicButton" type="button" value="Pro"/>
        <input class="loginButton" type="button" value="Login"/>
        <input class="loginButton" type="button" value="Registration"/>
    </div>
    <div class="content">

    </div>
    <div class="footer">
        Contact information:
        <br/>
        58000, Universytetska st. 28,
        <br/>
        Chernivtci, Ukraine
    </div>
</body>
</html>
