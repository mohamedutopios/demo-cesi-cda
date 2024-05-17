import api from './api';

const register = (name, email, password, roles) => {
  return api.post('/auth/register', { name, email, password, roles });
};

const login = (email, password) => {
  return api.post('/auth/login', { email, password }).then((response) => {
    console.log(response.data.data)
    if (response.data.data.token) {
      localStorage.setItem('user', JSON.stringify(response.data));
      
    }
    return response.data;
  });
};

const logout = () => {
  localStorage.removeItem('user');
};

export default { register, login, logout };
