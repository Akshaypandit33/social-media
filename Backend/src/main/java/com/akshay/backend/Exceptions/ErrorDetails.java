package com.akshay.backend.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

//    these are the field required from the thrown exception error
    private String message;
    private String error;
    private LocalDateTime timestamp;

}
