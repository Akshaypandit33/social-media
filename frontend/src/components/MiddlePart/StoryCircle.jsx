import React from 'react';
import {Avatar} from "@mui/material";
import {Add} from "@mui/icons-material";

const StoryCircle = () => {
    return (
        <div>

            <div
                src={""}
                className={"flex flex-col items-center mr-4 cursor-pointer"}>
                <Avatar sx={{width: "4rem", height: "4rem"}}/>


                <p className={"userName"}>Akshay</p>

            </div>
        </div>
    );
};

export default StoryCircle;