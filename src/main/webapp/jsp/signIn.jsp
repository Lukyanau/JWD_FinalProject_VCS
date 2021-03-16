<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Golden - Cars</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign_in.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/images/signIn.jpg);background-size: cover;">

<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="RentCar" method="post">
                        <h3 class="text-center text-info">Sign in</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Login:</label><br>
                            <input type="text" name="username" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="command" value="go_to_home_page"/>
                            <input type="submit"  value="LOG IN" class="btn btn-info btn-md"/>
                        </div>
                        <span class="error" style="color:#ff340a">${errorMessage}</span>
                        <div id="register-link" class="text-right">
                            <br />
                            <a href="RentCar?command=passing_registration" class="text-info">Register here</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>