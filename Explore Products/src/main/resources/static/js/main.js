(function() {
	console.log("Hello World!");
})();

function copyUrl() {
	if (document.getElementById("productUrl").value.trim().length > 0) {
		document.getElementById("productLink").value = document.getElementById("productUrl").value;
	}
}

function copyImageUrl() {
	if (document.getElementById("productImageLink").value.trim().length > 0) {
		document.getElementById("productImageSrc").value = document.getElementById("productImageLink").value;
	}
}

function getProductDescFromURL() {
	let url = document.getElementById("productUrl").value;
	let urlParts = url.split("/");
	let productDescIndex = urlParts.findIndex(part => part.includes(".com")) + 1;
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