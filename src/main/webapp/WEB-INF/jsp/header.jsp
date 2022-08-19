<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css" >
<div>
    <header>
        <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}">
            <div style="margin-left: 30px">
                <h2 style="color: blue"><fmt:message key='title.newsManagement'/></h2>
            </div>
        </a>
        <div align="right">
            <c:set var="loggedInUsername" value="${requestScope[JspConstants.LOGGED_IN_USERNAME_ATTRIBUTE]}"/>
            <c:choose>
                <c:when test="${loggedInUsername ne null}">
                    <fmt:message key='message.greeting'/> <c:out value="${loggedInUsername}"/><br/>
                    <fmt:message key='label.logout' var="logout"/>
                    <a href="${pageContext.request.contextPath}/logout"><c:out value="${logout}"/></a>
                    <br/>
                    <br/>
                    <br/>
                </c:when>
                <c:otherwise>
                    <fmt:message key='label.login' var="login"/>
                    <a href="${pageContext.request.contextPath}/login"><c:out value="${login}"/></a>
                    <br/>
                    <br/>
                    <br/>
                </c:otherwise>
            </c:choose>

            <a class="languageSelector" href="${pageContext.request.contextPath}/${JspConstants.LOCALE}/${JspConstants.CHANGE}/en">English</a>
            <a class="languageSelector" href="${pageContext.request.contextPath}/${JspConstants.LOCALE}/${JspConstants.CHANGE}/ru">Русский</a>
        </div>
        <hr size="15" noshade="noshade"/>
    </header>
</div>