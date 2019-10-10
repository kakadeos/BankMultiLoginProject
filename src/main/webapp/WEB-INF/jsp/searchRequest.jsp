<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<body>
<!-- Header File Navbar  -->
<%@ include file="header.jsp"%>
<!-- Header File Navbar End -->
	<div class="container">
		<div class="row">

			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h1>Bank Login System</h1>
				<form:form action="{id}">
					<div class="input-group">
						Search by Reason : 
						<input class="form-control" name="reason"/>
					</div>
					<input class="btn btn-primary" type="submit" value="Search" />
				</form:form>
				${message}
				
				
				<table border="2" width="100%" cellpadding="2">
					<tr>
						<th>ID</th>
						<th>Account Number</th>
						<th>Amount</th>
						<th>Reason</th>
						<th>Status</th>
					</tr>
					<c:forEach var="request" items="${list}">
						<tr>
						<th>${request.id}</th>
						<th>${request.accountNumber}</th>
						<th>${request.amount}</th>
						<th>${request.reason}</th>
						<th>${request.paymentStatus}</th>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-4"></div>

		</div>
		
		
		<div class="row">
		
		</div>
	</div>


	<!-- Bootstrap JS -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>