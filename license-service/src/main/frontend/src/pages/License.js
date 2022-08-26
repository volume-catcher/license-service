import React, { useState } from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import SearchTable from 'components/SearchTable';

const theme = createTheme({
	palette: {
		neutral: {
		  main: '#E0E0E0',
		  contrastText: '#fff',
		}
	}
});

const License = (props) => {
	const handleSubmit = (event) => {
		// axios 통신
	};
	
	const checkMsg = useState("생성되었습니다");

	const columns = ["순번", "라이선스 키"];
	
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
				라이선스 발급하기
			</Typography>
			<Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
				<Button
					type="submit"
					fullWidth
					variant="contained"
					sx={{ mt: 3, padding: 2, fontSize: 18 }}
				>
				라이선스 키 발급
				</Button>
			</Box>
			<Box sx={{ 
				backgroundColor: 'neutral.main', 
				mt: 5,
				padding: 1
			}}>
				UEOI-39NG-WKF2-SKE9
			</Box>
			<Box sx={{ margin: 1 }}>
				{/* TODO: checkId 로직 짜기(API, Client 둘 다) */}
				{checkMsg}
			</Box>
		</Container>
		</Grid>

		<Grid item xs={4} sm={8} md={6}>
		<Container maxWidth="sm">
		<Typography component="h1" variant="h5">
			전체 라이선스 목록
		</Typography>
		<Box sx={{ mt: 3 }}>
			<SearchTable rows={rows} columns={columns} searchPlaceHolder="라이선스 키 검색"/>
		</Box>
		</Container>
		</Grid>
		</Grid>
		</Box>
		</ThemeProvider>
    );
}

export default License;