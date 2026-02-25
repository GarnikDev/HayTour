import React from 'react';
import { Box, Typography, Button, Container, Grid, Card, CardMedia, CardContent, CardActionArea } from '@mui/material';
import DirectionsWalkIcon from '@mui/icons-material/DirectionsWalk';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import DirectionsBikeIcon from '@mui/icons-material/DirectionsBike';
import { useNavigate } from 'react-router-dom';

const tourTypes = [
    { title: 'Walking', icon: <DirectionsWalkIcon />, path: '/tours/walk', image: 'https://images.unsplash.com/photo-1551632811-561732d1e306?q=80&w=800', desc: 'Step into history with our local guides.' },
    { title: 'Bicycle', icon: <DirectionsBikeIcon />, path: '/tours/bike', image: 'https://images.unsplash.com/photo-1471506480208-91b3a4cc78be?q=80&w=800', desc: 'Eco-friendly paths and scenic routes.' },
    { title: 'Bus', icon: <DirectionsBusIcon />, path: '/tours/bus', image: 'https://images.unsplash.com/photo-1544620347-c4fd4a3d5957?q=80&w=800', desc: 'Comfortable journeys to distant wonders.' },
];

export default function Home() {
    const navigate = useNavigate();
    return (
        <Box sx={{ width: '100%' }}>
            <Box sx={{
                height: '70vh', width: '100%',
                backgroundImage: 'linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url("https://images.unsplash.com/photo-1523531294919-4bcd7c65e216?q=80&w=1470")',
                backgroundSize: 'cover', backgroundPosition: 'center',
                display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', color: 'white', textAlign: 'center'
            }}>
                <Typography variant="h2" sx={{ fontWeight: 800, mb: 2 }}>EXPLORE ARMENIA</Typography>
                <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', justifyContent: 'center' }}>
                    {tourTypes.map((t) => (
                        <Button key={t.title} variant="contained" startIcon={t.icon} onClick={() => navigate(t.path)}
                                sx={{ bgcolor: 'white', color: 'black', borderRadius: 'var(--border-radius)', '&:hover': { bgcolor: 'var(--secondary-color)', color: 'white' } }}>
                            {t.title}
                        </Button>
                    ))}
                </Box>
            </Box>
            <Container sx={{ py: 10 }}>
                <Grid container spacing={4}>
                    {tourTypes.map((tour) => (
                        <Grid item xs={12} md={4} key={tour.title}>
                            <Card sx={{ borderRadius: 'var(--border-radius)' }}>
                                <CardActionArea onClick={() => navigate(tour.path)}>
                                    <CardMedia component="img" height="200" image={tour.image} />
                                    <CardContent>
                                        <Typography variant="h5" sx={{ fontWeight: 'bold' }}>{tour.title} Tours</Typography>
                                        <Typography variant="body2" color="text.secondary">{tour.desc}</Typography>
                                    </CardContent>
                                </CardActionArea>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        </Box>
    );
}