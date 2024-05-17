
export const getUserDetails = () => {
    const userItem = localStorage.getItem('user');
   // console.log("user1" + userItem);
    if (!userItem) {
        console.log("No user found in localStorage.");
        return null;
    }

    // Parse l'objet utilisateur depuis le localStorage
    const user = JSON.parse(userItem);
    //console.log("user2" + user);

    // S'assurer que user et user.data.token existent
    if (!user || !user.data || !user.data.token) {
        console.log("User token not found.");
        return null;
    }

    // Extraire le token de user.data.token
    const token = user.data.token;

    // Séparer le token en ses parties
    const base64Url = token.split('.')[1];
    if (!base64Url) {
        console.log("Invalid token: missing payload.");
        return null;
    }

    // Remplacer les caractères de Base64Url par des caractères de Base64 standard
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    
    // Décoder la chaîne Base64 en chaîne UTF-8
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    // Parser la chaîne JSON en objet
    const decoded = JSON.parse(jsonPayload);
    //console.log(decoded);

    // Supposant que les informations que vous souhaitez sont dans 'sub' et 'roles'
    return { name: decoded.sub, role: decoded.roles };
};



