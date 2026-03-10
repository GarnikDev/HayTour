import React, { useState, useEffect, useRef } from 'react';
import {
    Box, Typography, Container, Card, CardMedia,
    CardContent, IconButton, Button
} from '@mui/material';
import DirectionsBikeIcon from '@mui/icons-material/DirectionsBike';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import DirectionsWalkIcon from '@mui/icons-material/DirectionsWalk';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

import { useNavigate } from 'react-router-dom';
import axios from "../api/axiosPublic";

export default function Home() {
    const [tours, setTours] = useState([]);
    const navigate = useNavigate();

    // --- Carousel Logic States ---
    const scrollRef = useRef(null);
    const [isDragging, setIsDragging] = useState(false);
    const [hasMoved, setHasMoved] = useState(false); // Track if user is dragging or clicking
    const [startX, setStartX] = useState(0);
    const [scrollLeft, setScrollLeft] = useState(0);

    const CARD_HEIGHT = 380;
    const IMAGE_HEIGHT = 220;
    const CARD_WIDTH = 340;
    const GAP = 24;

    useEffect(() => {
        axios.get("/api/tourOffers/view")
            .then((response) => setTours(response.data))
            .catch((error) => console.log("Error retrieving tours " + error));
    }, []);

    const scroll = (direction) => {
        if (scrollRef.current) {
            const scrollAmount = CARD_WIDTH + GAP;
            scrollRef.current.scrollBy({
                left: direction === 'left' ? -scrollAmount : scrollAmount,
                behavior: 'smooth'
            });
        }
    };

    const handleMouseDown = (e) => {
        setIsDragging(true);
        setHasMoved(false); // Reset on mouse down
        setStartX(e.pageX - scrollRef.current.offsetLeft);
        setScrollLeft(scrollRef.current.scrollLeft);
    };

    const handleMouseLeaveOrUp = () => setIsDragging(false);

    const handleMouseMove = (e) => {
        if (!isDragging) return;
        e.preventDefault();
        const x = e.pageX - scrollRef.current.offsetLeft;
        const walk = (x - startX) * 1.2;

        // If the mouse moves more than 5px, it's a drag, not a click
        if (Math.abs(walk) > 5) setHasMoved(true);

        scrollRef.current.scrollLeft = scrollLeft - walk;
    };

    const getTourIcon = (type) => {
        const iconStyle = { fontSize: '1.2rem', ml: 1, verticalAlign: 'middle' };
        switch (type) {
            case 'BIKE': return <DirectionsBikeIcon sx={iconStyle} />;
            case 'BUS': return <DirectionsBusIcon sx={iconStyle} />;
            case 'WALK': return <DirectionsWalkIcon sx={iconStyle} />;
            default: return null;
        }
    };

    const commonCardStyle = {
        height: CARD_HEIGHT,
        width: CARD_WIDTH,
        minWidth: CARD_WIDTH,
        display: 'flex',
        flexDirection: 'column',
        borderRadius: '16px',
        cursor: 'default',
        transition: 'all 0.3s ease-in-out',
        userSelect: 'none',
        backgroundColor: '#ffffff',
        border: '1px solid rgba(0,0,0,0.08)',
        '&:hover': {
            transform: 'translateY(-4px)',
            boxShadow: '0 12px 24px rgba(0,0,0,0.12)',
        },
        '&:hover .image-container': { padding: 0 },
        '&:hover .tour-image': { transform: 'scale(1.1)', borderRadius: 0 },
        '&:hover .content-area': { backgroundColor: 'var(--primary-color)', color: 'white' },
        '&:hover .content-area .MuiTypography-root': { color: 'white !important' },
        '&:hover .tour-type-icon': { color: 'white' },
        '&:hover .see-more-btn': {
            opacity: 1,
            transform: 'translateY(0)',
            pointerEvents: 'auto'
        }
    };

    const arrowButtonStyle = {
        position: 'absolute',
        top: '50%',
        transform: 'translateY(-50%)',
        zIndex: 10,
        backgroundColor: 'rgba(255, 255, 255, 0.8)',
        color: 'var(--primary-color)',
        opacity: 0,
        transition: 'opacity 0.3s ease, background-color 0.2s',
        boxShadow: '0 4px 12px rgba(0,0,0,0.15)',
        '&:hover': {
            backgroundColor: 'white',
            transform: 'translateY(-50%) scale(1.1)',
        }
    };

    return (
        <Box sx={{ width: '100%', overflowX: 'hidden' }}>
            <Box sx={{
                height: '60vh', width: '100%', position: 'relative',
                backgroundImage: 'url("https://images.unsplash.com/photo-1563811771046-ba984ff30900?q=80&w=2070")',
                backgroundSize: 'cover', backgroundPosition: 'center', display: 'flex', alignItems: 'center', justifyContent: 'center',
                '&::before': { content: '""', position: 'absolute', top: 0, left: 0, right: 0, bottom: 0, backgroundColor: 'rgba(0,0,0,0.4)' }
            }}>
                <Typography variant="h2" sx={{ position: 'relative', color: 'white', fontWeight: 900, textAlign: 'center', px: 2, fontSize: { xs: '2.5rem', md: '4rem' } }}>
                    Explore Armenia with HayTour
                </Typography>
            </Box>

            <Container maxWidth={false} sx={{ py: 8, px: { xs: 2, sm: 4, md: 8 } }}>
                <Typography variant="h4" fontWeight={800} color="var(--primary-color)" mb={4}>
                    Featured Tours
                </Typography>

                <Box sx={{ position: 'relative', '&:hover .nav-btn': { opacity: 1 } }}>
                    <IconButton className="nav-btn" onClick={() => scroll('left')} sx={{ ...arrowButtonStyle, left: -20 }}>
                        <ArrowBackIosNewIcon fontSize="small" />
                    </IconButton>

                    <IconButton className="nav-btn" onClick={() => scroll('right')} sx={{ ...arrowButtonStyle, right: -20 }}>
                        <ArrowForwardIosIcon fontSize="small" />
                    </IconButton>

                    <Box sx={{
                        position: 'relative',
                        maskImage: 'linear-gradient(to right, transparent, black 5%, black 95%, transparent)',
                        WebkitMaskImage: 'linear-gradient(to right, transparent, black 5%, black 95%, transparent)',
                    }}>
                        <Box
                            ref={scrollRef}
                            onMouseDown={handleMouseDown}
                            onMouseLeave={handleMouseLeaveOrUp}
                            onMouseUp={handleMouseLeaveOrUp}
                            onMouseMove={handleMouseMove}
                            sx={{
                                display: 'flex', gap: 3, pb: 4, pt: 1, px: '5%',
                                overflowX: 'auto',
                                cursor: isDragging ? 'grabbing' : 'grab',
                                scrollBehavior: isDragging ? 'auto' : 'smooth',
                                msOverflowStyle: 'none',
                                scrollbarWidth: 'none',
                                '&::-webkit-scrollbar': { display: 'none' },
                            }}
                        >
                            {tours.map((tour) => (
                                <Card key={tour.id} sx={commonCardStyle}>
                                    <Box className="image-container" sx={{ padding: '12px', transition: '0.3s', height: IMAGE_HEIGHT, overflow: 'hidden' }}>
                                        <CardMedia
                                            className="tour-image"
                                            component="img"
                                            height="100%"
                                            image={tour.imageUrl}
                                            draggable={false}
                                            sx={{ objectFit: 'cover', borderRadius: '12px', transition: '0.3s', WebkitUserDrag: 'none' }}
                                        />
                                    </Box>

                                    <CardContent className="content-area" sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column', justifyContent: 'space-between', p: 2, transition: '0.3s' }}>
                                        <Box>
                                            <Box sx={{ display: 'flex', alignItems: 'center', mb: 0.5 }}>
                                                <Typography variant="subtitle1" fontWeight={700} noWrap sx={{ flex: 1 }}>
                                                    {tour.title}
                                                </Typography>
                                                <Box className="tour-type-icon" sx={{ color: 'var(--primary-color)' }}>
                                                    {getTourIcon(tour.type)}
                                                </Box>
                                            </Box>
                                            <Typography variant="caption" noWrap display="block" color="text.secondary">
                                                {tour.place || "Armenia"}
                                            </Typography>
                                        </Box>

                                        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                            <Typography variant="h6" fontWeight={800} color="var(--primary-color)" sx={{ transition: 'color 0.3s' }}>
                                                ${tour.basePrice}
                                            </Typography>

                                            <Button
                                                className="see-more-btn"
                                                variant="contained"
                                                size="small"
                                                onClick={() => {
                                                    // FIX: Ensure path matches App.js route and skip if user was dragging
                                                    if (!hasMoved) navigate(`/tours/${tour.id}`);
                                                }}
                                                sx={{
                                                    opacity: 0,
                                                    transform: 'translateY(10px)',
                                                    transition: 'all 0.3s ease-in-out',
                                                    pointerEvents: 'none',
                                                    backgroundColor: 'white',
                                                    color: 'var(--primary-color)',
                                                    borderRadius: '20px',
                                                    fontWeight: 'bold',
                                                    textTransform: 'none',
                                                    '&:hover': { backgroundColor: '#f0f0f0' }
                                                }}
                                            >
                                                See details
                                            </Button>
                                        </Box>
                                    </CardContent>
                                </Card>
                            ))}
                        </Box>
                    </Box>
                </Box>
            </Container>
        </Box>
    );
}