import axios from 'axios';

// Create an Axios instance
const api = axios.create({
  baseURL: 'http://localhost:8080/api', 
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add a request interceptor to include the token in headers
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add a response interceptor to handle token expiration or other auth errors
api.interceptors.response.use(
  (response) => response.data, // Return the data part of the response
  (error) => {
    if (error.response && error.response.status === 401) {
        // For example, redirect to login page or refresh token
        console.error("Unauthorized! Redirecting to login.");
        // You might want to clear the stored token and user info
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        // Redirect to login, for example: window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);


export default api;
