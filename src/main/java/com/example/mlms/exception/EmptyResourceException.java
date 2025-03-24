package com.example.mlms.exception;

import org.springframework.http.HttpStatus;

import com.example.mlms.utils.constants.ErrorConstants;

public class EmptyResourceException extends CustomException
{
	public EmptyResourceException(String entity)
	{
        String errMsg="No "+entity+" found";
        int errCode= ErrorConstants.RESOURCE_NOT_FOUND_CODE;
        super(errMsg,errCode, HttpStatus.NOT_FOUND);
	}
}
