import { useState } from "react"

const useLocalStorage = (key, initialValue?) => {
    const [storage, setStorage] = useState(initialValue);
    const getContent = () => {
        if (storage) {
            return storage;
        }
        const storageItem = JSON.parse(String(window.localStorage.getItem(key)));
        setStorage(storageItem);
        return storageItem;
    };
    const setContent = (value) => {
        setStorage(value);
        if (!value) {
            window.localStorage.removeItem(key);
        } else {
            window.localStorage.setItem(key, JSON.stringify(value));
        }
    }
    return { setContent, getContent };
}

export default useLocalStorage;