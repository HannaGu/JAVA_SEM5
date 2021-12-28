function validateLoginPass(login, password , email) {
    if (!(login.length >= 4 && login.length <= 20)) {
        return "Длина логина от 4 до 20 символов";
    }
    if (!(password.length >= 4 && password.length <=20)) {
        return "Длина пароля от 4 до 20 символов";
    }
    if (!email.length >= 4) {
        return "Длина почты не менее 4 символов";
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
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let result = await validateLoginPass(login, password , email);
    if (result === true) {
        let data = {login: login, password: password,email:email, name:name, surname:surname};
        let res = await regUser(data);
        if (res.ok) {
            window.location.replace(window.location.origin);
        } else {
            mes.innerHTML = "Пользователь с таким логином уже существует";
        }

    } else {
        mes.innerHTML = result;
    }
}