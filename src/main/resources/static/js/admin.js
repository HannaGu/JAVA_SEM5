async function isAdmin() {
    let user = await getUser();
    return user['role'] === 'ROLE_ADMIN';
}

async function deleteTutor(id){
    let token=localStorage.getItem('token');
    await deleteTutorById(id, token);
    await loadTutorsForAdmin();
}