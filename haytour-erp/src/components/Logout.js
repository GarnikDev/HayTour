import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

export default function Logout() {
    const { logout } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        const performLogout = async () => {
            try {
                await logout(); // calls /logout → clears cookie
            } catch (err) {
                console.error("Logout error:", err);
            } finally {
                // Always redirect, even if API call fails
                navigate('/', { replace: true });
            }
        };

        performLogout();
    }, [logout, navigate]);

    return (
        <Box
            sx={{
                height: '100vh',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                gap: 3
            }}
        >
            <CircularProgress />
            <Typography variant="h6">
                Logging you out...
            </Typography>
        </Box>
    );
}