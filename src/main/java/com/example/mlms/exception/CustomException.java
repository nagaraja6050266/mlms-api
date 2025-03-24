package com.example.mlms.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.mlms.utils.constants.ErrorConstants;

@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception
{
	private int errCode= ErrorConstants.UNKNOWN_ERROR_CODE;
	private int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
	private List<String> details=new ArrayList<>();
	public CustomException(String message)
	{
		super(message);
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

	public CustomException(String message, int errCode, HttpStatus statusCode)
	{
		super(message);
		this.errCode = errCode;
		this.statusCode = statusCode.value();
	}

	public CustomException(String message, int errCode, HttpStatus statusCode, List<String> details){
		this(message,errCode,statusCode.value(),details);
	}

	public CustomException(String message, int errCode, int statusCode, List<String> details){
		super(message);
		this.errCode=errCode;
		this.statusCode=statusCode;
		this.details=details;
	}

	public CustomException(ErrorResponse errRes){
		this(errRes.getMessage(),errRes.getErrCode(),errRes.getStatusCode(),errRes.getDetails());
	}

}
