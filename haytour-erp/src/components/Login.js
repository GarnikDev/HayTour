import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Paper, Box, Typography, TextField, Button, Alert } from '@mui/material';
import axios from "../api/axiosConfig";
import { useAuth } from "../context/AuthContext";

export default function Login() {
    const { checkAuth } = useAuth();
    const [formData, setFormData] = useState({ username: "", password: "" });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios.post("/login", formData);
            await checkAuth();
            navigate("/");
        } catch (err) {
            setError(err.response?.data?.error || "Invalid username or password");
        }
    };

    return (
        <Box className="auth-page-wrapper">
            <Paper elevation={0} className="auth-card">
                <Typography
                    variant="h3"
                    align="center"
                    sx={{ color: 'var(--primary-color)', fontWeight: 800, mb: 4 }}
                >
                    Log In
                </Typography>

                <Box component="form" onSubmit={handleSubmit} noValidate>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Username"
                        name="username"
                        autoComplete="username"
                        autoFocus
                        value={formData.username}
                        onChange={handleChange}
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        autoComplete="current-password"
                        value={formData.password}
                        onChange={handleChange}
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        size="large"
                        sx={{ mt: 4, py: 2 }} // Matches Register button spacing
                    >
                        Login
                    </Button>

                    {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}
                </Box>
            </Paper>
        </Box>
    );
}