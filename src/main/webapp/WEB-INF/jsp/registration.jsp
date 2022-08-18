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
    <title><fmt:message key="title.registration"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css" >

</head>
<body>

<jsp:include page="header.jsp"/>


<div>
    <div class="center">
        <form:form method="post" action="${pageContext.request.contextPath}/${JspConstants.REGISTRATION}"
                   id="RegistrationFormId" name="RegistrationForm" modelAttribute="${JspConstants.USER_FORM_ATTRIBUTE}">
            <p>
                <form:label path="username" for="usernameInputId"><fmt:message key="label.username"/></form:label>
                :
                <form:input path="username" id="usernameInputId" type="text" name="username" required="required"/>
            </p>
            <p>
                <form:errors path="username" element="h1" cssClass="formError"/>
            </p>

            <p>
                <form:label path="password" for="passwordInputId"><fmt:message key="label.password"/></form:label>
                :
                <form:input path="password" id="passwordInputId" type="password" name="password" required="required"/>
            </p>
            <p>
                <form:errors path="password" element="h1" cssClass="formError"/>
            </p>

            <p>
                <form:label path="passwordRepeat" for="passwordRepeatInputId"><fmt:message key="label.password"/></form:label>
                :
                <form:input path="passwordRepeat" id="passwordRepeatInputId" type="password" name="passwordRepeat" required="required"/>
            </p>
            <p>
                <form:errors path="passwordRepeat" element="h1" cssClass="formError"/>
            </p>

            <p>
                <a href="javascript:document.LoginForm.submit()"><fmt:message key="button.goToLogin"/></a>
            </p>

            <p>
                <input type="submit" value="<fmt:message key="label.registration"/>">
            </p>
        </form:form>

        <form name="LoginForm" action="${pageContext.request.contextPath}/${JspConstants.LOGIN}">
        </form>
    </div>

</div>
</body>
</html>