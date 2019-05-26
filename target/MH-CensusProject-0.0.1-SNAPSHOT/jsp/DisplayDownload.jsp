<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.StringTokenizer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="${pageContext.request.contextPath}/resources/Stylesheets/display.css"
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
	$(document).ready(
			function() {
				$('#state1').change(
						function(event) {
							var statevalue = $("select#state1").val();
							$.get('getdistrictList', {
								stateName : statevalue
							}, function(response) {

								var select = $('#district1');
								select.find('option').remove();
								$.each(response, function(index, value) {
									$('<option>').val(value.district_Id).text(
											value.district_Name).appendTo(
											select);
								});
							});
						});

			});
</script>
</head>

<!-- Filtering Conditions on the Entire file -->
<!-- ======================================= -->


<body">
	<h6 align="right">
		<iframe
			src="http://free.timeanddate.com/clock/i5twqof3/n553/fn4/fs16/tct/pct/ahr/avt/ftb/tt0/tw0/th2/ta1/tb4"
			frameborder="0" width="142" height="40" allowTransparency="true"></iframe>
	</h6>
	<td>
		<div style='float: left; text-align: left'>
			<p align="right">
			<form action="/HibernateWebProject/editPage" method="post">
				
				<table>
					<tr>
						<td></td>
						<td><input id="pre" type="submit" value="Previous Page"
							name="Previous Page" /></td>
					</tr>
				</table>
			</form>
			</p>
		</div>
		<div style='float: right; text-align: right'>
			<p align="right">
				<a href="/HibernateWebProject/"> <input type="button" value="Sign Out"
					name="Sign Out" />
				</a>
			</p>
		</div>
	</td>

	<h1>Display CENSUS REPORT</h1>
	<br />
	<h2>Welcome, ${userLogin}</h2>

	<form:form class="Sign-Form animate" name="clientvalidate"
			action="/HibernateWebProject/displayCondition" modelAttribute="editTO">
			<div class="container">
		<table align="center">
						<tr>
							<td>Display by State and District</td>

							<td><select id="state1" name="Istate">
									<option value="select">--select a state--</option>
									<c:forEach items="${statelist}" var="statelists">
										<option value=${statelists.state_id}>${statelists.state_name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr></tr>
						<tr>
							<td></td>
							<td><select id="district1" name="Idistrict">
									<option value="select">--select a district--</option>
							</select></td>
						</tr>

						<tr>
							<td><form:label path="persons">Display by Persons Range</form:label></td>
							<td><form:input path="persons" name="persons"
									placeholder="Range - from" value=""
									onkeypress="return isNumberKey(event)" /><form:input path="rangePersons" name="rangePersons"
									placeholder="Range - to"
									onkeypress="return isNumberKey(event)" /></td>
						</tr>
						<tr>
							<td><form:label path="numberofhouseholds">Display by Households Range</form:label></td>
							<td><form:input path="numberofhouseholds" name="numberofhouseholds"
								placeholder="Range - from" value=""
								onkeypress="return isNumberKey(event)" /><form:input path="rangeNumberofhouseholds" name="rangeNumberofhouseholds"
								placeholder="Range - to"
								onkeypress="return isNumberKey(event)" />
							</td>
							</tr>
						<tr>
							<td><form:label path="educated">Display by Education Range</form:label></td>
							<td><form:input path="educated" name="educated"
								placeholder="Range - from" value=""
								onkeypress="return isNumberKey(event)" />
						
						<form:input path="rangeEducated" name="rangeEducated"
								placeholder="Range - to"
								onkeypress="return isNumberKey(event)" />
							</td>
							</tr>
						<tr>
							<td><form:label path="workers">Display by workers Range</form:label></td>
							<td><form:input path="workers" name="workers"
								placeholder="Range - from" value=""
								onkeypress="return isNumberKey(event)" />
						
						<form:input path="rangeWorkers" name="rangeWorkers"
								placeholder="Range - to"
								onkeypress="return isNumberKey(event)" />
							</td>
							</tr>

				<!-- Ordering Conditions -->
				<!-- =================== -->
				<tr>
					<td>Select an option for Order :</td>
					<td><select name="order">
							<option value="persons">Persons</option>
							<option value="numberofhouseholds">Households</option>
							<option value="educated">Educated</option>
							<option value="workers">Workers</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="submit"
						onclick="alert('The above conditions submitted-SUCCESSFULLY!!!')" /></td>
				</tr>
			</TABLE>
					</table>

		</center>
</form:form>
	<form:form action="/HibernateWebProject/downloadResult">

		<table>
			<tr>
				<td></td>
				<td><input type="submit" value="Download" /></td>
			</tr>
		</table>
	</form:form>
	<hr />

	<!-- Displaying the resultant query -->
	<!-- ============================== -->
	<p>
	<h4>${error}</h4>
	<c:if test="${entireList!=null}">
		<h3>Census Report of India</h3>
		<h5>${sort}</h5>
		<center>
			<TABLE BORDER=1 CELLSPACING=1 CELLPADDING=5 bgcolor='#C0C0C0'>
				<tr>
				<tr>
					<td>CENSUS_ID</td>
					<td>STATE NAME</td>
					<td>DISTRICTS</td>
					<td>PERSONS</td>
					<td>NUMBER-OF-HOUSEHOLDS</td>
					<td>EDUCATED</td>
					<td>WORKERS</td>
					<td>STATUS</td>
				</tr>
				<c:forEach items="${entireList}" var="out">
					<tr>
						<td>${out.census_Id}</td>
						<td>${out.district_details.stateDetails.state_name }</td>
						<td>${out.district_details.district_Name }</td>
						<td>${out.persons }</td>
						<td>${out.numberofhouseholds }</td>
						<td>${out.educated }</td>
						<td>${out.workers }</td>
						<td>${out.statusRecord }</td>
					</tr>
				</c:forEach>


			</table>
		</center>
	</c:if>
	</p>

</body>
</html>