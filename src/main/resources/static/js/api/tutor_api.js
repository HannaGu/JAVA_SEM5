
async function getAllTutors(token) {
    return await fetch("/user/getAllTutors", {
          method: "POST",
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
//TODO
async function getAllTutorsForAdmin(token) {
    return await fetch("/admin/getAllTutors", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}



async function getTutorById(id, token) {
    return await fetch(`/user/getTutorById/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function getTutorByIdForAdmin(id, token) {
    return await fetch(`/admin/getTutorById/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function userAddTutor() {
    let info;
    let token=localStorage.getItem("token");
    fetch('/getUserInfo',{
        headers:{'Authorization': `Bearer ${token}`}
    })
        .then(result=>{
            if(result.ok){
                return result.json()
            }
        }).then(data=> {
            info = {
            name: data.name,
            surname: data.surname,
            email: data.email,
            subject: document.getElementById("subject").value,
            cost: document.getElementById("cost").value
        };
        createTutor(info);
        alert("Ваше объявление успешно обработано");
        window.location.replace(window.location.origin);
    });

}

async function createTutor(data) {
    let token=localStorage.getItem("token");
    return await fetch("/user/createTutor",{
            method :'POST',
        headers:{'Authorization': `Bearer ${token}`,
            'Content-Type':'application/json'},
            body:JSON.stringify(data)
        });
}

async function deleteTutorById(data, token) {
    return await fetch("/user/deleteTutorById", {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}



/*
async function updateTutor() {
    let token=localStorage.getItem("token");
    return await fetch("/user/updateTutor", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify({

        })

    });
}*/
