const token = localStorage.getItem('token');

document.getElementById('send').addEventListener('click',() => {

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const message = document.getElementById('message').value;

    const contact = {
        name:name,
        email: email,
        phone: phone,
        message: message
    }

    fetch("http://localhost:8085/contact", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(contact)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        document.getElementById('name').value = "";
        document.getElementById('email').value = "";
        document.getElementById('phone').value = "";
        document.getElementById('message').value = "";
    })
})