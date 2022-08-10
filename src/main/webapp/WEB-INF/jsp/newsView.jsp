<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="com.example.news.constants.JspConstants" %>


<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>

<html>
<head>
    <title>
        <fmt:message key="title.view"/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div>

    <jsp:include page="linksPanel.jsp"/>

    <c:set var="news" value="${requestScope[JspConstants.NEWS_ATTRIBUTE]}"/>

    <div class="newsContainer">
        <div>
            <table class="view">
                <tr class="view">
                    <th class="view">
                        <fmt:message key="label.title"/>
                    </th>
                    <td class="view">
                        <span class="saveIndents"><c:out value='${news.title}'/></span>
                    </td>
                </tr>
                <tr class="view">
                    <th class="view">
                        <fmt:message key="label.date"/>
                    </th>
                    <td class="view">
                        <fmt:formatDate value="${news.date}" dateStyle="long"/>
                    </td>
                </tr>
                <tr class="view">
                    <th class="view">
                        <fmt:message key="label.brief"/>
                    </th>
                    <td class="view">
                        <span class="saveIndents"><c:out value='${news.brief}'/></span>
                    </td>
                </tr>
                <tr class="view">
                    <th class="view">
                        <fmt:message key="label.content"/>
                    </th>
                    <td class="view">
                        <span class="saveIndents"><c:out value='${news.content}'/></span>
                    </td>
                </tr>
            </table>
        </div>
        <div align="right">
            <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.EDIT}/${news.id}">
                <button><fmt:message key="label.EDIT"/></button>
            </a>
            <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.DELETE}/${news.id}">
                <button><fmt:message key="label.DELETE"/></button>
            </a>
        </div>
    </div>
</div>
</body>
</html>