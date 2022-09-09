import React from "react";
import { Button, Typography } from "@mui/material";
import { useTheme } from "@mui/material/styles";

const FallbackMsg = ({ text, retry }) => {
  const theme = useTheme();

  return (
    <>
      <Typography component="h1" variant="subtitle1">
        {text}
      </Typography>
      <Button
        variant="contained"
        sx={{
          mt: 1,
          backgroundColor: theme.palette.info.main,
        }}
        onClick={() => retry()}
      >
        다시 시도하기
      </Button>
    </>
  );
};

export default FallbackMsg;
