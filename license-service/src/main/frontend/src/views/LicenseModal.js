import React, { useState } from "react";
import { Modal } from "@mui/material";
import Box from "@mui/material/Box";
import LicenseDetail from "./LicenseDetail";

const LicenseModal = ({ openModal, handleCloseModal, license }) => {
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
        <LicenseDetail license={license} />
      </Box>
    </Modal>
  );
};

export default LicenseModal;
