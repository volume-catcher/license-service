import React from "react";
import { Link } from "react-router-dom";
import { styled } from "@mui/material/styles";

const StyledLink = styled(Link)(() => ({
  textDecoration: "inherit",
  color: "inherit",

  "&:focus": {
    textDecoration: "inherit",
  },
  "&:hover": {
    textDecoration: "inherit",
  },
  "&:visited": {
    textDecoration: "inherit",
  },
  "&:link": {
    textDecoration: "inherit",
  },
  "&:active": {
    textDecoration: "inherit",
  },
}));

export default (props) => <StyledLink {...props} />;
