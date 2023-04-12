<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<link href="/css/main.css" rel="stylesheet">

<div class="container">

	<div class="panel-body"> Welcome ${name} !! <a href="/list-products">Products</a>
		<br>
		<%@ include file="shareButton.jsp"%>
	</div>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4>
				Explore Products
				<form action="/search" method="get" style="float: right;">
					<input type="text" class="search" name="search" placeholder="Enter Product Name..."> 
					<input type="submit" class="search" value="Search" >
				</form>
			</h4>
		</div>
		<div class="panel-body">
			<c:if test="${empty products}">
				<jsp:include page="noproductfound.jsp" />
			</c:if>
			<div class="product-list">
				<c:forEach var="product" items="${products}">
					<div class="product">
						<div class="product-image">
							<img src="${product.productImageSrc}"
								alt="${product.productName}">
						</div>
						<div class="product-info">
							<h3 data-toggle="tooltip" title="${product.productName}" >${product.productName}</h3>
							<h5 data-toggle="tooltip" title="${product.description}" >${product.description}</h5>
							<a href="${product.productLink}"  target="_blank" class="btn btn-info">Click
								Here</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</div>
<script>
$('h3').tooltip();
$('h5').tooltip();
</script>
<script src="/js/main.js"></script>
<%@ include file="common/footer.jspf"%>