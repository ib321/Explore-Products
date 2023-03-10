<%@ include file="common/header.jspf"%>

<div class="container">
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
							<a href="${product.productLink}" class="product-btn">Click
								Here</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>