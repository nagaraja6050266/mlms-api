package com.example.mlms.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException
{
    Long entityId;
    public ResourceNotFoundException(String resource,Long entityId){
        String errMsg=resource + HttpStatus.NOT_FOUND.getReasonPhrase();
        int errCode=HttpStatus.NOT_FOUND.value();
        this.entityId=entityId;
        super(errMsg,errCode, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String resource){
		this(resource, null);
    }
}
