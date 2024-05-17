import api from './api';
import { authHeader } from '../helpers/auth-header';

const getAllProducts = () => {
  return api.get('/products', { headers: authHeader() });
};


const createProduct = (product) => {
  return api.post('/products/admin/post', product, { headers: authHeader() });
};


const deleteProduct = (productId) => {
  return api.delete(`/products/${productId}`, { headers: authHeader() });
};

const updateProduct = (productData) => {
  console.log(productData)
  return api.put('/products/admin/update', productData, {
    headers: {
      ...authHeader(),
      'Content-Type': 'application/json'
    }
  });
};

export const productService = { getAllProducts, createProduct, deleteProduct, updateProduct };
