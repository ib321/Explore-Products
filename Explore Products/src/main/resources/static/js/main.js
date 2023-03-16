(function(){
    console.log("Hello World!");
})();


function showPopup(message) {
            if (message != null && message != "") {
                alert(message); // display a popup box with the message
                setTimeout(function() {
                    window.close(); // close the popup window after 3 seconds
                }, 3000);
            }
        }