import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Collapse from "@mui/material/Collapse";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import Autocomplete from "@mui/material/Autocomplete";
import Snackbar from "@mui/material/Snackbar";
import { useTheme } from "@mui/material/styles";
import dayjs from "dayjs";
import ProductEdit from "views/ProductEdit";
import { instance } from "utils/apiInstance";
import { isNotEmptyArray } from "utils/utils";

const ProductHeader = ({ licenseKey, updateData, productsByLicense }) => {
  const theme = useTheme();
  const defaultValue = {
    productName: "",
    numOfAuthAvailable: 5,
    isActivated: true,
    expireAt: dayjs(Date.now() + 7 * 24 * 60 * 60 * 1000),
  };

  const [openPanel, setOpenPanel] = useState(false);
  const [productName, setProductName] = useState("");
  const [numOfAuthAvailable, setNumOfAuthAvailable] = useState();
  const [isActivated, setIsActivated] = useState();
  const [expireAt, setExpireAt] = useState();
  const [checkMsg, setCheckMsg] = useState("");
  const [openMsg, setOpenMsg] = useState(false);
  const [options, setOptions] = useState([]);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAllProducts();
  }, []);

  useEffect(() => {
    if (openPanel) {
      clearData();
      filterOptions();
    } else {
      setCheckMsg("");
    }
  }, [openPanel]);

  const clearData = () => {
    setProductName(defaultValue.productName);
    setNumOfAuthAvailable(defaultValue.numOfAuthAvailable);
    setIsActivated(defaultValue.isActivated);
    setExpireAt(defaultValue.expireAt);
  };

  const getAllProducts = () => {
    instance
      .get("/product")
      .then(({ data }) => {
        if (isNotEmptyArray(data)) {
          setProducts(data.flatMap((item) => item.name));
        }
      })
      .then(() => {
        filterOptions();
      });
  };

  const filterOptions = () => {
    setOptions(products.filter((item) => !productsByLicense.includes(item)));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    grantLicenseToProduct();
  };

  const grantLicenseToProduct = () => {
    const data = {
      licenseKey: licenseKey,
      productName: productName,
      isActivated: isActivated,
      numOfAuthAvailable: numOfAuthAvailable,
      expireAt: dayjs(expireAt).format("YYYY-MM-DDTHH:mm:ss"),
    };
    instance
      .post("/license-product", data)
      .then(() => {
        setCheckMsg("생성되었습니다");
        setOpenPanel(false);
        updateData();
      })
      .catch((error) => {
        if (error.response.status === 409) {
          setCheckMsg("이미 등록된 제품입니다");
        } else if (error.response.status === 400) {
          setCheckMsg("올바른 정보를 입력하세요");
        } else {
          setCheckMsg("오류가 발생했습니다");
        }
      })
      .finally(() => {
        setOpenMsg(true);
      });
  };

  return (
    <>
      <Box component="form" onSubmit={handleSubmit} noValidate>
        <Stack
          direction="row"
          spacing={2}
          justifyContent="space-between"
          alignItems="center"
        >
          <Typography variant="h6" gutterBottom component="div">
            제품
          </Typography>
          <Stack
            direction="row"
            spacing={2}
            justifyContent="flex-end"
            alignItems="center"
          >
            <Button
              variant="contained"
              size="small"
              onClick={() => setOpenPanel(!openPanel)}
            >
              {openPanel ? "취소" : "추가"}
            </Button>
            {openPanel && (
              <Button variant="contained" size="small" type="submit">
                저장
              </Button>
            )}
          </Stack>
        </Stack>

        <Collapse in={openPanel} timeout="auto" unmountOnExit>
          <Box sx={{ margin: 1 }}>
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              value={productName}
              isOptionEqualToValue={(option, value) =>
                option.value === value.value
              }
              onChange={(event, value) => setProductName(value)}
              fullWidth
              options={options}
              sx={{
                width: "100%",
                "& .MuiInputBase-root": {
                  height: 60,
                },
                background: theme.palette.grey[100],
                "&:hover": {
                  background: theme.palette.grey[200],
                },
                outline: "none",
              }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  label="제품"
                  required
                  sx={{ "& fieldset": { border: "none" } }}
                />
              )}
            />
            <ProductEdit
              update={openPanel}
              numOfAuthAvailable={numOfAuthAvailable}
              setNumOfAuthAvailable={setNumOfAuthAvailable}
              isActivated={isActivated}
              setIsActivated={setIsActivated}
              expireAt={expireAt}
              setExpireAt={setExpireAt}
            />
          </Box>
        </Collapse>
      </Box>
      <Snackbar
        open={openMsg}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        autoHideDuration={2000}
        onClose={() => setOpenMsg(false)}
        message={checkMsg}
      />
    </>
  );
};

export default ProductHeader;
