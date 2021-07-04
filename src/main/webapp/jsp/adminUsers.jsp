<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Courgette&family=Lato:wght@300;400;500;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <title><fmt:message key="admin_users.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body">
        <div class="section-welcome"
             style="background-image: url(${pageContext.request.contextPath}/images/signUp.jpg);">
            <div class="admin-section">
                <div class="container">
                    <h2 class="admin-section__title"><fmt:message key="admin_users.subtitle"/></h2>
                    <div class="default-table-wrapper">
                        <table class="default-table">
                            <tr>
                                <th><fmt:message key="admin_users.login"/></th>
                                <th><fmt:message key="admin_users.email"/></th>
                                <th><fmt:message key="admin_users.phone"/></th>
                                <th><fmt:message key="admin_users.name"/></th>
                                <th><fmt:message key="admin_users.surname"/></th>
                                <th><fmt:message key="admin_users.banned"/></th>
                                <th class="default-table__sort">
                                    <span>Sort by:</span>
                                    <a href="RentCar?command=sort_users&sortType=name">
                                        <fmt:message key="admin_users.name"/></a>
                                    <a href="RentCar?command=sort_users&sortType=login"><fmt:message
                                            key="admin_users.login"/></a>
                                </th>
                            </tr>
                            <c:forEach var="user" items="${allUsers}">
                                <tr>
                                    <td>${user.getLogin()}</td>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getPhoneNumber()}</td>
                                    <td>${user.getName()}</td>
                                    <td>${user.getSurname()}</td>
                                    <td>${user.isStatus()}</td>
                                    <c:choose>
                                        <c:when test="${user.isStatus() == true}">
                                            <td class="default-table__action">
                                                <a href="RentCar?command=unban_account&login=${user.getLogin()}"
                                                   class="default-table__button default-table__button--red"><fmt:message
                                                        key="admin_users.unban_button"/></a>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="default-table__action">
                                                <a href="RentCar?command=ban_account&login=${user.getLogin()}"
                                                   class="default-table__button default-table__button--green"><fmt:message
                                                        key="admin_users.ban_button"/></a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>