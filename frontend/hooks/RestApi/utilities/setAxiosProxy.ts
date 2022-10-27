import { AxiosRequestConfig } from "axios";

const setAxiosProxy = (request: AxiosRequestConfig<any>) => {
    if(!request.url?.startsWith("http")) {
        const proxyDomain = "http://localhost:8080"; // TODO: Externalise this.
        request.url = new URL(String(request.url), proxyDomain).href;
    }
    return request;
}

export default setAxiosProxy;