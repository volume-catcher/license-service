import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { DataGrid } from "@mui/x-data-grid";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useAxios } from "utils/api";
import { isNotEmptyArray, isNotEmptyString } from "utils/utils";

const Product = () => {
  const [rows, setRows] = useState([]);
  const [checkMsg, setCheckMsg] = useState("");
  const axios = useAxios();

  const theme = createTheme();
  const columns = [
    { field: "id", headerName: "순번", width: 70 },
    { field: "name", headerName: "제품명", width: 130 },
  ];

  const getRows = () => {
    axios.get("/product").then((res) => {
      const { data } = res;
      data.forEach((item, index) => (item.id = index + 1));
      console.log(data);
      setRows(data);
    });
  };

  const createProduct = (productName) => {
    axios
      .post("/product", productName)
      .then(() => {
        setCheckMsg("생성되었습니다");
        getRows();
      })
      .catch((error) => {
        if (error.response.status === 409) {
          setCheckMsg("이미 존재하는 제품입니다");
        } else {
          setCheckMsg("오류가 발생했습니다");
        }
      });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const data = {
      name: formData.get("product"),
    };
    if (isNotEmptyString(data.name)) {
      createProduct(data);
    } else {
      setCheckMsg("제품명을 입력하세요");
    }
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
                제품 생성하기
              </Typography>
              <Box
                component="form"
                onSubmit={handleSubmit}
                noValidate
                sx={{ mt: 1 }}
              >
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="product"
                  label="제품명"
                  name="product"
                  autoFocus
                />
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3 }}
                >
                  제품 생성
                </Button>
                <Box sx={{ margin: 1 }}>{checkMsg}</Box>
              </Box>
            </Container>
          </Grid>

          <Grid item xs={4} sm={8} md={6}>
            <Container maxWidth="sm">
              <Typography component="h1" variant="h5">
                전체 제품 목록
              </Typography>
              <Box sx={{ mt: 3 }}>
                {isNotEmptyArray(rows) ? (
                  <DataGrid
                    rows={rows}
                    columns={columns}
                    pageSize={5}
                    autoHeight
                  />
                ) : (
                  <Typography component="h1" variant="subtitle1">
                    제품을 불러올 수 없습니다
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

export default Product;
