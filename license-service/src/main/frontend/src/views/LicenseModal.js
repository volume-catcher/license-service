import React, { useState, useEffect } from "react";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import Snackbar from "@mui/material/Snackbar";
import TableContainer from "@mui/material/TableContainer";
import ProductHeader from "views/ProductHeader";
import ProductRow from "views/ProductRow";
import { isNotEmptyArray } from "utils/utils";
import { instance } from "utils/apiInstance";

const LicenseModal = ({ openModal, handleCloseModal, license }) => {
  const [rows, setRows] = useState([]);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [checkMsg, setCheckMsg] = useState("");

  useEffect(() => {
    if (openModal) {
      getProductsByLicense();
    } else {
      setRows([]);
    }
  }, [openModal]);

  const getProductsByLicense = () => {
    instance.get(`/license-product/license/${license}`).then(({ data }) => {
      setRows(data);
    });
  };

  const handleOpenSnackbar = () => {
    setOpenSnackbar(true);
  };

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    height: 600,
    maxHeight: "90%",
    width: 600,
    maxWidth: "90%",
    overflowY: "scroll",
    bgcolor: "background.paper",
    boxShadow: 12,
    p: 4,
  };

  return (
    <Modal
      open={openModal}
      onClose={handleCloseModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
      componentsProps={{
        backdrop: { style: { backgroundColor: "rgba(0,0,0,0.1)" } },
      }}
    >
      <Box sx={style}>
        <Typography variant="h6" gutterBottom component="div">
          라이선스
        </Typography>
        <Typography variant="h6" gutterBottom component="div">
          {license}
        </Typography>

        <ProductHeader
          licenseKey={license}
          updateData={getProductsByLicense}
          productsByLicense={rows.flatMap((item) => item.productName)}
          setCheckMsg={setCheckMsg}
          openSnackbar={handleOpenSnackbar}
        />

        {isNotEmptyArray(rows) ? (
          <TableContainer>
            <Table aria-label="collapsible table">
              <TableBody>
                {rows.map((row, index) => (
                  <ProductRow
                    key={index}
                    row={row}
                    setCheckMsg={setCheckMsg}
                    openSnackbar={handleOpenSnackbar}
                  />
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        ) : (
          <Typography component="h1" variant="subtitle1">
            등록된 제품이 없습니다
          </Typography>
        )}
        <Snackbar
          open={openSnackbar}
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          autoHideDuration={2000}
          onClose={() => setOpenSnackbar(false)}
          message={checkMsg}
        />
      </Box>
    </Modal>
  );
};

export default LicenseModal;
