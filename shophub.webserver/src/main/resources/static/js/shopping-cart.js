const shoppingCart = []
document.addEventListener("DOMContentLoaded", () => {
    const productNames = document.getElementsByTagName("h4");


    Array.from(productNames).forEach(productName => {
        productName.addEventListener("click", () => {
            const productContainer = productName.closest('ul');
            const productElements = productContainer.querySelectorAll('li p');
            const product = {
                name: productName.innerText,
                description: productElements[0].innerText, // First p tag (description)
                price: productElements[1].innerText,       // Second p tag (price)
                quantity: 1
            };
            shoppingCart.push(product);
            console.log("Product added to cart:", product);
            alert("Product added to cart: " + product.name);
        })
    })



    const shoppingCartButton = document.getElementById("shopping-cart-button");
    const shoppingCartContainer = document.getElementById("shopping-cart-container");
    shoppingCartButton.addEventListener("click", () => {
        if (shoppingCartContainer.classList.contains("open-cart")) {
            shoppingCartContainer.classList.remove("open-cart");
            shoppingCartContainer.classList.add("close-cart");
            console.log("Shopping cart closed.");
        } else {
            shoppingCartContainer.classList.remove("close-cart");
            shoppingCartContainer.classList.add("open-cart");
            shoppingCartContainer.innerHTML = ""; // Clear previous content
            shoppingCart.forEach(product => {
                const productElement = document.createElement("div");
                productElement.classList.add("cart-item");
                productElement.innerHTML = `
                    <h4>${product.name}</h4>
                    <p>${product.price}</p>
                    <p>Quantity: ${product.quantity}</p>
                `;
                shoppingCartContainer.appendChild(productElement);
            })
            console.log("Shopping cart opened.");
        }
    })
})



