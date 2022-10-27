import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { IntlProvider } from 'react-intl'
import { LOCALES } from '../i18n/locales';
import messages from '../i18n/messages';
import axios from 'axios';
import setAxiosProxy from '../hooks/RestApi/utilities/setAxiosProxy';

function MyApp({ Component, pageProps }: AppProps) {
  const locale = LOCALES.EN_US;

  axios.interceptors.request.use((req) => {
    req = setAxiosProxy(req);
    return req;
  });

  return (
    <IntlProvider locale={locale} messages={messages[locale]}>
      <Component {...pageProps} />
    </IntlProvider>
  );
}

export default MyApp
