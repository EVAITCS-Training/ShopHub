const shoppingCart = []
document.addEventListener("DOMContentLoaded", () => {
    const productNames = document.getElementsByTagName("h4");


    Array.from(productNames).forEach(productName => {
        productName.addEventListener("click", () => {
            const productContainer = productName.closest('ul');
            const productElements = productContainer.querySelectorAll('li p');
            const existingProduct = shoppingCart.find(el => el.name === productName.innerText);
            if (existingProduct) {
                existingProduct.quantity++;
                alert("Product quantity has increased " + existingProduct.name)
                return;
            }
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
            const confirmButton = document.createElement("button");
            confirmButton.onclick = sendShoppingCart();
            confirmButton.innerText = "Confirm Order";
            shoppingCartContainer.appendChild(confirmButton);
            console.log("Shopping cart opened.");
        }
    })
})


const sendShoppingCart = async () => {
    let orderDto = {
        orderRowDtoList: [],
        total: 0
    }
    for (let i = 0; i < shoppingCart.length; i++) {
        orderDto.total += shoppingCart[i].quantity * shoppingCart[i].price;
        orderDto.orderRowDtoList.push(
            {
                productName: shoppingCart[i].name,
                quantity: shoppingCart[i].quantity,
                price: shoppingCart[i].price
            }
        )
    }
    const response = await fetch("api/order/", {
        method: "POST",
        body: JSON.stringify(orderDto),
        headers: {
            "Content-Type": "application/json"
        }
    })

    if (response.status === 204) {
        alert("Your order has been successfully sent.");
        shoppingCart.clear();
    } else {
        alert("Something went wrong. Please try again later.");
    }
}



