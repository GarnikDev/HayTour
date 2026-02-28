import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Typography, TextField, Button, Alert, Paper, Grid } from '@mui/material';
import axios from "../api/axiosConfig";
import {useAuth} from "../context/AuthContext";

export default function Register() {
    const [formData, setFormData] = useState({
        username: "", phone: "", email: "", password: "", confirmPassword: "", idNumber: ""
    });

    const [formLogin, setLogin] = useState({
        username: "",
        password: ""
    });
    const { checkAuth } = useAuth();
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) =>{
        setFormData({ ...formData, [e.target.name]: e.target.value });
        setLogin({ ...formLogin, [e.target.name]: e.target.value });
    }


    const handleSubmit = async (event) => {
        event.preventDefault();
        if (formData.password !== formData.confirmPassword) {
            setError("Passwords don't match");
            return;
        }
        try {
            const response = await axios.post("/register", formData);
            await axios.post("/login", formLogin);
            await checkAuth();
            if (response.status === 201) navigate("/");

        } catch (err) {
            setError(err.response?.data?.error || 'Registration failed');
        }
    }

    return (
        <Box className="auth-page-wrapper">
            <Paper elevation={0} className="auth-card">
                <Typography variant="h3" align="center" sx={{ color: 'var(--primary-color)', fontWeight: 800, mb: 4 }}>
                    Create Account
                </Typography>
                <Box component="form" onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <TextField fullWidth label="Username" name="username" onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField fullWidth label="Phone" name="phone" onChange={handleChange} />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Email address" name="email" type="email" onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="ID number" name="idNumber" onChange={handleChange} />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Password" name="password" type="password" onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Confirm Password" name="confirmPassword" type="password" onChange={handleChange} required />
                        </Grid>
                    </Grid>
                    <Button type="submit" fullWidth variant="contained" size="large" sx={{ mt: 4, py: 2 }}>
                        Register
                    </Button>
                    {error && <Alert severity="error" sx={{ mt: 2 }}>{error}</Alert>}
                </Box>
            </Paper>
        </Box>
    );
}