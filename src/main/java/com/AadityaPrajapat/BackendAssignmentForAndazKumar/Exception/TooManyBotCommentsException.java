package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class TooManyBotCommentsException extends RuntimeException{
    public TooManyBotCommentsException(String msg){
        super(msg);
    }
}
