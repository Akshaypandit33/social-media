import React, {useState} from 'react';
import {ErrorMessage, Field, Form, Formik} from "formik";
import {
    Button,
    FormControl, IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    TextField,
} from "@mui/material";
import * as Yup from "yup";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";
import {useDispatch} from "react-redux";
import {loginUserAction} from "../../redux/Auth/auth.action";
import {useNavigate} from "react-router-dom";



const initialValues = {
    email:"",
    password:""
};

const validationSchema = Yup.object().shape({
    email: Yup.string().email('Invalid email').required('Email required'),
    password: Yup.string().required('Password required')
});

const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [formValue, setFormValue] = useState();


    const handleSubmit = (values) => {
        console.log(values);
        dispatch(loginUserAction({data:values}));
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
                <Form className={"space-y-5"}>
                    <div className="space-y-5 ">
                        <div>
                            <Field as={TextField}
                                   label="Email"
                                   name="email"
                                   placeholder="Email"
                                   variant="outlined"
                                   fullWidth
                            />

                            <ErrorMessage name={"email"} component={"div"} className={"text-red-500"}/>
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
                        <Button type={"submit"} variant={"contained"}
                                color={"primary"}
                                fullWidth={true}
                                sx={{padding: ".8rem 0rem"}}
                        >
                            Login
                        </Button>
                    </div>
                </Form>
            </Formik>

            <div className={"flex items-center justify-center pt-5 gap-2"}>
                <p>if you don't have a account ?</p>
                <Button onClick={()=> navigate("/register")}>Register</Button>
            </div>
        </div>
    );
};

export default Login;