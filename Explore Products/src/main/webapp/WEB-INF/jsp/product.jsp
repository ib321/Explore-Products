<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<link href="/css/switch.css/" rel="stylesheet">
<script>
	$(document).ready(function() {
		copyUrl();
		if ($("#productUrl").text().trim().length > 0) {
			console.log("inside url");
		    $("#productLink").value=$("#productUrl").value;
		}
		showHideDiv();
	});
	
	function showHideDiv() {
		var checkBox = document.getElementById("ToolsCheckBox");
		var ToolsDiv = document.getElementById("ToolsDiv");
		var imageUrl = document.getElementById("productImageLink") ?
				document.getElementById("productImageLink").value : "";
		if (imageUrl != "") {
			checkBox.checked = true;
		}
		if (true == checkBox.checked) {
			ToolsDiv.style.display = "block";
		} else {
			ToolsDiv.style.display = "none";
		}
	}
</script>
<div class="container">
	<h4 class="text-success">${message}</h4>
	<h4 class="text-danger">${dangermsg}</h4>
	<h4 class="text-warning">${warningmsg}</h4>
	<div class="row">
		<div class="col-md-6 col-md-offset" id="AddProductDiv">
			<div class="panel panel-primary">
				<div class="panel-heading">Add Product 
				<label class="switch" style="float: right;">
  					<input type="checkbox" id="ToolsCheckBox" onclick="showHideDiv()">
  					<span class="slider round" title="Click here to show or hide the tools."></span>
				</label>
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="product" enctype="multipart/form-data">
						<form:hidden path="id" />

						<fieldset class="form-group">
							<form:label path="productName">Product Name</form:label>
							<form:input path="productName" type="text" class="form-control"
								required="required" />
							<form:errors path="productName" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="description">Description</form:label>
							<form:input path="description" type="text" class="form-control"
								required="required" />
							<form:errors path="description" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="productLink">Product Link</form:label>
							<form:input path="productLink" type="text" class="form-control"
								required="required" />
							<form:errors path="productLink" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="productImageFile">Product Image</form:label>
							<form:input path="productImageFile" type="file" accept="image/*"
								class="form-control" required="required" />
							<form:errors path="productImageFile" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:hidden path="productImageSrc"
								class="form-control" readonly="true"/>
							<form:errors path="productImageSrc" cssClass="text-warning" />
						</fieldset>
						<br>
						<button type="submit" class="btn btn-success">Save</button>
						<button type="reset" class="btn btn-warning">Clear</button>
					</form:form>

				</div>
			</div>
		</div>


	<c:if test="true">
		<div class="col-md-6 col-md-offset" id="ToolsDiv">
			<div class="panel panel-primary">
				<div class="panel-heading">Image Link Generator</div>
				<div class="panel-body">
					<div style="display: none;">
						<a href="/upload" class="product-btn">Test Upload Page</a>
					</div>
					<br>
					<div>
						<label for="image-link">Download Image:</label><br>
						<input type="text" id="image-link">
						<button onclick="downloadImage()">Download</button>
					</div><br>
					<form action="/getImage" method="get">
						<label for="productUrl">Product URL:</label><br>
						<input type="text" id="productUrl" name="productUrl" value="${productUrl}"><br><br>
						<input type="submit" value="Fetch Image Link">
					</form>
					<div>
						<button onclick='getProductDescFromURL();'>Fill Description</button>
					</div>
					<!--  
					<button onclick='copyUrl();'>Transfer</button>
					<br>
					-->
					<c:if test="${not empty imageUrl}">
						<label for="productImageLink">Product Image Link:</label>
						<br>
						<input type="text" id="productImageLink" name="productImageLink"
							value="${imageUrl}">
						<button onclick='copyImageUrl();'>Transfer</button>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>

	</div>
</div>
<script src="/js/main.js"></script>
<%@ include file="common/footer.jspf"%>