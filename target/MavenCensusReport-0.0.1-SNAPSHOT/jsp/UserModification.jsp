<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="${pageContext.request.contextPath}/resources/Stylesheets/user.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/validation.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/numberKey.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document)
			.ready(
					function() {
						$('#state1')
								.change(
										function(event) {
											var statevalue = $("select#state1")
													.val();
											$
													.get(
															'getdistrictList',
															{
																stateName : statevalue
															},
															function(response) {

																var select = $('#district1');
																select
																		.find(
																				'option')
																		.remove();
																$
																		.each(
																				response,
																				function(
																						index,
																						value) {
																					$(
																							'<option>')
																							.val(
																									value.districtId)
																							.text(
																									value.districtName)
																							.appendTo(
																									select);
																				});
															});
										});
						$('#district1')
								.click(
										function(event) {
											var districtvalue = $(
													"select#district1").val();
											$.get(
															'getUpdatePojo',
															{
																districtName : districtvalue
															},
															function(response) {
																$
																		.each(
																				response,
																				function(
																						index,
																						value) {
																					document
																							.getElementById("persons").value = value.persons;
																					document
																							.getElementById("numberofhouseholds").value = value.numberofhouseholds;
																					document
																							.getElementById("educated").value = value.educated;
																					document
																							.getElementById("workers").value = value.workers;
																				});
															});
										});
					});
</script>
</head>
<body>
	<h5 align="right">
		<iframe
			src="http://free.timeanddate.com/clock/i5twqof3/n553/fn4/fs16/tct/pct/ahr/avt/ftb/tt0/tw0/th2/ta1/tb4"
			frameborder="0" width="142" height="40" allowTransparency="true"></iframe>
	</h5>
	<td>
		<div style='float: left; text-align: left'>
			<c:if test="${userLogin=='Administrator'}">
				<p1 align="left">
				<form action="adminPrevious" method="post">

					<table>
						<tr>
							<td><input id="pre" type="submit" value="Previous Page"
								name="Previous Page" /></td>
						</tr>
					</table>
				</form>
				</p1>
			</c:if>
		</div>
		<div style='float: right; text-align: right'>
			<p align="right">
				<a href=""> <input type="button"
					value="Sign Out" name="Sign Out" />
				</a>
			</p>
		</div>
	</td>
	<h1>Modification Page</h1>


	<h2>Welcome ${userLogin},</h2>
	<marquee direction="right">
		<p3>
		<h3>${error}</h3>
		</p3>
	</marquee>
	<table>
		<tr>
			<td><h3>Would you like to Modify the Existing DATA's</h3></td>
			<td>
				<button
					onclick="document.getElementById('change').style.display='block'"">Modify</button>
			</td>
		</tr>
	</table>
	<div id="change" class="changeField">
		<span onclick="document.getElementById('change').style.display='none'"
			class="close">×</span>
		<form:form class="Sign-Form animate" name="clientvalidate"
			action="modify" modelAttribute="editTO">
			<div class="container">

				<h2>
					Choose one of the function<br>
				</h2>
				<h3>
					<form:radiobutton path="choice" value="delete"
						label="Delete a Record ?" />
					<br />
					<form:radiobutton path="choice" value="insert"
						label="Insert a Record ?" />
					<br />
					<form:radiobutton path="choice" value="update"
						label="Update a Record ?" />
					<br />
				</h3>
				<hr />
				<h4>

					<table align="center">
						<tr>
							<td>Enter the State :</td>

							<td><select id="state1" name="Istate">
									<option value="select">--select a state--</option>
									<c:forEach items="${statelist}" var="statelists">
										<option value=${statelists.stateid}>${statelists.statename}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr></tr>
						<tr>
							<td>Enter the District :</td>
							<td><select id="district1" name="Idistrict">
									<option value="select">--select a district--</option>
							</select></td>
						</tr>

						<tr>
							<td><form:label path="persons">Enter the Persons field</form:label></td>
							<td><form:input path="persons" name="persons" id="persons"
									placeholder="Numberic-Values"
									onkeypress="return isNumberKey(event)" /></td>
						</tr>
						<tr>
							<td><form:label path="numberofhouseholds">Enter the Households field</form:label></td>
							<td><form:input path="numberofhouseholds"
									name="numberofhouseholds" id="numberofhouseholds"
									placeholder="Numberic-Values"
									onkeypress="return isNumberKey(event)" /></td>
						</tr>
						<tr>
							<td><form:label path="educated">Enter the Educated field</form:label></td>
							<td><form:input path="educated" name="educated"
									id="educated" placeholder="Numberic-Values"
									onkeypress="return isNumberKey(event)" /></td>
						</tr>
						<tr>
							<td><form:label path="workers">Enter the Workers field</form:label></td>
							<td><form:input path="workers" name="workers" id="workers"
									placeholder="Numberic-Values"
									onkeypress="return isNumberKey(event)" /></td>
						</tr>

					</table>
					<input type="submit" value="submit" />
				</h4>
			</div>
		</form:form>
	</div>
	<br />
	<h4>Would you like to display DATA?</h4>
	<form action="displayPage" method="post">

		<table>
			<tr>
				<td><input type="hidden" name="display" value="display" /></td>
				<td><input type="submit" value="display" /></td>
			</tr>
		</table>
	</form>
</body>
</html>