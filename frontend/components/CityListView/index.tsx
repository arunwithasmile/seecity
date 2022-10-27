import { Pagination, Stack, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import useGetApi from "../../hooks/RestApi/useGetApi";
import useTranslate from "../../hooks/useTranslate";
import CityCard from "./CityCard";

type Props = {
    searchString: string
};

const CityListView = ({ searchString }: Props) => {

    const translate = useTranslate();
    const { get, getState } = useGetApi();
    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(0);

    useEffect(() => {
        console.log("Changed")
        const size = Math.floor((window.innerHeight - 180) / 80);
        setPageSize(size);
        get(`/cities?page=0&size=${size}` + (searchString ? `&search=${searchString}` : ""));
    }, [searchString]);

    const handleChange = (event: React.ChangeEvent<unknown>, value: number) => {
        setPage(value);
        get(`/cities?page=${value - 1}&size=${pageSize}` + (searchString ? `&search=${searchString}` : ""));
    };

    return (
        <>
            <div style={{minHeight: "50vh", width: "100%", display: "grid", placeItems: "center"}}>
                {
                    getState.data?.content ? getState.data.content.map((city, index) => (
                        <CityCard city={city} key={`city-card-${index + 1}`} />
                    )) : null
                }
                {
                    getState.data?.totalElements == 0 ? translate("empty", "city") : ""
                }
            </div>
            <Pagination count={getState.data?.totalPages} page={page} onChange={handleChange} />
        </>
    );
}
export default CityListView;