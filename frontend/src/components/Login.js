import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';
import axios from "../api/axiosConfig";
import { useAuth } from "../context/AuthContext";

export default function Login() {
    const { checkAuth } = useAuth();
    const [formData, setFormData] = useState({ username: "", password: "" });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios.post("/login", formData);
            await checkAuth();
            navigate("/api/clients/view");
        } catch (err) {
            setError(err.response?.data?.error || "Invalid username or password");
        }
    }

    return (
        <div className="auth-card">
            <h2 className="text-center mb-4" style={{ color: 'var(--primary-color)', fontWeight: 'bold' }}>Log In</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Label>Username*</Form.Label>
                    <Form.Control name="username" type="text" value={formData.username} onChange={handleChange} required />
                </Form.Group>
                <Form.Group className="mb-4">
                    <Form.Label>Password*</Form.Label>
                    <Form.Control name="password" type="password" value={formData.password} onChange={handleChange} required />
                </Form.Group>
                <Button type="submit" className="w-100 btn-primary">Login</Button>
                {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
            </Form>
        </div>
    );
}