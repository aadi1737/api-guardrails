package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleTooManyBotComment(TooManyBotCommentsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleCooldDown(CoolDownException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleRuntime(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> botLikeException(BotCanNotLikeException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


}
