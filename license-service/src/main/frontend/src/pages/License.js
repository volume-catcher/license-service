import React, { useEffect, useState, useCallback } from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { useTheme } from "@mui/material/styles";
import { isNotEmptyString, isNotEmptyArray } from "utils/utils";
import { instance } from "utils/apiInstance";
import SearchTable from "views/SearchTable";
import LicenseModal from "views/LicenseModal";
import FallbackMsg from "views/FallbackMsg";

const License = () => {
  const theme = useTheme();
  const [checkMsg, setCheckMsg] = useState("");
  const [response, setResponse] = useState("");
  const [rows, setRows] = useState([]);
  const [openModal, setOpenModal] = useState(false);
  const [selectedLicense, setSelectedLicense] = useState();

  const columns = ["순번", "라이선스 키"];

  useEffect(() => {
    getRows();
  }, []);

  const getRows = useCallback(() => {
    instance.get("/license").then((res) => {
      const { data } = res;
      setRows(
        data.map((item, index) => ({
          id: index,
          name: item.key,
        }))
      );
    });
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    createLicense();
  };

  const createLicense = () => {
    instance
      .post("/license")
      .then(({ data }) => {
        setResponse(data.key);
        setCheckMsg("생성되었습니다");
      })
      .then(() => {
        getRows();
      })
      .catch(() => {
        setCheckMsg("오류가 발생했습니다");
      });
  };

  const handleOpenModal = (license) => {
    setOpenModal(true);
    setSelectedLicense(license);
  };

  const handleCloseModal = () => {
    setOpenModal(false);
  };

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
            {isNotEmptyString(response) && (
              <Box
                sx={{
                  backgroundColor: theme.palette.grey[300],
                  mt: 5,
                  padding: 1,
                  display: "flex",
                  justifyContent: "center",
                }}
              >
                {response}
              </Box>
            )}
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
          </Container>
        </Grid>

        <Grid item xs={4} sm={8} md={6}>
          <Container maxWidth="sm">
            <Typography component="h1" variant="h5">
              전체 라이선스 목록
            </Typography>
            <Box sx={{ mt: 3 }}>
              {isNotEmptyArray(rows) ? (
                <>
                  <SearchTable
                    rows={rows}
                    columns={columns}
                    placeholder={"라이선스 키 검색"}
                    rowOnClick={handleOpenModal}
                  />
                  <LicenseModal
                    openModal={openModal}
                    handleCloseModal={handleCloseModal}
                    license={selectedLicense}
                  />
                </>
              ) : (
                <FallbackMsg
                  text="라이선스를 불러올 수 없습니다"
                  retry={getRows}
                />
              )}
            </Box>
          </Container>
        </Grid>
      </Grid>
    </Box>
  );
};

export default License;
