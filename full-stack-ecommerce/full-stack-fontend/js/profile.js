async function getUserDetails(){
    const token = localStorage.getItem('token');

    if (!token) {
        alert('Token tapilmadi');
        return;
    }

    let response = await fetch('http://localhost:8085/auth/profile', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })

    if (response.ok) {
        const data = await response.json();
        // console.log(data);
        document.getElementById('user-username').textContent = data.username;
        document.getElementById('user-name').textContent = data.firstName;
        document.getElementById('user-surname').textContent = data.lastName;
        document.getElementById('user-email').textContent = data.email;
    }
}

getUserDetails();

const userProductSaleBtn=document.querySelector('.sub-main button');

userProductSaleBtn.addEventListener('click',()=>{
    window.location.href="userProducts.html";
})