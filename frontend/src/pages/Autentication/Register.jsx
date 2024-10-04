import React, { useState } from 'react';
import { ErrorMessage, Field, Form, Formik } from "formik";
import {
    Button, FormControl, FormControlLabel, IconButton, InputAdornment, InputLabel, OutlinedInput,
    Radio, RadioGroup, TextField
} from "@mui/material";

import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

import * as Yup from "yup";
import {useDispatch} from "react-redux";
import {registerUserAction} from "../../redux/Auth/auth.action";
import {RadioButtonUnchecked} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";

const initialValues = {
    email: "",
    password: "",
    name: "",
    gender: "",
    userName:""
};

const validationSchema = Yup.object().shape({
    email: Yup.string().email('Invalid email').required('Email required'),
    password: Yup.string().required('Password required').min(6, "Password must be at least 6 characters"),
    name: Yup.string().required('Name required'),
    gender: Yup.string().required('Gender required'),
    userName: Yup.string().required('User Name required')
});

const Register = () => {
    const navigate = useNavigate();
    const dispatch=useDispatch();
    const [formValue, setFormValue] = useState();


    const handleSubmit = (values) => {
        console.log(values); // Updated to log form values
        dispatch(registerUserAction({data:values}));
    };

    const [showPassword, setShowPassword] = useState(false);

    const handleClickShowPassword = () => setShowPassword((show) => !show);

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const handleMouseUpPassword = (event) => {
        event.preventDefault();
    };

    return (
        <div>
            <Formik initialValues={initialValues} onSubmit={handleSubmit} validationSchema={validationSchema}>
                {({ handleSubmit }) => (
                    <Form className="space-y-5">
                        <div className="space-y-5">
                            <div>
                                <Field as={TextField}
                                       label="Name"
                                       name="name"
                                       placeholder="Name"
                                       variant="outlined"
                                       fullWidth
                                />
                                <ErrorMessage name="name" component="div" className="text-red-500"/>
                            </div>
                            <div>
                                <Field as={TextField}
                                       label="User Name"
                                       name="userName"
                                       placeholder="User Name"
                                       variant="outlined"
                                       fullWidth
                                />
                                <ErrorMessage name="userName" component="div" className="text-red-500"/>
                            </div>
                            <div>
                                <Field as={TextField}
                                       label="Email"
                                       name="email"
                                       placeholder="Email"
                                       variant="outlined"
                                       fullWidth
                                />
                                <ErrorMessage name="email" component="div" className="text-red-500"/>
                            </div>

                            <div>
                                <Field name="password">
                                    {({field}) => (
                                        <FormControl variant="outlined" fullWidth>
                                            <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                                            <OutlinedInput
                                                id="outlined-adornment-password"
                                                type={showPassword ? 'text' : 'password'}
                                                {...field}
                                                endAdornment={
                                                    <InputAdornment position="end">
                                                        <IconButton
                                                            aria-label="toggle password visibility"
                                                            onClick={handleClickShowPassword}
                                                            onMouseDown={handleMouseDownPassword}
                                                            onMouseUp={handleMouseUpPassword}
                                                            edge="end"
                                                        >
                                                            {showPassword ? <VisibilityOff/> : <Visibility/>}
                                                        </IconButton>
                                                    </InputAdornment>
                                                }
                                                label="Password"
                                            />
                                        </FormControl>
                                    )}
                                </Field>
                                <ErrorMessage name="password" component="div" className="text-red-500"/>
                            </div>

                            <div>
                                <FormControl component="fieldset">
                                    <RadioGroup row>
                                        <FormControlLabel
                                            name="gender"
                                            value="male"
                                            control={<Field as={Radio}/>}
                                            label="Male"
                                        />
                                        <FormControlLabel
                                            name="gender"
                                            value="female"
                                            control={<Field as={Radio}/>}
                                            label="Female"
                                        />
                                        <FormControlLabel
                                            name="gender"
                                            value="other"
                                            control={<Field as={Radio}/>}
                                            label="Other"
                                        />
                                    </RadioGroup>
                                </FormControl>
                                <ErrorMessage name="gender" component="div" className="text-red-500"/>
                            </div>

                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                fullWidth
                                sx={{padding: ".8rem 0rem"}}
                            >
                                Register
                            </Button>
                        </div>
                    </Form>
                )}
            </Formik>

            <div className={"flex justify-center items-center gap-2 pt-5"}>
                <p>
                    Already have an account?
                </p>
                <Button onClick={()=>navigate("/login")}>Login</Button>
            </div>
        </div>
    );
};

export default Register;
