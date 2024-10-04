package com.akshay.backend.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class UserException extends Exception{
   public UserException (String message)
   {
       super(message);
   }
}
