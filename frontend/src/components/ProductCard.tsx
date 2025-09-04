import { Card, CardHeader, Typography } from '@mui/material';
import type { CartItem } from '../context/CartContext';

export default function ProductCard({item, addToCart}:{ item:CartItem; addToCart: (item: CartItem) => void }) {
  return (
    <Card sx={{ width: 200, height: 200, padding: 2, boxShadow: 3, display: 'flex', flexDirection: 'column' }}>
        <CardHeader title={item.name} subheader={item.price} onClick={() => addToCart(item)}/>
        <Typography variant='body2' sx={{ mt: 1 }}>
            {item.description}
        </Typography>
    </Card>
  )
}
