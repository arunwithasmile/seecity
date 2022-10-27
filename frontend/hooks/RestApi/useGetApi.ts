import axios from "axios";
import { useRouter } from "next/router";
import { useState } from "react";
import useAuth from "../Auth/useAuth";

const useGetApi = () => {
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
    const anyNull: any = null;
    const [getState, setState] = useState({ loading: false, success: true, data: anyNull});

    const get = (url) => {
        const authHeader = getAuthHeader();
        if (!authHeader) {
            return;
        }
        axios.get(url, authHeader).then((response) => {
            setState({ loading: false, success: true, data: response.data });
        }).catch((err) => {
            setState({ loading: false, success: false, data: err });
            console.error(err);
        });
    };

    return { get, getState };
};

export default useGetApi;