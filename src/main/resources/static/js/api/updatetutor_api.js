let curUrl =  document.URL.split('/')
let tutor_id = curUrl[4]

async function onUpdateTutorLoad(){
    let token = localStorage.getItem('token');
    let data=await getTutorByIdForAdmin(tutor_id, token);
    let tutorData=document.getElementById('tutorData');
    tutorData.innerHTML=`
    <input type="hidden" id="id" value="${data.id}"></input>
    <p>Имя:</p>
    <input id="name" placeholder="Имя" value="${data.name}"> </input>
    <p>Фамилия:</p>
    <input id="surname" placeholder="Фамилия" value="${data.surname}"></input>
    <p>Почта: </p>
    <input id="email" placeholder="Email" value="${data.email}"></input>
     <p>Предмет: </p>
    <input id="subject" placeholder="Предмет" value="${data.subject}"></input>
     <p>Оплата за час (USD): </p>
    <input id="cost" type="number" placeholder="Цена" value="${data.cost}"></input>`
}

async function updateTutor(){
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let id=document.getElementById('id').value
    let name = document.getElementById('name').value;
    let surname = document.getElementById('surname').value;
    let cost = document.getElementById('cost').value;
    let email = document.getElementById('email').value;
    let subject = document.getElementById('subject').value;

    if (validateTutor()) {
        await updateTutor({
            id: id,
            name: name,
            description: description,
            cost: cost,
            expirationDate: expirationDate

        }, token);
        alert( 'Изменение прошло успешно');
    } else {
        alert( 'Невалидные данные');}
}

function validateTutor() {
    let nameL = document.getElementById('name').value.length;
    let surnameL = document.getElementById('surname').value.length;
    let emailL = document.getElementById('email').value.length;
    let subjectL = document.getElementById('subject').value.length;
    let cost = document.getElementById('cost').value;

    if (!(nameL >= 2 && nameL <= 20)) {
        return false;
    }
    if (!(surnameL >= 2 && nameL <= 20)) {
        return false;
    }
    if (!(emailL >= 2 && emailL <= 20)) {
        return false;
    }
    if (!(subjectL >= 1)) {
        return false;
    }
    if (cost === null) {
        return false;
    }
    return true;
}



async function updateTutor(data, token) {
    return await fetch("/admin/updateTutor", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}