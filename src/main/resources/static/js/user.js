let curUrl =  document.URL.split('/')
let user_id = curUrl[4]

async function genUserInfo() {
    let token = localStorage.getItem('token');
    let info = document.querySelector('.neededInfo');
    info.innerHTML='';
    let table = document.createElement('table');
    table.setAttribute('class', 'table');

    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    console.log(textUserData);

    let userRents = await getAllUserRentByUserId(textUserData['id'], token);

    for (let i = userRents.length - 1; i >= 0; i--) {
        if (i === userRents.length - 1) {
            let tr = document.createElement('tr');
            let th1 = document.createElement('th');
            th1.innerHTML = 'Name';
            let th2 = document.createElement('th');
            th2.innerHTML = 'Surname';
            let th3 = document.createElement('th');
            th3.innerHTML = 'Product name';
            let th4 = document.createElement('th');
            th4.innerHTML = 'Expiration date';
            let th5 = document.createElement('th');
            th5.innerHTML = 'Expired?';
            let th6 = document.createElement('th');
            th6.innerHTML = 'Delete';
            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tr.appendChild(th5);
            tr.appendChild(th6);
            table.appendChild(tr);
        }
        let tr = document.createElement('tr');
        console.log(userRents)
        for (let y = 0; y < 6; y++) {
            let th = document.createElement('th');
            switch (y) {
                case 0: {
                    th.innerHTML = userRents[i]['userName'];
                    break;
                }
                case 1: {
                    th.innerHTML = userRents[i]['userSurname'];
                    break;
                }
                case 2: {
                    th.innerHTML = userRents[i]['scooter']['name'];
                    break;
                }
                case 3: {
                    th.innerHTML = userRents[i]['scooter']['expirationDate'];
                    break;
                }
                case 4: {
                    th.innerHTML = userRents[i]['rent'];
                    break;
                }
                case 5: {
                    let genBut = buttonWithParams('Delete');

                    genBut.onclick = async () => {
                        async function deleteComp(userRentElement) {
                            let token = localStorage.getItem('token');
                            console.log(userRentElement['userName']);
                            await deleteScooterByNameU({name:userRentElement['userName']},token);
                            await genUserInfo();

                        }
                        await deleteComp(userRents[i]);
                    };
                    th.appendChild(genBut);

                    break;
                }
            }
            tr.appendChild(th);
        }

        table.appendChild(tr);
    }
    info.appendChild(table);

}


async function adminDeleteUser(id){
    let token = localStorage.getItem('token');
    await deleteContractByUserId(id, token);
    await deleteUserById(id, token);
    alert("Репетитор успешно удален");
    window.location.replace(window.location.origin);

}
async function deleteUserById(id, token) {
    if(confirm('Уверены, что хотите удалить выбранный объект?')){
        return await fetch(`/admin/deleteUserById/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'content-type': 'application/json'
            },
        });}
}

async function deleteContractByUserId(id, token) {
    return await fetch(`/admin/deleteContractByUserId/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }
    });
}


async function onUserUpdateLoad(){
    let token=localStorage.getItem("token");
    fetch(`/admin/getUser/${user_id}`,{
        headers:{'Authorization': `Bearer ${token}`}
    }).then(result=>{
        if(result.ok){
            return result.json()
        }
    }).then(data=>{
        userData.innerHTML=`
                <p>Имя:</p>
                <input id="userName" placeholder="Имя" value="${data.name}"> </input>
                <p>Фамилия:</p>
                <input id="userSurname" placeholder="Фамилия" value="${data.surname}"></input>
                <p>Почта: </p>
                <input id="userEmail" placeholder="Email" value="${data.email}"></input>`
    });

}
