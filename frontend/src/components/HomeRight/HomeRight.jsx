import React from 'react';
import SearchUser from "../SearchUser/SearchUser";
import PopularUserCard from "./PopularUserCard";
import {Card} from "@mui/material";

const HomeRight = () => {
    const popularUser=[1,1,1,1];
    return (
        <div className={"p-5"}>
            <SearchUser/>

            <Card className={"p-3"}>
                <div className={"flex justify-between py-5 items-center"}>
                    <p className={"font-semibold opacity-70"}>suggestions for you</p>
                    <p className={"font-semibold opacity-95"}>View All</p>
                </div>

                <div className={"space-y-0"}>
                    {popularUser.map(item =>
                        <div>
                            <PopularUserCard/>
                        </div>)}

                </div>
            </Card>

        </div>
    );
};

export default HomeRight;