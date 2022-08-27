import React from 'react';
import Typography from '@mui/material/Typography';
import { createTheme } from '@mui/material/styles';

const ProductDetail = (props) => {
    const theme = createTheme({
        palette: {
          titleGray: '#6E6E6E',
        }
      });

    return (
        <>
            <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                제품별 인증 가능 횟수
            </Typography>
            <Typography >
                {props.product.numOfAuthAvailable}
            </Typography>
            <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                활성 여부
            </Typography>
            <Typography >
                {props.product.isActivated}
            </Typography>
            <Typography sx={{ color: theme.palette.titleGray, fontSize: 14, marginTop: 2 }}>
                만료 일시
            </Typography>
            <Typography >
                {props.product.expireAt}
            </Typography>
        </>
    )
}

export default ProductDetail;