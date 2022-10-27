import { Button, Card, CardMedia, IconButton } from "@mui/material";
import { Stack } from "@mui/system";
import styles from "./city.card.module.scss";
import CreateIcon from '@mui/icons-material/Create';
import { useRouter } from "next/router";
import useAuth from "../../hooks/Auth/useAuth";

type Props = {
    city: City,
}
const CityCard = ({ city }: Props) => {
    const router = useRouter();
    const { getUser } = useAuth();
    const user = getUser();
    const allowEdit = user.roles.find(roleEntry => roleEntry.role === "admin" || roleEntry.role === "ROLE_ALLOW_EDIT") != null;
    const editCity = () => {
        router.push(`cities/${city.id}/edit`, `cities/${city.id}/edit`);
    }
    return (
        <Card className={styles.card}>
            <Stack direction="row" justifyContent="space-between">
                <div className={styles.media} style={{ backgroundImage: `url(${city.photoUrl})` }}></div>
                <div style={{ width: "80%" }}>
                    <div className={styles.name}>{city.name}</div>
                    <div className={styles.description}></div>
                </div>
                {
                    allowEdit ? (
                        <div className={styles.action}>
                            <IconButton onClick={editCity}><CreateIcon /></IconButton>
                        </div>
                    ) : null
                }
            </Stack>
        </Card>
    )
}
export default CityCard;