
async function onUserSubmit(event) {
    event.preventDefault();

    let nameInput = document.getElementById('user-name');
    let surnameInput = document.getElementById('user-surname');
    let emailInput = document.getElementById('user-email');
    let usernameInput = document.getElementById('user-username');
    let passwordInput = document.getElementById('user-password');

    let inputs = [nameInput, surnameInput, emailInput, usernameInput, passwordInput];

    let isValid = true;

    inputs.forEach(input => {
        if (input.value.trim() === '') {
            input.style.border = '1px solid red';
            isValid = false;
        } else {
            input.style.border = '1px solid green';
        }
    });

    if (!isValid) {
        alert('Zəhmət olmasa, bütün sahələri doldurun!');
        return;
    }

    let userObject = {
        firstName: nameInput.value,
        lastName: surnameInput.value,
        email: emailInput.value,
        username: usernameInput.value,
        password: passwordInput.value
    };

    let response = await fetch('http://localhost:8085/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userObject)
    });

    let data = response.json();

    if (response.status === 200) {
        alert('Qeydiyyat uğurla tamamlandı!');
        nameInput.value="";
        surnameInput.value="";
        emailInput.value="";
        usernameInput.value="";
        passwordInput.value="";
        window.location.href = "login.html";
    }
    else if (response.status === 400) {
        alert(data.message);
    }  else {
        alert('Bilinməyən xəta baş verdi.');
    }
}

