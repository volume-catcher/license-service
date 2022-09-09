import React from "react";
import { Box, Typography } from "@mui/material";
import { useTheme } from "@mui/material/styles";

export default function NotFound() {
  const theme = useTheme();

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        minHeight: "80vh",
        backgroundColor: theme.palette.info.contrastText,
      }}
    >
      <Typography variant="h1">😲</Typography>
      <Typography
        variant="h1"
        style={{ color: theme.palette.info.main, fontWeight: "bold" }}
      >
        404
      </Typography>
      <Typography variant="h4" style={{ color: theme.palette.info.main }}>
        Oops, page not found
      </Typography>
      <Typography
        variant="h6"
        style={{ color: theme.palette.grey[600], marginTop: 4 }}
      >
        The page you’re looking for doesn’t exist.
      </Typography>
    </Box>
  );
}
