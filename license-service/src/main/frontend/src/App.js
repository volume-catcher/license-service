import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ResponsiveAppBar from "views/ResponsiveAppBar";
import License from "pages/License";
import NotFound from "pages/NotFound";
import SignIn from "pages/SignIn";
import SignUp from "pages/SignUp";
import Product from "pages/Product";
import { AxiosInterceptor } from "utils/apiInstance";
import { createTheme, ThemeProvider } from "@mui/material";

function App() {
  const theme = createTheme();

  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <ResponsiveAppBar />
        <AxiosInterceptor>
          <Routes>
            <Route path="/" element={<License />}></Route>
            <Route path="/product" element={<Product />}></Route>
            <Route path="/signin" element={<SignIn />}></Route>
            <Route path="/signup" element={<SignUp />}></Route>
            <Route path="*" element={<NotFound />}></Route>
          </Routes>
        </AxiosInterceptor>
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
