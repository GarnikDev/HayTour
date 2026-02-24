import React from "react";
import { Typography, Container, Box, Button } from '@mui/material';
import { useNavigate } from "react-router-dom";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';

export default function RegistrationSuccessful() {
    const navigate = useNavigate();

    return (
        <Container maxWidth="xs">
            {/* We apply the 'auth-card' class here for the shadow and white background */}
            <Box className="auth-card" sx={{
                mt: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                textAlign: 'center'
            }}>
                <CheckCircleOutlineIcon sx={{
                    fontSize: 80,
                    color: 'var(--primary-color)',
                    mb: 2
                }} />

                <Typography variant="h4" gutterBottom sx={{ fontWeight: 'bold', color: 'var(--text-main)' }}>
                    Registration Successful!
                </Typography>

                <Typography variant="body1" sx={{ color: 'var(--text-muted)', mb: 4 }}>
                    Welcome to HayTour. Your account has been created successfully.
                    You can now access all our tour types and rental services.
                </Typography>

                <Button
                    variant="contained"
                    fullWidth
                    onClick={() => navigate("/login")}
                    style={{
                        backgroundColor: 'var(--primary-color)',
                        borderRadius: 'var(--border-radius)',
                        padding: '12px'
                    }}
                >
                    Back to Login
                </Button>
            </Box>
        </Container>
    );
}