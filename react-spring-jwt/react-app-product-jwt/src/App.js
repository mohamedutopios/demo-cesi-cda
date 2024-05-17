import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import ProductList from './components/ProductList';
import Header from './shared/Header';
import CreateProduct from './components/CreateProduct';
import PrivateRoute from './helpers/PrivateRoute'

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/" element={<Login />} />

        {/* Protéger la route /products */}
        <Route path="/products" element={
          <PrivateRoute>
            <ProductList />
          </PrivateRoute>
        } />

        {/* Protéger la route /create-product */}
        <Route path="/create-product" element={
          <PrivateRoute>
            <CreateProduct />
          </PrivateRoute>
        } />
      </Routes>
    </Router>
  );
}
export default App;

