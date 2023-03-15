<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<style>
.panel-body
{
font-size: 20px;
}
</style>




<div class="container">

	<div class="panel-body"> Welcome ${name} !! <a href="/list-products">Products</a>.
		<br>
		Click on share button to share all your products.
		<button class="popup-btn"><img  src="/share.jpg" alt="Share" style="background-color: #5cb85c; vertical-align: middle; margin-left: 5px; height: 16px; width: 16px;"> Share</button>
	</div>
	<!-- 
	<a type="button" class="btn btn-success" href="/show-products?user=${name}">Products</a>
	 -->

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


<div class="video-popup">
	<div class="popup-content">
		<span class="close">&times;</span>
		<p>Copy this link:</p>
		<input type="text" id="link"
			value="http://localhost:8098/show-products?user=${name}">
		<button id="copy">Copy</button>
	</div>
</div>

<script>
	var btn = document.querySelector(".popup-btn");
	var popup = document.querySelector(".video-popup");
	var close = document.querySelector(".close");
	var link = document.getElementById("link");
	var copy = document.getElementById("copy");

	btn.onclick = function() {
		popup.style.display = "block";
	};

	close.onclick = function() {
		popup.style.display = "none";
	};

	copy.onclick = function() {
		link.select();
		link.setSelectionRange(0, 99999);
		document.execCommand("copy");
	};
</script>
<style>
.popup-btn {
	color: #fff;
	background-color: #5cb85c;
	border-color: #4cae4c;
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	float: right;
}

.video-popup {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
}

.popup-content {
	background-color: #5cb85c;
	opacity: 0.8;
	margin: auto;
	padding: 20px;
	position: absolute;
	top: 20%;
	left: 65%;
	transform: translate(-50%, -50%);
	width: 300px;
	max-width: 90%;
}

.close {
	color: red;
	float: right;
	font-size: 28px;
}

.close:hover, .close:focus {
	color: black;
}
</style>
<%@ include file="common/footer.jspf"%>