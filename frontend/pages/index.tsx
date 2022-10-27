import Head from 'next/head'
import styles from '../styles/index.module.css'
import AppHeader from '../components/AppHeader';
import useTranslate from '../hooks/useTranslate';
import { useState } from 'react';
import CityListView from '../components/CityListView';

const HomePage = () => {
  const translate = useTranslate();
  const [searchString, setSearchString] = useState("");
  return (
    <>
      <AppHeader onSearch={(val) => setSearchString(val)} />
      <div className={styles.container}>
        <Head>
          <title>Home - See City</title>
          <link rel="icon" href="/favicon.png" />
        </Head>

        <main className={styles.main}>
          <div style={{padding: "0.5rem"}}>
            {translate('welcome')}
          </div>
          <CityListView searchString={searchString} />
        </main>

        <footer className={styles.footer}>
          {translate('created-by')} Arun S P
        </footer>
      </div>
    </>
  )
}

export default HomePage;
