import React from 'react';
import {Avatar, Button, CardHeader, IconButton} from "@mui/material";
import {red} from "@mui/material/colors";

const PopularUserCard = () => {

    return (
        <div>
            <CardHeader
                avatar={
                    <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                        R
                    </Avatar>
                }
                action={
                    <Button size={"small"}>
                        Follow
                    </Button>
                }
                title="user1"
                subheader="@user1"
            />
        </div>
    );
};

export default PopularUserCard;