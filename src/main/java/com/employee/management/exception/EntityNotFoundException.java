package com.employee.management.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String entity, Long id ) {
        super(entity+" with id: " + id +" not found.");
    }
}
