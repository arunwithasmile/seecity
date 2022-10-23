import Head from 'next/head'
import styles from '../styles/index.module.css'
import AppHeader from '../components/AppHeader';
import useTranslate from '../hooks/useTranslate';

const HomePage = () => {
  const translate = useTranslate();
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
