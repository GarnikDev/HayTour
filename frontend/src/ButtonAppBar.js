import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './context/AuthContext';

export default function ButtonAppBar() {

    const { user } = useAuth();
    const navigate = useNavigate();

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}
                    >
                        HayTour
                    </Typography>

                    {!user ? (
                    <>
                        <Button color="inherit" onClick={() => navigate('/login')}>Log in</Button>
                        <Button color="inherit" onClick={() => navigate('/register')}>Sign Up</Button>
                    </>
                    ) : (
                        <Button color="inherit" onClick={() => navigate('/logout')}>Log Out</Button>
                    )}

                </Toolbar>
            </AppBar>
        </Box>
    );
}
