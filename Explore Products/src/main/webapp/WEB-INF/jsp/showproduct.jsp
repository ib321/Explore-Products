<%@ include file="common/header.jspf"%>

<link href="/css/main.css" rel="stylesheet">

<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4>
				Explore Products
				<form action="/search-products" method="get" style="float: right;">
					<input type="text" class="search" name="search" placeholder="Enter Product Name..."> 
					<input type="submit" disabled class="search" value="Search" >
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
							<a href="${product.productLink}"  target="_blank" class="btn btn-info">Click
								Here</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>