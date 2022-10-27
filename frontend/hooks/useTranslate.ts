import { useIntl } from "react-intl";

const useTranslate = () => {
    const intl = useIntl();
    const translate = (key, modulePrefix="common", globalPrefix="app") => (
        intl.formatMessage({ id: `${globalPrefix}.${modulePrefix}.${key}`})
    );
    return translate;
}

export default useTranslate;