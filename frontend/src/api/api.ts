import axios from 'axios';

const API_BASE_URL = "";

const api = axios.create({
  baseURL: `${API_BASE_URL}/api`, 
  headers: {
    'Content-Type': 'application/json',
  },
});

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

api.interceptors.response.use(
  (response) => response.data, 
  (error) => {
    if (error.response && error.response.status === 401) {
        console.error("Unauthorized! Redirecting to login.");
        localStorage.removeItem('token');
        localStorage.removeItem('username');
    }
    return Promise.reject(error);
  }
);


export default api;
