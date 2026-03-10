import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
    Box, Typography, Container, TextField, Button,
    Alert, Paper, Grid, ToggleButton, ToggleButtonGroup
} from '@mui/material';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import DirectionsBikeIcon from '@mui/icons-material/DirectionsBike';
import DirectionsWalkIcon from '@mui/icons-material/DirectionsWalk';
import axios from "../api/axiosConfig";
import { useAuth } from "../context/AuthContext";

export default function CreateTour() {
    const { user } = useAuth();
    const navigate = useNavigate();

    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const [imageFile, setImageFile] = useState(null);
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        basePrice: "",
        duration: "",
        place: "",
        type: "WALK" // Default value
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // Custom handler for the icon toggle
    const handleTypeChange = (event, newType) => {
        if (newType !== null) {
            setFormData({ ...formData, type: newType });
        }
    };

    const handleFileChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            setImageFile(e.target.files[0]);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        if (!imageFile) {
            setError("Please upload an image for the tour.");
            setLoading(false);
            return;
        }

        const data = new FormData();
        const tourData = {
            ...formData,
            basePrice: parseFloat(formData.basePrice),
            appUserId: user?.id
        };

        data.append("tourOffer", new Blob([JSON.stringify(tourData)], { type: "application/json" }));
        data.append("image", imageFile);

        try {
            await axios.post("/api/tourOffers/tourOffer", data, {
                headers: { "Content-Type": undefined }
            });
            navigate("/tours");
        } catch (err) {
            setError(err.response?.data?.message || "Failed to create tour.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container maxWidth="md" sx={{ py: 5 }}>
            <Paper elevation={3} sx={{ p: 4, borderRadius: 3 }}>
                <Typography variant="h4" mb={3} fontWeight={700} color="primary">
                    Create New Tour
                </Typography>

                <Box component="form" onSubmit={handleSubmit}>
                    <Grid container spacing={3}>
                        <Grid item xs={12} sm={8}>
                            <TextField fullWidth label="Tour Title" name="title" value={formData.title} onChange={handleChange} required />
                        </Grid>

                        {/* ICON PICKLIST */}
                        <Grid item xs={12} sm={4}>
                            <Typography variant="caption" color="text.secondary" display="block" mb={1}>
                                Tour Type
                            </Typography>
                            <ToggleButtonGroup
                                value={formData.type}
                                exclusive
                                onChange={handleTypeChange}
                                fullWidth
                                color="primary"
                            >
                                <ToggleButton value="WALK" aria-label="walk">
                                    <DirectionsWalkIcon />
                                </ToggleButton>
                                <ToggleButton value="BIKE" aria-label="bike">
                                    <DirectionsBikeIcon />
                                </ToggleButton>
                                <ToggleButton value="BUS" aria-label="bus">
                                    <DirectionsBusIcon />
                                </ToggleButton>
                            </ToggleButtonGroup>
                        </Grid>

                        <Grid item xs={12} sm={6}>
                            <TextField fullWidth label="Place" name="place" value={formData.place} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField fullWidth label="Base Price ($)" name="basePrice" type="number" value={formData.basePrice} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField fullWidth label="Duration" name="duration" value={formData.duration} onChange={handleChange} required />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField fullWidth multiline rows={4} label="Description" name="description" value={formData.description} onChange={handleChange} required />
                        </Grid>
                        {/* Image Upload remains the same... */}
                        <Grid item xs={12}>
                            <Typography variant="subtitle1" mb={1} fontWeight={600}>Tour Cover Image</Typography>
                            <Button variant="outlined" component="label" fullWidth sx={{ py: 2, borderStyle: 'dashed' }}>
                                {imageFile ? imageFile.name : "Select Image File"}
                                <input type="file" hidden accept="image/*" onChange={handleFileChange} />
                            </Button>
                        </Grid>
                    </Grid>
                    {error && <Alert severity="error" sx={{ mt: 3 }}>{error}</Alert>}
                    <Box mt={4} display="flex" gap={2}>
                        <Button type="submit" variant="contained" size="large" disabled={loading}>{loading ? "Publishing..." : "Publish Tour"}</Button>
                        <Button variant="text" onClick={() => navigate('/tours')}>Cancel</Button>
                    </Box>
                </Box>
            </Paper>
        </Container>
    );
}