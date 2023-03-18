<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		copyUrl();
		console.log("inside ready");
		if ($("#productUrl").text().trim().length > 0) {
			console.log("inside url");
		    $("#productLink").value=$("#productUrl").value;
		}
	});
</script>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset">
			<div class="panel panel-primary">
				<div class="panel-heading">Add Product</div>
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
							<form:input path="productImageSrc" type="text"
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



		<div class="col-md-6 col-md-offset">
			<div class="panel panel-primary">
				<div class="panel-heading">Image Link Generator</div>
				<div class="panel-body">
					<div>
						<a href="/upload" class="product-btn">Test Upload Page</a>
					</div>
					<form action="/getImage" method="get">
						<label for="productUrl">Product URL:</label><br> 
						<input type="text" id="productUrl" name="productUrl" value="${productUrl}"><br> <br>
						<input type="submit" value="Fetch Image Link">
					</form>
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
						<br>
						<br>
						Generate Auto description from URL:
						<br>
						<button onclick='getProductDescFromURL();'>Generate Description</button>
					</c:if>
				</div>
			</div>
		</div>

	</div>
</div>
<script src="/js/main.js"></script>
<%@ include file="common/footer.jspf"%>