<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page import="com.example.news.constants.JspConstants" %>

<fmt:setLocale value="${sessionScope[JspConstants.LOCALE_ATTRIBUTE]}"/>
<fmt:setBundle basename="${JspConstants.LOCALE_BASENAME}"/>


<html>
<head>
    <title>
        <fmt:message key='title.list'/>
    </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<div>

    <jsp:include page="linksPanel.jsp"/>


    <div class="newsPreviewList">
        <c:forEach var="news" items="${requestScope[JspConstants.NEWS_LIST_ATTRIBUTE]}">
            <a href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${news.id}"
               style="text-decoration: none; color: black; background-color: #eeeeee;">
                <div class="newsPreview">
                    <div class="newsPreviewHead">
								<span class="saveIndents">
									<strong class="newsPreviewTitle">
										<c:out value="${news.title}"/>
									</strong>
								</span>
                        <h4 class="newsPreviewDate">
                            <fmt:formatDate value="${news.date}" dateStyle="long"/>
                        </h4>
                    </div>
                    <div class="newsPreviewBody">
                        <div class="newsPreviewBrief">
								<span class="saveIndents">
											<c:out value="${news.brief}"/>
								</span>
                        </div>
                        <div class="newsPreviewManagePanel">
                            <a class="newsPreviewManageElement"
                               href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${news.id}"><fmt:message key='label.view'/></a>
                            <a class="newsPreviewManageElement"
                               href="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.EDIT}/${news.id}"><fmt:message key='label.edit'/></a>
                            <input type="checkbox" name="deleteNewsId" form="deleteNewsFormId" value="${news.id}">
                        </div>
                    </div>
                </div>
            </a>
        </c:forEach>
        <div align="right" style="margin: 10px;">
            <form id="deleteNewsFormId" action="${pageContext.request.contextPath}/${JspConstants.SITE_BASENAME}/${JspConstants.DELETE}" method="post"
                  onsubmit="return deleteSubmitted()">
                <fmt:message key='label.DELETE' var="delete"/>
                <input type="submit" name="submit" value="${delete}">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/newsList.js"></script>
</body>
</html>