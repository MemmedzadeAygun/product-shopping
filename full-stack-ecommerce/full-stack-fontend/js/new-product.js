

const token = localStorage.getItem('token');
async function createProduct() {
    document.getElementById('add-product').addEventListener('submit', (e) => {
        e.preventDefault();

        const brand = document.getElementById('brand').value;
        const model = document.getElementById('model').value;
        const category = document.getElementById('category').value;
        const description = document.getElementById('description').value;
        const price = parseFloat(document.getElementById('price').value);
        const ratingInput = document.getElementById('rating').value;
        const img_url = document.getElementById('img-url').value;

        const rating = parseInt(ratingInput);
        if (isNaN(rating) || rating < 1 || rating > 5) {
            alert('Please enter a valid rating between 1 and 5.');
            return;
        }

        const product = {
            brand: brand,
            model: model,
            category: category,
            description: description,
            price: price,
            rating: rating,
            imgUrl: img_url
        };

        const productId = localStorage.getItem('productId');
        if (productId) {
            updateProduct(productId, product);
        }else{
            fetch('http://localhost:8085/products/create', {
               method: 'POST',
               headers: {
                   "Authorization": `Bearer ${token}`,
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify(product)
           })
           alert('Product added successfully!');
           document.getElementById('brand').value = "";
           document.getElementById('model').value = "";
           document.getElementById('category').value = "";
           document.getElementById('description').value = "";
           document.getElementById('price').value = "";
           document.getElementById('rating').value = "";
           document.getElementById('img-url').value = "";
           window.location.href = "userProducts.html";
        }
    });
}

createProduct();

function editProduct() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    if (productId) {
        localStorage.setItem("productId", productId);

        fetch(`http://localhost:8085/products/${productId}`, {
            method: 'GET',
            headers: {
                "Authorization": `Bearer ${token}`
            }
        })
        .then(response => response.json())
        .then(product => {
            document.getElementById('brand').value = product.brand;
            document.getElementById('model').value = product.model;
            document.getElementById('category').value = product.category;
            document.getElementById('description').value = product.description;
            document.getElementById('price').value = product.price;
            document.getElementById('rating').value = product.rating;
            document.getElementById('img-url').value = product.imgUrl;
        })
    }
}

editProduct();

function updateProduct(productId, product) {
    fetch('http://localhost:8085/products/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            id: productId,
            brand: product.brand,
            model: product.model,
            category: product.category,
            description: product.description,
            price: product.price,
            rating: product.rating,
            imgUrl: product.imgUrl
        })
    })
    .then(data => {
        console.log("Success:", data);
        alert('Product updated successfully!');
        localStorage.removeItem('productId');
        window.location.href = "userProducts.html";
    })
}

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})