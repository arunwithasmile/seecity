import Head from 'next/head'
import styles from '../styles/index.module.css'
import AppHeader from '../components/AppHeader';
import useTranslate from '../hooks/useTranslate';
import useRestApi from '../hooks/RestApi/useRestApi';
import { useEffect } from 'react';

const HomePage = () => {
  const translate = useTranslate();
  const { get } = useRestApi();
  useEffect(() => {
    get("http://localhost:8080/cities");
  }, []);
  return (
    <>
      <AppHeader />
      <div className={styles.container}>
        <Head>
          <title>Home - See City</title>
          <link rel="icon" href="/favicon.png" />
        </Head>

        <main className={styles.main}>
          {translate('welcome')}
        </main>

        <footer className={styles.footer}>
          {translate('created-by')} Arun S P
        </footer>
      </div></>
  )
}

export default HomePage;
