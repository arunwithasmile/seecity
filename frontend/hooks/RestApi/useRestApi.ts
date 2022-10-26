import axios from "axios";

const useRestApi = () => {
    const get = (url) => {
        axios.get(url).then((response) => {
            console.log(response);
        }).catch((err) => {
            console.error(err);
        });
    };
    return { get };
};

export default useRestApi;