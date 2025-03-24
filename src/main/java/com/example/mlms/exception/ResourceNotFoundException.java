package com.example.mlms.exception;

import org.springframework.http.HttpStatus;

import com.example.mlms.utils.constants.ErrorConstants;

public class ResourceNotFoundException extends CustomException
{
    Long entityId;
    public ResourceNotFoundException(String resource,Long entityId){
        String errMsg=resource + ErrorConstants.NOT_FOUND;
        int errCode=ErrorConstants.RESOURCE_NOT_FOUND_CODE;
        this.entityId=entityId;
        super(errMsg,errCode, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String resource){
		this(resource, null);
    }
}
