import axios from "axios";

export const useAxios = () => {
  return {
    get: request("GET"),
    post: request("POST"),
    put: request("PUT"),
    patch: request("PATCH"),
    delete: request("DELETE"),
  };

  function request(method) {
    return (url, data) => {
      const requestOptions = {
        method,
        headers: authHeader(url),
      };
      if (data) {
        requestOptions.headers["Content-Type"] = "application/json";
        requestOptions.data = JSON.stringify(data);
      }
      return axios(url, requestOptions).then();
    };
  }

  function authHeader(url) {
    // return auth header with jwt if user is logged in and request is to the api url
    const token = localStorage.getItem("token");
    if (!!token /* && isLogIn*/) {
      console.log(token);
      return { Authorization: `Bearer ${token}` };
    } else {
      return {};
    }
  }
};
