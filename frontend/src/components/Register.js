import Form from 'react-bootstrap/Form';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/axiosConfig"; // Use the instance
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';

export default function Register() {
    const [formData, setFormData] = useState({
        username: "",
        phone: "",
        email: "",
        password: "",
        confirmPassword: "",
        idNumber: ""
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

        if (formData.password !== formData.confirmPassword) {
            setError("Passwords don't match");
            return;
        }
        setError("");

        try {
            const response = await axios.post("/register", formData);
            if (response.status === 201) {
                navigate("/registrationSuccessful");
            } else {
                setError(response.data?.error || "Registration failed");
            }
        } catch (err) {
            setError(err.response?.data?.error || 'An error occurred during registration');
        }
    }

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2 className="text-center mb-4" style={{ color: 'var(--primary-color)', fontWeight: 'bold' }}>
                    Create Account
                </h2>
                <Form onSubmit={handleSubmit}>
                    {/* ID and Phone in one row for a cleaner look */}
                    <div className="row">
                        <div className="col-md-6">
                            <Form.Group className="mb-3" controlId="formGroupUsername">
                                <Form.Label>Username*</Form.Label>
                                <Form.Control name="username" type="text" value={formData.username} onChange={handleChange} required />
                            </Form.Group>
                        </div>
                        <div className="col-md-6">
                            <Form.Group className="mb-3" controlId="formGroupPhone">
                                <Form.Label>Phone</Form.Label>
                                <Form.Control name="phone" type="text" value={formData.phone} onChange={handleChange} />
                            </Form.Group>
                        </div>
                    </div>

                    <Form.Group className="mb-3" controlId="formGroupEmail">
                        <Form.Label>Email address*</Form.Label>
                        <Form.Control name="email" type="email" value={formData.email} onChange={handleChange} required />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formGroupPassword">
                        <Form.Label>Password*</Form.Label>
                        <Form.Control name="password" type="password" value={formData.password} onChange={handleChange} required />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="formGroupConfirmPassword">
                        <Form.Label>Confirm Password*</Form.Label>
                        <Form.Control name="confirmPassword" type="password" value={formData.confirmPassword} onChange={handleChange} required />
                    </Form.Group>

                    <Button type="submit" className="w-100 btn-primary py-2">
                        Register
                    </Button>
                    {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
                </Form>
            </div>
        </div>
    );
}