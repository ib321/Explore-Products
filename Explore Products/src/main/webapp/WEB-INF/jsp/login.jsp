
<head>
<title>Log in</title>
<link href="/css/main.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<div class="container">
		<form class="form-signin" method="POST" action="/login">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group">
				<span>${error}</span> 
				<span>${message}</span> 
				
				<input name="username"	type="text" class="form-control" placeholder="Username" autofocus /> 
					
				<input name="password" type="password" class="form-control" placeholder="Password" /> 
					
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
				
				<h4 class="text-center">
					<a href="/registration">Create an account</a>
				</h4>
				<!-- 
				<h4 class="text-center">
					<a type="button" class="btn btn-success" href="/show-products?user=admin">Show Admin Product</a>
				</h4>
				 -->
				
			</div>
		</form>
	</div>

	<script src="/js/main.js"></script>
</body>