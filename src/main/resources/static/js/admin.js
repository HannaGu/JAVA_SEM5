async function isAdmin() {
    let user = await getUser();
    return user['role'] === 'ROLE_ADMIN';
}



