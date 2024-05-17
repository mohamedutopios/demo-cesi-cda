import Cookies from 'js-cookie';

export function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'));
  const csrfToken = Cookies.get('XSRF-TOKEN'); // Récupérer le jeton CSRF depuis le cookie
  const headers = {};

  console.log("csrfToken", csrfToken)

  if (user && user.data.token) {
      headers['Authorization'] = 'Bearer ' + user.data.token;
  }

  if (csrfToken) {
      headers['X-CSRF-TOKEN'] = csrfToken; 

  }

  return headers;
}
  