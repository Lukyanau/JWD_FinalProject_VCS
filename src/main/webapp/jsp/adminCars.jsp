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
    <title><fmt:message key="main_page.title"/></title>
</head>

<body>
<div class="layout">
    <c:import url="${pageContext.request.contextPath}/jsp/header.jsp"/>
    <div class="layout-body">
        <div class="section-welcome"
             style="background-image: url(${pageContext.request.contextPath}/images/signUp.jpg);">
            <div class="admin-section">
                <div class="container">
                    <div class="add-rooms-form">
                        <h3 class="add-rooms-form__title"><fmt:message key="admin_cars.add_car"/></h3>
                        <form action="RentCar" method="post" class="form">
                            <div class="form-grid">
                                <div class="form__item">
                                    <div class="form-group">
                                        <span class="form-label"><fmt:message key="admin_cars.car_color"/> </span>
                                        <input type="text" class="form-control" name="color"
                                               placeholder="<fmt:message key="admin_cars.input_color"/>" required
                                               pattern="^\w{3,15}$"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="admin_cars.incorrect_color"/>')"
                                               onchange="this.setCustomValidity('')" value="${carData['color']}"/>
                                    </div>
                                </div>
                                <div class="form__item">
                                    <div class="form-group">
                                        <span class="form-label"><fmt:message key="admin_cars.car_mark"/></span>
                                        <select class="form-control" required name="mark">
                                            <option value="" selected hidden><fmt:message
                                                    key="admin_cars.select_mark"/></option>
                                            <option value="bmw"><fmt:message key="admin_cars.bmw"/></option>
                                            <option value="mercedes"><fmt:message key="admin_cars.mercedes"/></option>
                                            <option value="peugeot"><fmt:message key="admin_cars.peugeot"/></option>
                                            <option value="ford"><fmt:message key="admin_cars.ford"/></option>
                                            <option value="volkswagen"><fmt:message
                                                    key="admin_cars.volkswagen"/></option>
                                        </select>
                                        <span class="select-arrow"></span>
                                    </div>
                                </div>
                                <div class="form__item">
                                    <div class="form-group">
                                        <span class="form-label"><fmt:message key="admin_cars.car_model"/> </span>
                                        <input type="text" class="form-control" name="model"
                                               placeholder="<fmt:message key="admin_cars.input_model"/>" required
                                               pattern="^[\w\d]{2,15}$"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="admin_cars.incorrect_model"/>')"
                                               onchange="this.setCustomValidity('')" value="${carData['model']}"/>
                                    </div>
                                </div>
                                <div class="form__item">
                                    <div class="form-group">
                                        <span class="form-label"><fmt:message key="admin_cars.car_price"/></span>
                                        <input type="text" class="form-control" name="price"
                                               placeholder=
                                               <fmt:message key="admin_cars.input_price"/> required
                                               pattern="^\d+\.?\d+$"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="admin_cars.incorrect_price"/>')"
                                               onchange="this.setCustomValidity('')" value="${carData['price']}"/>
                                    </div>
                                </div>
                                <div class="form__item">
                                    <div class="form-group">
                                        <span class="form-label"><fmt:message key="admin_cars.car_description"/></span>
                                        <input type="text" class="form-control" name="description"
                                               placeholder=
                                               <fmt:message key="admin_cars.input_description"/> required
                                               pattern="^[\w ]{5,25}$"
                                               oninvalid="this.setCustomValidity('<fmt:message
                                                       key="admin_cars.incorrect_description"/>')"
                                               onchange="this.setCustomValidity('')" value="${carData['description']}"/>
                                    </div>
                                </div>
                                <div class="form__item">
                                    <div class="form-btn">
                                        <input type="hidden" name="command" value="add_car">
                                        <button type="submit" class="submit-btn">
                                            <fmt:message key="admin_cars.add_car"/></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <h2 class="admin-section__title"><fmt:message key="admin_cars.cars"/></h2>
                    <div class="default-table-wrapper">
                        <table class="default-table">
                            <tr>
                                <th><fmt:message key="admin_cars.model"/></th>
                                <th><fmt:message key="admin_cars.color"/></th>
                                <th><fmt:message key="admin_cars.mark"/></th>
                                <th><fmt:message key="admin_cars.description"/></th>
                                <th><fmt:message key="admin_cars.price"/></th>
                                <th><fmt:message key="admin_cars.status"/></th>
                                <th class="default-table__sort">
                                    <span><fmt:message key="admin_cars.sort_by"/>:</span>
                                    <a href="RentCar?command=sort_cars&sort_type=price">
                                        <fmt:message key="admin_cars.price_type_sort"/></a>
                                    <a href="RentCar?command=sort_cars&sort_type=model">
                                        <fmt:message key="admin_cars.model_type_sort"/></a>
                                </th>
                                <th><fmt:message key="admin_cars.deleting"/></th>
                            </tr>
                            <c:forEach var="car" items="${cars}">
                                <tr>
                                    <td>${car.getModel()}</td>
                                    <td>${car.getColor()}</td>
                                    <td>${car.getMark()}</td>
                                    <td>${car.getDescription()}</td>
                                    <td>${car.getPrice()}</td>
                                    <td>${car.isStatus()}</td>
                                    <c:choose>
                                        <c:when test="${car.isStatus() == true}">
                                            <td class="default-table__action">
                                                <a href="RentCar?command=deactivate_car&carId=${car.getCarId()}"
                                                   class="default-table__button default-table__button--red">
                                                    <fmt:message key="admin_cars.disable_button"/></a>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="default-table__action">
                                                <a href="RentCar?command=activate_car&carId=${car.getCarId()}"
                                                   class="default-table__button default-table__button--green">
                                                    <fmt:message key="admin_cars.activate_button"/></a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="default-table__action">
                                        <a href="RentCar?command=delete_car&carId=${car.getCarId()}"
                                           class="default-table__button default-table__button--red">
                                            <fmt:message key="admin_cars.delete_button"/></a>
                                    </td>
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
