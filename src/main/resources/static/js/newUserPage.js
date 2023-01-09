const form_new = document.getElementById('formForNewUser');
const role_new = document.querySelector('#roles').selectedOptions;


async function newUser() {
    form_new.addEventListener('submit', addNewUser);


    async function addNewUser(event) {
        event.preventDefault();
        const urlNew = 'api/admin/newAddUser';
        let listOfRole = [];
        for (let i = 0; i < role_new.length; i++) {
            listOfRole.push("ROLE_" + role_new[i].value);
            // alert(listOfRole);
        }
        let method = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: form_new.username.value,
                login: form_new.username.value,
                userlastname: form_new.userlastname.value,
                age: form_new.age.value,
                email: form_new.email.value,
                pass: form_new.pass.value,
                roles: listOfRole
            })
        }
        await fetch(urlNew,method).then(() => {
            form_new.reset();
            $('[href="#tab1"]').click();
            getAdminPage();
        });
    }
}