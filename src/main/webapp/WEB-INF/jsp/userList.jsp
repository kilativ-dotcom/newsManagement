<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>


<html>
<head>
    <title>
        <fmt:message key='title.userList'/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div>

    <jsp:include page="linksPanel.jsp"/>


    <form:form>
        <table>
            <tr>
                <th>
                    <fmt:message key='label.username' var="username"/>
                    <c:out value="${username}"/>
                </th>
                <th>
                    <fmt:message key='label.roles' var="roles"/>
                    <c:out value="${roles}"/>
                </th>
                <th>
                    <fmt:message key='label.DELETE' var="delete"/>
                    <c:out value="${delete}"/>
                </th>
                <th>
                    <fmt:message key='label.EDIT' var="edit"/>
                    <c:out value="${edit}"/>
                </th>
            </tr>
            <c:forEach var="user" items="${requestScope[JspConstants.USERS_ATTRIBUTE]}">
                <tr>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.roles}"/></td>
                    <td><input type="checkbox" name="deleteUsername" value="${user.username}"></td>
                    <td><a href="${pageContext.request.contextPath}/${JspConstants.USER}/${user.username}">
                        <button type="button">
                            <c:out value="${edit}"/>
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
        </table>

        <input type="submit" name="submit" value="${delete}">
    </form:form>



</div>

</body>
</html>