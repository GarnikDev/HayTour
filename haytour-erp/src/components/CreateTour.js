import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
    Box,
    Typography,
    Container,
    TextField,
    Button,
    Alert,
    Paper,
    Grid
} from '@mui/material';
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
        place: ""
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
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

        // 1. Initialize FormData
        const data = new FormData();

        const tourData = {
            title: formData.title,
            description: formData.description,
            basePrice: parseFloat(formData.basePrice),
            duration: formData.duration,
            place: formData.place,
            appUserId: user?.id // Change 'createdBy' to 'appUserId' to match DTO
        };

        // 3. Append parts to match @RequestPart names in Spring Boot
        // We wrap the JSON in a Blob so the Content-Type for this part is 'application/json'
        data.append("tourOffer", new Blob([JSON.stringify(tourData)], { type: "application/json" }));
        data.append("image", imageFile);

        try {
            const response = await axios.post("/api/tourOffers/tourOffer", data, {
                headers: {
                    // Setting this to undefined lets the browser automatically
                    // set "multipart/form-data" with the correct boundary.
                    "Content-Type": undefined
                }
            });

            if (response.status === 201 || response.status === 200) {
                navigate("/tours");
            }
        } catch (err) {
            console.error("Upload error:", err);
            setError(err.response?.data?.message || "Failed to create tour. Check server logs.");
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
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                label="Tour Title"
                                name="title"
                                value={formData.title}
                                onChange={handleChange}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <TextField
                                fullWidth
                                label="Place"
                                name="place"
                                value={formData.place}
                                onChange={handleChange}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField
                                fullWidth
                                label="Base Price ($)"
                                name="basePrice"
                                type="number"
                                value={formData.basePrice}
                                onChange={handleChange}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={3}>
                            <TextField
                                fullWidth
                                label="Duration"
                                name="duration"
                                placeholder="e.g. 3 hours"
                                value={formData.duration}
                                onChange={handleChange}
                                required
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                multiline
                                rows={4}
                                label="Description"
                                name="description"
                                value={formData.description}
                                onChange={handleChange}
                                required
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Typography variant="subtitle1" mb={1} fontWeight={600}>
                                Tour Cover Image
                            </Typography>
                            <Button
                                variant="outlined"
                                component="label"
                                fullWidth
                                sx={{ py: 2, borderStyle: 'dashed' }}
                            >
                                {imageFile ? imageFile.name : "Select Image File"}
                                <input
                                    type="file"
                                    hidden
                                    accept="image/*"
                                    onChange={handleFileChange}
                                />
                            </Button>
                        </Grid>
                    </Grid>

                    {error && <Alert severity="error" sx={{ mt: 3 }}>{error}</Alert>}

                    <Box mt={4} display="flex" gap={2}>
                        <Button
                            type="submit"
                            variant="contained"
                            size="large"
                            disabled={loading}
                            sx={{ minWidth: 150 }}
                        >
                            {loading ? "Publishing..." : "Publish Tour"}
                        </Button>
                        <Button
                            variant="text"
                            onClick={() => navigate('/tours')}
                            disabled={loading}
                        >
                            Cancel
                        </Button>
                    </Box>
                </Box>
            </Paper>
        </Container>
    );
}