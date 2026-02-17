import Form from 'react-bootstrap/Form';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Button from 'react-bootstrap/Button';

export default function Login() {

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
        setError("");

        try {
            const response = await axios.post(
                "http://localhost:8081/login",
                {
                    username: formData.username,
                    password: formData.password
                },
                { withCredentials: true } // Important: send cookie automatically
            );

            if (response.status === 200) {
                // No need to store token if using HttpOnly cookie
                navigate("/api/clients/view"); // replace with your protected route
            } else {
                setError("Invalid credentials");
            }

        } catch (err) {
            setError("Invalid username or password");
        }
    }

    return (
        <Form onSubmit={handleSubmit} style={{ width: "300px", margin: "0 auto" }}>

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

            <Form.Group className="mb-3" controlId="formGroupPassword">
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

            <Button type="submit" className="w-100">Login</Button>

            {error && <p style={{ color: "red", marginTop: "10px" }}>{error}</p>}
        </Form>
    );
}
