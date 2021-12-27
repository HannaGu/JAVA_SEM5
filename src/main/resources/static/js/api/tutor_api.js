
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




async function adminAddTutor() {
    let token=localStorage.getItem("token");

     let info = {
            name: document.getElementById("name").value,
            surname: document.getElementById("surname").value,
            email: document.getElementById("email").value,
            subject: document.getElementById("subject").value,
            cost: document.getElementById("cost").value,
            rate: 1
        };
        await createTutorAdmin(info);
        alert("Репетитор успешно добавлен");
        window.location.replace(window.location.origin);


}

async function createTutorAdmin(data) {
    let token=localStorage.getItem("token");
    return await fetch("/admin/createTutor",{
        method :'POST',
        headers:{'Authorization': `Bearer ${token}`,
            'Content-Type':'application/json'},
        body:JSON.stringify(data)
    });
}

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
}


async function adminDeleteTutor(id){
    let token = localStorage.getItem('token');
    await deleteContractByTutorId(id, token);
    await deleteTutorById(id, token);
    alert("Репетитор успешно удален");
    window.location.replace(window.location.origin);

}

async function deleteTutorById(id, token) {
    if(confirm('Уверены, что хотите удалить выбранный объект?')){
    return await fetch(`/admin/deleteTutorById/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
    });}
}

async function deleteContractByTutorId(id, token) {
    return await fetch(`/admin/deleteContractByTutorId/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }
    });
}