import React, { useState } from "react";
import { Box, TextField, Button, Typography, Container, Paper } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SignUpForm = () => {
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [mobile, setMobile] = useState("");
  const [address, setAddress] = useState("");

  const [errors, setErrors] = useState({});
  const navigate = useNavigate()
  const handleRegister = async () =>{
    const payload = {
      name: username,
      phone: mobile,
      email: email,
      password: password,
      role:"CUSTOMER"   
    }
    try{
    const data = await axios.post("http://localhost:8080/api/auth/register", payload)
    console.log(data)
    if(data.data.id){
      alert("User Registered Successfully!")
      navigate("/")
    }else{
      alert("Something went wrong!")
    }
  }catch(error){
    if(error.status === 400){
      alert("Something went wrong!")
    }
  }
  }
  // Email validation
  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  // Mobile validation
  const validateMobile = (mobile) => {
    const mobileRegex = /^[0-9]{10}$/;
    return mobileRegex.test(mobile);
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    const newErrors = {};

    if (!username) newErrors.username = "Username is required!";
    if (!firstName) newErrors.firstName = "First name is required!";
    if (!lastName) newErrors.lastName = "Last name is required!";
    if (!email || !validateEmail(email)) newErrors.email = "Valid email is required!";
    if (!password) newErrors.password = "Password is required!";
    if (password !== confirmPassword) newErrors.confirmPassword = "Passwords do not match!";
    if (!mobile || !validateMobile(mobile)) newErrors.mobile = "Valid mobile number is required!";
    if (!address) newErrors.address = "Address is required!";

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setErrors({});
    console.log("Signup successful!", { username, firstName, lastName, email, password, mobile, address });
  };

  return (
    <Container maxWidth="sm">
      <Paper
        elevation={3}
        style={{
          padding: "20px",
          marginTop: "50px",
          textAlign: "center",
          borderRadius: "10px",
        }}
      >
        <Typography variant="h5" component="h2" gutterBottom>
          Sign Up
        </Typography>
        <Box
          component="form"
          onSubmit={handleSubmit}
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: 2,
          }}
        >
          <TextField
            label="Username"
            variant="outlined"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            fullWidth
            required
            error={!!errors.username}
            helperText={errors.username}
          />
          {/* <TextField
            label="First Name"
            variant="outlined"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            fullWidth
            required
            error={!!errors.firstName}
            helperText={errors.firstName}
          /> */}
          {/* <TextField
            label="Last Name"
            variant="outlined"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            fullWidth
            required
            error={!!errors.lastName}
            helperText={errors.lastName}
          /> */}
          <TextField
            label="Email ID"
            variant="outlined"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            fullWidth
            required
            error={!!errors.email}
            helperText={errors.email}
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            fullWidth
            required
            error={!!errors.password}
            helperText={errors.password}
          />
          {/* <TextField
            label="Confirm Password"
            variant="outlined"
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            fullWidth
            required
            error={!!errors.confirmPassword}
            helperText={errors.confirmPassword}
          />*/}
          <TextField
            label="Mobile Number"
            variant="outlined"
            type="text"
            value={mobile}
            onChange={(e) => setMobile(e.target.value)}
            fullWidth
            required
            error={!!errors.mobile}
            helperText={errors.mobile}
          /> 
          {/* <TextField
            label="Address"
            variant="outlined"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            fullWidth
            required
            error={!!errors.address}
            helperText={errors.address}
          /> */}

          <Button
          onClick={handleRegister}
            type="submit"
            variant="contained"
            color="primary"
            fullWidth
            sx={{ marginTop: "10px" }}
          >
            Sign Up
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default SignUpForm;
