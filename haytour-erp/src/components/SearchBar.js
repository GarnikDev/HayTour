import React from 'react';
import { TextField, InputAdornment, Box } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

export default function SearchBar({ searchTerm, onSearchChange }) {
    return (
        <Box display="flex" justifyContent="center" mb={6}>
            <TextField
                fullWidth
                variant="outlined"
                placeholder="Search by title or description..."
                value={searchTerm}
                onChange={(e) => onSearchChange(e.target.value)}
                InputProps={{
                    startAdornment: (
                        <InputAdornment position="start">
                            <SearchIcon color="action" />
                        </InputAdornment>
                    ),
                }}
                sx={{
                    maxWidth: 700,
                    backgroundColor: 'white',
                    borderRadius: '12px',
                    '& .MuiOutlinedInput-root': { borderRadius: '12px' },
                    boxShadow: '0px 2px 10px rgba(0, 0, 0, 0.05)'
                }}
            />
        </Box>
    );
}