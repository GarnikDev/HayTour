import React, { useState, useEffect, useMemo } from 'react';
import {
    Box, Typography, Container, Grid, Card, CardMedia,
    CardContent, Fab, useTheme, useMediaQuery
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate } from 'react-router-dom';
import axios from "../api/axiosConfig";
import SearchBar from './SearchBar';

export default function Tours() {
    const [tours, setTours] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const navigate = useNavigate();

    const theme = useTheme();
    const isLgUp = useMediaQuery(theme.breakpoints.up('lg'));
    const isMdUp = useMediaQuery(theme.breakpoints.up('md'));
    const isSmUp = useMediaQuery(theme.breakpoints.up('sm'));

    const CARD_HEIGHT = 300;
    const IMAGE_HEIGHT = 160;

    useEffect(() => {
        axios.get("/api/tourOffers/view")
            .then(res => setTours(res.data))
            .catch(err => console.error("Error fetching tours", err));
    }, []);

    const filteredTours = useMemo(() => {
        if (!searchTerm) return tours;
        const term = searchTerm.toLowerCase();
        const titleMatches = [];
        const descMatches = [];

        tours.forEach(tour => {
            if (tour.title?.toLowerCase().includes(term)) titleMatches.push(tour);
            else if (tour.description?.toLowerCase().includes(term)) descMatches.push(tour);
        });
        return [...titleMatches, ...descMatches];
    }, [tours, searchTerm]);

    const allItems = [{ id: 'create-card-static', isCreateCard: true }, ...filteredTours];

    const itemsPerRow = isLgUp ? 4 : (isMdUp ? 3 : (isSmUp ? 2 : 1));
    const rows = [];
    for (let i = 0; i < allItems.length; i += itemsPerRow) {
        rows.push(allItems.slice(i, i + itemsPerRow));
    }

    const commonCardStyle = {
        height: CARD_HEIGHT,
        width: '280px',
        display: 'flex',
        flexDirection: 'column',
        borderRadius: 'var(--border-radius, 16px)',
        cursor: 'pointer',
        transition: 'all 0.3s ease-in-out',
        overflow: 'hidden',
        backgroundColor: 'var(--card-bg, #ffffff)',
        border: '1px solid rgba(0,0,0,0.08)',
        // HOVER EFFECTS (General)
        '&:hover': {
            transform: 'translateY(-4px)',
            boxShadow: '0 12px 24px rgba(0,0,0,0.12)',
        },
        // Regular Card Image Effects
        '&:hover .image-container': {
            padding: 0,
        },
        '&:hover .tour-image': {
            transform: 'scale(1.1)',
            borderRadius: 0,
        },
        // Regular Card Content Effects
        '&:hover .content-area': {
            backgroundColor: 'var(--primary-color)',
            color: 'white',
        },
        '&:hover .content-area .MuiTypography-root': {
            color: 'white !important',
        },
        // Create Tour Card Specific Hover Effects
        '&:hover.create-card': {
            backgroundColor: 'var(--primary-color)',
            borderColor: 'var(--primary-color)',
        },
        '&:hover.create-card .create-icon, &:hover.create-card .create-text': {
            color: 'white !important',
        }
    };

    return (
        <Container maxWidth="lg" sx={{ py: 6 }}>
            <Box mb={6} textAlign="center">
                <Typography
                    variant="h2"
                    fontWeight={900}
                    color="var(--primary-color)"
                    sx={{ fontSize: { xs: '2rem', md: '3.5rem' }, mb: 1 }}
                >
                    Tour Management
                </Typography>
                <Typography variant="body1" color="text.secondary">
                    Quickly manage and update your tour offerings
                </Typography>
            </Box>

            <SearchBar searchTerm={searchTerm} onSearchChange={setSearchTerm} />

            <Box sx={{ borderRadius: 'var(--border-radius-lg, 24px)', overflow: 'hidden' }}>
                {rows.map((row, rowIndex) => (
                    <Box key={rowIndex} sx={{ backgroundColor: rowIndex % 2 === 0 ? '#f5f7fa' : 'transparent', py: 4, px: { xs: 2, md: 4 } }}>
                        <Grid container spacing={3} alignItems="stretch">
                            {row.map((item) => (
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
                                            <AddIcon
                                                className="create-icon"
                                                sx={{
                                                    fontSize: 50,
                                                    color: 'var(--primary-color)',
                                                    mb: 1,
                                                    transition: 'color 0.3s ease-in-out'
                                                }}
                                            />
                                            <Typography
                                                className="create-text"
                                                variant="h6"
                                                fontWeight={700}
                                                sx={{
                                                    color: 'var(--primary-color)',
                                                    transition: 'color 0.3s ease-in-out'
                                                }}
                                            >
                                                Create Tour
                                            </Typography>
                                        </Card>
                                    ) : (
                                        <Card sx={commonCardStyle} onClick={() => navigate(`/edit-tour/${item.id}`)}>
                                            <Box
                                                className="image-container"
                                                sx={{
                                                    padding: '12px',
                                                    transition: 'padding 0.3s ease-in-out',
                                                    overflow: 'hidden',
                                                    height: IMAGE_HEIGHT
                                                }}
                                            >
                                                <CardMedia
                                                    className="tour-image"
                                                    component="img"
                                                    height="100%"
                                                    image={item.imageUrl}
                                                    alt={item.title}
                                                    sx={{
                                                        objectFit: 'cover',
                                                        borderRadius: '12px',
                                                        transition: 'all 0.3s ease-in-out',
                                                    }}
                                                />
                                            </Box>

                                            <CardContent
                                                className="content-area"
                                                sx={{
                                                    flexGrow: 1,
                                                    display: 'flex',
                                                    flexDirection: 'column',
                                                    justifyContent: 'space-between',
                                                    p: 2,
                                                    transition: 'all 0.3s ease-in-out'
                                                }}
                                            >
                                                <Box>
                                                    <Typography variant="subtitle1" fontWeight={700} noWrap sx={{ transition: 'color 0.3s', color: 'var(--text-main)', fontSize: '1rem' }}>
                                                        {item.title}
                                                    </Typography>
                                                    <Typography variant="caption" noWrap display="block" sx={{ transition: 'color 0.3s', color: 'text.secondary' }}>
                                                        {item.place}
                                                    </Typography>
                                                </Box>
                                                <Typography variant="h6" fontWeight={800} sx={{ transition: 'color 0.3s', color: 'var(--primary-color)', fontSize: '1.1rem' }}>
                                                    ${item.basePrice}
                                                </Typography>
                                            </CardContent>
                                        </Card>
                                    )}
                                </Grid>
                            ))}
                        </Grid>
                    </Box>
                ))}
            </Box>

            <Fab
                color="primary"
                sx={{ position: 'fixed', bottom: 32, right: 32, display: { xs: 'flex', md: 'none' } }}
                onClick={() => navigate('/create-tour')}
            >
                <AddIcon />
            </Fab>
        </Container>
    );
}