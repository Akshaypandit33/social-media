import React from 'react';
import { Avatar, Card, IconButton } from "@mui/material";
import { Add, Article, Image, Videocam } from "@mui/icons-material";
import StoryCircle from "./StoryCircle";
import PostCard from "../Post/PostCard";

// Dummy arrays for stories and posts
const story = [1, 1, 1, 1, 1];
const post = [1, 1, 1, 1, 1];

const MiddlePart = () => {
    const handleOpenPostModel = () => {
        console.log("Handling post");
    };

    return (
        <div className={" " +
            "sm:px-8 lg:px-20 max-w-screen-lg " +
            "mx-auto"}>
            {/* Story Section */}
            <section className={"flex items-center overflow-x-auto space-x-4 p-5 rounded-b-md"}>
                <div
                    className={"flex flex-col items-center mr-4 cursor-pointer"}>
                    <Avatar sx={{ width: "4rem", height: "4rem" }}>
                        <Add sx={{ fontSize: "3rem" }} />
                    </Avatar>
                    <p className="text-center">New</p>
                </div>

                {/* Story Circles */}
                {story.map((item, index) => (
                    <StoryCircle key={index} />
                ))}
            </section>

            {/* Create Post Section */}
            <section>
                <Card className={"p-5 mt-5"}>
                    <div className={"flex justify-between items-center"}>
                        <Avatar />
                        <input
                            readOnly={true}
                            type={"text"}
                            placeholder="What's on your mind?"
                            className={"outline-none w-full sm:w-[90%] bg-slate-100 rounded-full px-5 bg-transparent border border-[#3b4054]"}
                        />
                    </div>

                    <div className={"flex justify-center space-x-4 sm:space-x-9 mt-5"}>
                        <div className="flex items-center cursor-pointer">
                            <IconButton color={"primary"} onClick={handleOpenPostModel}>
                                <Image />
                            </IconButton>
                            <span>Media</span>
                        </div>

                        <div className="flex items-center cursor-pointer">
                            <IconButton color={"primary"} onClick={handleOpenPostModel}>
                                <Videocam />
                            </IconButton>
                            <span>Video</span>
                        </div>

                        <div className="flex items-center cursor-pointer">
                            <IconButton color={"primary"} onClick={handleOpenPostModel}>
                                <Article />
                            </IconButton>
                            <span>Article</span>
                        </div>
                    </div>
                </Card>
            </section>

            {/* Post Section */}
            <section className={"mt-10 space-y-8"}>
                {post.map((item, index) => (
                    <div key={index} className="w-full mx-auto">
                        <PostCard />
                    </div>
                ))}
            </section>
        </div>
    );
};

export default MiddlePart;
