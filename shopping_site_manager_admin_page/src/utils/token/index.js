//tokenをsave
export function setToken(tokenKey,token){
    return sessionStorage.setItem(tokenKey,token);
}

//tokenをget
export function getToken(tokenKey,token){
    return sessionStorage.getItem(tokenKey,token);
}

//tokenをdelete
export function removeToken(tokenKey){
    if(getToken(tokenKey)){
        return sessionStorage.removeItem(tokenKey);
    }
    return null;
}