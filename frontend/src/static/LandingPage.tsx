import { Box, Card, Container, List, ListItem, Typography } from "@mui/material"

export default function LandingPage() {
  return (
    <Container maxWidth="md">
        <Card sx={{ mt: 4, p: 10, backgroundColor: '#5464f1ff' }}>
            <Typography variant="h3" component="h1" gutterBottom>
                Welcome to ShopHub
            </Typography>
            <Typography variant="body1" gutterBottom>
                Your one-stop solution for managing your shopping needs. Explore our features and get started today!
            </Typography>
        </Card>
        <Box sx={{ mt: 4, display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: 2, ":nth-last-of-type": { width: 100 } }}>
            <Card>
                <Typography variant="h5" sx={{ m: 2 }}>
                    Features
                </Typography>
                <List>
                    <ListItem>
                        Secure Money Transactions
                    </ListItem>
                    <ListItem>
                        Real-time Inventory Management
                    </ListItem>
                </List>
            </Card>
            <Card>
                <Typography variant="h5" sx={{ m: 2 }}>
                    Getting Started
                </Typography>
            </Card>
            <Card>
                <Typography variant="h5" sx={{ m: 2 }}>
                    Contact Us
                </Typography>
            </Card>
        </Box>
    </Container>
  )
}
