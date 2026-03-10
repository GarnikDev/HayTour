import React, { useState, useEffect, useCallback, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
    Box, Typography, Container, TextField, Button,
    Paper, Grid, CircularProgress, IconButton, Card, CardContent,
    Stepper, Step, StepLabel, StepContent, Divider,
    Stack, Chip
} from '@mui/material';
import {
    Save as SaveIcon, Close as CloseIcon,
    ArrowBack as ArrowBackIcon, Add as AddIcon, Place as PlaceIcon,
    RemoveCircleOutline as RemoveIcon, AddCircleOutline as AddQtyIcon,
    AccessTime as TimeIcon, Group as GroupIcon, LocationOn as MapIcon,
    Delete as DeleteIcon
} from '@mui/icons-material';

import axios from "../api/axiosConfig";
import axiosPublic from "../api/axiosPublic";
import { useAuth } from "../context/AuthContext";

export default function TourDetails() {
    const { id } = useParams();
    const { user } = useAuth();
    const navigate = useNavigate();
    const fileInputRef = useRef(null);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [tour, setTour] = useState(null);

    // Booking State
    const [booking, setBooking] = useState({
        local: { ADULT: 0, CHILD: 0, INFANT: 0 },
        foreign: { ADULT: 0, CHILD: 0, INFANT: 0 }
    });

    // --- INLINE EDITING STATES ---
    const isManager = user?.role === 'MANAGER';

    // Main Fields (Title, Desc, Duration, etc.)
    const [activeMainEdit, setActiveMainEdit] = useState(null); // 'title', 'description', etc.
    const [tempMainValue, setTempMainValue] = useState("");

    // Image Edit
    const [imageFile, setImageFile] = useState(null);
    const [imagePreview, setImagePreview] = useState(null);

    // List Edits: Store the index being edited and its temporary data
    const [editStop, setEditStop] = useState({ index: null, data: null });
    const [editSlot, setEditSlot] = useState({ index: null, data: null });
    const [editPrice, setEditPrice] = useState({ index: null, data: null });

    const fetchTour = useCallback(async () => {
        try {
            const response = await axiosPublic.get(`/api/tourOffers/view/${id}`);
            setTour(response.data);
            setLoading(false);
        } catch (err) {
            setError("Could not find this tour.");
            setLoading(false);
        }
    }, [id]);

    useEffect(() => { fetchTour(); }, [fetchTour]);

    // --- Booking Logic ---
    const handleBookingCount = (type, category, delta) => {
        const currentTotal = Object.values(booking.local).reduce((a, b) => a + b, 0) +
            Object.values(booking.foreign).reduce((a, b) => a + b, 0);

        if (delta > 0 && currentTotal >= (tour?.maxGroupSize || 99)) return;

        setBooking(prev => ({
            ...prev,
            [type]: { ...prev[type], [category]: Math.max(0, prev[type][category] + delta) }
        }));
    };

    const calculateTotal = () => {
        if (!tour?.prices) return 0;
        let total = 0;
        ['local', 'foreign'].forEach(type => {
            const isForeign = type === 'foreign';
            Object.keys(booking[type]).forEach(cat => {
                const priceObj = tour.prices.find(p => p.category === cat && p.isForeigner === isForeign);
                total += (priceObj ? priceObj.price : 0) * booking[type][cat];
            });
        });
        return total;
    };

    // --- Save Handlers ---

    const saveMainField = async (field) => {
        try {
            const updatedTour = { ...tour, [field]: tempMainValue };
            const data = new FormData();
            data.append("tourOffer", new Blob([JSON.stringify(updatedTour)], { type: "application/json" }));

            await axios.put(`/api/tourOffers/edit/${id}`, data);
            setTour(updatedTour);
            setActiveMainEdit(null);
        } catch (err) { alert("Failed to save changes."); }
    };

    const saveImage = async () => {
        try {
            const data = new FormData();
            data.append("tourOffer", new Blob([JSON.stringify(tour)], { type: "application/json" }));
            data.append("image", imageFile);

            await axios.put(`/api/tourOffers/edit/${id}`, data);
            setImageFile(null);
            setImagePreview(null);
            fetchTour();
        } catch (err) { alert("Failed to save image."); }
    };

    const cancelImage = () => {
        setImageFile(null);
        setImagePreview(null);
    };

    const saveStop = async () => {
        try {
            const payload = { ...editStop.data, tourOfferId: id, stopOrder: editStop.index };
            if (payload.id) {
                await axios.put(`/stops/edit/${payload.id}`, payload);
            } else {
                await axios.post(`/stops/stop`, payload);
            }
            setEditStop({ index: null, data: null });
            fetchTour();
        } catch (err) { alert("Failed to save stop."); }
    };

    const deleteStop = async (stopId) => {
        if (!stopId) return; // Unsaved item
        try {
            await axios.delete(`/stops/delete/${stopId}`);
            fetchTour();
        } catch (err) { alert("Failed to delete stop."); }
    };

    const saveSlot = async () => {
        try {
            const payload = { ...editSlot.data, tourOfferId: id };
            if (payload.id) {
                await axios.put(`/tour_slots/edit/${payload.id}`, payload);
            } else {
                await axios.post(`/tour_slots/tour_slot`, payload);
            }
            setEditSlot({ index: null, data: null });
            fetchTour();
        } catch (err) { alert("Failed to save slot."); }
    };

    const deleteSlot = async (slotId) => {
        if (!slotId) return;
        try {
            await axios.delete(`/tour_slots/delete/${slotId}`);
            fetchTour();
        } catch (err) { alert("Failed to delete slot."); }
    };

    const savePrice = async () => {
        try {
            const payload = { ...editPrice.data, tourOfferId: id };
            if (payload.id) {
                await axios.put(`/tour_price/edit/${payload.id}`, payload);
            } else {
                await axios.post(`/tour_price/tour_price`, payload);
            }
            setEditPrice({ index: null, data: null });
            fetchTour();
        } catch (err) { alert("Failed to save price."); }
    };

    // --- Helper for Manager Click-to-Edit Styles ---
    const editableSx = isManager ? {
        cursor: 'pointer',
        borderRadius: 1,
        transition: 'background-color 0.2s',
        '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.04)' },
        padding: '2px 4px',
        margin: '-2px -4px' // offsets padding so layout doesn't shift
    } : {};

    const startMainEdit = (field, currentValue) => {
        if (!isManager) return;
        setActiveMainEdit(field);
        setTempMainValue(currentValue);
    };

    if (loading) return <Box display="flex" justifyContent="center" py={10}><CircularProgress /></Box>;
    if (error) return <Typography color="error" align="center">{error}</Typography>;

    return (
        <Container maxWidth="xl" sx={{ py: 4 }}>
            <Button startIcon={<ArrowBackIcon />} onClick={() => navigate(-1)} sx={{ mb: 3 }}>Back</Button>

            <Grid container spacing={5} alignItems="flex-start">

                {/* --- LEFT COLUMN --- */}
                <Grid item xs={12} md={8}>

                    {/* TITLE */}
                    <Box mb={2}>
                        {activeMainEdit === 'title' ? (
                            <Stack direction="row" spacing={1} alignItems="center">
                                <TextField
                                    fullWidth variant="standard"
                                    value={tempMainValue}
                                    onChange={(e) => setTempMainValue(e.target.value)}
                                    inputProps={{ style: { fontSize: 32, fontWeight: 'bold' } }}
                                />
                                <IconButton color="success" onClick={() => saveMainField('title')}><SaveIcon /></IconButton>
                                <IconButton color="error" onClick={() => setActiveMainEdit(null)}><CloseIcon /></IconButton>
                            </Stack>
                        ) : (
                            <Typography variant="h3" fontWeight="bold" sx={editableSx} onClick={() => startMainEdit('title', tour.title)}>
                                {tour.title}
                            </Typography>
                        )}
                    </Box>

                    {/* IMAGE (Fixed 4:3 Aspect Ratio) */}
                    <Box sx={{ width: '100%', position: 'relative', mb: 4 }}>
                        <Paper elevation={2} sx={{
                            width: '100%',
                            aspectRatio: '4/3', // Enforces square-like width > height
                            backgroundImage: `url(${imagePreview || tour.imageUrl})`,
                            backgroundSize: 'cover',
                            backgroundPosition: 'center',
                            borderRadius: 4,
                            overflow: 'hidden',
                            cursor: isManager ? 'pointer' : 'default',
                            '&:hover .image-overlay': { opacity: isManager ? 1 : 0 }
                        }} onClick={() => isManager && !imageFile && fileInputRef.current.click()}>

                            {/* Hover overlay instructing managers */}
                            {!imageFile && isManager && (
                                <Box className="image-overlay" sx={{
                                    position: 'absolute', inset: 0, bgcolor: 'rgba(0,0,0,0.3)',
                                    display: 'flex', alignItems: 'center', justifyContent: 'center',
                                    opacity: 0, transition: 'opacity 0.2s'
                                }}>
                                    <Typography variant="h6" color="white" fontWeight="bold">Click to change cover image</Typography>
                                </Box>
                            )}
                        </Paper>

                        <input
                            type="file" hidden ref={fileInputRef}
                            onChange={(e) => {
                                const file = e.target.files[0];
                                if (file) {
                                    setImageFile(file);
                                    setImagePreview(URL.createObjectURL(file));
                                }
                            }}
                        />

                        {/* Save/Cancel popover if image is changed */}
                        {imageFile && (
                            <Paper sx={{ position: 'absolute', bottom: 20, right: 20, p: 1, display: 'flex', gap: 1, borderRadius: 2 }}>
                                <Button variant="contained" color="success" onClick={saveImage} startIcon={<SaveIcon />}>Save Image</Button>
                                <IconButton onClick={cancelImage}><CloseIcon /></IconButton>
                            </Paper>
                        )}
                    </Box>

                    {/* QUICK INFO (Duration, Size, Point) */}
                    <Grid container spacing={2} sx={{ mb: 4, bgcolor: '#f8f9fa', p: 3, borderRadius: 4 }}>
                        {/* Duration */}
                        <Grid item xs={4} textAlign="center">
                            <Stack alignItems="center" spacing={1}>
                                <TimeIcon color="primary" />
                                <Typography variant="caption" color="textSecondary" fontWeight="bold">DURATION</Typography>
                                {activeMainEdit === 'duration' ? (
                                    <Stack direction="row" alignItems="center">
                                        <TextField size="small" variant="standard" value={tempMainValue} onChange={(e) => setTempMainValue(e.target.value)} />
                                        <IconButton size="small" color="success" onClick={() => saveMainField('duration')}><SaveIcon fontSize="small"/></IconButton>
                                        <IconButton size="small" color="error" onClick={() => setActiveMainEdit(null)}><CloseIcon fontSize="small"/></IconButton>
                                    </Stack>
                                ) : (
                                    <Typography fontWeight="bold" sx={editableSx} onClick={() => startMainEdit('duration', tour.duration)}>{tour.duration}</Typography>
                                )}
                            </Stack>
                        </Grid>

                        {/* Max Group Size */}
                        <Grid item xs={4} textAlign="center">
                            <Stack alignItems="center" spacing={1}>
                                <GroupIcon color="primary" />
                                <Typography variant="caption" color="textSecondary" fontWeight="bold">GROUP SIZE</Typography>
                                {activeMainEdit === 'maxGroupSize' ? (
                                    <Stack direction="row" alignItems="center">
                                        <TextField size="small" type="number" variant="standard" value={tempMainValue} onChange={(e) => setTempMainValue(e.target.value)} />
                                        <IconButton size="small" color="success" onClick={() => saveMainField('maxGroupSize')}><SaveIcon fontSize="small"/></IconButton>
                                        <IconButton size="small" color="error" onClick={() => setActiveMainEdit(null)}><CloseIcon fontSize="small"/></IconButton>
                                    </Stack>
                                ) : (
                                    <Typography fontWeight="bold" sx={editableSx} onClick={() => startMainEdit('maxGroupSize', tour.maxGroupSize)}>Max {tour.maxGroupSize} People</Typography>
                                )}
                            </Stack>
                        </Grid>

                        {/* Start Point */}
                        <Grid item xs={4} textAlign="center">
                            <Stack alignItems="center" spacing={1}>
                                <MapIcon color="primary" />
                                <Typography variant="caption" color="textSecondary" fontWeight="bold">START POINT</Typography>
                                {activeMainEdit === 'startPoint' ? (
                                    <Stack direction="row" alignItems="center">
                                        <TextField size="small" variant="standard" value={tempMainValue} onChange={(e) => setTempMainValue(e.target.value)} />
                                        <IconButton size="small" color="success" onClick={() => saveMainField('startPoint')}><SaveIcon fontSize="small"/></IconButton>
                                        <IconButton size="small" color="error" onClick={() => setActiveMainEdit(null)}><CloseIcon fontSize="small"/></IconButton>
                                    </Stack>
                                ) : (
                                    <Typography fontWeight="bold" sx={editableSx} onClick={() => startMainEdit('startPoint', tour.startPoint)}>{tour.startPoint}</Typography>
                                )}
                            </Stack>
                        </Grid>
                    </Grid>

                    {/* DESCRIPTION */}
                    <Box sx={{ mb: 6 }}>
                        <Typography variant="h5" fontWeight="bold" gutterBottom>About this tour</Typography>
                        {activeMainEdit === 'description' ? (
                            <Box>
                                <TextField
                                    multiline fullWidth variant="outlined" rows={6}
                                    value={tempMainValue} onChange={(e) => setTempMainValue(e.target.value)}
                                />
                                <Stack direction="row" spacing={1} mt={1}>
                                    <Button variant="contained" color="success" onClick={() => saveMainField('description')}>Save Description</Button>
                                    <Button variant="text" color="inherit" onClick={() => setActiveMainEdit(null)}>Cancel</Button>
                                </Stack>
                            </Box>
                        ) : (
                            <Typography variant="body1" sx={{ color: '#444', lineHeight: 1.8, fontSize: '1.1rem', ...editableSx }} onClick={() => startMainEdit('description', tour.description)}>
                                {tour.description}
                            </Typography>
                        )}
                    </Box>

                    {/* ITINERARY (STOPS) */}
                    <Box sx={{ mb: 4 }}>
                        <Typography variant="h5" fontWeight="bold" mb={3}>Itinerary</Typography>
                        <Stepper orientation="vertical">
                            {(tour.stops || []).map((stop, index) => (
                                <Step key={stop.id || index} active={true}>
                                    {editStop.index === index ? (
                                        // Edit Mode for this Stop
                                        <Box sx={{ ml: 4, mb: 2, p: 2, border: '1px dashed #ccc', borderRadius: 2 }}>
                                            <TextField
                                                size="small" fullWidth label="Stop Title" variant="standard" sx={{ mb: 2 }}
                                                value={editStop.data.title || ""}
                                                onChange={(e) => setEditStop({ ...editStop, data: { ...editStop.data, title: e.target.value }})}
                                            />
                                            <TextField
                                                size="small" fullWidth label="Description" variant="outlined" multiline rows={3} sx={{ mb: 2 }}
                                                value={editStop.data.description || ""}
                                                onChange={(e) => setEditStop({ ...editStop, data: { ...editStop.data, description: e.target.value }})}
                                            />
                                            <Stack direction="row" spacing={1}>
                                                <Button size="small" variant="contained" color="success" onClick={saveStop}>Save</Button>
                                                <Button size="small" onClick={() => setEditStop({ index: null, data: null })}>Cancel</Button>
                                                {stop.id && <IconButton color="error" size="small" onClick={() => deleteStop(stop.id)} sx={{ ml: 'auto' }}><DeleteIcon fontSize="small" /></IconButton>}
                                            </Stack>
                                        </Box>
                                    ) : (
                                        // View Mode
                                        <>
                                            <StepLabel icon={<PlaceIcon color="primary" />}>
                                                <Typography fontWeight="bold" sx={{ display: 'inline-block', ...editableSx }} onClick={() => isManager && setEditStop({ index, data: stop })}>
                                                    {stop.title}
                                                </Typography>
                                            </StepLabel>
                                            <StepContent>
                                                <Typography color="textSecondary" sx={{ ...editableSx, display: 'inline-block' }} onClick={() => isManager && setEditStop({ index, data: stop })}>
                                                    {stop.description}
                                                </Typography>
                                            </StepContent>
                                        </>
                                    )}
                                </Step>
                            ))}
                        </Stepper>

                        {/* Add Stop Button */}
                        {isManager && editStop.index === null && (
                            <Button startIcon={<AddIcon />} sx={{ mt: 2 }} onClick={() => setEditStop({ index: tour.stops?.length || 0, data: { title: "", description: "" } })}>
                                Add Stop
                            </Button>
                        )}
                    </Box>
                </Grid>

                {/* --- RIGHT COLUMN: STICKY SIDEBAR --- */}
                <Grid item xs={12} md={4}>
                    <Box sx={{ position: 'sticky', top: 24, zIndex: 10 }}>
                        <Card elevation={4} sx={{ borderRadius: 4, border: '1px solid #eee' }}>
                            <CardContent sx={{ p: 4 }}>
                                <Typography variant="h5" fontWeight="bold" gutterBottom>Book This Tour</Typography>

                                {/* TIME SLOTS */}
                                <Box sx={{ mb: 3 }}>
                                    <Typography variant="subtitle2" fontWeight="bold" color="textSecondary" mb={1}>SELECT TIME</Typography>
                                    <Stack direction="row" flexWrap="wrap" gap={1}>
                                        {(tour.slots || []).map((slot, index) => (
                                            editSlot.index === index ? (
                                                <Paper key={index} sx={{ p: 1, display: 'flex', alignItems: 'center', gap: 1, border: '1px solid #1976d2' }}>
                                                    <input type="time" value={editSlot.data.startTime || ""} onChange={(e) => setEditSlot({ ...editSlot, data: { ...editSlot.data, startTime: e.target.value }})} style={{border: 'none', outline: 'none', width: '70px'}} />
                                                    <span>-</span>
                                                    <input type="time" value={editSlot.data.endTime || ""} onChange={(e) => setEditSlot({ ...editSlot, data: { ...editSlot.data, endTime: e.target.value }})} style={{border: 'none', outline: 'none', width: '70px'}} />
                                                    <IconButton size="small" color="success" onClick={saveSlot}><SaveIcon fontSize="small" /></IconButton>
                                                    <IconButton size="small" color="error" onClick={() => setEditSlot({ index: null, data: null })}><CloseIcon fontSize="small" /></IconButton>
                                                    {slot.id && <IconButton size="small" color="error" onClick={() => deleteSlot(slot.id)}><DeleteIcon fontSize="small" /></IconButton>}
                                                </Paper>
                                            ) : (
                                                <Chip
                                                    key={slot.id || index}
                                                    label={`${slot.startTime} - ${slot.endTime}`}
                                                    variant="outlined"
                                                    color="primary"
                                                    onClick={() => isManager && setEditSlot({ index, data: slot })}
                                                    sx={{ fontWeight: 'bold', ...(isManager && { '&:hover': { bgcolor: 'rgba(25, 118, 210, 0.1)' } }) }}
                                                />
                                            )
                                        ))}
                                    </Stack>

                                    {isManager && editSlot.index === null && (
                                        <Button size="small" startIcon={<AddIcon />} sx={{ mt: 1 }} onClick={() => setEditSlot({ index: tour.slots?.length || 0, data: { startTime: "09:00", endTime: "12:00" } })}>
                                            Add Time Slot
                                        </Button>
                                    )}
                                </Box>

                                <Divider sx={{ my: 3 }} />

                                {/* PRICING TIERS */}
                                {['local', 'foreign'].map((type) => (
                                    <Box key={type} mb={3}>
                                        <Typography variant="overline" color="primary" fontWeight="bold" sx={{ letterSpacing: 1.2 }}>{type.toUpperCase()} RATES</Typography>

                                        {['ADULT', 'CHILD', 'INFANT'].map((cat) => {
                                            const isForeign = type === 'foreign';
                                            const priceIndex = (tour.prices || []).findIndex(p => p.category === cat && p.isForeigner === isForeign);
                                            const priceObj = priceIndex >= 0 ? tour.prices[priceIndex] : null;

                                            // If manager is editing THIS specific price
                                            if (editPrice.index === `${type}-${cat}`) {
                                                return (
                                                    <Box key={cat} display="flex" justifyContent="space-between" alignItems="center" mb={2}>
                                                        <Typography variant="body1" fontWeight="bold">{cat}</Typography>
                                                        <Stack direction="row" alignItems="center">
                                                            <TextField
                                                                size="small" type="number" variant="standard" sx={{ width: 60 }}
                                                                value={editPrice.data.price || 0}
                                                                onChange={(e) => setEditPrice({ ...editPrice, data: { ...editPrice.data, price: parseFloat(e.target.value) || 0 }})}
                                                            />
                                                            <IconButton size="small" color="success" onClick={savePrice}><SaveIcon fontSize="small"/></IconButton>
                                                            <IconButton size="small" color="error" onClick={() => setEditPrice({ index: null, data: null })}><CloseIcon fontSize="small"/></IconButton>
                                                        </Stack>
                                                    </Box>
                                                );
                                            }

                                            // Only show the row if a price exists OR if the user is a manager (so they can add it)
                                            if (!priceObj && !isManager) return null;

                                            return (
                                                <Box key={cat} display="flex" justifyContent="space-between" alignItems="center" mb={2}>
                                                    <Box flexGrow={1} sx={editableSx} onClick={() => isManager && setEditPrice({ index: `${type}-${cat}`, data: priceObj || { category: cat, isForeigner: isForeign, price: 0 } })}>
                                                        <Typography variant="body1" fontWeight="bold">{cat}</Typography>
                                                        <Typography variant="caption" color="textSecondary" sx={{fontSize: '0.85rem'}}>
                                                            ${priceObj?.price || 0} / person
                                                        </Typography>
                                                    </Box>
                                                    <Stack direction="row" alignItems="center" spacing={1.5} sx={{ bgcolor: '#f0f2f5', borderRadius: 2, px: 1 }}>
                                                        <IconButton size="small" onClick={() => handleBookingCount(type, cat, -1)} disabled={booking[type][cat] === 0}><RemoveIcon fontSize="small" /></IconButton>
                                                        <Typography fontWeight="bold" sx={{ minWidth: 20, textAlign: 'center' }}>{booking[type][cat]}</Typography>
                                                        <IconButton size="small" onClick={() => handleBookingCount(type, cat, 1)}><AddQtyIcon fontSize="small" /></IconButton>
                                                    </Stack>
                                                </Box>
                                            );
                                        })}
                                    </Box>
                                ))}

                                {/* CHECKOUT BUTTON */}
                                <Box sx={{ mt: 3, p: 3, bgcolor: '#003580', borderRadius: 4, color: 'white', textAlign: 'center' }}>
                                    <Typography variant="subtitle2" sx={{ opacity: 0.8 }}>Total Amount</Typography>
                                    <Typography variant="h3" fontWeight="bold" sx={{ my: 1 }}>${calculateTotal()}</Typography>
                                    <Button
                                        fullWidth variant="contained" color="warning" size="large"
                                        sx={{ mt: 2, py: 1.5, fontWeight: 'bold', borderRadius: 2, fontSize: '1.1rem', boxShadow: '0 4px 14px 0 rgba(255,167,38,0.39)' }}
                                    >
                                        RESERVE NOW
                                    </Button>
                                    <Typography variant="caption" sx={{ mt: 2, display: 'block', opacity: 0.7 }}>
                                        Max Group Size: {tour.maxGroupSize} people
                                    </Typography>
                                </Box>

                            </CardContent>
                        </Card>
                    </Box>
                </Grid>
            </Grid>
        </Container>
    );
}