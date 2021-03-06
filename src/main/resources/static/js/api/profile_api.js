

/*async function onProfileLoad(){
    await getUserByToken( token).then(data=> {
        userName.innerHTML = `Имя: ${data.name}`
        userSurname.innerHTML =`Фамилия: ${data.surname}`
        userEmail.innerHTML=`Email: ${data.email}`;
    })
}*/

async function onProfileLoad(){
    let token=localStorage.getItem("token");
    fetch('/getUserInfo',{
        headers:{'Authorization': `Bearer ${token}`}
        }).then(result=>{
            if(result.ok){
                return result.json()
            }
        }).then(data=>{
        userInfo.innerHTML=`
                Имя:<br>
                <input id="userName" placeholder="Имя" value="${data.name}"> <br>
                Фамилия:<br>
                <input id="userSurname" placeholder="Фамилия" value="${data.surname}"><br>
                Почта:<br>
                <input id="userEmail" placeholder="Email" value="${data.email}"><br>
                <input type="hidden" id="userId" placeholder="Email" value="${data.id}"></input>
                <button onclick = 'updateProfile()'>Обновить</button>`
        });
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    await getContractsByUserId(textUserData['id'],token);
 }

 function getContractsByUserId(id, token) {
    fetch(`/user/getAllByUserId/${id}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(result=>{
        if(result.ok){
            return result.json()
        }
    }).then(data=> {
    for(let i=0; i<data.length; i++){
         myTutorsTable.innerHTML+=`
         <tr>
         <td>${data[i].tutor.name}</td> 
         <td>${data[i].tutor.surname}</td> 
         <td>${data[i].tutor.email}</td>
         <td>${data[i].tutor.subject}</td>
         <td>${data[i].hours}</td>
         <td>${data[i].total}</td>
         <td>${data[i].tutor.rate}</td>
          <td>
          <select name="rate" id="sel${i}">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          </select>
         </td>
         <td><button onclick="updateRate(${data[i].tutor.id}, ${data[i].tutor.rate}, document.getElementById('sel${i}').value)">
         Отправить отзыв</button></td>
         </tr>`
    }
    });
}

async function updateRate(id, currentRate, userRate){
    let token=localStorage.getItem("token");
    let rate=await countRate(currentRate, userRate);
    await updateTutorRate( {
        id: id,
        rate: rate,}
        ,token);
    alert("Ваш отзыв успешно отправлен");
}

function countRate(curRate, userRate){
    let rate;
    if(curRate<=1)
        rate=userRate;
    else{
        rate=(parseFloat(curRate)+parseFloat(userRate))/2.0;
    }
    return rate;
}


async function updateTutorRate(data, token) {
    return await fetch("/user/updateTutorRate", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}

async function updateProfile(){
    let token=localStorage.getItem('token');
    let userId=document.getElementById('userId').value;
    let userName=document.getElementById('userName').value;
    let userSurname=document.getElementById('userSurname').value;
    let userEmail=document.getElementById('userEmail').value;
    let info={
        id:userId,
        name:userName,
        surname:userSurname,
        email:userEmail
    }
    if(userName.length===0||userSurname.length===0|| userEmail.length===0){
        document.getElementById('error').innerHTML='Заполните все поля.'}
    else{
        await updateUser(info,token);
        alert("Изменение сохранено");
        window.location.replace(window.location.origin+'/profile');
    }
}


async function updateUser(data, token) {
    return await fetch("/user/updateUserById", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}


async function profileValidation(name, surname, email){
    if(name===''||surname===''||email==='')
        return "Заполните все поля";
else{return true;}}