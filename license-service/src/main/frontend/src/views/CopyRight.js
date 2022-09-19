import React from "react";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";

export default function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {"Copyright © "}
      <Link
        color="inherit"
        href="https://github.com/volume-catcher/license-service"
      >
        volume-catcher
      </Link>
      {" 2022."}
    </Typography>
  );
}
