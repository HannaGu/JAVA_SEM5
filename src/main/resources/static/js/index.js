async function generateSearch() {
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchTutor').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
     inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allTutors');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadTutors();
    }else{
        let listProject = await getAllTutors(token);
        let searchResults=document.getElementById('searchResults');
        //searchResults.innerHTML='Результаты поиска: ';
        list.innerHTML+=`<tr>
        <td>Репетитор</td>
        <td></td>
        <td>Предмет</td>
        <td>Почта</td>
        <td>Оплата в час (USD)</td>
        <td>Оценка</td>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult === listProject[i]['name']|| inputResult === listProject[i]['surname']|| inputResult === listProject[i]['subject']) {
               list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['subject']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i]['cost']}</td>
                <td>${listProject[i]['rate']}</td>
                <td><a href="/orderPage/+${listProject[i]['id']}">Заказать</a></td>
                </tr>
                `;}
        }
        if(list.innerHTML==='')
            searchResults.innerHTML+='ничего не найдено';

    }
}

async function generateSearchForAdmin() {
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchTutor').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
        inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allTutors');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadTutorsForAdmin();
    }else{
        let listProject = await getAllTutorsForAdmin(token);
        let searchResults=document.getElementById('searchResults');
        list.innerHTML+=`<tr>
        <td>Репетитор</td>
        <td></td>
        <td>Предмет</td>
        <td>Почта</td>
        <td>Оплата в час (USD)</td>
        <td>Оценка</td>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult.toUpperCase() === listProject[i]['name'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['surname'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['subject'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['name'].toUpperCase()+' '+listProject[i]['surname'].toUpperCase()){
                list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['subject']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i]['cost']}</td>
                <td>${listProject[i]['rate']}</td>
                <td><a href="/updatetutorAdmin/+${listProject[i]['id']}">Изменить</a></td>
                <td><button onclick="adminDeleteTutor(${listProject[i]['id']})">Удалить</button></td>
                </tr>`;}
        }
        if(list.innerHTML==='')
            searchResults.innerHTML+='ничего не найдено';

    }
}

async function onIndexLoad() {
    let token = localStorage.getItem('token');
    if (await isAuth()) {
        if (await isAdmin()) {
            await loadAdminIndex();
            await loadTutorsForAdmin();
            await loadUsersForAdmin();
        } else {
            await loadUserIndex();
            await loadTutors();
        }
    } else {
        //genLogReg(result);
    }
}

async function generateUserSearchForAdmin(){
    let token = localStorage.getItem('token');
    let inputResult_origin = document.getElementById('searchUserAdmin').value;
    let inputResult=inputResult_origin;
    if(inputResult_origin.length>0){
        inputResult = inputResult_origin[0].toUpperCase() + inputResult_origin.slice(1);}
    let list = document.getElementById('allUsers');
    list.innerHTML = '';
    if(inputResult.length===0){
        await loadUsersForAdmin();
    }else{
        let listProject = await getAllUsers();
        list.innerHTML+=`<tr>
        <td>Пользователь</td>
        <td></td>
        <td>Логин</td>
        <td>Почта</td>
        <td>Роль</td>
    </tr>`;
        for (let i = 0; i < listProject.length; i++) {
            if (inputResult.toUpperCase() === listProject[i]['name'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['surname'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['login'].toUpperCase()||
                inputResult.toUpperCase() === listProject[i]['name'].toUpperCase()+' '+listProject[i]['surname'].toUpperCase())
            {
                list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['login']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i].userRole.name}</td>
                <td><a href="/updateuserAdmin/+${listProject[i]['id']}">Изменить</a></td>
                <td><button onclick="adminDeleteUser(${listProject[i]['id']})">Удалить</button></td>
                </tr>`;}
        }
    }
}


async function loadUserIndex(){
    let hrefs=document.getElementById('refsForUser');
    let myProfileHref= await a('profile', 'Мой профиль');
    let myTutorsHrefs=await a('addtutor','Добавить объявление');
    let search=document.getElementById('search');
    let searchButton=await button( generateSearch,'Поиск');
    hrefs.appendChild(myProfileHref);
    hrefs.appendChild(await br());
    hrefs.appendChild(myTutorsHrefs);
    hrefs.appendChild(await br());
    search.appendChild(searchButton);
}

async function loadAdminIndex(){
    let search=document.getElementById('search');
    let searchUser=document.getElementById('searchUser');
    searchUser.innerHTML=`<input type="text" id="searchUserAdmin" placeholder="Имя/фамилия/логин">`;
    let tutorContainer=document.getElementById('allTutorsContainer');
    let userContainer=document.getElementById('allUsersContainer');
    let searchButton=await button( generateSearchForAdmin,'Поиск');
    let searchUserButton=await button( generateUserSearchForAdmin,'Поиск');
    let createHref=await a( 'addtutoradmin','Добавить репетитора');
    let createHrefUser=await a( 'adduseradmin','Добавить пользователя');
    search.appendChild(searchButton);
    searchUser.appendChild(searchUserButton);
    tutorContainer.appendChild(createHref);
    userContainer.appendChild(createHrefUser);

}


async function loadTutors(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allTutors');
    list.innerHTML = '';
    let listProject = await getAllTutors(token);
    list.innerHTML+=`<tr>
        <td>Репетитор</td>
        <td></td>
        <td>Предмет</td>
        <td>Почта</td>
        <td>Оплата в час (USD)</td>
        <td>Оценка</td>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['subject']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i]['cost']}</td>
                <td>${listProject[i]['rate']}</td>
                <td><a href="/orderPage/+${listProject[i]['id']}">Заказать</a></td>
                 </tr>
    `;}
}


async function loadTutorsForAdmin(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allTutors');
    list.innerHTML = '';

    let listProject = await getAllTutorsForAdmin(token);
    list.innerHTML+=`<tr>
        <td>Репетитор</td>
        <td></td>
        <td>Предмет</td>
        <td>Почта</td>
        <td>Оплата в час (USD)</td>
        <td>Оценка</td>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['subject']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i]['cost']}</td>
                <td>${listProject[i]['rate']}</td>
                <td><a href="/updatetutorAdmin/+${listProject[i]['id']}">Изменить</a></td>
                <td><button onclick="adminDeleteTutor(${listProject[i]['id']})">Удалить</button></td>
                </tr>
    `;}
}


async function loadUsersForAdmin(){
    let token = localStorage.getItem('token');
    let list = document.getElementById('allUsers');
    list.innerHTML = '';
    let listProject = await getAllUsers();
    list.innerHTML+=`<tr>
        <td>Пользователь</td>
        <td></td>
        <td>Логин</td>
        <td>Почта</td>
        <td>Роль</td>
    </tr>`;
    for (let i = 0; i < listProject.length; i++) {
        list.innerHTML+=`<tr>
                <td>${listProject[i]['name']}</td>
                <td>${listProject[i]['surname']}</td>
                <td>${listProject[i]['login']}</td>
                <td>${listProject[i]['email']}</td>
                <td>${listProject[i].userRole.name}</td>
                <td><button onclick='adminDeleteUser(${listProject[i]['id']})'>Удалить</button></td>
                </tr>
    `;}
}





function rateChangeOption(){
    let rateSelect = document.getElementById('rateFilter');
    let selectedOption = rateSelect.options[rateSelect.selectedIndex];
     if(selectedOption.label==='Сначала низкие'){
        let sortedRows = Array.from(allTutors.rows)
            .slice(1)
            .sort((rowA, rowB) => rowA.cells[5].innerHTML > rowB.cells[5].innerHTML ? 1 : -1);

         allTutors.tBodies[0].append(sortedRows);
    }
}