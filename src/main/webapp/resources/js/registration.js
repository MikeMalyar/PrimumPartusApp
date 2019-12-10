function registration() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    var errorDiv = document.getElementById("errorMessage");

    $.ajax({
        url: "/user/register",
        type: "post",
        data: JSON.stringify({
            "email" : email,
            "password": password,
            "confirmPassword": confirmPassword,
            "role": "USER"
        }),
        contentType: "application/json"
    }).done(function (data) {
        if(data.success === true) {
            errorDiv.style.display = "none";

            window.location.href = "/login";
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

function redirectToLogin() {
    window.location.href = "/login";
}