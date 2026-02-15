import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Login = () => {
    const baseUrl = "http://localhost:8081/login";
    const [user, setUser] = useState();

    const login = () => {
        <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" placeholder="Enter email" />
                <Form.Text className="text-muted">
                    We'll never share your email with anyone else.
                </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>
    }

    const fetchClients = () => {
        axios.get(baseUrl)
            .then((response) => {
                console.log("RAW API RESPONSE:", response.data);
                setUser(response.data);
            })
            .catch((error) => {
                console.log("Error retrieving clients " + error);
            });
    };

    return (
        <div>
            <h1>Log in</h1>
            <Login />
        </div>
    );
};

export default Login;