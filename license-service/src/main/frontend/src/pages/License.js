import React, { useEffect, useState } from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import LicenseList from "components/LicenseList";
import { isNotEmptyString, isNotEmptyArray } from "utils/utils";
import { useAxios } from "utils/api";

const theme = createTheme({
  palette: {
    neutral: {
      main: "#E0E0E0",
      contrastText: "#fff",
    },
  },
});

const License = () => {
  const [checkMsg, setCheckMsg] = useState("");
  const [createdLicenseKey, setCreatedLicenseKey] = useState("");
  const [rows, setRows] = useState([]);
  const axios = useAxios();
  const columns = ["순번", "라이선스 키"];

  const handleSubmit = (event) => {
    event.preventDefault();
    createLicense();
  };

  const createLicense = () => {
    axios
      .post("/license")
      .then((res) => {
        const { data } = res;
        setCreatedLicenseKey(data.key);
        setCheckMsg("생성되었습니다");
        getRows();
      })
      .catch(() => {
        setCheckMsg("오류가 발생했습니다");
      });
  };

  const getRows = () => {
    axios.get("/license").then((res) => {
      const { data } = res;
      setRows(
        data.map((item, index) => ({
          id: index,
          name: item.key,
        }))
      );
    });
  };

  useEffect(() => {
    getRows();
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <Box sx={{ marginTop: 8 }}>
        <CssBaseline />
        <Grid
          container
          spacing={{ xs: 6, md: 0 }}
          columns={{ xs: 4, sm: 8, md: 12 }}
        >
          <Grid item xs={4} sm={8} md={6}>
            <Container maxWidth="xs">
              <Typography component="h1" variant="h5">
                라이선스 발급하기
              </Typography>
              <Box
                component="form"
                onSubmit={handleSubmit}
                noValidate
                sx={{ mt: 1 }}
              >
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, padding: 2, fontSize: 18 }}
                >
                  라이선스 키 발급
                </Button>
              </Box>
              {isNotEmptyString(createdLicenseKey) && (
                <Box
                  sx={{
                    backgroundColor: "neutral.main",
                    mt: 5,
                    padding: 1,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                  }}
                >
                  {createdLicenseKey}
                </Box>
              )}
              <Box sx={{ margin: 1 }}>{checkMsg}</Box>
            </Container>
          </Grid>

          <Grid item xs={4} sm={8} md={6}>
            <Container maxWidth="sm">
              <Typography component="h1" variant="h5">
                전체 라이선스 목록
              </Typography>
              <Box sx={{ mt: 3 }}>
                {isNotEmptyArray(rows) ? (
                  <LicenseList
                    rows={rows}
                    columns={columns}
                    searchPlaceHolder="라이선스 키 검색"
                  />
                ) : (
                  <Typography component="h1" variant="subtitle1">
                    라이선스를 불러올 수 없습니다
                  </Typography>
                )}
              </Box>
            </Container>
          </Grid>
        </Grid>
      </Box>
    </ThemeProvider>
  );
};

export default License;
