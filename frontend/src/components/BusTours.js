import React, { useState } from 'react';
import { Box, Typography, Container, Grid, Paper, Divider, Button, TextField, Stack, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import { useNavigate } from 'react-router-dom';
import { tourStyles } from '../styles/tourStyles';

export default function BusTours() {
    const navigate = useNavigate();
    const [adults, setAdults] = useState(1);
    const [kids, setKids] = useState(0);
    const totalPrice = (adults * 45) + (kids * 30);

    const stops = ["Garni Pagan Temple", "Geghard Monastery", "Symphony of Stones", "Charents Arch", "Lavash Workshop"];

    return (
        <Box sx={{ flexGrow: 1, pb: 10 }}>
            <Box sx={tourStyles.header}>
                <Typography variant="h3" sx={{ fontWeight: 800 }}>Garni & Geghard Classic</Typography>
                <Typography variant="subtitle1">Armenia's Iconic Landmarks in Comfort</Typography>
            </Box>

            <Container sx={{ mt: -6 }}>
                <Paper sx={tourStyles.heroImage("https://images.unsplash.com/photo-1544620347-c4fd4a3d5957?q=80&w=1470")} />

                <Box sx={{ mt: 8 }}>
                    <Typography variant="h4" sx={tourStyles.sectionTitle}>About the Tour</Typography>
                    <Typography variant="body1" sx={tourStyles.descriptionText}>
                        Travel beyond city limits to witness UNESCO-protected architectural wonders in a modern air-conditioned bus.
                    </Typography>
                </Box>

                <Divider sx={{ my: 6 }} />

                <Typography variant="h5" sx={tourStyles.sectionTitle}>Tour Route & Stops</Typography>
                <List sx={{ display: 'grid', gridTemplateColumns: { sm: '1fr 1fr' }, mb: 4 }}>
                    {stops.map((stop, i) => (
                        <ListItem key={i} sx={{ px: 0 }}>
                            <ListItemIcon sx={{ minWidth: '35px' }}><LocationOnIcon color="primary" /></ListItemIcon>
                            <ListItemText primary={stop} />
                        </ListItem>
                    ))}
                </List>

                <Box sx={tourStyles.mapPaper}>
                    <iframe
                        title="Bus Tour Map"
                        src={tourStyles.getMapUrl('bus')}
                        width="100%"
                        height="100%"
                        style={{ border: 0, borderRadius: 'inherit' }}
                        allowFullScreen
                        loading="lazy">
                    </iframe>
                </Box>

                <Paper elevation={4} sx={tourStyles.bookingContainer}>
                    <Grid container spacing={4} alignItems="center">
                        <Grid item xs={12} md={6}>
                            <Stack direction="row" spacing={2}>
                                <TextField label="Adults ($45)" type="number" fullWidth sx={{ bgcolor: 'white' }} value={adults} onChange={(e) => setAdults(Math.max(1, parseInt(e.target.value) || 0))} />
                                <TextField label="Children ($30)" type="number" fullWidth sx={{ bgcolor: 'white' }} value={kids} onChange={(e) => setKids(Math.max(0, parseInt(e.target.value) || 0))} />
                            </Stack>
                        </Grid>
                        <Grid item xs={12} md={6} sx={{ textAlign: { xs: 'center', md: 'right' } }}>
                            <Stack direction="row" spacing={3} alignItems="center" justifyContent={{xs: 'center', md: 'flex-end'}}>
                                <Box>
                                    <Typography variant="caption" color="text.secondary">Total Estimate</Typography>
                                    <Typography variant="h4" sx={{ fontWeight: 'bold', color: 'var(--primary-color)' }}>${totalPrice}</Typography>
                                </Box>
                                <Button variant="contained" color="secondary" size="large" sx={{ height: '60px', px: 4 }} onClick={() => navigate('/booking')}>BOOK NOW</Button>
                            </Stack>
                        </Grid>
                    </Grid>
                </Paper>
            </Container>
        </Box>
    );
}