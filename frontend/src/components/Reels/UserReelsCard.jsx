import React from 'react';

const UserReelsCard = () => {
    return (
        <div className={"w-[15rem] px-2"}>
            <video className={"w-full h-full"} autoPlay={true}
                   controls={true}

                   src={"https://videos.pexels.com/video-files/26309582/11946482_360_640_30fps.mp4"}/>
        </div>
    );
};

export default UserReelsCard;