import axios from "axios";
import {
    LOGIN_FAILURE,
    LOGIN_REQUEST,
    LOGIN_SUCCESS,
    REGISTER_FAILURE,
    REGISTER_REQUEST,
    REGISTER_SUCCESS
} from "./auth.actionType";
import {API_BASE_URL} from "../../config/config";


export const loginUserAction=(loginData)=> async (dispatch) => {
    dispatch({type:LOGIN_REQUEST})
    try {
        const {data}=await axios.post(`${API_BASE_URL}/auth/login`, loginData.data);

        if(data.jwt)
        {
            localStorage.setItem("jwt", data.jwt);

        }

        dispatch({type:LOGIN_SUCCESS, payload:data.jwt});
        console.log("Login successfull",data);
    }
    catch (error) {
        console.log(error);
        dispatch({type:LOGIN_FAILURE, payload:error});
    }
}


export const registerUserAction=(registerData)=> async (dispatch) => {
    dispatch({type:REGISTER_REQUEST})
    try {
        const {data}=await axios.post(`${API_BASE_URL}/auth/register`, registerData.data);

        if(data.jwt)
        {
            localStorage.setItem("jwt", data.jwt);

        }
        dispatch({type:REGISTER_SUCCESS, payload:data.jwt});
        console.log("Registeration successfull",data);
    }
    catch (error) {
        console.log(error);
        dispatch({type:REGISTER_FAILURE, payload:error});
    }
}