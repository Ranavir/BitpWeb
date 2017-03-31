<%
    if(null!=request.getAttribute("errorMessage"))
    {
       request.getAttribute("errorMessage");
    }
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BITP Login</title>
<meta charset="utf-8">
		<meta name = "format-detection" content = "telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="theme-color" content="#042048" />
		<link rel="shortcut icon" href="/assets/images/favicon.png" type="image/x-icon">
		<link rel="icon" href="assets/images/favicon.png" type="image/x-icon">
		<!-- Bootstrap -->
		<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="assets/css/custom.css" rel="stylesheet" media="screen">
		<script src="plugins/jquery/jquery.min.js"></script>

</head>
<body id="root" class="login-body">
		<h1 class="text-center" style="color: deepskyblue;">BITP Management Portal</h1>
		<div class="container-fluid white">
			<div class="container">
				<div class="col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
					<div class="login">
						<header class="text-center"><img src="assets/images/customer_login.png" class="img-responsive center-block logo"/></header>
						<div class="panel">
							<div class="panel-body">
								<form action="login.do" class="loginForm" id="login_form" method="GET" accept-charset="utf-8">
									<div class="form-group">
										<label>User Name</label>
										<input type="text" name="username" class="form-control"/>
																			</div>
									<div class="form-group">
										<label>Password</label>
										<input type="password" name="password" class="form-control"/>
																			</div>
									<div class="form-action">
										<input class="btn btn-block primary" type="submit" value="Login">
									</div>
								</form>
								<div style="color: red;text-align: center;font-weight: bold;"><c:if test="${not empty errorMessage}">
   										<c:out value="${errorMessage}"/>
									</c:if></div>
							</div>
							
						</div>
					</div>
				</div>
			</div>	
		</div>	
		<div class="text-center login-style">
			<div class="row">
				
				<div class="col-xs-8 col-sm-8 col-md-8">
					<div class="row">
						
				</div>
				
			</div>
			</div>
		</div>

	<script src="plugins/jquery/jquery.validate.min.js"></script>
	<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
	
	<script src="assets/js/custom-form-validator.js"></script>
	<script src="assets/js/custom.js"></script>


	</body>

</html>