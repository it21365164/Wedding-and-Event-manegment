package com.example.backend.exception;

public class AssignStaffEventsNotFoundException extends RuntimeException {

    public AssignStaffEventsNotFoundException(Long id){

        super("Could not found the staff with id "+ id);
    }

}
