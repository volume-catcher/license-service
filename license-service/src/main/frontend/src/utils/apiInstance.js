import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const TOKEN = "token";

export const instance = axios.create({
  timeout: 3000,
});

instance.interceptors.request.use(function (config) {
  const token = localStorage.getItem(TOKEN);
  const isRequiredAuth = !["/signin", "/signup"].includes(config.url);

  if (isRequiredAuth && !!token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }

  if (config.data) {
    config.headers["Content-Type"] = "application/json";
    config.data = JSON.stringify(config.data);
  }

  return config;
});

export const AxiosInterceptor = ({ children }) => {
  const navigate = useNavigate();

  useEffect(() => {
    const resInterceptor = (response) => {
      return response;
    };

    const errInterceptor = (error) => {
      if (error.response.status === 401) {
        if (!!localStorage.getItem(TOKEN)) {
          localStorage.removeItem(TOKEN);
        }
        navigate("/signin");
      }
      return Promise.reject(error);
    };

    const interceptor = instance.interceptors.response.use(
      resInterceptor,
      errInterceptor
    );

    return () => instance.interceptors.response.eject(interceptor);
  }, [navigate]);

  return children;
};
