import React, { useState, useEffect } from 'react';
import {
    Box,
    Typography,
    Container,
    Grid,
    Card,
    CardMedia,
    CardContent,
    CardActionArea
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from "../api/axiosPublic";

export default function Home() {

    const [tours, setTours] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get("/api/tourOffers/view")
            .then((response) => {
                setTours(response.data);
            })
            .catch((error) => {
                console.log("Error retrieving tours " + error);
            });
    }, []);

    return (
        <Box sx={{ width: '100%' }}>
            <Container sx={{ py: 6 }}>
                <Typography
                    variant="h4"
                    sx={{ mb: 6, fontWeight: 700 }}
                >
                    Available Tours
                </Typography>

                <Grid container spacing={4}>
                    {tours.map((tour) => (
                        <Grid item xs={12} sm={6} md={4} key={tour.id}>
                            <Card
                                sx={{
                                    borderRadius: 3,
                                    boxShadow: 3,
                                    transition: "0.3s",
                                    '&:hover': {
                                        transform: "translateY(-8px)"
                                    }
                                }}
                            >
                                <CardActionArea
                                    onClick={() => navigate(`/tour/${tour.id}`)}
                                >
                                    <CardMedia
                                        component="img"
                                        height="220"
                                        image={tour.imageUrl}
                                        alt={tour.title}
                                    />

                                    <CardContent>
                                        <Typography
                                            variant="h6"
                                            sx={{ fontWeight: 600 }}
                                        >
                                            {tour.title}
                                        </Typography>

                                        <Typography
                                            variant="body2"
                                            color="text.secondary"
                                            sx={{ mt: 1 }}
                                        >
                                            From ${tour.basePrice}
                                        </Typography>
                                    </CardContent>
                                </CardActionArea>
                            </Card>
                        </Grid>
                    ))}
                </Grid>

                {tours.length === 0 && (
                    <Typography>No tours found</Typography>
                )}
            </Container>
        </Box>
    );
}