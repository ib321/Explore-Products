<%@ include file="common/header.jspf"%>

<title>Create an account</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/jquery/2.2.4/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css?family=Roboto');

body {
	font-family: 'Roboto', sans-serif;
	background-image: url("/commonimage/bg-image.jpg");
	background-size: cover;
	background-position: center;
}

.container {
	width: 500px;
	margin: 50px auto;
}

.form-signin {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #fff;
}

.form-signin-heading {
	margin-bottom: 20px;
}

.form-group {
	width: 80%;
	margin-bottom: 15px;
}

.form-label {
	display: block;
	font-weight: bold;
}

.form-input {
	display: block;
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.form-errors {
	display: block;
	color: #f00;
}

.btn {
	width: 80%;
	padding: 12px;
	border: none;
	border-radius: 10px;
}

.btn-primary {
	background-color: #007bff;
	color: #fff;
}

.form-control {
  display: block;
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.text-warning {
  display: block;
  color: #f00;
}
</style>

<div class="container">

	<form:form method="post" class="form-signin" modelAttribute="userForm">

		<h2 class="form-signin-heading">Create your account</h2>

		<div class="form-group">
			<form:label path="fullname">Your name</form:label>
			<form:input path="fullname" type="text" class="form-control"
				maxlength="64" placeholder="First and last name" required="required" />
			<form:errors path="fullname" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="username">User ID</form:label>
			<form:input path="username" type="text" class="form-control"
				maxlength="64" placeholder="Enter your User ID" required="required" />
			<form:errors path="username" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="email">Email Address</form:label>
			<form:input path="email" type="text" class="form-control"
				maxlength="64" placeholder="Enter email address" required="required" />
			<form:errors path="email" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control"
				maxlength="128" placeholder="Enter your password" required="required" />
			<form:errors path="password" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="passwordConfirm">Confirm your password</form:label>
				<form:input path="passwordConfirm" type="password" class="form-control"
					maxlength="128" placeholder="Confirm password" required="required" />
			<form:errors path="passwordConfirm" cssClass="text-warning" />
		</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

	</form:form>

</div>

<script src="/js/main.js"></script>

<%@ include file="common/footer.jspf"%>