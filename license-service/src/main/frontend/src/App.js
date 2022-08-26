import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ResponsiveAppBar from 'components/ResponsiveAppBar';
import License from 'pages/License';
import NotFound from 'pages/NotFound';
import SignIn from 'pages/SignIn';
import SignUp from 'pages/SignUp';
import Product from 'pages/Product';
import CollapsibleTable from 'components/CollapsibleTable';

function App() {
  return (
	<BrowserRouter>
		<ResponsiveAppBar />
		<Routes>
			<Route path="/" element={<License />}></Route>
			<Route path="/product" element={<Product />}></Route>
			<Route path="/signin" element={<SignIn />}></Route>
			<Route path="/signup" element={<SignUp />}></Route>
			<Route path="/detail" element={<CollapsibleTable />}></Route>
			<Route path="*" element={<NotFound />}></Route>
		</Routes>
	</BrowserRouter>
  );
}

export default App;
