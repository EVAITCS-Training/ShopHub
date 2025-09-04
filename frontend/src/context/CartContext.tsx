import { useState, useContext, createContext } from "react";

export type CartItem = {
    id?: number | undefined;
    name: string;
    price: number;
    description: string;
    quantity: number;
}

type CartContextType = {
    cartItems: CartItem[];
    addToCart: (item: CartItem) => void;
    removeFromCart: (id: number | undefined) => void;
    clearCart: () => void;
}

export const CartContext = createContext<CartContextType | undefined>(undefined);

export const CartProvider = ({ children }: { children: React.ReactNode }) => {
    const [cartItems, setCartItems] = useState<CartItem[]>([]);

    const addToCart = (item: CartItem) => {
        console.log("Adding to cart:", item);
        setCartItems((prevItems) => {
            const existingItem = prevItems.find((i) => i.name === item.name);
            if (existingItem) {
                return prevItems.map((i) =>
                    i.name === item.name ? { ...i, quantity: i.quantity + item.quantity } : i
                );
            }
            console.log("Current cart items: ", prevItems);
            return [...prevItems, item];
        });
    };

    const removeFromCart = (name: string) => {
        setCartItems((prevItems) => prevItems.filter((item) => item.name !== name));
    };
    const clearCart = () => {
        setCartItems([]);
    };

    return (
        <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
            {children}
        </CartContext.Provider>
    );
}

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) throw new Error('useCart must be used within a CartProvider');
  return context;
};