import React from 'react';
import {useParams} from "react-router-dom";
import {Avatar, Box, Button, Card, Tab, Tabs} from "@mui/material";
import PostCard from "../../components/Post/PostCard";
import UserReelsCard from "../../components/Reels/UserReelsCard";

const Profile = () => {
    const tabs=[
        {value:"post", name:"Post"},
        {value:"reels", name:"Reels"},
        {value:"saved", name:"Saved"},
        {value:"archive", name:"Archive"},
    ];
    const [value, setValue] = React.useState('post');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const post=[1,1,1,1,1];

    const reels=[1,1,1,1,1];
    const {id} = useParams();
    return (
        <Card className={"my-10 w-screen"}>
            <div className={"rounded-md"}>
                <div className={"h-[15rem]"}>
                    <img className={"w-full  h-full rounded-t-md"}
                         src={"https://cdn.pixabay.com/photo/2024/03/09/16/59/typewriter-8622984_1280.jpg"}
                         alt={"profile"}/>
                </div>

                <div className={"px-5 flex justify-between items-start mt-5 h-[5rem]"}>
                    <Avatar src={""} sx={{width: "10rem", height: "10rem"}} className={"transform -translate-y-24"}/>

                    {true ?
                        <Button sx={{borderRadius: "20px"}} variant={"outlined"}>Edit Profile</Button> :
                        <Button sx={{borderRadius: "20px"}} variant={"outlined"}>Follow</Button>}
                </div>
                <div className="p-5 ">
                    <div className="flex flex-col items-start">
                        <h1 className="font-bold text-xl">Akshay Pandit</h1>
                        <p className="text-gray-500">@AkshayPandit</p>
                    </div>

                    <div className="flex gap-5 items-center py-3">
                        <span>41 posts</span>
                        <span>340 followers</span>
                        <span>87 followings</span>
                    </div>

                    <div className="flex flex-col items-start">
                        <p>this is the bio of the user</p>
                    </div>
                </div>

                <section>
                    <Box sx={{ width: '100%', borderBottom:1, borderColor:"divider" }}>
                        <Tabs
                            value={value}
                            onChange={handleChange}
                            aria-label="wrapped label tabs example"
                        >
                            {tabs.map((item) =>
                                <Tab value={item.value} label={item.name} />
                            )};

                        </Tabs>
                    </Box>

                    <div className={"flex justify-center"}>
                        {value === "post" ? <div className={"space-y-5 w-[70%] my-10"}>

                            {post.map((item) =>
                                <div className={"border rounded-md"}>
                                    <PostCard/>
                                </div>
                            )}
                        </div>: value==="reels" ? <div className={"flex flex-wrap gap-5 justify-center my-10"}>
                            {reels.map((item) => <UserReelsCard/>)}
                        </div> :
                            (
                                ""
                            )}
                    </div>
                </section>
            </div>
        </Card>
    );
};

export default Profile;