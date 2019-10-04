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
<!-- Header File Navbar  -->
<%@ include file="header.jsp"%>
<!-- Header File Navbar End -->

	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
			<h1>Bank Login System</h1>
				<table border="2" width="100%" cellpadding="2">
					<tr>
						<th>ID</th>
						<th>User</th>
						<th>Account Number</th>
						<th>Amount</th>
						<th>Reason</th>
						<th>Status</th>
						<th>Accept</th>
						<th>Reject</th>
					</tr>
					<c:forEach var="request" items="${list}">
						<tr>
						<th>${request.id}</th>
						<th>${request.username}</th>
						<th>${request.accountNumber}</th>
						<th>${request.amount}</th>
						<th>${request.reason}</th>
						<th>${request.paymentStatus}</th>
						<th><a href="acceptRequest/${request.id}">Accpet</a></th>
						<th><a href="rejectRequest/${request.id}">Reject</a></th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<!-- Bootstrap JS -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>