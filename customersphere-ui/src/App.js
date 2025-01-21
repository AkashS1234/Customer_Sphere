import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom"; // Import Navigate for redirect
import LoginForm from "./components/LoginForm";
import SignUpForm from "./components/SignUpForm";
import Dashboard from "./components/Dashboard"; // Import Dashboard component
import './App.css';
import AdminDashboard from "./components/AdminDashboard";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Manage authentication state
  const [user, setUser] = useState()
  const handleLogin = () => {
    setIsAuthenticated(true); // Simulate successful login
  };

  const handleLogout = () => {
    setIsAuthenticated(false); // Simulate logout
    setUser (null)
  }; 

  return (
    <Router>
      <div className="App">
        <h1 className="loginheader">{ user ? user?.role === "ADMIN" ?"Welcome to Admin Dashboard":"Welcome to Profile Page":"Welcome to Customer Sphere"}</h1>
        <Routes>
          <Route path="/" element={<LoginForm user={user} setUser={setUser} onLogin={handleLogin} />} /> {/* Pass login handler to LoginForm */}
          <Route path="/signup" element={<SignUpForm />} />
          <Route
            path="/dashboard"
            element={isAuthenticated ? <Dashboard user={user} setUser={setUser} onLogout={handleLogout} /> : <Navigate to="/" />} // Redirect to login if not authenticated
          />
          <Route
            path="/admin-dashboard"
            element={isAuthenticated ? <AdminDashboard user={user} setUser={setUser} onLogout={handleLogout} /> : <Navigate to="/" />} // Redirect to login if not authenticated
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
