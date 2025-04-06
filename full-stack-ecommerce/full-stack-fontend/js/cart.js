document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})

const token = localStorage.getItem('token');

function loadOnTable() {
    fetch('http://localhost:8085/cart/getCards', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        let tableContent = "";
        let total=0;
        data.forEach(card => {
            total+=card.subTotal;
            tableContent += `
            <tr>
                <td>
                    <img src="${card.product.imgUrl}" style="width: 80px; height: 70px; object-fit: cover;">
                    ${card.product.model}
                </td>
                <td>${card.product.price}$</td>
                <td>
                    <input type="number" value="${card.quantity}" min="1" class="form-control quantity-input" data-cart-id="${card.id}" style="width: 50px;">
                </td>
                <td>${card.subTotal}$</td>
                <td><button type="button" class="btn btn-danger btn-sm delete-btn" data-id="${card.id}">Remove</button></td>
            </tr>
            `;
        });

        document.getElementById('tbody').innerHTML = tableContent;

        document.getElementById('sub-total').textContent = total + "$";
        document.getElementById('total').textContent = total + "$";

        document.querySelectorAll('.quantity-input').forEach(input => {
            input.addEventListener('change', (e) => {
                let newQuantity = e.target.value;
                let cartId = e.target.getAttribute('data-cart-id');
                fetch('http://localhost:8085/cart/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        id: cartId,
                        quantity: newQuantity
                    })
                })
                .then(msg => {
                    console.log(msg);
                    loadOnTable();
                })
            })
        });
       
    })
}

loadOnTable();

function deleteFromCart(){
    document.addEventListener('click',(e) => {
        if (e.target.classList.contains('delete-btn')) {
            let cartId = e.target.getAttribute('data-id');
            if (confirm("Are you sure you want to delete?")) {
                fetch(`http://localhost:8085/cart/${cartId}`, {
                    method: 'DELETE',
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                })
                .then(data => {
                    e.target.closest('tr').remove();
                    loadOnTable();
                })
            }
        }
    })
}

deleteFromCart();

document.getElementById('checkout').addEventListener('click',() => {

    fetch('http://localhost:8085/cart/getCards', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
       let cartIds = data.map(cart => cart.id);
           console.log(cartIds);

           localStorage.setItem('cartIds',JSON.stringify(cartIds));
           window.location.href = "checkout.html";
    })
    
})