import { AppBar, Avatar, Box, Container, IconButton, InputBase, Menu, MenuItem, Toolbar, Typography } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import styles from "./AppHeader.module.scss";
import { Stack } from "@mui/system";
import useAuth from "../../hooks/Auth/useAuth";
import { useEffect, useRef, useState } from "react";
import ClearIcon from '@mui/icons-material/Clear';
import Logout from '@mui/icons-material/Logout';
import { useRouter } from "next/router";

type Props = {
    onSearch?: (string) => void
};

const AppHeader = ({ onSearch }: Props) => {
    const { getUser, logout } = useAuth();
    const router = useRouter();
    const nullUser: any = null;
    const [user, setUser] = useState(nullUser);
    const [searchString, setSearch] = useState("");
    const inputRef = useRef<any>(null);
    useEffect(() => {
        setUser(getUser());
    });

    let timeout;
    const onSearchChange = (event) => {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            const { value } = event.target;
            setSearch(value);
        }, 600);
    }

    const clearSearch = () => {
        setSearch("");
        if (inputRef.current) {
            inputRef.current.value = null;
        }
    };

    useEffect(() => {
        if (onSearch) {
            onSearch(searchString);
        }
    }, [searchString]);

    const [anchorEl, setAnchorEl] = useState<any>(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    const onLogout = () => {
        logout();
        handleClose();
        router.push("/login");
    };
    return (
        <AppBar position='sticky' className={styles.bar}>
            <Toolbar>
                <Box className={styles.logo}>
                    <img src="../../seecity.png" />
                </Box>
                {
                    onSearch ? (
                        <Box className={styles.search}>
                            <SearchIcon />
                            <InputBase placeholder="Search" onChange={onSearchChange} inputRef={inputRef} />
                            <IconButton onClick={clearSearch}><ClearIcon style={{ margin: 0 }} /></IconButton>
                        </Box>
                    ) : (
                        <Box className={styles['search-placeholder']}>&nbsp;</Box>
                    )
                }

                <div className={styles.profile}
                    onClick={handleClick}
                >
                    <Stack direction="row" spacing={1}>
                        <Avatar sx={{ width: 32, height: 32 }}></Avatar>
                        {user && <Box>{`${user.firstName} ${user.lastName}`}</Box>}
                    </Stack>
                </div>
                <Menu
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
                >
                    <MenuItem onClick={onLogout}>
                        <Logout fontSize="small" />
                        <Typography style={{marginLeft: "1rem"}}>
                            Logout
                        </Typography>
                    </MenuItem>
                </Menu>
            </Toolbar>
        </AppBar>
    )
};

export default AppHeader;