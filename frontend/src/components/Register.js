import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Typography, TextField, Button, Alert, Paper, Grid } from '@mui/material';
import axios from "../api/axiosConfig";
import { useAuth } from "../context/AuthContext";

export default function Register() {
    const [formData, setFormData] = useState({
        username: "", phone: "", email: "", password: "", confirmPassword: "", idNumber: ""
    });

    // Keeping separate state for login as per your original logic
    const [formLogin, setLogin] = useState({
        username: "",
        password: ""
    });

    const { checkAuth } = useAuth();
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });

        // Update login fields simultaneously if they match (username/password)
        if (name === "username" || name === "password") {
            setLogin(prev => ({ ...prev, [name]: value }));
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');

        if (formData.password !== formData.confirmPassword) {
            setError("Passwords don't match");
            return;
        }

        setLoading(true);
        try {
            // 1. Register the user
            const regResponse = await axios.post("/register", formData);

            // 2. If registration is successful, log them in automatically
            if (regResponse.status === 200 || regResponse.status === 201) {
                await axios.post("/login", formLogin);

                // 3. Update the Global Auth Context (JWT, User data, etc.)
                await checkAuth();

                // 4. Direct Redirect to Home
                navigate("/");
            }
        } catch (err) {
            setError(err.response?.data?.error || 'Registration failed. Try a different username/email.');
        } finally {
            setLoading(false);
        }
    }

    return (
        <Box className="auth-page-wrapper" sx={{ py: 8, display: 'flex', justifyContent: 'center' }}>
            <Paper elevation={3} sx={{ p: 4, maxWidth: 600, width: '100%', borderRadius: 4 }}>
                <Typography variant="h3" align="center" sx={{ color: 'primary.main', fontWeight: 800, mb: 4 }}>
                    Create Account
                </Typography>

                <Box component="form" onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <TextField fullWidth label="Username" name="username" value={formData.username} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField fullWidth label="Phone" name="phone" value={formData.phone} onChange={handleChange} />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Email address" name="email" type="email" value={formData.email} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="ID number" name="idNumber" value={formData.idNumber} onChange={handleChange} />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Password" name="password" type="password" value={formData.password} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Confirm Password" name="confirmPassword" type="password" value={formData.confirmPassword} onChange={handleChange} required />
                        </Grid>
                    </Grid>

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        size="large"
                        disabled={loading}
                        sx={{ mt: 4, py: 1.5, fontWeight: 700, borderRadius: 2 }}
                    >
                        {loading ? "Creating Account..." : "Register"}
                    </Button>

                    {error && <Alert severity="error" sx={{ mt: 2, borderRadius: 2 }}>{error}</Alert>}

                    <Box sx={{ mt: 3, textAlign: 'center' }}>
                        <Typography variant="body2" color="text.secondary">
                            Already have an account?
                            <Button size="small" onClick={() => navigate('/login')} sx={{ fontWeight: 700 }}>Login here</Button>
                        </Typography>
                    </Box>
                </Box>
            </Paper>
        </Box>
    );
}