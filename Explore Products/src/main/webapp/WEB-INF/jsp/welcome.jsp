<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="panel-body">
		Welcome ${name}!! <a href="/list-products">Click here</a> to Login And
		Manage your Products.
		<a type="button" class="btn btn-success" href="/show-products?user=${name}">Show Product</a>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">Explore Products</div>
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
							<a href="${product.productLink}" class="product-btn">Click Here</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>