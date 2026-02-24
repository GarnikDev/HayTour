import Form from 'react-bootstrap/Form';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/axiosConfig"; // Use the instance
import Button from 'react-bootstrap/Button';
import { useAuth } from "../context/AuthContext";
import Alert from 'react-bootstrap/Alert';

export default function Login() {
    const { checkAuth } = useAuth();

    const [formData, setFormData] = useState({
        username: "",
        password: ""
    });

    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError(""); // Clear previous error

        try {
            await axios.post("/login", formData); // Simplified: formData has username/password
            await checkAuth(); // Updates auth state
            navigate("/api/clients/view");
        } catch (err) {
            setError(err.response?.data?.error || "Invalid username or password");
        }
    }

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2 className="text-center mb-4" style={{ color: 'var(--primary-color)', fontWeight: 'bold' }}>
                    Log In
                </h2>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="formGroupUsername">
                        <Form.Label>Username*</Form.Label>
                        <Form.Control
                            name="username"
                            type="text"
                            placeholder="Enter username"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="formGroupPassword">
                        <Form.Label>Password*</Form.Label>
                        <Form.Control
                            name="password"
                            type="password"
                            placeholder="Enter password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </Form.Group>

                    <Button type="submit" className="w-100 btn-primary py-2">
                        Login
                    </Button>

                    {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
                </Form>
            </div>
        </div>
    );
}