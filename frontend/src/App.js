import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Register from "./components/Register";
import RegistrationSuccessful from "./components/RegistrationSuccessful";
import ButtonAppBar from "./ButtonAppBar";


function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <div className="App-header">
                    <ButtonAppBar/>
                    <Routes>
                        <Route path="/register" element={<Register></Register>}/>
                        <Route path="/registrationSuccessful" element={<RegistrationSuccessful></RegistrationSuccessful>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;
