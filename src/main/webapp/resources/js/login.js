function login() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    var errorDiv = document.getElementById("errorMessage");

    $.ajax({
        url: "/user/login",
        type: "post",
        data: JSON.stringify({
            "email" : email,
            "password": password
        }),
        contentType: "application/json"
    }).done(function (data) {
        if(data.success === true) {
            errorDiv.style.display = "none";

            window.location.href = "/";
        } else {
            var errorMessage = "";
            if(data.message === null || data.message === undefined) {
                errorMessage = "Unexpected error just occurred.";
            } else {
                errorMessage = data.message;
            }

            errorDiv.innerText = errorMessage;
            errorDiv.style.display = "";
        }
    }).fail(function (data) {
        var errorMessage = "";
        var result = data.responseJSON;
        if(result === undefined || result.message === null || result.message === undefined) {
            errorMessage = "Unexpected error just occurred.";
        } else {
            errorMessage = result.message;
        }

        errorDiv.innerText = errorMessage;
        errorDiv.style.display = "";
    });
}

function redirectToRegistration() {
    window.location.href = "/registration";
}