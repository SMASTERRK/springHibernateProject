<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/resources/Stylesheets/login.css"
	rel="stylesheet" type="text/css" />
<link href='https://fonts.googleapis.com/css?family=Architects Daughter'
	rel='stylesheet'>
<title>LoginPage</title>
</head>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/validation.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/numberKey.js"
	type="text/javascript"></script>

<body>
	<h5 align="right">
		<iframe
			src="http://free.timeanddate.com/clock/i5twqof3/n553/fn4/fs16/tct/pct/ahr/avt/ftb/tt0/tw0/th2/ta1/tb4"
			frameborder="0" width="142" height="40" allowTransparency="true"></iframe>
	</h5>
	<center>
		<h2>CENSUS REPORT - GOVERNMENT OF INDIA</h2>
	</center>
	<center><h3>${error}</h3></center>
	<h3>Login with your Account</h3>
	<table>
		<form action="login" method="post">
			<tr>
				<td><h4>Enter the UserName:</h4></td>
				<td><input type="text" placeholder="UserName" name="UserName"
					value="" /></td>
			</tr>
			<tr>
				<td><h4>Enter the Password:</h4></td>
				<td><input type="password" placeholder="Password"
					name="Password" value="" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit" /></td>
			</tr>
		</form>
	</table>

	<table>
		<tr>
			<td><h3>Don't have an account? Register Now</h3></td>
			<td>
				<button
					onclick="document.getElementById('Register').style.display='block'"">Sign
					Up</button>
			</td>
		</tr>
	</table>

	<div id="Register" class="RegisterForm">
		<span
			onclick="document.getElementById('Register').style.display='none'"
			class="close">×</span>
		<form:form modelAttribute="user" class="Sign-Form animate"
			name="clientvalidate" action="register"
			method="POST" onsubmit="return validateform()">
			<div class="container">
				<label><b><form:label path="fname">Enter the First Name</form:label></b></label>
				<form:input path="fname" name="fname" placeholder="First Name"
					value="${regInfo.fname}" required="required" />
				<label><b><form:label path="lname">Enter the LastName</form:label></b></label>
				<form:input path="lname" name="lname" placeholder="Last Name"
					value="${regInfo.lname}" required="required" />
				<label><b><form:label path="uname">Enter the User Name</form:label></b></label>
				<form:input path="uname" name="uname" placeholder="User Name"
					value="${regInfo.uname}" required="required" />
				<label><b><form:label path="pword">Enter the Password</form:label></b></label>
				<form:input path="pword" name="pword" placeholder="Enter Password"
					value="${regInfo.pword}" required="required" />
				<label><b><form:label path="rpword">Re-enter the Passoword</form:label></b></label>
				<form:input path="rpword" name="rpword"
					placeholder="Re-enter Password" value="${regInfo.rpword}" required="required" />
				<label><b><form:label path="email">Enter the Email Id</form:label></b></label>
				<form:input path="email" name="email"
					placeholder="enter the valid email" value="${regInfo.email}"
					required="required" />
				<label><b><form:label path="monbile">Enter the Mobile Number</form:label></b></label>
				<form:input path="monbile" onkeypress="return isNumberKey(event)"
					name="monbile" placeholder="enter the 10 digit mobile"
					value="${regInfo.monbile}" required="required" />
				<label><b><form:label path="role">Enter the role(User/Admin)</form:label></b></label>
				<form:input path="role" name="role"
					placeholder="(User role/Admin role)" value="${regInfo.role}"
					required="required" />
				<p>By creating an account you agree to our ..Terms & Privacy</p>
				<input type="submit" value="submit" />
			</div>
		</form:form>
	</div>
</body>
</html>
