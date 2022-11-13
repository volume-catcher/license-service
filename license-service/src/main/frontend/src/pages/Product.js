import React, { useState, useCallback } from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { useTheme } from "@mui/material/styles";
import { isNotEmptyString, isNotNull } from "utils/utils";
import { instance } from "utils/apiInstance";
import SearchTable from "views/SearchTable";

const Product = () => {
  const theme = useTheme();
  const [rows, setRows] = useState([]);
  const [totalElements, setTotalElements] = useState([]);
  const [checkMsg, setCheckMsg] = useState("");
  const [product, setProduct] = useState("");

  const columns = [
    { id: "id", label: "순번", width: "30%" },
    { id: "name", label: "제품명", width: "70%" },
  ];

  const getRows = (searchWord, page) => {
    const uri = getUri(searchWord, page);

    instance.get(uri).then(({ data }) => {
      if (!!data) {
        setRows(data.content);
        setTotalElements(data.totalElements);
      }
    });
  };

  const getUri = (searchWord, page) => {
    let uri = "/products";

    if (isNotEmptyString(searchWord)) {
      uri += `/${searchWord}`;
    }

    if (!isNotNull(page)) {
      page = 0;
    }

    uri += `?page=${page}`;

    return uri;
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (isNotEmptyString(product)) {
      createProduct();
    } else {
      setCheckMsg("제품명을 입력하세요");
    }
  };

  const createProduct = useCallback(() => {
    const data = {
      name: product,
    };

    instance
      .post("/products", data)
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
  });

  return (
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
                label="제품명"
                autoFocus
                value={product}
                onChange={(e) => setProduct(e.target.value)}
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3 }}
              >
                제품 생성
              </Button>
              <Box
                sx={{
                  margin: 1,
                  display: "flex",
                  justifyContent: "center",
                  color: theme.palette.grey[700],
                }}
              >
                {checkMsg}
              </Box>
            </Box>
          </Container>
        </Grid>

        <Grid item xs={4} sm={8} md={6}>
          <Container maxWidth="sm">
            <Typography component="h1" variant="h5">
              전체 제품 목록
            </Typography>
            <Box sx={{ mt: 3 }}>
              <SearchTable
                getData={getRows}
                count={totalElements}
                rows={rows}
                columns={columns}
                placeholder={"제품 검색"}
              />
            </Box>
          </Container>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Product;
