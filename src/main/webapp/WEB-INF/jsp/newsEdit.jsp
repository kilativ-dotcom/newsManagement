<%--@elvariable id="newsForm" type="com.example.news.presentation.form.NewsForm.java"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>

<html>
<head>
    <title>
        <fmt:message key="title.edit"/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>


<div>
    <jsp:include page="linksPanel.jsp"/>

    <c:set var="newsId" value="${requestScope[JspConstants.NEWS_ID_ATTRIBUTE]}"/>

    <c:choose>
        <c:when test="${newsId ne null}">
            <c:set var="formAction"
                   value="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.EDIT}/${newsId}"/>
        </c:when>
        <c:otherwise>
            <c:set var="formAction"
                   value="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.ADD}"/>
        </c:otherwise>
    </c:choose>

    <form:form method="post" action="${formAction}" id="EditNewsFormId" name="EditNewsForm"
               modelAttribute="${JspConstants.NEWS_FORM_ATTRIBUTE}">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div class="newsContainer">
            <div>
                <table class="view">
                    <tr>
                        <th class="view">
                            <form:label path="title" for="titleTextId"><fmt:message key="label.title"/></form:label>
                        </th>
                        <td class="view">
                            <form:textarea path="title" id="titleTextId" name="newsTitle"
                                           style="resize: none; width: 100%;"
                                           form="EditNewsFormId" rows="3" maxlength="100"/>
                        </td>
                    </tr>
                    <tr><form:errors path="title" element="h1" cssClass="formError"/></tr>
                    <tr>
                        <th class="view">
                            <form:label path="date" for="dateId"><fmt:message key="label.date"/></form:label>
                        </th>
                        <td class="view">
                            <form:input path="date" type="date" id="dateId" name="newsDate"/>
                            <script type="text/javascript">
                                let dateId = document.getElementById("dateId");
                                if (dateId.value == '') {
                                    dateId.value = new Date().toLocaleDateString('en-ca');
                                }
                            </script>
                        </td>
                    </tr>
                    <tr><form:errors path="date" element="h1"/></tr>
                    <tr>
                        <th class="view">
                            <form:label path="brief" for="briefId"><fmt:message key="label.brief"/></form:label>
                        </th>
                        <td class="view">
                            <form:textarea path="brief" id="briefId" name="newsBrief"
                                           style="resize: none; width: 100%;"
                                           form="EditNewsFormId" rows="7" maxlength="500"/>
                        </td>
                    </tr>
                    <tr><form:errors path="brief" element="h1"/></tr>
                    <tr>
                        <th class="view">
                            <form:label path="content" for="contentId"><fmt:message key="label.content"/></form:label>
                        </th>
                        <td class="view">
                            <form:textarea path="content" id="contentId" name="newsBrief"
                                           style="resize: none; width: 100%;"
                                           form="EditNewsFormId" rows="30" maxlength="2048"/>
                        </td>
                    </tr>
                    <tr><form:errors path="content" element="h1"/></tr>
                </table>
            </div>
            <div align="center">
                <input type="hidden" name="newsId" value="${newsId}">
                <fmt:message key='label.SAVE' var="save"/>
                <input type="submit" name="action" value="${save}">
                <form method="get"
                      action="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}">
                    <fmt:message key='label.CANCEL' var="cancel"/>
                    <input type="submit" name="action" value="${cancel}">
                </form>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>