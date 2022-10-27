import { Button, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import axios from 'axios';
import Head from 'next/head';
import { useState } from 'react';
import useAuth from '../hooks/Auth/useAuth';
import usePostApi from '../hooks/RestApi/usePostApi';
import useTranslate from '../hooks/useTranslate';
import styles from '../styles/index.module.css';

const LoginPage = () => {

    const translate = useTranslate();
    const [role, setRole] = useState('ROLE_ALLOW_READ');

    const [postState, setState] = useState({ loading: false, success: true, data: null });

    const post = (url, data) => {
        axios.post(url, data).then((response) => {
            setState({ loading: false, success: true, data: response.data });
        }).catch((err) => {
            setState({ loading: false, success: false, data: err });
            console.error(err);
        });
    };

    const onRoleChange = (event) => {
        setRole(event.target.value);
    };

    const onSubmit = (event) => {
        event.preventDefault();
        var { firstName, lastName, username, password } = document.forms[0];
        var user = {
            firstName: firstName.value,
            lastName: lastName.value,
            username: username.value,
            password: password.value,
            roles: [
                {
                    role
                }
            ]
        };
        console.log(user);
        post("/user/new", user);
    };

    return (
        <div className={styles.container}>
            <Head>
                <title>Add User - See City</title>
                <link rel="icon" href="/favicon.png" />
            </Head>

            <main className={styles.main}>
                <div style={{ maxWidth: 400 }} className={styles.login}>
                    <h2 style={{ textAlign: "center" }}>{translate('register')}</h2>
                    {
                        postState && postState.success ? (
                            <div style={{ textAlign: "center", color: "#496d27" }}>{postState.data}</div>
                        ) : null
                    }
                    <form onSubmit={onSubmit}>
                        <TextField
                            margin="normal"
                            fullWidth
                            label="First Name"
                            name="firstName"
                            autoFocus
                            size="small"
                        />
                        <TextField
                            required
                            margin="normal"
                            fullWidth
                            label="Last Name"
                            name="lastName"
                            size="small"
                        />
                        <TextField
                            required
                            margin="normal"
                            fullWidth
                            label="Username"
                            name="username"
                            size="small"
                        />
                        <TextField
                            required
                            margin="normal"
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            size="small"
                        />
                        <FormControl margin="normal" fullWidth size="small">
                            <InputLabel id="demo-select-small">Role</InputLabel>
                            <Select
                                labelId="demo-select-small"
                                id="demo-select-small"
                                value={role}
                                label="Age"

                                onChange={onRoleChange}
                            >
                                <MenuItem value="admin">Admin</MenuItem>
                                <MenuItem value="ROLE_ALLOW_EDIT">Editor</MenuItem>
                                <MenuItem value="ROLE_ALLOW_READ">Visitor</MenuItem>
                            </Select>
                        </FormControl>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                        >
                            Save
                        </Button>
                    </form>
                </div>

                <a style={{ fontSize: 13, marginTop: "2rem" }} href="login">{translate('login.title')}</a>
            </main>

            <footer className={styles.footer}>
                {translate('created-by')} Arun S P
            </footer>
        </div>
    );
};

export default LoginPage;