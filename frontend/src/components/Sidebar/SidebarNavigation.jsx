import {
    AccountCircle,
    ControlPoint,
    Explore,
    Group,
    Home,
    ListAlt,
    Message,
    Notifications
} from "@mui/icons-material";

export const navigationMenu=[
    {
        title:"Home",
        icon:<Home/>,
        path:"/"
    },
    {
        title:"Reels",
        icon:<Explore/>,
        path:"/Reels"
    },
    {
        title:"Create Reels",
        icon:<ControlPoint/>,
        path:"/"
    },
    {
        title:"Notification",
        icon:<Notifications/>,
        path:"/"
    },
    {
        title:"Message",
        icon:<Message/>,
        path:"/"
    },
    {
        title:"Lists",
        icon:<ListAlt/>,
        path:"/"
    },
    {
        title:"Communities",
        icon:<Group/>,
        path:"/"
    },
    {
        title:"profile",
        icon:<AccountCircle/>,
        path:"/"
    },
]