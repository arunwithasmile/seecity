import { AppBar, Avatar, Box, Container, InputBase, Toolbar } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import styles from "./AppHeader.module.scss";
import { Stack } from "@mui/system";

const AppHeader = () => {
    return (
        <AppBar position='sticky' className={styles.bar}>
            <Toolbar>
                <Box className={styles.logo}>
                    <img src="seecity.png" />
                </Box>
                <Box className={styles.search}>
                    <SearchIcon />
                    <InputBase placeholder="Search" />
                </Box>
                <Stack direction="row" spacing={1} className={styles.profile}>
                    <Avatar sx={{width: 32, height: 32}}></Avatar>
                    <Box>Arun S P</Box>
                </Stack>
            </Toolbar>
        </AppBar>
    )
};

export default AppHeader;