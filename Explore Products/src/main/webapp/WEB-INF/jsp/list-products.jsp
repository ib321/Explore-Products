<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-product">Add Product</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>Explore Products</h3>
		</div>
		<div class="panel-body">
			<div class="product-list">
				<c:forEach var="product" items="${products}">
					<div class="product">
						<div class="product-image">
							<img src="${product.productImageSrc}"
								alt="${product.productName}">
						</div>
						<div class="product-info">
							<h3>${product.productName}</h3>
							<h5>${product.description}</h5>
							<a href="${product.productLink}" class="product-btn">Click
								Here</a> <a type="button" class="btn btn-success"
								href="/update-product?id=${product.id}">Update</a> <a
								type="button" class="btn btn-warning"
								href="/delete-product?id=${product.id}">Delete</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- Remaining code Now not being used  May be removed in future-->
		<div class="panel-body" style="display: none;">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="20%">Image</th>
						<th width="20%">Name</th>
						<th width="20%">Description</th>
						<th width="20%">Link</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<td><div class="product-image"><img src="${product.productImageSrc}" alt="${product.productName}"></div></td>
							<td>${product.productName}</td>
							<td>${product.description}</td>
							<td><a href="${product.productLink}">Click Here</a></td>
							<td><a type="button" class="btn btn-success"
								href="/update-product?id=${product.id}">Update</a>
							<a type="button" class="btn btn-warning"
								href="/delete-product?id=${product.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>