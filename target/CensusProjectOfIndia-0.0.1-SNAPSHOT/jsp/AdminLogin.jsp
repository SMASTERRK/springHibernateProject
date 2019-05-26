<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminLogin</title>
<link
	href="${pageContext.request.contextPath}/resources/Stylesheets/admin.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<h5 align="right">
		<iframe
			src="http://free.timeanddate.com/clock/i5twqof3/n553/fn4/fs16/tct/pct/ahr/avt/ftb/tt0/tw0/th2/ta1/tb4"
			frameborder="0" width="142" height="40" allowTransparency="true"></iframe>
	</h5>
	<p align="right">
		<form action="intialPage" method="post">

					<table>
						<tr>
							<td><input type="submit" value="Sign Out"
								name="Sign Out" /></td>
						</tr>
					</table>
				</form>
	</p>
	<h1>ADMINISTRATOR PAGE</h1>
	<h2>Welcome, ${userLogin}</h2>
	<marquee direction="right">
		<p3>
		<h3>${error}</h3>
		</p3>
	</marquee>
	<form method="post" action="filepath">
		<h4>Choose a file to upload:</h4>
		<input type="file" name="file" /><br /> <br /> <input type="submit"
			value="submit" />
	</form>
	<h4>New Users waiting for Approval!!!</h4>
	<button
		onclick="document.getElementById('approval').style.display='block'"">Show
		List</button>
	<div id="approval" class="RegisterApproval">
		<form:form method="POST" action="approveUsers">
			<span
				onclick="document.getElementById('approval').style.display='none'"
				class="close">×</span>
			<c:if test="${approval!=null}">
				<center>
					<TABLE BORDER=1 CELLSPACING=1 CELLPADDING=4 bgcolor='#C0C0C0'>
						<tr>
							<td>Select</td>
							<td>FirstName</td>
							<td>LastName</td>
							<td>UserName</td>
							<td>Email-Id</td>
							<td>Mobile</td>
							<td>Role</td>
						</tr>
						<c:forEach items="${approval}" var="out">
							<tr>
								<td><input type="checkbox" name="listapprove"
									value=${out.uname } /></td>
								<td>
									<h4>${out.fname}</h4>
								</td>
								<td>
									<h4>${out.lname}</h4>
								</td>
								<td>
									<h4>${out.uname}</h4>
								</td>
								<td>
									<h4>${out.email}</h4>
								</td>
								<td>
									<h4>${out.monbile}</h4>
								</td>
								<td>
									<h4>${out.role}</h4>
								</td>
							</tr>
						</c:forEach>
					</Table>
				</center>
				<input type="submit" value="submit" />
				<br />
				<br />
			</c:if>
		</form:form>
	</div>
	<br />
	<br />
	<table style="width: 100%">
		<tr>
			<th><h4>Would you like to display DATA?</h4></th>
			<th>Would you like to Edit DATA?
			<th></th>
		</tr>
	</table>
	<table style="width: 100%">
		<tr>
			<th>
				<form action="directdisplayPage" method="post">
					<input type="submit" value="display" />
				</form>
			</th>
			<th>
				<form action="editPage" method="post">
					<input type="submit" value="Edit data" />
				</form>
			</th>
		</tr>
	</table>
</body>
</html>