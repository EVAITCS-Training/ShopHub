import { Box, Button, Container, Typography } from '@mui/material'
import { useQuery } from '@tanstack/react-query'
import axios from 'axios'
import ProductCard from './ProductCard'
import { useCart } from '../context/CartContext'
import { useState } from 'react'
import AddProduct from './AddProduct'

interface Product {
    id: number | undefined
    name: string
    price: number
    description: string
}

// State vs Props
// State: Local, private, controlled by the component
// Props: External, controlled by whatever renders the component, read-only or immutable
export default function ProductIndex() {
    const [openAddProduct, setOpenAddProduct] = useState(false);
    const {addToCart , cartItems} = useCart();
    const {data, error, isLoading } = useQuery<Product[]>({
        queryKey: ['products'],
        queryFn: async () => {
            const res = await axios.get(import.meta.env.VITE_API_URL + 'product/')
            if ( res.status !== 200 ) {
                throw new Error('Error fetching products')
            }
            console.log(res.data)
            return res.data
        }
    })

    const handleClose = () => {
        setOpenAddProduct(false)
    }

    if (isLoading) return <div>Loading...</div>
    if (error) return <div>Error: {(error as Error).message}</div>
  return (
    <Container maxWidth="md">
        <Typography variant='h3'>
            Shophub Products
        </Typography>
        { sessionStorage.getItem("jwt") ?
            <Button onClick={() =>setOpenAddProduct(true)}>
            Add Product
        </Button> : null}
        <Box sx={{ mt: 4, display: 'flex', flexDirection: 'row', flexWrap: 'wrap', gap: 2, rowGap: 2}}>
            {
                data?.map(product => {return <ProductCard key={product.id} item={product} addToCart={() =>{
                    addToCart({ ...product, quantity: 1 })
                    console.log("Cart items after adding: ", cartItems);
                } }/>})
            }
        </Box>
        <AddProduct open={openAddProduct} handleClose={handleClose} />
    </Container>
  )
}
