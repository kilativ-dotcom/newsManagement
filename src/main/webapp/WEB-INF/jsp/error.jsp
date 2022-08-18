<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isErrorPage="true" import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>
<html>
<head>
    <title><fmt:message key="title.error"/></title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div>

    <jsp:include page="linksPanel.jsp"/>

    <h1 style="color: blueviolet">
        <fmt:message key="message.errorWithCode"/> ${status}
    </h1><br/>
    <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}"><fmt:message
            key="label.newsList"/></a>

    <table>
        <tr>
            <td>Date</td>
            <td>${timestamp}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${status}</td>
        </tr>
        <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
    </table>

</div>
</body>
</html>
