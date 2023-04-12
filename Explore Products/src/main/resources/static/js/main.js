(function() {
	console.log("Hello World!");
})();

function copyUrl() {
	if (document.getElementById("productUrl").value.trim().length > 0) {
		document.getElementById("productLink").value = document.getElementById("productUrl").value;
	}
}

function copyImageUrl() {
	if (document.getElementById("productImageLink").value.trim().length > 0 && document.getElementById("productImageLink").value != "Did not worked!") {
		//document.getElementById("productImageSrc").value = document.getElementById("productImageLink").value;
		document.getElementById("image-link").value = document.getElementById("productImageLink").value;
	}
}

function getProductDescFromURL() {
	let url = document.getElementById("productUrl").value;
	let urlParts = url.split("/");
	let productDescIndex = urlParts.findIndex(part => part.includes(".")) + 1;
	let productDesc = urlParts[productDescIndex];
	document.getElementById("description").value = productDesc;
}

function showPopup(message) {
	if (message != null && message != "") {
		alert(message); // display a popup box with the message
		setTimeout(function() {
			window.close(); // close the popup window after 3 seconds
		}, 3000);
	}
}

async function downloadImage() {
	const imageSrc = document.getElementById('image-link').value;
	let fileName = 'default-file-name.jpg';
	/*if (imageSrc.lastIndexOf('/') + 1 < imageSrc.length) {
		fileName = imageSrc.substring(imageSrc.lastIndexOf('/') + 1);
		if (fileName.includes('?')) {
			fileName = fileName.substring(0, fileName.indexOf('?'));
		}
	}*/
	const image = await fetch(imageSrc);
	const imageBlog = await image.blob();
	const imageURL = URL.createObjectURL(imageBlog);
	const link = document.createElement('a');
	link.href = imageURL;
	link.download = fileName;
	document.body.appendChild(link);
	link.click();
	document.body.removeChild(link);
}

function filterProducts() {
	var searchText = document.getElementById('searchText').value.toLowerCase();
	// Get all product elements
	var products = document.querySelectorAll('.product');
	for (var i = 0; i < products.length; i++) {
		// Get the product name
		var productName = products[i].querySelector('.product-info h3').textContent.toLowerCase();
		if (productName.indexOf(searchText) > -1) {
			products[i].style.display = 'block';
		} else {
			products[i].style.display = 'none';
		}
	}
}