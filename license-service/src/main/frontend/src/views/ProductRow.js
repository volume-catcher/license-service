import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import CheckIcon from "@mui/icons-material/Check";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import { styled, createTheme } from "@mui/material/styles";
import Snackbar from "@mui/material/Snackbar";
import ProductEdit from "./ProductEdit";
import ProductDetail from "./ProductDetail";
import dayjs from "dayjs";
import { useAxios } from "utils/api";

export default function ProductRow({ row }) {
  const { licenseKey, productName } = row;
  const axios = useAxios();
  const [openPanel, setOpenPanel] = useState(false);
  const [edit, setEdit] = useState(false);
  const [openMsg, setOpenMsg] = useState(false);

  const [numOfAuthAvailable, setNumOfAuthAvailable] = useState(
    row.numOfAuthAvailable
  );
  const [isActivated, setIsActivated] = useState(row.isActivated);
  const [expireAt, setExpireAt] = useState(row.expireAt);
  const [checkMsg, setCheckMsg] = useState("");

  const theme = createTheme({
    palette: {
      backgroundGray: "#F6F6F6",
      hoverGray: "#E9E9E9",
      titleGray: "#6E6E6E",
    },
  });

  const StyledTableRow = styled(TableRow)(() => ({
    backgroundColor: theme.palette.backgroundGray,
    "&:hover": {
      background: theme.palette.hoverGray,
    },
  }));

  const handleSubmit = () => {
    const data = {
      licenseKey: licenseKey,
      productName: productName,
      isActivated: isActivated,
      numOfAuthAvailable: numOfAuthAvailable,
      expireAt: dayjs(expireAt).format("YYYY-MM-DDTHH:mm:ss"),
    };
    axios
      .put("/license-product", data)
      .then(() => {
        setEdit(false);
        setCheckMsg("수정되었습니다");
      })
      .catch((error) => {
        if (error.response.status === 400) {
          setCheckMsg("올바른 정보를 입력하세요");
        } else {
          setCheckMsg("오류가 발생했습니다");
        }
      })
      .finally(() => {
        setOpenMsg(true);
      });
  };

  const handleOnClick = (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (edit) {
      handleSubmit();
    } else {
      setEdit(true);
      setOpenPanel(true);
    }
  };

  useEffect(() => {
    if (openPanel) {
      setCheckMsg("");
    }
  }, [openPanel]);

  return (
    <>
      <StyledTableRow
        sx={{ "& > *": { borderBottom: "unset" } }}
        onClick={() => setOpenPanel(!openPanel)}
      >
        <TableCell>
          <IconButton aria-label="expand row" size="small">
            {openPanel ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {productName}
        </TableCell>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={handleOnClick}
          >
            {edit ? <CheckIcon /> : <EditIcon />}
          </IconButton>
        </TableCell>
      </StyledTableRow>
      <TableRow key={productName}>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={openPanel} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              {edit ? (
                <ProductEdit
                  update={edit}
                  numOfAuthAvailable={numOfAuthAvailable}
                  setNumOfAuthAvailable={setNumOfAuthAvailable}
                  isActivated={isActivated}
                  setIsActivated={setIsActivated}
                  expireAt={expireAt}
                  setExpireAt={setExpireAt}
                />
              ) : (
                <ProductDetail
                  numOfAuthAvailable={numOfAuthAvailable}
                  isActivated={isActivated}
                  expireAt={expireAt}
                />
              )}
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
      <Snackbar
        open={openMsg}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        autoHideDuration={2000}
        onClose={() => setOpenMsg(false)}
        message={checkMsg}
      />
    </>
  );
}
