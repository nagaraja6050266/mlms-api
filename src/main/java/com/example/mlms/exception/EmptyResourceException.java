package com.example.mlms.exception;

import org.springframework.http.HttpStatus;

public class EmptyResourceException extends CustomException
{
	public EmptyResourceException(String entity)
	{
        String errMsg="No "+entity+" found";
        int errCode= HttpStatus.NOT_FOUND.value();
        super(errMsg,errCode, HttpStatus.NOT_FOUND);
	}
}
