import axios from "axios";
import { useRouter } from "next/router";
import { useState } from "react";
import useLocalStorage from "../Storage/useLocalStorage";

// {
// 	"username": "admin.seecity",
// 	"password": "S@mpl3User"
// }

const useAuth = () => {
    const { setContent, getContent } = useLocalStorage("session");
    const [loginState, setLoginState] = useState({loading: true, success: true});
    const router = useRouter();
    const login = (username, password) => {
        const data = { username, password };
        axios.post("/auth", data).then((response) => {
            setContent(response.data);
            setLoginState({loading: false, success: true});
            router.push("/");
        }).catch((err) => {
            setLoginState({loading: false, success: false});
        });
        return loginState;
    };
    const logout = () => {
        setContent(null);
    };
    const getToken = () => {
        return getContent()?.token;
    }
    const getUser = () => {
        return getContent()?.user;
    }
    return { login, logout, getToken, getUser, loginState };
}

export default useAuth;