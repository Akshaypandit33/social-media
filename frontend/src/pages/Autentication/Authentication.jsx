import React from 'react';
import {Card, Grid2} from "@mui/material";
import Login from "./Login";
import Register from "./Register";
import {Route, Routes} from "react-router-dom";

const Authentication = () => {
    return (
        <div className={""}>
            <Grid2 container>
                <Grid2 className={"h-screen overflow-hidden"} size={{ xs: 7}} >
                    <img className={"w-full h-full"} src={"https://cdn.pixabay.com/photo/2018/11/29/21/51/social-media-3846597_1280.png"} alt={"social media"}/>
                </Grid2>

                <Grid2 size={{ xs: 5}}>
                    <div className={"px-20 flex flex-col justify-center h-full"} >

                        <Card className={"card p-8"}>

                            <div className={"flex flex-col items-center mb-5 space-y-1"}>
                                <h1 className={"logo text-center"}>Mystify</h1>
                                <p className={"text-center text-sm w-[70%]"}>Mystify application tagline </p>


                                <Routes>
                                    <Route path="/" element={<Login/>}/>
                                    <Route path="/login" element={<Login/>} />
                                    <Route path="/register" element={<Register/>} />
                                </Routes>

                            </div>
                        </Card>
                    </div>
                </Grid2>
            </Grid2>
        </div>
    );
};

export default Authentication;