<%@ include file="common/header.jspf"%>

<title>Create an account</title>
<link href="/css/bootstrap-3.3.6.css/" rel="stylesheet">
<script src="/js/bootstrap-3.3.6.js/"></script>
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
			<form:label path="username">User Name</form:label>
			<form:input path="username" type="text" class="form-control"
				placeholder="Username" required="required" />
			<form:errors path="username" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control"
				placeholder="Password" required="required" />
			<form:errors path="password" cssClass="text-warning" />
		</div>

		<div class="form-group">
			<form:label path="passwordConfirm">Confirm your password</form:label>
				<form:input path="passwordConfirm" type="password" class="form-control"
					placeholder="Confirm Password" required="required" />
			<form:errors path="passwordConfirm" cssClass="text-warning" />
		</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

	</form:form>

</div>

<script src="/js/main.js"></script>

<%@ include file="common/footer.jspf"%>