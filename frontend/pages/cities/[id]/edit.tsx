import { Button, Card, CardContent, CardMedia, TextField, Typography } from "@mui/material";
import { Stack } from "@mui/system";
import Head from "next/head";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import AppHeader from "../../../components/AppHeader";
import useGetApi from "../../../hooks/RestApi/useGetApi";
import usePostApi from "../../../hooks/RestApi/usePostApi";
import useTranslate from "../../../hooks/useTranslate";
import styles from '../../../styles/index.module.css';

const CityEditPage = () => {
    const translate = useTranslate();
    const router = useRouter();
    const { get, getState } = useGetApi();
    const { post, postState } = usePostApi();
    const [cityId, setId] = useState("");
    const [city, setCity] = useState({ name: "", photoUrl: "" });
    const [edited, setEdited] = useState(false);
    console.log();

    useEffect(() => {
        if (router.isReady) {
            const id = String(router.query.id);
            setId(id);
            get(`/cities/${id}`);
        }
    }, [router]);

    useEffect(() => {
        if (getState.success && getState.data) {
            setCity(getState.data);
        }
    }, [getState]);

    const onNameChange = (event) => {
        setCity({ ...city, name: event.target.value });
    };
    const onUrlChange = (event) => {
        setCity({ ...city, photoUrl: event.target.value });
    };

    const onSubmit = (event) => {
        event.preventDefault();
        post(`cities/${cityId}`, city);
        setEdited(true);
    };

    useEffect(() => {
        if (edited && postState.success) {
            router.push("/");
        }
    }, [edited, postState]);
    return (
        <>
            <Head>
                <title>Login - See City</title>
                <link rel="icon" href="/favicon.png" />
            </Head>
            <AppHeader />
            <div style={{ textAlign: "center", marginTop: "3rem" }}>{translate('edit', 'city')}</div>
            <main className={styles.main} style={{ paddingTop: 0, minHeight: "80vh" }}>
                <Stack direction="row" justifyContent="space-around" width="80%">
                    <Card style={{display: "flex", alignItems: "center", padding: "2rem"}}>
                        <form onSubmit={onSubmit}>
                            <TextField
                                required
                                margin="normal"
                                fullWidth
                                label="Name"
                                name="name"
                                value={city?.name}
                                autoFocus
                                onChange={onNameChange}
                            />
                            <TextField
                                margin="normal"
                                fullWidth
                                name="photoUrl"
                                label="Photo URL"
                                value={city?.photoUrl}
                                onChange={onUrlChange}
                            />
                            <Stack direction="row" justifyContent="flex-end" spacing={2}>
                                <Button onClick={() => router.back()}>Cancel</Button>
                                <Button
                                    type="submit"
                                    variant="contained"
                                >
                                    Save
                                </Button>
                            </Stack>
                        </form>
                    </Card>
                    <Card style={{ maxWidth: 280 }}>
                        <CardMedia
                            component="img"
                            height="140"
                            image={city?.photoUrl}
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h5" component="div">
                                {city?.name}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Dicta, quasi nam magnam vero molestiae officia earum obcaecati, provident quas eaque ut sunt, excepturi perferendis aliquam!
                            </Typography>
                        </CardContent>
                    </Card>
                </Stack>
            </main>
            <footer className={styles.footer}>
                {translate('created-by')} Arun S P
            </footer>
        </>
    );
}

export default CityEditPage;