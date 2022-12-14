import { Button, TextField } from '@mui/material';
import Head from 'next/head';
import useAuth from '../hooks/Auth/useAuth';
import useTranslate from '../hooks/useTranslate';
import styles from '../styles/index.module.css';
const LoginPage = () => {
    const translate = useTranslate();
    const { login, loginState } = useAuth();
    const onSubmit = (event) => {
        event.preventDefault();
        var { username, password } = document.forms[0];
        login(username.value, password.value);
    };
    return (
        <div className={styles.container}>
            <Head>
                <title>Login - See City</title>
                <link rel="icon" href="/favicon.png" />
            </Head>

            <main className={styles.main}>
                <div style={{ maxWidth: 400 }}>
                    <h2 style={{ textAlign: "center" }}>{translate('login.title')}</h2>
                    {
                        loginState && !loginState.success ? (
                            <div style={{ textAlign: "center", color: "#ff6400" }}>{translate('login.failed')}</div>
                        ) : null
                    }
                    <form onSubmit={onSubmit}>
                        <TextField
                            required
                            margin="normal"
                            fullWidth
                            label="Username"
                            name="username"
                            autoFocus
                        />
                        <TextField
                            required
                            margin="normal"
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            autoComplete="current-password"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                        >
                            Login
                        </Button>
                    </form>
                </div>
                <a style={{fontSize: 13, marginTop: "2rem"}} href="register">{translate('register')}</a>
            </main>

            <footer className={styles.footer}>
                {translate('created-by')} Arun S P
            </footer>
        </div>
    );
};

export default LoginPage;