import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
    Box,
    Typography,
    Container,
    TextField,
    Button,
    Alert,
    Paper,
    Grid,
    Divider
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import SaveIcon from '@mui/icons-material/Save';
import axios from "../api/axiosConfig";

export default function EditTour() {
    const { id } = useParams();
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
        imageUrl: "" // Keep track of the existing URL
    });

    // 1. Fetch existing data on mount
    useEffect(() => {
        axios.get(`/api/tourOffers/view/${id}`)
            .then(res => {
                setFormData(res.data);
            })
            .catch(err => {
                console.error("Fetch error:", err);
                setError("Could not load tour details.");
            });
    }, [id]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleFileChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            setImageFile(e.target.files[0]);
        }
    };

    // 2. Handle Update (PUT)
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        const data = new FormData();
        const tourDto = {
            title: formData.title,
            description: formData.description,
            basePrice: parseFloat(formData.basePrice),
            duration: formData.duration,
            place: formData.place
        };

        // Wrap JSON in Blob for @RequestPart
        data.append("tourOffer", new Blob([JSON.stringify(tourDto)], { type: "application/json" }));

        // Append image only if a new one is selected
        if (imageFile) {
            data.append("image", imageFile);
        }

        try {
            await axios.put(`/api/tourOffers/edit/${id}`, data, {
                headers: { "Content-Type": undefined }
            });
            navigate("/tours");
        } catch (err) {
            console.error("Update error:", err);
            setError(err.response?.data?.message || "Failed to update tour.");
        } finally {
            setLoading(false);
        }
    };

    // 3. Handle Delete (DELETE)
    const handleDelete = async () => {
        if (!window.confirm("Are you sure you want to delete this tour? This action cannot be undone.")) {
            return;
        }

        setLoading(true);
        try {
            await axios.delete(`/api/tourOffers/delete/${id}`);
            navigate("/tours");
        } catch (err) {
            console.error("Delete error:", err);
            setError("Failed to delete tour.");
            setLoading(false);
        }
    };

    return (
        <Container maxWidth="md" sx={{ py: 5 }}>
            <Paper elevation={3} sx={{ p: 4, borderRadius: 3 }}>
                <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
                    <Typography variant="h4" fontWeight={700} color="secondary">
                        Edit Tour
                    </Typography>
                    <Button
                        variant="outlined"
                        color="error"
                        startIcon={<DeleteIcon />}
                        onClick={handleDelete}
                        disabled={loading}
                    >
                        Delete Tour
                    </Button>
                </Box>

                <Box component="form" onSubmit={handleSubmit}>
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <TextField fullWidth label="Tour Title" name="title" value={formData.title} onChange={handleChange} required />
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

                        <Grid item xs={12}>
                            <Divider sx={{ my: 2 }} />
                            <Typography variant="subtitle1" mb={2} fontWeight={600}>
                                Tour Image
                            </Typography>

                            {/* PREVIEW LOGIC */}
                            <Box mb={2} sx={{ width: '100%', maxWidth: 400 }}>
                                {imageFile ? (
                                    <>
                                        <Typography variant="caption" color="primary">New Image Selected:</Typography>
                                        <img
                                            src={URL.createObjectURL(imageFile)}
                                            alt="Preview"
                                            style={{ width: '100%', height: '200px', objectFit: 'cover', borderRadius: '8px', border: '2px solid #1976d2' }}
                                        />
                                    </>
                                ) : formData.imageUrl ? (
                                    <>
                                        <Typography variant="caption">Current Image:</Typography>
                                        <img
                                            src={formData.imageUrl}
                                            alt="Current"
                                            style={{ width: '100%', height: '200px', objectFit: 'cover', borderRadius: '8px' }}
                                        />
                                    </>
                                ) : null}
                            </Box>

                            <Button variant="outlined" component="label" fullWidth sx={{ py: 1.5, borderStyle: 'dashed' }}>
                                {imageFile ? "Change Selected Image" : "Upload New Image"}
                                <input type="file" hidden accept="image/*" onChange={handleFileChange} />
                            </Button>
                        </Grid>
                    </Grid>

                    {error && <Alert severity="error" sx={{ mt: 3 }}>{error}</Alert>}

                    <Box mt={4} display="flex" gap={2}>
                        <Button
                            type="submit"
                            variant="contained"
                            color="secondary"
                            size="large"
                            startIcon={<SaveIcon />}
                            disabled={loading}
                            sx={{ minWidth: 150 }}
                        >
                            {loading ? "Saving..." : "Save Changes"}
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