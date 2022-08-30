import React from "react";
import { Typography } from "@mui/material";
import ProductList from "components/ProductList";

const LicenseDetail = ({ license }) => {
  return (
    <>
      <Typography variant="h6" gutterBottom component="div">
        라이선스
      </Typography>
      <Typography variant="h6" gutterBottom component="div">
        {license}
      </Typography>
      <ProductList license={license} />
    </>
  );
};

export default LicenseDetail;
