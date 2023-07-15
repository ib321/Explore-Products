<head>
<title>Log in</title>
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
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

.form-signin {
	max-width: 400px;
	padding: 30px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-heading {
	text-align: center;
	margin-bottom: 30px;
}

.form-control {
	height: 40px;
	font-size: 14px;
	border-radius: 5px;
	box-shadow: none;
}

.btn-primary {
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	font-size: 16px;
	width: 100%;
	margin-top: 20px;
	cursor: pointer;
}

.btn-primary:hover {
	background-color: #0069d9;
	color: #fff;
}

.text-center {
	text-align: center;
	margin-top: 20px;
	font-size: 14px;
	color: #777;
}

.text-center a {
	color: #007bff;
	text-decoration: none;
}

.text-center a:hover {
	color: #0056b3;
}

.text-danger, .text-success {
	margin-top: 10px;
	font-size: 14px;
	color: #dc3545;
}

.text-success {
	color: #28a745;
}
</style>
</head>
<body>
	<div class="container">
		<form class="form-signin" method="POST" action="/login">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group">
				<h6 class="text-danger">${error}</h6>
				<h6 class="text-success">${message}</h6>
				
				<label for="username">User ID</label>
				<br>
				<input name="username" type="text" maxlength="64" class="form-control" required="required" placeholder="Enter your User ID" autofocus /> 
				<br>
				<label for="password">Password</label>
				<br>
				<input name="password" type="password" maxlength="128" class="form-control" required="required" placeholder="Enter your password" /> 
					
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
				<button class="btn btn-primary" type="submit">Log In</button>

				<h6 class="text-center">
					Don't have an account? <a href="/registration">Create new account</a>
				</h6>
			</div>
		</form>
</div>
	<script src="/js/main.js"></script>
</body>
