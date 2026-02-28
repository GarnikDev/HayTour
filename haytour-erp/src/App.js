import { ThemeProvider } from '@mui/material/styles';
import { activeTheme, createAppTheme } from './styles/styles';
import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AuthProvider } from "./context/AuthContext";
import { Container } from '@mui/material';
import './App.css';

import ButtonAppBar from "./ButtonAppBar";
import Footer from "./components/Footer";
import Login from "./components/Login";
import Register from "./components/Register";
import Logout from "./components/Logout";
import Tours from "./components/Tours";
import CreateTour from "./components/CreateTour";
import EditTour from "./components/EditTour";

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
              <Container component="main" sx={{ mt: 4, mb: 4, minHeight: '80vh' }}>
                <Routes>
                  <Route path="/" element={<Tours />} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/register" element={<Register />} />
                  <Route path="/logout" element={<Logout />} />
                  <Route path="/tours" element={<Tours />} />
                  <Route path="/create-tour" element={<CreateTour />} />
                  <Route path="/edit-tour/:id" element={<EditTour />} />
                </Routes>
              </Container>
              <Footer />
            </BrowserRouter>
          </div>
        </AuthProvider>
      </ThemeProvider>
  );
}

export default App;