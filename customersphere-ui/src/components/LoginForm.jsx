// src/components/LoginForm.jsx
import React, { useState } from "react";
import {
  Box,
  TextField,
  Button,
  Typography,
  Container,
  Paper,
  Link,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next"; // Import useTranslation
import { login } from "../services/authapi";

const LoginForm = ({ onLogin, user, setUser }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState(false);
  const { t, i18n } = useTranslation(); // Destructure useTranslation hook
  const navigate = useNavigate();

  // Email validation function
  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateEmail(email)) {
      setEmailError(true);
      return;
    }
    setEmailError(false);
    console.log("Email:", email, "Password:", password);
    onLogin(); // Call the onLogin prop to mark user as authenticated
    const data = await login(email, password);
    console.log(data);
    setUser(data)
    data?.role === "ADMIN"
      ? navigate("/admin-dashboard")
      :
    data?.role === "CUSTOMER" ?
    navigate("/dashboard") : data?.response?.status === 401 ? alert("Invalid Username or Password") // Redirect to dashboard after login
  : alert("Something went wrong")
  };

  const switchLanguage = (language) => {
    i18n.changeLanguage(language); // Change the app's language
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
          {t("login")}
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
            label={t("email_label")}
            variant="outlined"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            error={emailError}
            helperText={emailError ? t("email_helper") : ""}
            fullWidth
            required
          />
          <TextField
            label={t("password_label")}
            variant="outlined"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            fullWidth
            required
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            fullWidth
            sx={{ marginTop: "10px" }}
          >
            {t("submit")}
          </Button>
          <Typography
            variant="body2"
            component="p"
            style={{ marginTop: "15px" }}
          >
            {t("signup_prompt")}{" "}
            <Link href="/signup" style={{ cursor: "pointer" }}>
              {t("signup_link")}
            </Link>
          </Typography>
        </Box>
        <div style={{ marginTop: "20px" }}>
          <Button onClick={() => switchLanguage("en")}>English</Button>
          <Button onClick={() => switchLanguage("fr")}>Spanish</Button>
        </div>
      </Paper>
    </Container>
  );
};

export default LoginForm;
