import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import CheckIcon from "@mui/icons-material/Check";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { styled, useTheme } from "@mui/material/styles";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import CancelIcon from "@mui/icons-material/Cancel";
import dayjs from "dayjs";
import ProductEdit from "views/ProductEdit";
import ProductDetail from "views/ProductDetail";
import { instance } from "utils/apiInstance";

export default function ProductRow(props) {
  const theme = useTheme();
  const { row, setCheckMsg, openSnackbar } = props;
  const { licenseKey, productName } = row;

  const [openPanel, setOpenPanel] = useState(false);
  const [edit, setEdit] = useState(false);

  const [numOfAuthAvailable, setNumOfAuthAvailable] = useState(
    row.numOfAuthAvailable
  );
  const [isActivated, setIsActivated] = useState(row.isActivated);
  const [expireAt, setExpireAt] = useState(row.expireAt);

  const [oldNumOfAuthAvailable, setOldNumOfAuthAvailable] = useState();
  const [oldIsActivated, setOldIsActivated] = useState();
  const [oldExpireAt, setOldExpireAt] = useState();

  const StyledTableRow = styled(TableRow)(() => ({
    backgroundColor: theme.palette.grey[100],
    "&:hover": {
      background: theme.palette.grey[200],
    },
    "& > *": { borderBottom: "unset" },
  }));

  useEffect(() => {
    if (openPanel) {
      setCheckMsg("");
    }
  }, [openPanel]);

  const updateProductOnLicense = () => {
    const data = {
      licenseKey: licenseKey,
      productName: productName,
      isActivated: isActivated,
      numOfAuthAvailable: numOfAuthAvailable,
      expireAt: dayjs(expireAt).format("YYYY-MM-DDTHH:mm:ss"),
    };

    instance
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
        openSnackbar();
      });
  };

  const handleOnClick = (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (edit) {
      updateProductOnLicense();
    } else {
      setEdit(true);
      setOpenPanel(true);
      setOldNumOfAuthAvailable(numOfAuthAvailable);
      setOldIsActivated(isActivated);
      setOldExpireAt(expireAt);
    }
  };

  const handleCancelOnClick = (e) => {
    e.stopPropagation();
    setNumOfAuthAvailable(oldNumOfAuthAvailable);
    setIsActivated(oldIsActivated);
    setExpireAt(oldExpireAt);
    setEdit(false);
  };

  return (
    <>
      <StyledTableRow onClick={() => setOpenPanel(!openPanel)}>
        <TableCell sx={{ width: "30%" }}>
          <IconButton aria-label="expand row" size="small">
            {openPanel ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row" sx={{ width: "40%" }}>
          {productName}
        </TableCell>
        <TableCell sx={{ width: "15%" }}>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={handleOnClick}
          >
            {edit ? <CheckIcon /> : <EditIcon />}
          </IconButton>
        </TableCell>
        <TableCell sx={{ width: "15%" }}>
          {edit && (
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={handleCancelOnClick}
            >
              <CancelIcon />
            </IconButton>
          )}
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
    </>
  );
}
