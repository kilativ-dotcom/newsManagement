<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css" >

<div class="linksPanel">
    <h3 class="linksPanelHead"><fmt:message key='label.news'/></h3>
    <div class="linksPanelBody">
        <ul class="linksPanelBodyContent">
            <li>
                <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}"><fmt:message key='label.newsList'/></a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.ADD}"><fmt:message key='label.addNews'/></a>
            </li>
        </ul>
    </div>
</div>