package com.tony.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id) {
        super("Record not found: " + id);
    }
    
}
