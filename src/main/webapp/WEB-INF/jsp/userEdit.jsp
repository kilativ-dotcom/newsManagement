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
        <fmt:message key="title.userEdit"/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>


<div>
    <jsp:include page="linksPanel.jsp"/>

    <c:set var="user" value="${requestScope[JspConstants.USER_ATTRIBUTE]}"/>

    <a href="${pageContext.request.contextPath}/${JspConstants.USER}">< <fmt:message key="title.userList"/></a>

    <form:form>
        <table>
            <tr>
                <th>
                    <fmt:message key='label.username' var="username"/>
                    <c:out value="${username}"/>
                </th>
                <td><c:out value="${user.username}"/></td>

                <td>
                    <fmt:message key='label.SAVE' var="save"/>
                    <input type="submit" name="submit" value="${save}">
                </td>
            </tr>
            <c:forEach var="role" items="${requestScope[JspConstants.ROLES_ATTRIBUTE]}">
                <tr>
                    <th><c:out value="${role}"/></th>
                    <td><input type="checkbox" name="role" value="${role}"
                            <c:if test="${user.roles.contains(role)}">
                                checked
                            </c:if>
                    /></td>
                </tr>
            </c:forEach>
        </table>

    </form:form>

</div>
</body>
</html>