import { ThemeProvider } from '@mui/material/styles';
import { activeTheme, createAppTheme } from './styles/styles';
import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AuthProvider } from "./context/AuthContext";
import './App.css';

// Components
import ButtonAppBar from "./ButtonAppBar";
import Footer from "./components/Footer";
import Home from "./components/Home";
import Login from "./components/Login";
import Register from "./components/Register";
import Logout from "./components/Logout";
import ClientList from "./components/ClientList";
import WalkingTours from './components/WalkingTours';
import BusTours from './components/BusTours';
import BikeTours from './components/BikeTours';
import TourDetails from './components/TourDetails';

function App() {const muiTheme = createAppTheme();

    useEffect(() => {
        const root = document.documentElement;
        Object.entries(activeTheme).forEach(([key, value]) => {
            root.style.setProperty(key, value);
        });
    }, []);

    return (
        <ThemeProvider theme={muiTheme}>
            <AuthProvider>
                <div className="App">
                    <BrowserRouter>
                        <ButtonAppBar />
                        <main>
                            <Routes>
                                <Route path="/" element={<Home />} />
                                <Route path="/api/clients/view" element={<ClientList />} />
                                <Route path="/register" element={<Register />} />
                                <Route path="/login" element={<Login />} />
                                <Route path="/logout" element={<Logout />} />
                                <Route path="/tours/walk" element={<WalkingTours />} />
                                <Route path="/tours/bus" element={<BusTours/>}/>
                                <Route path="/tours/bike" element={<BikeTours/>}/>
                                <Route path="/tours/:id" element={<TourDetails />} />
                            </Routes>
                        </main>
                        <Footer />
                    </BrowserRouter>
                </div>
            </AuthProvider>
        </ThemeProvider>
    );
}

export default App;