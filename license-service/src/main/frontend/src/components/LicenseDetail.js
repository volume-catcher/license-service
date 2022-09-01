import React from "react";
import { Typography } from "@mui/material";
import ProductRegisteredWithLicense from "components/ProductRegisteredWithLicense";

const LicenseDetail = ({ license }) => {
  return (
    <>
      <Typography variant="h6" gutterBottom component="div">
        라이선스
      </Typography>
      <Typography variant="h6" gutterBottom component="div">
        {license}
      </Typography>
      <ProductRegisteredWithLicense license={license} />
    </>
  );
};

export default LicenseDetail;
