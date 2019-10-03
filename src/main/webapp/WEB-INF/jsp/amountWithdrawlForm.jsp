<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Bank Project</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h1>Bank Login System</h1>
				<form:form action="requestMoney" modelAttribute="withdrawl">
					<div class="input-group">
						Account Number :
						<form:input class="form-control" path="accountNumber" />
					</div>

					<div class="input-group">
						Amount :
						<form:input class="form-control" path="amount" />
					</div>

					<div class="input-group">
						Reason :
						<form:input class="form-control" path="reason" />
					</div>

					<input class="btn btn-primary" type="submit" value="Request" />
				</form:form>
				${message}
			</div>
			<div class="col-md-4"></div>

		</div>
	</div>


	<!-- Bootstrap JS -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>