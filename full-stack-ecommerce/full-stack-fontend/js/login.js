async function onLogin(event){
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const requestBody = {
        username: username,
        password: password
    }

    let response = await fetch('http://localhost:8085/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    });

    const data = await response.json();
    if (response.status === 200) {
        if (data.token) {
            localStorage.setItem("token",data.token);
        }
        alert('Login successfully');
        document.getElementById('username').value = "";
        document.getElementById('password').value = "";
        window.location.href = 'index.html';
    }
    else if (response.status === 401) {
        alert('Username or password incorrect');
    }
}

