import React from 'react';
import { Typography } from '@mui/material';
import ProductList from 'components/ProductList';

const LicenseDetail = (props) => {
    return (
        <>
            <Typography variant="h6" gutterBottom component="div">
                라이선스
            </Typography>
            <Typography variant="h6" gutterBottom component="div">
                FJ93-FSI3-3J94-AKFC {/* props.licenseKey */}
            </Typography>
            <ProductList />
        </>
    )
}

export default LicenseDetail;