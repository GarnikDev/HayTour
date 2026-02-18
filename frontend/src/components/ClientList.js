import React, { useEffect, useState } from 'react';
import axios from '../api/axiosConfig';
import ListGroup from 'react-bootstrap/ListGroup';
import Table from 'react-bootstrap/Table';
import { useAuth } from "../context/AuthContext";

const ClientList = () => {

    const { user, loading } = useAuth();
    const [clients, setClients] = useState([]);

    const fetchClients = () => {
        axios.get("/api/clients/view")
            .then((response) => {
                console.log("RAW API RESPONSE:", response.data);
                setClients(response.data);
            })
            .catch((error) => {
                console.log("Error retrieving clients " + error);
            });
    };

    useEffect(() => {
        if (!loading && user) {
            fetchClients();
        }
    }, [loading, user]);

    if (loading) return <p>Loading...</p>;
    if (!user) return <p>Please login first</p>;

    const ShowClients = () => (
        <ListGroup>
            {clients.length === 0 ? (
                <ListGroup.Item>No clients found</ListGroup.Item>
            ) : (
                clients.map(client => (
                    <ListGroup.Item key={client.id}>
                        {client.user?.name}, {client.user?.email}, {client.user?.role}, Country: {client.country}, Language ID: {client.languageId}
                    </ListGroup.Item>
                ))
            )}
        </ListGroup>
    );

    const TableClients = () => (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Country</th>
                <th>Language ID</th>
            </tr>
            </thead>
            <tbody>
            {clients.length === 0 ? (
                <tr>
                    <td colSpan={6}>No clients found</td>
                </tr>
            ) : (
                clients.map((client, index) => (
                    <tr key={client.id}>
                        <td>{index + 1}</td>
                        <td>{client.user?.name}</td>
                        <td>{client.user?.email}</td>
                        <td>{client.user?.role}</td>
                        <td>{client.country}</td>
                        <td>{client.languageId}</td>
                    </tr>
                ))
            )}
            </tbody>
        </Table>
    );

    return (
        <div>
            <h3>Clients List</h3>
            <ShowClients />
            <h3>Clients Table</h3>
            <TableClients />
        </div>
    );
};

export default ClientList;
