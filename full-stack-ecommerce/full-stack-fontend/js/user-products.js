const addProductBtn = document.querySelector('.sub-main button');

addProductBtn.addEventListener('click', () => {
    window.location.href = "newProduct.html";
})

const token = localStorage.getItem('token');

function loadOnProducts() {
    fetch('http://localhost:8085/products/getAllByUserId', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            let tableContent = "";
            data.forEach(element => {
                tableContent += `
                <tr><td>${element.id}</td>
                <td>${element.brand}</td>
                <td>${element.model}</td>
                <td>${element.category}</td>
                <td>
                    <img src="${element.imgUrl}" alt="${element.model}" style="width: 100px; height: 80px; object-fit: cover;">
                </td>
                <td>${element.price} $</td>
                <td>${element.rating}/5</td>
                <td>
                    <button type="button" class="btn btn-primary edit-btn" data-id="${element.id}">Edit</button>
                    <button type="button" class="btn btn-danger delete-btn" data-id=${element.id}>Delete</button>
                </td></tr>
            `
            });
            document.getElementById('products-tbody').innerHTML += tableContent;
        })
}

loadOnProducts();

document.addEventListener('click', (e) => {
    if (e.target.classList.contains('edit-btn')) {
        const productId = e.target.getAttribute('data-id');
        console.log(productId);
        
        window.location.href = `newProduct.html?id=${productId}`;
    }
});

function deleteProduct() {
    document.addEventListener('click', (e) => {
        if (e.target.classList.contains('delete-btn')) {
            const productId = e.target.getAttribute('data-id');
            if (confirm("Silmek istediyinize eminsiniz?")) {
                fetch(`http://localhost:8085/products/${productId}`, {
                    method: 'DELETE',
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                })
                .then(data => {
                    alert("Product deleted successfully");
                    e.target.closest('tr').remove();
                })
            }
        }
    });
}

deleteProduct();

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})