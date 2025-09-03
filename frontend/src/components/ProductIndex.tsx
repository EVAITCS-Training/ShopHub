import { Box, Container, Typography } from '@mui/material'
import { useQuery } from '@tanstack/react-query'
import axios from 'axios'
import React from 'react'
import ProductCard from './ProductCard'

// State vs Props
// State: Local, private, controlled by the component
// Props: External, controlled by whatever renders the component, read-only or immutable
export default function ProductIndex() {
    const {data, error, isLoading } = useQuery({
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

    if (isLoading) return <div>Loading...</div>
    if (error) return <div>Error: {(error as Error).message}</div>
  return (
    <Container maxWidth="md">
        <Typography variant='h3'>
            Shophub Products
        </Typography>
        <Box sx={{ mt: 4, display: 'flex', flexDirection: 'row', flexWrap: 'wrap', gap: 2, rowGap: 2}}>
            {
                data?.map(product => {return <ProductCard key={product.id} name={product.name} price={product.price} description={product.description} />})
            }
        </Box>
    </Container>
  )
}
