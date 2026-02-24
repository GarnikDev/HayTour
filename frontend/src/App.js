import React from 'react';
import { AuthProvider } from "./context/AuthContext";
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Register from "./components/Register";
import RegistrationSuccessful from "./components/RegistrationSuccessful";
import ButtonAppBar from "./ButtonAppBar";
import Login from "./components/Login";
import ClientList from "./components/ClientList";
import Logout from "./components/Logout";
import { useEffect } from "react";
import { activeTheme } from './styles/styles';
import Home from "./components/Home";
import Footer from "./components/Footer";
import WalkingTours from './components/WalkingTours'; // adjust path as needed
import BusTours from './components/BusTours';
import BikeTours from './components/BikeTours';

function App() {
    useEffect(() => {
        const root = document.documentElement;
        Object.entries(activeTheme).forEach(([key, value]) => {
            root.style.setProperty(key, value);
        });
    }, []);

    return (
        <AuthProvider>
            <div className="App">
                <BrowserRouter>
                    <ButtonAppBar />
                    {/* The main content area */}
                    <main style={{ minHeight: '80vh' }}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/api/clients/view" element={<ClientList />} />
                            <Route path="/register" element={<Register />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/logout" element={<Logout />} />
                            <Route path="/registrationSuccessful" element={<RegistrationSuccessful />} />
                            <Route path="/tours/walk" element={<WalkingTours />} />
                            <Route path="/tours/bus" element={<BusTours/>}/>
                            <Route path="/tours/bike" element={<BikeTours/>}/>
                        </Routes>
                    </main>
                    <Footer />
                </BrowserRouter>
            </div>
        </AuthProvider>
    );
}

export default App;
