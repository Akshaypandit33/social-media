import React from 'react';
import { Fade, Menu, MenuItem } from '@mui/material';

const PostCardMoreOption = ({ anchorEl, handleClose }) => {
    const open = Boolean(anchorEl);

    return (
        <div>
            <Menu
                id="fade-menu"
                MenuListProps={{
                    'aria-labelledby': 'fade-button',
                }}
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                TransitionComponent={Fade}
            >
                <MenuItem onClick={handleClose}>Report</MenuItem>
                <MenuItem onClick={handleClose}> About this account</MenuItem>

            </Menu>
        </div>
    );
};

export default PostCardMoreOption;
