// src/axios.js
import axios from "axios";

// Crear una instancia de Axios con la configuración predeterminada
const axiosInstance = axios.create({
  baseURL: "http://localhost:5183/", // URL base del servidor
  headers: {
    "Content-Type": "application/json",
  },
});

// Interceptor de solicitud para agregar configuraciones adicionales si es necesario
axiosInstance.interceptors.request.use(
  (config) => {
    // Puedes agregar configuraciones adicionales aquí si es necesario
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor de respuesta para manejar errores globales
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // Manejo de errores globales
    return Promise.reject(error);
  }
);

export default axiosInstance;
