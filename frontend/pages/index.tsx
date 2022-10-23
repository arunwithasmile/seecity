import { AppBar } from '@mui/material';
import Head from 'next/head'
import styles from '../styles/index.module.css'
import AppHeader from './components/AppHeader';

const HomePage = () => {
  return (
      <>
      <AppHeader />
      <div className={styles.container}>
        <Head>
          <title>Home - See City</title>
          <link rel="icon" href="/favicon.png" />
        </Head>

        <main className={styles.main}>
          Welcome to See City
        </main>

        <footer className={styles.footer}>
          Created by Arun S P

        </footer>
      </div></>
  )
}

export default HomePage;
