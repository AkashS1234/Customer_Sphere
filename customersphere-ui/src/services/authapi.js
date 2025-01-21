// src/services/authApi.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

// Login function
export const login = async (email, password) => {
    try {
      // Send POST request with application/json
      const response = await axios.post(
        `${API_URL}/login`,
        { email, password }, // JSON payload
        {
          headers: {
            'Content-Type': 'application/json', // Ensure correct content type
          },
        }
      );
  
      // Log and return the response data (user details)
      console.log('Login successful:', response.data);
      return response.data; // This should now return the full user details
    } catch (error) {
      // Handle errors gracefully
      const errorMessage =
        error.response?.data?.message || 'An error occurred during login';
      console.error('Login error:', errorMessage);
      return error;
    }
  };

// Register function
export const register = async (email, password) => {
  try {
    const response = await axios.post(`${API_URL}/register`, {
      email,
      password,
    });

    console.log('Registration successful:', response.data); // Log success response
    return response.data; // Handle the response data (e.g., success message)
  } catch (error) {
    const errorMessage =
      error.response?.data?.message || 'An error occurred during registration';
    console.error('Registration error:', errorMessage);
    throw new Error(errorMessage); // Throw a meaningful error message
  }
};
