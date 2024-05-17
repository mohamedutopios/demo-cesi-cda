import React from 'react';
import { Navigate } from 'react-router-dom';

// Création d'un composant fonctionnel PrivateRoute
const PrivateRoute = ({ children }) => {
    // Vous pouvez remplacer cette logique par votre propre logique de vérification d'authentification
    const user = localStorage.getItem('user');
    const isLoggedIn = !!user;

    // Si isLoggedIn est true, retourner les enfants (le composant que PrivateRoute englobe),
    // sinon rediriger l'utilisateur vers la page de login
    return isLoggedIn ? children : <Navigate to="/login" />;
};
export default PrivateRoute