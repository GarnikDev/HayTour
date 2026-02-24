import * as React from 'react';
import { AppBar, Box, Toolbar, Typography, Button, Container, IconButton } from '@mui/material';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew'; // Import the back icon
import { useNavigate } from 'react-router-dom';
import { useAuth } from './context/AuthContext';

export default function ButtonAppBar() {
    const { user } = useAuth();
    const navigate = useNavigate();

    return (
        <AppBar
            position="sticky"
            elevation={0}
            sx={{
                backgroundColor: 'var(--card-bg)',
                borderBottom: '1px solid rgba(0,0,0,0.1)',
                backdropFilter: 'blur(8px)'
            }}
        >
            <Container maxWidth="lg">
                <Toolbar disableGutters>
                    
                    {/* BACK BUTTON */}
                    <IconButton
                        onClick={() => navigate(-1)} // Navigates to the previous page
                        sx={{
                            mr: 2,
                            color: 'var(--text-main)',
                            transition: 'transform 0.2s',
                            '&:hover': {
                                backgroundColor: 'rgba(0,0,0,0.05)',
                                transform: 'translateX(-3px)' // Subtle "peek" animation
                            }
                        }}
                    >
                        <ArrowBackIosNewIcon sx={{ fontSize: '1.2rem' }} />
                    </IconButton>

                    {/* LOGO AREA */}
                    <Typography
                        variant="h5"
                        component="div"
                        onClick={() => navigate('/')}
                        sx={{
                            flexGrow: 1,
                            cursor: 'pointer',
                            fontWeight: 'bold',
                            letterSpacing: '-1px',
                            color: 'var(--primary-color)',
                            fontFamily: 'var(--font-family)'
                        }}
                    >
                        HAY<span style={{ color: 'var(--secondary-color)' }}>TOUR</span>
                    </Typography>

                    {/* NAVIGATION LINKS */}
                    <Box sx={{ display: 'flex', gap: 2 }}>
                        {!user ? (
                            <>
                                <Button
                                    sx={{ color: 'var(--text-main)', fontWeight: 500 }}
                                    onClick={() => navigate('/login')}
                                >
                                    Log in
                                </Button>
                                <Button
                                    variant="contained"
                                    sx={{
                                        backgroundColor: 'var(--primary-color)',
                                        color: '#fff',
                                        borderRadius: 'var(--border-radius)',
                                        textTransform: 'none',
                                        '&:hover': { backgroundColor: 'var(--primary-color)', opacity: 0.9 }
                                    }}
                                    onClick={() => navigate('/register')}
                                >
                                    Sign Up
                                </Button>
                            </>
                        ) : (
                            <Button
                                variant="outlined"
                                sx={{
                                    color: 'var(--primary-color)',
                                    borderColor: 'var(--primary-color)',
                                    borderRadius: 'var(--border-radius)'
                                }}
                                onClick={() => navigate('/logout')}
                            >
                                Log Out
                            </Button>
                        )}
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}