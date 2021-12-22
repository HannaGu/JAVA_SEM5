function validateLoginPass(login, password , email) {
    if (!(login.length >= 5 && login.length <= 20)) {
        return "Login should from 5 to 20 characters length";
    }
    if (!(password.length >= 5 && password.length <= 20)) {
        return "Password should from 5 to 20 characters length";
    }
    if (!email.length >= 5) {
        return "Email should from 5 characters length";
    }

    return true;
}

async function regUser(data) {
    return await fetch("/register", {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

async function reg() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let mes = document.getElementById("message");
    let result = validateLoginPass(login, password , email);
    if (result === true) {
        let data = {login: login, password: password,email:email};
        let res = await regUser(data);
        if (res.ok) {
            window.location.replace(window.location.origin);
        } else {
            mes.innerHTML = "this user already exist";
        }

    } else {
        mes.innerHTML = result;
    }
}