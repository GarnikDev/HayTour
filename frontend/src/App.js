import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import {Routes, Route, BrowserRouter} from 'react-router-dom';
import Register from "./components/Register";
import RegistrationSuccessful from "./components/RegistrationSuccessful";
import ButtonAppBar from "./ButtonAppBar";
import Login from "./components/Login";
import ClientList from "./components/ClientList";


function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <ButtonAppBar/>
                <div className="App-header">
                    <Routes>
                        <Route path="/api/clients/view" element={<ClientList></ClientList>} />
                        <Route path="/register" element={<Register></Register>}/>
                        <Route path="/login" element={<Login></Login>}/>
                        <Route path="/registrationSuccessful" element={<RegistrationSuccessful></RegistrationSuccessful>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;
