async function getUserProfile(){
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
        console.log(data);
        document.getElementById('user_name').textContent = data.username;
    }
}

getUserProfile();

function logOut(){
    const token = localStorage.getItem('token');
    let profile = document.querySelector('.profile');
    let card_shopping = document.querySelector('.card-shopping');

    let logOutBtn = document.getElementById('logout-btn');
    if (token) {
        logOutBtn.addEventListener('click', (event) => {
            localStorage.removeItem("token");
            profile.style.display = "none";
            card_shopping.style.display = "none";
            event.target.textContent = "Log in";
            event.target.style.backgroundColor = "#fff";
            event.target.style.color = "black";
        })
    }else{
        profile.style.display = "none";
        card_shopping.style.display = "none";
        logOutBtn.target.textContent = "Log in";
        logOutBtn.target.style.backgroundColor = "#fff";
        logOutBtn.target.style.color = "black";
    }
}
logOut();

let profileImg=document.querySelector('.profile img');

profileImg.addEventListener('click',()=>{
    window.location.href="profile.html";
})

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})