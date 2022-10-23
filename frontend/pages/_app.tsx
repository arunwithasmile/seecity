import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { IntlProvider } from 'react-intl'
import { LOCALES } from '../i18n/locales';
import messages from '../i18n/messages';

function MyApp({ Component, pageProps }: AppProps) {
  const locale = LOCALES.EN_US;
  console.log("IntlProvider", messages[locale]);
  return (
    <IntlProvider locale={locale} messages={messages[locale]}>
      <Component {...pageProps} />
    </IntlProvider>
  );
}

export default MyApp
