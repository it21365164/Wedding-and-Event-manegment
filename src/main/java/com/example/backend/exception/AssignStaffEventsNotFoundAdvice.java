package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AssignStaffEventsNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AssignStaffEventsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String>exceptionHandler(AssignStaffEventsNotFoundException exception){
        Map<String,String>errorMap=new HashMap<>();
        return errorMap;
    }
}
