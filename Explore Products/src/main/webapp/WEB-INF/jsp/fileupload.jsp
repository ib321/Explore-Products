
<html>
<head><title>File Upload</title></head>
<body>

<h1>Upload an Image File</h1>

	<form id="uploadForm" method="post" action="/upload" enctype="multipart/form-data">
		<input id="input-file" type="file" name="file" accept="image/png, image/jpeg" />			
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button class="btn btn-sm btn-outline-success float-right" type="submit">Upload</button>
	</form>
</body>
</html>