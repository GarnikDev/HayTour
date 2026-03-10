import React, { useState, useEffect, useMemo } from 'react';
import {
    Box, Typography, Container, Grid, Card, CardMedia,
    CardContent, Fab, useTheme, useMediaQuery
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import DirectionsBikeIcon from '@mui/icons-material/DirectionsBike';
import DirectionsWalkIcon from '@mui/icons-material/DirectionsWalk';
import { useNavigate } from 'react-router-dom';
import axios from "../api/axiosConfig";
import SearchBar from './SearchBar';

export default function Tours() {
    const [tours, setTours] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const navigate = useNavigate();

    const theme = useTheme();
    const CARD_HEIGHT = 300;
    const IMAGE_HEIGHT = 160;

    const getTourIcon = (type) => {
        const iconStyle = { fontSize: '1.2rem', ml: 1, verticalAlign: 'middle' };
        switch (type) {
            case 'BIKE': return <DirectionsBikeIcon sx={iconStyle} />;
            case 'BUS': return <DirectionsBusIcon sx={iconStyle} />;
            case 'WALK': return <DirectionsWalkIcon sx={iconStyle} />;
            default: return null;
        }
    };

    useEffect(() => {
        axios.get("/api/tourOffers/view")
            .then(res => setTours(res.data))
            .catch(err => console.error("Error fetching tours", err));
    }, []);

    const filteredTours = useMemo(() => {
        if (!searchTerm) return tours;
        const term = searchTerm.toLowerCase();
        return tours.filter(tour =>
            tour.title?.toLowerCase().includes(term) ||
            tour.description?.toLowerCase().includes(term)
        );
    }, [tours, searchTerm]);

    const allItems = [{ id: 'create-card-static', isCreateCard: true }, ...filteredTours];

    const commonCardStyle = {
        height: CARD_HEIGHT,
        width: '280px', // Fixed width to match your original design
        display: 'flex',
        flexDirection: 'column',
        borderRadius: '16px',
        cursor: 'pointer',
        transition: 'all 0.3s ease-in-out',
        overflow: 'hidden',
        backgroundColor: '#ffffff',
        border: '1px solid rgba(0,0,0,0.08)',
        boxSizing: 'border-box',
        '&:hover': {
            transform: 'translateY(-4px)',
            boxShadow: '0 12px 24px rgba(0,0,0,0.12)',
        },
        '&:hover .image-container': { padding: 0 },
        '&:hover .tour-image': { transform: 'scale(1.1)', borderRadius: 0 },
        '&:hover .content-area': { backgroundColor: 'var(--primary-color)', color: 'white' },
        '&:hover .content-area .MuiTypography-root': { color: 'white !important' },
        '&:hover .tour-type-icon': { color: 'white !important' },
        '&:hover.create-card': { backgroundColor: 'var(--primary-color)', borderColor: 'var(--primary-color)' },
        '&:hover.create-card .create-icon, &:hover.create-card .create-text': { color: 'white !important' }
    };

    return (
        <Container maxWidth="lg" sx={{ py: 6 }}>
            <Box mb={6} textAlign="center">
                <Typography variant="h2" fontWeight={900} color="var(--primary-color)" sx={{ fontSize: { xs: '2rem', md: '3.5rem' }, mb: 1 }}>
                    Tour Management
                </Typography>
                <Typography variant="body1" color="text.secondary">
                    Manage, edit, and create your tour offerings
                </Typography>
            </Box>

            <SearchBar searchTerm={searchTerm} onSearchChange={setSearchTerm} />

            <Box sx={{
                backgroundColor: '#f5f7fa',
                borderRadius: '24px',
                p: { xs: 2, md: 4 },
                minHeight: '400px',
                mt: 4
            }}>
                <Grid container spacing={3}>
                    {allItems.map((item) => (
                        <Grid item xs={12} sm={6} md={4} lg={3} key={item.id} sx={{ display: 'flex', justifyContent: 'center' }}>
                            {item.isCreateCard ? (
                                <Card
                                    className="create-card"
                                    sx={{
                                        ...commonCardStyle,
                                        justifyContent: 'center',
                                        alignItems: 'center',
                                        border: '2px dashed var(--primary-color)',
                                        backgroundColor: 'rgba(0, 53, 128, 0.01)',
                                        boxShadow: 'none',
                                    }}
                                    onClick={() => navigate('/create-tour')}
                                >
                                    <Box sx={{ textAlign: 'center' }}>
                                        <AddIcon className="create-icon" sx={{ fontSize: 50, color: 'var(--primary-color)', mb: 1, transition: '0.3s' }} />
                                        <Typography className="create-text" variant="h6" fontWeight={700} sx={{ color: 'var(--primary-color)', transition: '0.3s' }}>
                                            Create Tour
                                        </Typography>
                                    </Box>
                                </Card>
                            ) : (
                                <Card sx={commonCardStyle} onClick={() => navigate(`/edit-tour/${item.id}`)}>
                                    <Box className="image-container" sx={{ padding: '12px', transition: '0.3s', overflow: 'hidden', height: IMAGE_HEIGHT }}>
                                        <CardMedia className="tour-image" component="img" height="100%" image={item.imageUrl} alt={item.title} sx={{ objectFit: 'cover', borderRadius: '12px', transition: '0.3s' }} />
                                    </Box>
                                    <CardContent className="content-area" sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column', justifyContent: 'space-between', p: 2, transition: '0.3s' }}>
                                        <Box>
                                            <Box sx={{ display: 'flex', alignItems: 'center', mb: 0.5 }}>
                                                <Typography variant="subtitle1" fontWeight={700} noWrap sx={{ color: 'var(--text-main)', fontSize: '1rem', flex: 1 }}>
                                                    {item.title}
                                                </Typography>
                                                <Box className="tour-type-icon" sx={{ color: 'var(--primary-color)', display: 'flex' }}>
                                                    {getTourIcon(item.type)}
                                                </Box>
                                            </Box>
                                            <Typography variant="caption" noWrap display="block" sx={{ color: 'text.secondary' }}>
                                                {item.place}
                                            </Typography>
                                        </Box>
                                        <Typography variant="h6" fontWeight={800} sx={{ color: 'var(--primary-color)', fontSize: '1.1rem' }}>
                                            ${item.basePrice}
                                        </Typography>
                                    </CardContent>
                                </Card>
                            )}
                        </Grid>
                    ))}
                </Grid>
            </Box>

            <Fab color="primary" sx={{ position: 'fixed', bottom: 32, right: 32, display: { xs: 'flex', md: 'none' } }} onClick={() => navigate('/create-tour')}>
                <AddIcon />
            </Fab>
        </Container>
    );
}