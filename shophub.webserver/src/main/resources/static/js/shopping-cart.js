document.addEventListener("DOMContentLoaded", () => {
    const productNames = document.getElementsByTagName("h4");

    const shoppingCart = []

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
})

