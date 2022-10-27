import axios from "axios";
import { useRouter } from "next/router";
import { useState } from "react";
import useAuth from "../Auth/useAuth";

const usePostApi = () => {
    const { getToken } = useAuth();
    const router = useRouter();

    const getAuthHeader = () => {
        const token = getToken();
        if (!token) {
            router.push("login");
            return null;
        }
        return { headers: { 'Authorization': `Bearer ${token}` } };
    }

    const [postState, setState] = useState({ loading: false, success: true, data: null });

    const post = (url, data) => {
        const authHeader = getAuthHeader();
        if (!authHeader) {
            return;
        }
        axios.post(url, data, authHeader).then((response) => {
            setState({ loading: false, success: true, data: response.data });
        }).catch((err) => {
            setState({ loading: false, success: false, data: err });
            console.error(err);
        });
    };

    return { post, postState };
};

export default usePostApi;