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
		showHideTools();
		showToolsOnLoad();
	});
	
	function showHideTools() {
		var checkBox = document.getElementById("ToolsCheckBox");
		var ToolsDiv = document.getElementById("ToolsDiv");
		if (true == checkBox.checked) {
			ToolsDiv.style.display = "block";
		} else {
			ToolsDiv.style.display = "none";
		}
	}
	function showToolsOnLoad() {
		var checkBox = document.getElementById("ToolsCheckBox");
		var imageUrl = document.getElementById("productImageLink") ?
				document.getElementById("productImageLink").value : "";
		if (imageUrl != "") {
			checkBox.checked = true;
		}
		showHideTools();
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
  					<input type="checkbox" id="ToolsCheckBox" onclick="showHideTools()">
  					<span class="slider round" title="Click here to show or hide the tools."></span>
				</label>
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="product" enctype="multipart/form-data">
						<form:hidden path="id" />

						<fieldset class="form-group">
							<form:label path="productName">Product Name</form:label>
							<form:input path="productName" type="text" class="form-control"
								maxlength="400" placeholder="Enter product name" required="required" />
							<form:errors path="productName" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="description">Description</form:label>
							<form:input path="description" type="text" class="form-control"
								maxlength="600" placeholder="Enter product description" required="required" />
							<form:errors path="description" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="productLink">Product Link</form:label>
							<form:input path="productLink" type="text" class="form-control"
								maxlength="1000" placeholder="Enter product link" required="required" />
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
				<div class="panel-heading">Tools</div>
				<div class="panel-body">

					<form action="/getImage" method="get">
						<label for="productUrl">Product URL:</label><br>
						<input type="text" class="form-control" required="required" maxlength="1000" placeholder="Enter product url to fetch image link" id="productUrl" name="productUrl" value="${productUrl}">
						<br>
						<input type="submit" class="btn btn-success" value="Fetch Image Link">
					</form>

					<div style="display: none;">
						<button onclick='getProductDescFromURL();' class="btn btn-success">Fill Description</button>
					</div>

					<c:if test="${not empty imageUrl}">
						<label for="productImageLink">Product Image Link:</label>
						<br>
						<input type="text" class="form-control" required="required" maxlength="1000" placeholder="Enter image link" id="productImageLink" name="productImageLink"
							value="${imageUrl}">
						<br>
						<button onclick='copyImageUrl();' class="btn btn-success">Transfer</button>
						<br>
						<br>
					</c:if>
					<div>
						<label for="image-link">Download Image:</label><br>
						<input type="text" class="form-control" required="required" maxlength="1000" placeholder="Enter image link to download image" id="image-link">
						<br>
						<button onclick="downloadImage()" class="btn btn-success">Download</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	</div>
</div>
<script src="/js/main.js"></script>
<%@ include file="common/footer.jspf"%>