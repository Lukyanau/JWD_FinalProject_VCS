<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/images/registration.jpg);background-size: cover;">
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">Please Register</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="RentCar" method="post">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="name" id="name" class="form-control input-sm"
                                           placeholder="Name">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="surname" id="surname" class="form-control input-sm"
                                           placeholder="Surname">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="email" id="email" class="form-control input-sm"
                                   placeholder="Email">
                        </div>
                        <div class="form-group">
                            <input type="text" name="phoneNumber" id="phoneNumber" class="form-control input-sm"
                                   placeholder="+375(xx)xxx-xx-xx">
                        </div>
                        <div class="form-group">
                            <input type="text" name="login" id="login" class="form-control input-sm"
                                   placeholder="Login">
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="Password">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder="Confirm Password">
                                </div>
                            </div>
                        </div>
                        <div class="someDiv">
                            <input type="hidden" name="command" value="sign_up"/>
                            <input type="submit" value="Register" class="btn btn-info btn-block">
                            <input type="hidden" name="command" value="passing_sign_in"/>
                            <input type="submit" value="Return To Sign In" class="btn btn-info btn-block">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>