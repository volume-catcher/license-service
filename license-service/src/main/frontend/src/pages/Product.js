import React, { useState } from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { DataGrid } from '@mui/x-data-grid';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme();

const Product = () => {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      product: data.get('product')
    });
  };

  const checkMsg = useState("생성되었습니다");

  const columns = [
	{ field: 'id', headerName: '순번', width: 70 },
	{ field: 'name', headerName: '제품명', width: 130 },
  ];
  
  const rows = [
	{ id: 1, name: 'PowerPoint' },
	{ id: 2, name: 'Word' },
	{ id: 3, name: 'Excel' },
	{ id: 4, name: 'OneNote' },
	{ id: 5, name: 'OneDrive' },
	{ id: 6, name: 'Access' },
	{ id: 7, name: 'Publisher' },
	{ id: 8, name: 'Teams' },
	{ id: 9, name: 'Outlook' },
	{ id: 10, name: 'PowerPoint2' },
	{ id: 11, name: 'Word2' },
	{ id: 12, name: 'Excel2' },
	{ id: 13, name: 'OneNote2' },
	{ id: 14, name: 'OneDrive2' },
	{ id: 15, name: 'Access2' },
	{ id: 16, name: 'Publisher2' },
	{ id: 17, name: 'Teams2' },
	{ id: 18, name: 'Outlook2' },
	{ id: 19, name: 'PowerPoint3' },
	{ id: 20, name: 'Word3' },
	{ id: 21, name: 'Excel3' },
	{ id: 22, name: 'OneNote3' },
	{ id: 23, name: 'OneDrive3' },
	{ id: 24, name: 'Access3' },
	{ id: 25, name: 'Publisher3' },
	{ id: 26, name: 'Teams3' },
	{ id: 27, name: 'Outlook3' },
  ];

  return (
    <ThemeProvider theme={theme}>
		<Box sx={{ marginTop: 8 }}>
		<CssBaseline />
		<Grid container spacing={{ xs: 6, md: 0 }} columns={{ xs: 4, sm: 8, md: 12 }}>

		<Grid item xs={4} sm={8} md={6}>
		<Container maxWidth="xs">
			<Typography component="h1" variant="h5">
				제품 생성하기
			</Typography>
			<Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
				<TextField
					margin="normal"
					required
					fullWidth
					id="product"
					label="제품명"
					name="product"
					autoFocus
				/>
				<Button
					type="submit"
					fullWidth
					variant="contained"
					sx={{ mt: 3 }}
				>
				제품 생성
				</Button>
				<Box sx={{ margin: 1 }}>
					{/* TODO: checkId 로직 짜기(API, Client 둘 다) */}
					{checkMsg}
				</Box>
			</Box>
		</Container>
		</Grid>

		<Grid item xs={4} sm={8} md={6}>
		<Container maxWidth="sm">
		<Typography component="h1" variant="h5">
			전체 제품 목록
		</Typography>
		<Box sx={{ mt: 3 }}>
			<DataGrid
				rows={rows}
				columns={columns}
				pageSize={5}
				autoHeight
			/>
		</Box>
		</Container>
		</Grid>
		</Grid>
		</Box>
	</ThemeProvider>
  );
}

export default Product;