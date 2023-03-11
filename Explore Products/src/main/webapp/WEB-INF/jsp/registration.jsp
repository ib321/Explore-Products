<%@ include file="common/header.jspf"%>

<title>Create an account</title>
<link href="/css/common.css" rel="stylesheet">
</head>

<body>

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
</body>

