import React, {useState} from 'react';
import {Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, IconButton, Typography} from "@mui/material";
import {
    Bookmark, BookmarkBorder,
    ChatBubbleOutline,

    Favorite,
    FavoriteBorder,
    MoreVert,
    Share
} from "@mui/icons-material";
import {red} from "@mui/material/colors";
import PostCardMoreOption from "./PostCardMoreOption";

const PostCard = () => {

    const [liked, setLiked] = useState(false);
    const [bookmarked, setBookmarked] = useState(false);
    const [anchorEl, setAnchorEl] = useState(null);

    const handleMenuClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <Card className={""}>
                <CardHeader
                    avatar={
                        <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                            R
                        </Avatar>
                    }
                    title="Akshay"
                    subheader="September 14, 2016"
                    action={
                        <IconButton aria-label="settings" onClick={handleMenuClick}>
                            <MoreVert />
                        </IconButton>
                    }

                />
                <PostCardMoreOption anchorEl={anchorEl} handleClose={handleMenuClose} />

                <CardMedia
                    component="img"
                    // height="20%"
                    image="https://cdn.pixabay.com/photo/2023/12/26/11/48/peacock-8470021_1280.jpg"
                    alt="Paella dish"
                />

                <CardContent>
                    <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                        This impressive paella is a perfect party dish and a fun meal to cook
                        together with your guests. Add 1 cup of frozen peas along with the mussels,
                        if you like.
                    </Typography>
                </CardContent>

                <CardActions className={"flex justify-between"}>
                    <div>
                        <IconButton onClick={() => setLiked(!liked)}>
                            {liked ? <Favorite /> : <FavoriteBorder />}
                        </IconButton>

                        <IconButton>
                            <ChatBubbleOutline />
                        </IconButton>
                        <IconButton aria-label="share">
                            <Share />
                        </IconButton>
                    </div>
                    <div>
                        <IconButton onClick={() => setBookmarked(!bookmarked)}>
                            {bookmarked ? <Bookmark /> : <BookmarkBorder/>}
                        </IconButton>
                    </div>
                </CardActions>
            </Card>
        </div>
    );
};

export default PostCard;