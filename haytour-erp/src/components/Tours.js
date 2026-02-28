import React, { useState, useEffect } from 'react';
import { Box, Typography, Container, Grid, Card, CardMedia, CardContent, Button, Fab } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate } from 'react-router-dom';
import axios from "../api/axiosConfig"; // Using auth config to see internal details

export default function Tours() {
    const [tours, setTours] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get("/api/tourOffers/view")
            .then(res => setTours(res.data))
            .catch(err => console.error("Error fetching tours", err));
    }, []);

    return (
        <Container sx={{ py: 4 }}>
            <Box display="flex" justifyContent="space-between" alignItems="center" mb={4}>
                <Typography variant="h4" fontWeight={700}>Tour Management</Typography>
                <Button
                    variant="contained"
                    startIcon={<AddIcon />}
                    onClick={() => navigate('/create-tour')}
                >
                    Create Tour
                </Button>
            </Box>

            <Grid container spacing={3}>
                {tours.map((tour) => (
                    <Grid item xs={12} sm={6} md={4} key={tour.id}>
                        <Card sx={{ height: '100%', borderRadius: 2 }}
                              onClick={() => navigate(`/edit-tour/${tour.id}`)}>

                            <CardMedia component="img" height="160" image={tour.imageUrl} alt={tour.title} />
                            <CardContent>
                                <Typography variant="h6">{tour.title}</Typography>
                                <Typography variant="body2" color="text.secondary">{tour.place}</Typography>
                                <Typography variant="h6" color="primary" sx={{ mt: 1 }}>${tour.basePrice}</Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            {/* Mobile friendly Floating Action Button */}
            <Fab color="primary" sx={{ position: 'fixed', bottom: 16, right: 16 }} onClick={() => navigate('/create-tour')}>
                <AddIcon />
            </Fab>
        </Container>
    );
}