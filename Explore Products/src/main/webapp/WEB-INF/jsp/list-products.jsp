<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="/css/main.css" rel="stylesheet">
<script src="/js/main.js"></script>
<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-product">Add Product</a>
	</div>
	<h4 class="text-success" >${message}</h4>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4>
				Explore Products
				<form action="/search-products" method="get" style="float: right;">
					<input type="text" class="search" name="search" placeholder="Enter Product Name..."> 
					<input type="submit" class="search" value="Search" >
				</form>
			</h4>
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
							<a href="${product.productLink}" target="_blank" class="btn btn-info">Click Here</a>
							<a type="button" class="btn btn-success"
								href="/update-product?id=${product.id}">Update</a>
							<a type="button" class="btn btn-warning"
								href="/delete-product?id=${product.id}">Delete</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>


<!-- Remaining code to view product in table format ! Not being used May be removed in future
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
-->