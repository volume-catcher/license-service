import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import StyledLink from "views/StyledLink";
import Copyright from "views/CopyRight";
import { useAxios } from "utils/api";

const theme = createTheme();

const SignUp = () => {
  const axios = useAxios();
  const navigate = useNavigate();
  const [checkMsg, setCheckMsg] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const data = {
      id: formData.get("email"),
      password: formData.get("password"),
    };

    if (formData.get("password") !== formData.get("rePassword")) {
      setCheckMsg("입력된 비밀번호가 서로 다릅니다");
    } else {
      axios
        .post("/signup", data)
        .then(() => {
          navigate("/signin");
        })
        .catch((error) => {
          if (error.response.status === 400) {
            setCheckMsg("아이디와 비밀번호는 3자 이상 20자 이하로 구성하세요");
          } else if (error.response.status === 409) {
            setCheckMsg("사용할 수 없는 아이디입니다");
          } else {
            setCheckMsg("오류가 발생했습니다");
          }
        });
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  name="email"
                  label="아이디"
                  autoComplete="username"
                  autoFocus
                />
                <Grid xs={12} container justifyContent="flex-start">
                  {checkMsg}
                </Grid>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="password"
                  name="password"
                  label="비밀번호"
                  type="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="password"
                  name="rePassword"
                  label="비밀번호 확인"
                  type="password"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              가입하기
            </Button>
            <StyledLink to="/signin">
              <Typography
                variant="subtitle1"
                color="text.primary"
                align="center"
                sx={{ textDecoration: "underline" }}
              >
                로그인하기
              </Typography>
            </StyledLink>
          </Box>
        </Box>
        <Copyright sx={{ mt: 5 }} />
      </Container>
    </ThemeProvider>
  );
};

export default SignUp;
