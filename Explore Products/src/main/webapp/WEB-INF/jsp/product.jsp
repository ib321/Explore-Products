<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<!-- Code with Error Used for file upload need to be updated -->
<div class="panel-body" style="display: none;">
  <form action="upload.jsp" method="post" enctype="multipart/form-data">
    <input type="file" name="imageFile"><br>
    <input type="submit" value="Upload">
  </form>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Add Product</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="product">
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
							<form:label path="productImageSrc">Product Image link</form:label>
							<form:input path="productImageSrc" type="text"
								class="form-control" required="required" />
							<form:errors path="productImageSrc" cssClass="text-warning" />
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="common/footer.jspf"%>