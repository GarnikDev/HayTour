import React, { useState } from 'react';
import { Box, Typography, Container, Grid, Paper, Button, TextField, Stack, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import PedalBikeIcon from '@mui/icons-material/PedalBike';
import GroupsIcon from '@mui/icons-material/Groups';
import EscalatorWarningIcon from '@mui/icons-material/EscalatorWarning';
import { useNavigate } from 'react-router-dom';

export default function BikeTours() {
    const navigate = useNavigate();
    const [adults, setAdults] = useState(1);
    const [kids, setKids] = useState(0);
    const totalPrice = (adults * 35) + (kids * 20);

    const stops = ["Hrazdan Gorge", "Yerevan Lake", "Circular Park", "Victory Park", "Botanical Garden"];

    return (
        <Box sx={{ flexGrow: 1, pb: 10 }}>
            <Box sx={{ bgcolor: 'var(--primary-color)', color: 'white', py: 6, textAlign: 'center' }}>
                <Typography variant="h3" sx={{ fontWeight: 800 }}>Yerevan Green Cycle</Typography>
                <Typography variant="subtitle1">Pedal Through Parks and Gorges</Typography>
            </Box>

            <Container sx={{ mt: -4 }}>
                <Paper elevation={4} sx={{ height: '500px', borderRadius: '15px', backgroundImage: 'url("https://images.unsplash.com/photo-1471506480208-8e93abc301ba?q=80&w=1470")', backgroundSize: 'cover', backgroundPosition: 'center' }} />
            </Container>

            <Container sx={{ mt: 8 }}>
                <Grid container spacing={6} sx={{ mb: 8 }}>
                    <Grid item xs={12} md={4.5}>
                        <Typography variant="h5" gutterBottom sx={{ fontWeight: 'bold' }}>Cycling Route</Typography>
                        <List>{stops.map((stop, i) => (<ListItem key={i} sx={{ px: 0 }}><ListItemIcon><PedalBikeIcon color="primary" /></ListItemIcon><ListItemText primary={stop} /></ListItem>))}</List>
                    </Grid>
                    <Grid item xs={12} md={7.5}>
                        <Typography variant="h5" gutterBottom sx={{ fontWeight: 'bold' }}>Bike Path Map</Typography>
                        <Paper elevation={1} sx={{ height: '400px', borderRadius: '12px', overflow: 'hidden', border: '1px solid #eee' }}>
                            <iframe title="map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12196.347012579172!2d44.4883319!3d40.1625902!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x406ab99763137e3b%3A0xe9628004f4a3e790!2sHrazdan%20Gorge!5e0!3m2!1sen!2sam!4v1700000000000" width="100%" height="100%" style={{ border: 0 }}></iframe>
                        </Paper>
                    </Grid>
                </Grid>

                <Paper elevation={3} sx={{ p: 4, borderRadius: '15px', bgcolor: '#fafafa' }}>
                    <Stack direction={{ xs: 'column', md: 'row' }} alignItems="center" justifyContent="space-evenly" sx={{ width: '100%' }}>
                        <Stack direction="row" spacing={2}>
                            <Box sx={{ width: '140px' }}><Stack direction="row" alignItems="center" spacing={1} sx={{ mb: 1 }}><GroupsIcon color="action" fontSize="small" /><Typography variant="subtitle2">Adults</Typography></Stack>
                                <TextField type="number" size="small" fullWidth value={adults} onChange={(e) => setAdults(Math.max(1, parseInt(e.target.value) || 0))} /></Box>
                            <Box sx={{ width: '140px' }}><Stack direction="row" alignItems="center" spacing={1} sx={{ mb: 1 }}><EscalatorWarningIcon color="action" fontSize="small" /><Typography variant="subtitle2">Children</Typography></Stack>
                                <TextField type="number" size="small" fullWidth value={kids} onChange={(e) => setKids(Math.max(0, parseInt(e.target.value) || 0))} /></Box>
                        </Stack>
                        <Box sx={{ bgcolor: 'white', py: 1.5, px: 3, borderRadius: '10px', border: '1px solid #e0e0e0', display: 'flex', alignItems: 'center', justifyContent: 'space-between', width: '280px' }}>
                            <Typography variant="body1">Total</Typography>
                            <Typography variant="h5" sx={{ fontWeight: 700, color: 'primary.main' }}>${totalPrice}</Typography>
                        </Box>
                        <Button variant="contained" size="large" sx={{ px: 4, py: 1.5, minWidth: '180px' }} onClick={() => navigate('/booking')}>Proceed to Book</Button>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    );
}