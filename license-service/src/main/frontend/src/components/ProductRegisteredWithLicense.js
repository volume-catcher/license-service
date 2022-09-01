import React, { useState, useEffect } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import Typography from "@mui/material/Typography";
import { useAxios } from "utils/api";
import { isNotEmptyArray } from "utils/utils";
import CreateLicenseProduct from "./CreateLicenseProduct";
import ProductRow from "./ProductRow";

export default function ProductRegisteredWithLicense({ license }) {
  const [rows, setRows] = useState([]);
  const [products, setProducts] = useState([]);
  const axios = useAxios();

  const getLicenseProducts = () => {
    axios.get(`/license-product/license/${license}`).then(({ data }) => {
      setRows(data);
    });
  };

  const getProducts = () => {
    axios.get("/product").then(({ data }) => {
      if (isNotEmptyArray(data)) {
        setProducts(data.flatMap((item) => item.name));
      }
    });
  };

  useEffect(() => {
    getLicenseProducts();
    getProducts();
  }, []);

  return (
    <>
      <CreateLicenseProduct
        licenseKey={license}
        updateData={getLicenseProducts}
        options={rows
          .flatMap((row) => row.productName)
          .concat(products)
          .filter(
            (item) =>
              !rows.flatMap((row) => row.productName).includes(item) ||
              !products.includes(item)
          )}
      />

      {isNotEmptyArray(rows) ? (
        <TableContainer>
          <Table aria-label="collapsible table">
            <TableBody>
              {rows.map((row, index) => (
                <ProductRow key={index} row={row} />
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <Typography component="h1" variant="subtitle1">
          등록된 제품이 없습니다
        </Typography>
      )}
    </>
  );
}
