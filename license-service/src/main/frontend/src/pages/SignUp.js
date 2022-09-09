import React, { useEffect, useState } from "react";
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
import StyledLink from "views/StyledLink";
import Copyright from "views/CopyRight";
import { isNotEmptyString } from "utils/utils";
import { instance } from "utils/apiInstance";

const SignUp = () => {
  const navigate = useNavigate();
  const [checkMsg, setCheckMsg] = useState("");
  const [id, setId] = useState("");
  const [pw, setPw] = useState("");
  const [pwCheck, setPwCheck] = useState("");

  useEffect(() => {
    if (!isValidInput(id) || !isValidInput(pw)) {
      setCheckMsg("아이디와 비밀번호는 3자 이상 20자 이하로 구성하세요");
    } else if (isNotEmptyString(pwCheck) && pw !== pwCheck) {
      setCheckMsg("입력된 비밀번호가 서로 다릅니다");
    } else {
      setCheckMsg("");
    }
  }, [id, pw, pwCheck]);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (isValidInput(id) && isValidInput(pw) && pw === pwCheck) {
      signin();
    }
  };

  const signin = () => {
    const data = {
      id: id,
      password: pw,
    };

    instance
      .post("/signup", data)
      .then(() => {
        navigate("/signin");
      })
      .catch((error) => {
        if (error.response.status === 409) {
          setCheckMsg("사용할 수 없는 아이디입니다");
        } else {
          setCheckMsg("오류가 발생했습니다");
        }
      });
  };

  const isValidInput = (val) => {
    if (val.length >= 3 && val.length <= 20) {
      return true;
    } else {
      return false;
    }
  };

  return (
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
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                label="아이디"
                autoComplete="username"
                autoFocus
                value={id}
                onChange={(e) => setId(e.target.value)}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                label="비밀번호"
                type="password"
                value={pw}
                onChange={(e) => setPw(e.target.value)}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                label="비밀번호 확인"
                type="password"
                value={pwCheck}
                onChange={(e) => setPwCheck(e.target.value)}
              />
            </Grid>
            <Grid item xs={12} justifyContent="center">
              {checkMsg}
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
  );
};

export default SignUp;
