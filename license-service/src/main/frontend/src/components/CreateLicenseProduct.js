import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Collapse from "@mui/material/Collapse";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import Autocomplete from "@mui/material/Autocomplete";
import ProductEdit from "./ProductEdit";
import { useAxios } from "utils/api";
import dayjs from "dayjs";
import { Snackbar } from "@mui/material";

const CreateLicenseProduct = ({ licenseKey, updateData, options }) => {
  const defaultValue = {
    productName: "",
    numOfAuthAvailable: 5,
    isActivated: true,
    expireAt: dayjs(Date.now() + 7 * 24 * 60 * 60 * 1000),
  };
  const axios = useAxios();

  const [openPanel, setOpenPanel] = useState(false);
  const [productName, setProductName] = useState(defaultValue.productName);
  const [numOfAuthAvailable, setNumOfAuthAvailable] = useState(
    defaultValue.numOfAuthAvailable
  );
  const [isActivated, setIsActivated] = useState(defaultValue.isActivated);
  const [expireAt, setExpireAt] = useState(defaultValue.expireAt);
  const [checkMsg, setCheckMsg] = useState("");
  const [openMsg, setOpenMsg] = useState(false);

  useEffect(() => {
    if (openPanel) {
      clearData();
    } else {
      setCheckMsg("");
    }
  }, [openPanel]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      licenseKey: licenseKey,
      productName: productName,
      isActivated: isActivated,
      numOfAuthAvailable: numOfAuthAvailable,
      expireAt: dayjs(expireAt).format("YYYY-MM-DDTHH:mm:ss"),
    };
    axios
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

  const clearData = () => {
    setProductName(defaultValue.productName);
    setNumOfAuthAvailable(defaultValue.numOfAuthAvailable);
    setIsActivated(defaultValue.isActivated);
    setExpireAt(defaultValue.expireAt);
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
          <Stack
            direction="row"
            spacing={2}
            justifyContent="flex-start"
            alignItems="center"
          >
            <Typography variant="h6" gutterBottom component="div">
              제품
            </Typography>
          </Stack>
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
              sx={{ width: 300 }}
              renderInput={(params) => <TextField {...params} label="제품" />}
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

export default CreateLicenseProduct;
