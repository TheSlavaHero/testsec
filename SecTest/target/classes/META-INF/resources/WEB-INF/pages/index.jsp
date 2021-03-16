<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
    <div align="center">

        <c:if test="${authentication eq false}">
            <p>You are not verified. Please, enter code that was send to you via email to get verified.</p>
            <c:url value="/verify" var="verify" />
            <form action="${verify}" method="POST">
                <br/><input type="text" name="authKey" /><br/>
                <input type="submit" value="Update" />
            </form>
        </c:if>

        <c:if test="${authentication eq true}">
        <h1>Your login is: ${login}, your roles are:</h1>
        <c:forEach var="s" items="${roles}">
            <h3><c:out value="${s}" /></h3>
        </c:forEach>

        <c:if test="${admin}">
            <c:url value="/admin" var="adminUrl" />
            <p><a href="${adminUrl}">Click</a> for admin page</p>
        </c:if>

        <c:url value="/update" var="updateUrl" />
        <form action="${updateUrl}" method="POST">
            E-mail:<br/><input type="text" name="email" value="${email}" /><br/>
            Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
            Age:<br/><input type="text" name="age" value="${age}" /><br/>
            <input type="submit" value="Update" />
        </form>

        <c:url value="/logout" var="logoutUrl" />
        <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
        </c:if>
    </div>
</body>
</html>
