<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>
<html>
<body>
<div class="layout-header">
    <div class="container">
        <div class="layout-header__row">
            <a href="RentCar?command=passing_home" class="logo">
                <div class="logo__subtitle"><fmt:message key="header.subtitle"/></div>
                <fmt:message key="header.rent_name"/>
            </a>
            <div class="layout-header__menu">
                <ul class="navigation">
                    <c:if test="${not empty user}">
                        <c:if test="${userRole == 'USER'}">
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_catalog" class="navigation__link">
                                    <fmt:message key="header.catalog"/>
                                </a>
                            </li>
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_user_profile" class="navigation__link">
                                    <fmt:message key="header.account"/>
                                </a>
                            </li>
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_payment_card" class="navigation__link">
                                    <fmt:message key="header.make_deposit"/>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${userRole == 'ADMIN'}">
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_admin_orders" class="navigation__link">
                                    <fmt:message key="header.check_orders"/>
                                </a>
                            </li>
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_admin_cars" class="navigation__link">
                                    <fmt:message key="header.cars"/>
                                </a>
                            </li>
                            <li class="navigation__item">
                                <a href="RentCar?command=passing_users_admin" class="navigation__link">
                                    <fmt:message key="header.check_users"/>
                                </a>
                            </li>
                        </c:if>
                        <li class="navigation__item">
                            <a href="RentCar?command=log_out" class="navigation__link">
                                <fmt:message key="header.log_out"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty user}">
                        <li class="navigation__item">
                            <a href="RentCar?command=passing_sign_in" class="navigation__link">
                                <fmt:message key="header.sign_in"/>
                            </a>
                        </li>
                        <li class="navigation__item">
                            <a href="RentCar?command=passing_sign_up" class="navigation__link">
                                <fmt:message key="header.sign_up"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <div class="language-select">
                    <div class="language-select__current">
                        <span class="language-select__label">
                             <c:choose>
                                 <c:when test="${language eq 'ru'}"> <fmt:message key="header.language_ru"/></c:when>
                                 <c:when test="${language eq 'en'}"> <fmt:message key="header.language_en"/></c:when>
                                 <c:otherwise>
                                     <fmt:message key="header.language_en"/>
                                 </c:otherwise>
                             </c:choose>
                        </span>
                        <span class="language-select__arrow"></span>
                    </div>
                    <div class="language-select__dropdown">
                        <ul class="menu">
                            <li>
                                <a href="RentCar?command=change_language&language=ru"><span>
                                    <fmt:message key="header.language_ru"/></span></a>
                            </li>
                            <li>
                                <a href="RentCar?command=change_language&language=en"><span>
                                    <fmt:message key="header.language_en"/></span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <button class="burger burger-js"><span class="burger__line"></span>
            </button>
        </div>
    </div>
</div>
<div class="mobile-menu">
    <button class="burger burger-close-js active"><span class="burger__line"></span>
    </button>
    <div class="mobile-menu__list">
        <ul class="navigation">
            <c:if test="${not empty user}">
                <c:if test="${userRole == 'USER'}">
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_about_us" class="navigation__link">
                            <fmt:message key="header.about"/>
                        </a>
                    </li>
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_catalog" class="navigation__link">
                            <fmt:message key="header.catalog"/>
                        </a>
                    </li>
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_user_profile" class="navigation__link">
                            <fmt:message key="header.account"/>
                        </a>
                    </li>
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_make_deposit" class="navigation__link">
                            <fmt:message key="header.make_deposit"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${userRole == 'ADMIN'}">
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_admin_catalog" class="navigation__link">
                            <fmt:message key="header.check_orders"/>
                        </a>
                    </li>
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_admin_cars" class="navigation__link">
                            <fmt:message key="header.cars"/>
                        </a>
                    </li>
                    <li class="navigation__item">
                        <a href="RentCar?command=passing_users_admin" class="navigation__link">
                            <fmt:message key="header.check_users"/>
                        </a>
                    </li>
                </c:if>
                <li class="navigation__item">
                    <a href="RentCar?command=log_out" class="navigation__link">
                        <fmt:message key="header.log_out"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${empty user}">
                <li class="navigation__item">
                    <a href="RentCar?command=passing_sign_in" class="navigation__link">
                        <fmt:message key="header.sign_in"/>
                    </a>
                </li>
                <li class="navigation__item">
                    <a href="RentCar?command=passing_sign_up" class="navigation__link">
                        <fmt:message key="header.sign_up"/>
                    </a>
                </li>
            </c:if>
        </ul>
        <div class="language-select">
            <div class="language-select__current">
                        <span class="language-select__label">
                             <c:choose>
                                 <c:when test="${language eq 'ru'}"> <fmt:message key="header.language_ru"/></c:when>
                                 <c:when test="${language eq 'en'}"> <fmt:message key="header.language_en"/></c:when>
                                 <c:otherwise>
                                     <fmt:message key="header.language_en"/>
                                 </c:otherwise>
                             </c:choose>
                        </span>
                <span class="language-select__arrow"></span>
            </div>
            <div class="language-select__dropdown">
                <ul class="menu">
                    <li>
                        <a href="RentCar?command=change_language&language=ru"><span>
                                    <fmt:message key="header.language_ru"/></span></a>
                    </li>
                    <li>
                        <a href="RentCar?command=change_language&language=en"><span>
                                    <fmt:message key="header.language_en"/></span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>