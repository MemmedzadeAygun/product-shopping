const token = localStorage.getItem('token');
function getAllCategory() {
    fetch('http://localhost:8085/products/getAllProducts', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {

            const uniqueCategory = new Set();
            data.forEach(element => {
                uniqueCategory.add(element.category);
            });
            console.log(uniqueCategory);


            uniqueCategory.forEach(category => {
                let p = document.createElement('p');
                p.textContent = category;
                p.style.cursor = "pointer";

                p.addEventListener('click', () => {
                    filterProductsByCategory(category);
                })

                document.querySelector('.shop').appendChild(p);
            });
        })
}

getAllCategory();

let allProducts = [];

function getAllProducts() {
    fetch('http://localhost:8085/products/getAllProducts', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            allProducts = data;
            renderProducts(allProducts);
            console.log(allProducts);

        });
}

function renderProducts(products) {
    const productsContainer = document.querySelector('.products');
    productsContainer.innerHTML = '';

    products.forEach(element => {
        const product = document.createElement('div');
        product.classList.add('product');
        product.setAttribute('data-id', element.id);


        const product_img = document.createElement('div');
        product_img.classList.add('product-img');

        const img = document.createElement('img');
        img.src = element.imgUrl;

        const product_detail = document.createElement('div');
        product_detail.classList.add('product-detail');

        const model = document.createElement('p');
        model.classList.add('model');
        model.textContent = element.model;

        const price = document.createElement('p');
        price.classList.add('price');
        price.textContent = element.price + '$';

        const rate = document.createElement('p');
        let stars = '';
        for (let i = 0; i < element.rating; i++) {
            stars += '<i class="fa-solid fa-star"></i>';
        }
        rate.innerHTML = stars;

        const btn = document.createElement('button');
        btn.textContent = "Add to Cart";
        btn.setAttribute('data-id', element.id);

        btn.addEventListener('click',(e)=>{
            e.stopPropagation();
            let productId=btn.getAttribute('data-id')
            console.log(productId);
            addToCart(productId);
        })

        product.appendChild(product_img);
        product_img.appendChild(img);
        product.appendChild(product_detail);
        product_detail.appendChild(model);
        product_detail.appendChild(price);
        product_detail.appendChild(rate);
        product.appendChild(btn);
        productsContainer.appendChild(product);
    });
}

getAllProducts();

function filterProductsByCategory(category) {
    fetch('http://localhost:8085/products/getAllProducts', {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            const products = document.querySelector('.products');

            products.innerHTML = '';

            const filtered = data.filter(product => product.category === category);

            renderProducts(filtered);
        })
}

document.querySelectorAll('.rate p').forEach((rateP, index) => {
    rateP.addEventListener('click', () => {
        const starCount = 5 - index;
        const filtered = allProducts.filter(product => product.rating === starCount);
        renderProducts(filtered);
    });
});

document.querySelector('.right-side button').addEventListener('click', () => {
    getAllProducts();
});

document.getElementById('sortSelect').addEventListener('change', () => {

    function sortProducts() {
        const sortValue = document.getElementById('sortSelect').value;
        let sortedProducts = [...allProducts];

        if (sortValue === 'priceAsc') {
            sortedProducts.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
        } else if (sortValue === 'priceDesc') {
            sortedProducts.sort((a, b) => parseFloat(b.price) - parseFloat(a.price));
        }

        renderProducts(sortedProducts);
    }

    sortProducts();
});

function searchProducts() {
    let API_URL = "http://localhost:8085/products/searchProduct";
    let query = document.getElementById('searchInput').value;

    fetch(`${API_URL}?query=${query}`, {
        method: 'GET',
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(data => {
            renderProducts(data);
        })
}

searchProducts();