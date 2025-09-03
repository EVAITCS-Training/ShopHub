import { Card, CardHeader, Typography } from '@mui/material';
import type { CartItem } from '../context/CartContext';

export default function ProductCard(props: { item:CartItem; addToCart: (item: CartItem) => void }) {
  return (
    <Card sx={{ width: 200, height: 200, padding: 2, boxShadow: 3, display: 'flex', flexDirection: 'column' }}>
        <CardHeader title={props.item.name} subheader={props.item.price} onClick={() => props.addToCart(props.item)}/>
        <Typography variant='body2' sx={{ mt: 1 }}>
            {props.item.description}
        </Typography>
    </Card>
  )
}
