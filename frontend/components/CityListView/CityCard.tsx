import { Button, Card, CardMedia, IconButton } from "@mui/material";
import { Stack } from "@mui/system";
import styles from "./city.card.module.scss";
import CreateIcon from '@mui/icons-material/Create';
import { useRouter } from "next/router";

type Props = {
    city: City,
}
const CityCard = ({ city }: Props) => {
    const router = useRouter();
    const editCity = () => {
        router.push(`cities/${city.id}/edit`, `cities/${city.id}/edit`);
    }
    return (
        <Card className={styles.card}>
            <Stack direction="row" justifyContent="space-between">
                <div className={styles.media} style={{ backgroundImage: `url(${city.photoUrl})` }}></div>
                <div style={{width: "80%"}}>
                    <div className={styles.name}>{city.name}</div>
                    <div className={styles.description}></div>
                </div>
                <div className={styles.action}>
                    <IconButton onClick={editCity}><CreateIcon /></IconButton>
                </div>
            </Stack>
        </Card>
    )
}
export default CityCard;