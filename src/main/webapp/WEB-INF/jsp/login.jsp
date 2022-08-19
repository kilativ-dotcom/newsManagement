<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.login"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css" >

</head>
<body>

<jsp:include page="header.jsp"/>


<div>
    <div class="center">
        <form:form method="post" action="${pageContext.request.contextPath}/${JspConstants.LOGIN}"
                   id="LoginFormId" name="LoginForm" modelAttribute="${JspConstants.USER_FORM_ATTRIBUTE}">
            <p>
                <label for="usernameInputId"><fmt:message key="label.username"/></label>
                :
                <input id="usernameInputId" type="text" name="username" required="required"/>
            </p>

            <p>
                <label for="passwordInputId"><fmt:message key="label.password"/></label>
                :
                <input id="passwordInputId" type="password" name="password" required="required"/>
            </p>

            <p>
            <c:if test="${param.error ne null}">
                <h1 class="formError"><fmt:message key="message.badCredentials"/></h1>
            </c:if>
            </p>

            <p>
                <a href="javascript:document.RegistrationForm.submit()"><fmt:message key="button.goToRegistration"/></a>
            </p>

            <p>
                <input type="submit" value="<fmt:message key="label.login"/>">
            </p>
        </form:form>

        <form name="RegistrationForm" action="${pageContext.request.contextPath}/${JspConstants.REGISTRATION}">
        </form>
    </div>

</div>
</body>
</html>