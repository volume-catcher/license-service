import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { isPositiveOrZeroNumber } from "utils/utils";

const ProductEdit = (props) => {
  const { numOfAuthAvailable, setNumOfAuthAvailable } = props;
  const { isActivated, setIsActivated } = props;
  const { expireAt, setExpireAt } = props;

  const handleNumOfAuthAvailableChange = (e) => {
    const value = e.target.value;
    if (isPositiveOrZeroNumber(value)) {
      setNumOfAuthAvailable(value);
    } else {
      setNumOfAuthAvailable(0);
    }
  };

  return (
    <>
      <Typography sx={{ color: "#6E6E6E", fontSize: 14, marginTop: 2 }}>
        제품별 인증 가능 횟수
      </Typography>
      <TextField
        id="filled-number"
        type="number"
        InputLabelProps={{
          shrink: true,
        }}
        variant="outlined"
        value={numOfAuthAvailable}
        InputProps={{
          inputProps: { min: 0 },
        }}
        onChange={handleNumOfAuthAvailableChange}
        autoFocus
      />
      <Typography sx={{ color: "#6E6E6E", fontSize: 14, marginTop: 2 }}>
        활성 여부
      </Typography>
      <Button
        variant="contained"
        color="info"
        onClick={() => setIsActivated(!isActivated)}
      >
        {isActivated ? "활성화" : "비활성화"}
      </Button>
      <Typography sx={{ color: "#6E6E6E", fontSize: 14, marginTop: 2 }}>
        만료 일시
      </Typography>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <DateTimePicker
          value={expireAt}
          onChange={(e) => setExpireAt(e)}
          inputFormat={"YYYY-MM-DD HH:mm"}
          disablePast
          inputProps={{ readOnly: true }}
          renderInput={(params) => <TextField {...params} />}
        />
      </LocalizationProvider>
    </>
  );
};

export default ProductEdit;
