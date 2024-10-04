import React from 'react';
import {Grid2, Tab, Tabs} from "@mui/material";
import Sidebar from "../../components/Sidebar/Sidebar";
import { Route, Routes, useLocation } from "react-router-dom";
import MiddlePart from "../../components/MiddlePart/MiddlePart";
import Reels from "../../components/Reels/Reels";
import CreateReels from "../../components/Reels/CreateReels";
import Profile from "../Profile/Profile";
import HomeRight from "../../components/HomeRight/HomeRight";

const HomePage = () => {
    const location = useLocation();
    return (
        <div className="flex justify-between px-10">
            <Grid2 container spacing={0}>

                {/* Sidebar */}
                <Grid2 size={{xs:0 ,lg:3}}>
                    <div className="sticky top-0">
                        <Sidebar />
                    </div>
                </Grid2>

                {/* MiddlePart (Main content) */}
                <Grid2 size={{xs:12 ,lg:8}} className="px-5">
                    <div className="flex justify-center">
                        <Routes>
                            <Route path="/" element={<MiddlePart />} />
                            <Route path="/reels" element={<Reels />} />
                            <Route path="/create-reels" element={<CreateReels />} />
                            <Route path="/profile/:id" element={<Profile />} />
                        </Routes>
                    </div>
                </Grid2>

                {/* HomeRight (Messages, etc.) */}
                {location.pathname==="/" && <Grid2 size={{lg: 3}}>
                    <div className="sticky top-0 ">
                        <HomeRight/>
                    </div>
                </Grid2>}

            </Grid2>
        </div>
    );
};

export default HomePage;

