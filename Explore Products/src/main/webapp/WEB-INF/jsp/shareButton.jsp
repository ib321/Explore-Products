<!DOCTYPE html>
<html lang="en">
<head>

<style>
.share {
	background-color: #5cb85c;
	vertical-align: middle;
	margin-left: 5px;
	height: 16px;
	width: 16px;
}

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
	height: 80%;
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
	font-size: 15px;
}

.close {
	color: red;
	float: right;
	font-size: 28px;
}

.close:hover, .close:focus {
	color: black;
}

#copy {
	float: right;
}
</style>
</head>
<body>

	Click On Share Button And Share Your All Products.
	<button class="popup-btn">
		<img src="/commonimage/share.png" alt="Share" class="share"> Share
	</button>

	<div class="video-popup">
		<div class="popup-content">
			<span class="close">&times;</span>
			Share this link:
			<span class="glyphicon glyphicon-info-sign" title="Share this link with anyone.using this link they can view, browse and purchase all your added product"></span><br>
			<input type="text" id="link"
				value="http://localhost:8098/show-products?refShareId=${urlUserName}">
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
</body>
</html>

