(function() {
	console.log("Hello World!");
})();

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
//Above Javascript fucntions are not being used

function copyUrl() {
	if (document.getElementById("productUrl").value.trim().length > 0) {
		document.getElementById("productLink").value = document.getElementById("productUrl").value;
	}
}

function copyProductName() {
	if (document.getElementById("productNameFill").value.trim().length > 0) {
		document.getElementById("productName").value = document.getElementById("productNameFill").value;
	}
	else {
		alert('Fetched Product name field is empty');
	}
}

function copyProductDesc() {
	if (document.getElementById("productDescFill").value.trim().length > 0) {
		document.getElementById("description").value = document.getElementById("productDescFill").value;
	}
	else {
		alert('Fetched Product description field is empty');
	}
}

async function downloadImage() {
	const imageSrc = document.getElementById('productImageLink').value;
	if (imageSrc == "") {
		alert('Please fill out Product image link field to download image');
		return;
	}
	if (isValidImageURL(imageSrc)) {
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
	else {
		alert('Invalid image URL');
	}
}

function isValidImageURL(url) {
    const urlWithoutQueryParams = url.split('?')[0];
    return urlWithoutQueryParams.match(/\.(jpeg|jpg|gif|png|webp|bmp|ico|jfif|pjpeg|pjp)$/) != null;
}

// Above All javascript function is being used in product.jsp

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