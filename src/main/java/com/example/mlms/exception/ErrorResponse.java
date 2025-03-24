package com.example.mlms.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

@NoArgsConstructor

public class ErrorResponse
{
	private int errCode= 1000;
	private int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
	private String message="Unknown Error";
	private List<String> details;

	public ErrorResponse(String message){
		this.message=message;
	}

	public ErrorResponse(String message, int errCode){
		this.message=message;
		this.errCode=errCode;
	}

	public ErrorResponse(String message, HttpStatus code){
		this.message=message;
		this.statusCode=code.value();
	}

	public ErrorResponse(int errCode, int statusCode, String message)
	{
		this.errCode = errCode;
		this.statusCode = statusCode;
		this.message = message;
	}

	public ErrorResponse(int errCode, int statusCode, String message, List<String> details)
	{
		this(errCode,statusCode,message);
		this.details=details;
	}

	public int getErrCode()
	{
		return errCode;
	}

	public void setErrCode(int errCode)
	{
		this.errCode = errCode;
	}

	public int getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}

	public List<String> getDetails()
	{
		return details;
	}

	public void setDetails(List<String> details)
	{
		this.details = details;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
