import { useState } from "react";
import { ListItem, List, Box, Button, Typography, Divider } from "@mui/material";
import { Link } from "react-router-dom";
import {useCart} from "../context/CartContext"
import axios from "axios";

export type CartItem = {
  id: number | undefined;
  name: string;
  price: number;
  description: string;
  quantity: number;
};

interface OrderRowDto {
  productName: string,
  price: number,
  quantity: number
}


function NavBar() {
  const [cartOpen, setCartOpen] = useState(false);

  
  const cart = useCart()

  const processOrder = async () => {
    const orderRowDtoList: OrderRowDto[] = []
    let total = 0
    cart.cartItems.forEach(item => {
      orderRowDtoList.push({
        productName: item.name,
        price: item.price,
        quantity: item.quantity
      })
      total = total + (item.price * item.quantity);
    })
    const orderDto = {
      orderRowDtoList: orderRowDtoList,
      total: total
    }
    await axios.post(import.meta.env.VITE_API_URL+ "order/", orderDto,
      {
        headers: {
          "Content-Type": "application/json"
        }
      }
    ).then(res => {
      if (res.status !== 204) {
        throw new Error("Error occurred while processing order")
      }
      cart.clearCart();
    })
  }

  return (
    <Box sx={{ position: "relative" }}>
      {/* Navbar */}
      <Box
        sx={{
          width: "100%",
          height: "60px",
          bgcolor: "primary.main",
          boxShadow: 3,
          display: "flex",
          justifyContent: "center",
        }}
      >
        <Box
          sx={{
            width: "100%",
            maxWidth: "1200px",
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            px: 3,
          }}
        >
          {/* Logo */}
          <Box sx={{ fontSize: "1.5rem", fontWeight: "bold", color: "#fff" }}>
            ShopHub
          </Box>

          {/* Links + Cart */}
          <List sx={{ display: "flex", flexDirection: "row", gap: 4, m: 0, p: 0 }}>
            <ListItem sx={{ width: "auto", p: 0 }}>
              <Link
                to="/"
                style={{ color: "#7FFF00", textDecoration: "none", fontWeight: "500" }}
              >
                Home
              </Link>
            </ListItem>
            <ListItem sx={{ width: "auto", p: 0 }}>
              <Link
                to="/products/"
                style={{ color: "#7FFF00", textDecoration: "none", fontWeight: "500" }}
              >
                Store
              </Link>
            </ListItem>

            { sessionStorage.getItem("jwt") ? 
            <ListItem sx={{ width: "auto", p: 0 }}>
              <Button
                onClick={() => setCartOpen(!cartOpen)}
                sx={{ color: "#7FFF00", textTransform: "none", fontWeight: "500" }}
              >
                Cart ({cart.cartItems.length})
              </Button>
            </ListItem> : null }
          </List>
        </Box>
      </Box>

      {/* Custom cart dropdown */}
      {cartOpen && (
        <Box
          sx={{
            position: "absolute",
            top: "60px", // below navbar
            right: 24, // match navbar padding
            width: 300,
            bgcolor: "background.paper",
            boxShadow: 3,
            borderRadius: 2,
            p: 2,
            zIndex: 1000,
          }}
        >
          <Typography variant="h6">Your Cart</Typography>
          <Divider sx={{ my: 1 }} />

          {cart.cartItems.length > 0 ? (
            cart.cartItems.map((item) => (
              <Box
                key={item.id}
                sx={{
                  py: 1,
                  borderBottom: "1px solid #ddd",
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                }}
              >
                <Box>
                  <Typography variant="subtitle1">{item.name}</Typography>
                  <Typography variant="body2" color="text.secondary">
                    {item.quantity} × ${item.price}
                  </Typography>
                </Box>
                <Typography variant="body1">
                  ${(item.quantity * item.price).toFixed(2)}
                </Typography>
              </Box>
            ))
          ) : (
            <Typography variant="body2">Cart is empty</Typography>
          )}
          <Button
          variant="outlined"
          color="primary"
          fullWidth
          sx={{ mt: 2 }}
          onClick={() => processOrder()}
          >
            Process Order
          </Button>
          <Button
            variant="outlined"
            color="error"
            onClick={() => setCartOpen(false)}
            fullWidth
            sx={{ mt: 2 }}
          >
            Close
          </Button>
        </Box>
      )}
    </Box>
  );
}

export default NavBar;
