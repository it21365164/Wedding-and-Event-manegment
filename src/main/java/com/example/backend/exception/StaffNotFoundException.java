package com.example.backend.exception;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(Long id){
        super("Could not found the staff with id "+ id);
    }
}
