import { LOCALES } from "./locales";

// Ideally the library Intl understands simple key/value pairs for messages.
// But it's hard to manage long qualified keys as the app grows. So we write them in
// a nested JSON format and convert it to a flat object later
const messageStore = {
    [LOCALES.EN_US]: {
        app: {
            common: {
                "welcome": "Welcome to See City!",
                "created-by": "Created by",
                "login": {
                    "title": "Login",
                    "failed": "Authentication Failed"
                }
            },
            header: {
                "profile": "Profile"
            },
            city: {
                edit: "Edit City",
                empty: "No Cities found. Try a different search maybe?"
            }
        }
    },
    [LOCALES.FRENCH]: {
        app: {
            common: {
                "hi": 5,
            }
        }
    }
};

// This flattens the above JSON to a qualified key/value pair that Intl will understand
const flattenObj = (obj: any, prevKey?, flatObj?) => {
    if(!flatObj) {
        flatObj = {}
    }
    if(obj instanceof Object) {
        Object.keys(obj).map((key) => flattenObj(obj[key], (prevKey ? prevKey + "." : "") + key, flatObj));
        return flatObj;
    } else {
        flatObj[prevKey] = obj;
        return flatObj;
    }
}

const messages = {};

Object.keys(messageStore).map((langKey) => {
    const lang = messageStore[langKey];
    const flatLang = flattenObj(lang);
    messages[langKey] = flatLang;
})

export default messages;
