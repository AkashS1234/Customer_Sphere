// src/Dashboard.jsx
import React from "react";
import { Container, Typography, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Dashboard = ({ onLogout, user }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout(); // Call the onLogout function passed from App.js
    navigate("/"); // Redirect to login page after logout
  };

  return (
    <Container maxWidth="lg">
      <Typography variant="h3" gutterBottom>
      Hi {user?.name}
      </Typography>
      <Box sx={{ mt: 4 }}>
        <Button variant="contained" color="primary" onClick={handleLogout}>
          Logout
        </Button>
      </Box>
    </Container>
  );
};

export default Dashboard;
