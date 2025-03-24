package com.example.mlms.exception;

public class EntitiesAssociatedException extends CustomException
{
    String entity;
	public EntitiesAssociatedException(String message, String entity)
    {
        this.entity=entity;
        super(message);
    }
}
