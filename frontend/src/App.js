import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Login';

function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <h1>My Client Management App</h1>
                </header>

                <main>
                    <Routes>
                        {/* Redirect from / to /login */}
                        <Route path="/" element={<Navigate to="/login" replace />} />
                        {/* Client list page */}
                        <Route path="/login" element={<Login />} />
                    </Routes>
                </main>

                <footer>
                    <p>© 2026 HayTour</p>
                </footer>
            </div>
        </Router>
    );
}

export default App;
