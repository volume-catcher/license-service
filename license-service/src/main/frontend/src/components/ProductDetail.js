import React from "react";
import Typography from "@mui/material/Typography";
import { createTheme } from "@mui/material/styles";
import dayjs from "dayjs";

const ProductDetail = ({ numOfAuthAvailable, isActivated, expireAt }) => {
  const theme = createTheme({
    palette: {
      titleGray: "#6E6E6E",
    },
  });

  return (
    <>
      <Typography
        sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}
      >
        제품별 인증 가능 횟수
      </Typography>
      <Typography>{numOfAuthAvailable}</Typography>
      <Typography
        sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}
      >
        활성 여부
      </Typography>
      <Typography>{isActivated ? "활성화" : "비활성화"}</Typography>
      <Typography
        sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}
      >
        만료 일시
      </Typography>
      <Typography>{dayjs(expireAt).format("YYYY-MM-DD HH:mm")}</Typography>
    </>
  );
};

export default ProductDetail;
