import { Card, CardHeader, Typography } from '@mui/material';

export default function ProductCard(props: { name: string; price: number; description: string }) {
  return (
    <Card sx={{ width: 200, height: 200, padding: 2, boxShadow: 3, display: 'flex', flexDirection: 'column' }}>
        <CardHeader title={props.name} subheader={props.price}/>
        <Typography variant='body2' sx={{ mt: 1 }}>
            {props.description}
        </Typography>
    </Card>
  )
}
