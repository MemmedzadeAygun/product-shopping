const token = localStorage.getItem('token');

function getProduct(){
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    if (productId) {

        fetch(`http://localhost:8085/products/${productId}`,{
            method: 'GET',
            headers : {
                "Authorization": `Bearer ${token}`,
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            
           let productImg = document.querySelector('.product-image img');
           productImg.src = data.imgUrl;

           productModel = document.querySelector('.right-side h2');
           productModel.textContent = data.model;

           let stars = '';
           for (let index = 0; index < data.rating; index++) {
                stars+='<i class="fa-solid fa-star"></i>';
           }
           document.getElementById('rating').innerHTML = stars;

           document.getElementById('price').textContent = data.price + "$";
           document.getElementById('brand').textContent = data.brand;
        })
    }
}

getProduct();

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})

document.getElementById('add-to-cart').addEventListener('click',(e)=>{
    const urlParams=new URLSearchParams(window.location.search);
    const productId=urlParams.get('id');
    
    const cart={
        productId:productId
    }

    fetch(`http://localhost:8085/cart/add`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(cart)
    })
    .then(data => {
        alert("Product add to cart successfully");
    })
})