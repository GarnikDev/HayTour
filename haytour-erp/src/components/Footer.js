import React from 'react';
import { Box, Container, Grid, Typography, Link, IconButton } from '@mui/material';
import FacebookIcon from '@mui/icons-material/Facebook';
import InstagramIcon from '@mui/icons-material/Instagram';
import TwitterIcon from '@mui/icons-material/Twitter';

export default function Footer() {
    return (
        <Box sx={{
            bgcolor: 'var(--primary-color)',
            color: 'white',
            py: 6,
            mt: 'auto',
            borderTop: '5px solid var(--secondary-color)'
        }}>
            <Container maxWidth="lg">
                <Grid container spacing={4}>
                    <Grid item xs={12} md={4}>
                        <Typography variant="h6" sx={{ fontWeight: 'bold', mb: 2 }}>
                            HAYTOUR
                        </Typography>
                        <Typography variant="body2" sx={{ opacity: 0.8, lineHeight: 1.8 }}>
                            Discover the hidden gems of Armenia. From city walks to
                            mountain bike trails, we provide authentic experiences
                            for every traveler.
                        </Typography>
                    </Grid>

                    <Grid item xs={6} md={2}>
                        <Typography variant="subtitle1" sx={{ fontWeight: 'bold', mb: 2 }}>Services</Typography>
                        <Link href="/tours/walk" color="inherit" underline="hover" display="block" sx={{ mb: 1 }}>Walking Tours</Link>
                        <Link href="/tours/bike" color="inherit" underline="hover" display="block" sx={{ mb: 1 }}>Bicycle Tours</Link>
                        <Link href="/tours/bus" color="inherit" underline="hover" display="block">Bus Tours</Link>
                    </Grid>

                    <Grid item xs={6} md={2}>
                        <Typography variant="subtitle1" sx={{ fontWeight: 'bold', mb: 2 }}>Company</Typography>
                        <Link href="#" color="inherit" underline="hover" display="block" sx={{ mb: 1 }}>About Us</Link>
                        <Link href="#" color="inherit" underline="hover" display="block" sx={{ mb: 1 }}>Contact</Link>
                        <Link href="#" color="inherit" underline="hover" display="block">Privacy Policy</Link>
                    </Grid>

                    <Grid item xs={12} md={4}>
                        <Typography variant="subtitle1" sx={{ fontWeight: 'bold', mb: 2 }}>Follow Us</Typography>
                        <Box>
                            <IconButton color="inherit"><FacebookIcon /></IconButton>
                            <IconButton color="inherit"><InstagramIcon /></IconButton>
                            <IconButton color="inherit"><TwitterIcon /></IconButton>
                        </Box>
                        <Typography variant="body2" sx={{ mt: 2, opacity: 0.7 }}>
                            Support: info@haytour.am
                        </Typography>
                    </Grid>
                </Grid>

                <Box sx={{ borderTop: '1px solid rgba(255,255,255,0.1)', mt: 4, pt: 4, textAlign: 'center' }}>
                    <Typography variant="body2" sx={{ opacity: 0.5 }}>
                        © 2026 HayTour Armenia. All rights reserved.
                    </Typography>
                </Box>
            </Container>
        </Box>
    );
}