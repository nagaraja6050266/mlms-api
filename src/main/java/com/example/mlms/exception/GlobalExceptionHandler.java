package com.example.mlms.exception;

import jakarta.validation.ConstraintViolationException;

import java.util.ArrayList;

import org.postgresql.util.PSQLState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.postgresql.util.PSQLException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(CustomException.class)
	public static ResponseEntity<ErrorResponse> handleCustom(CustomException e){
		ErrorResponse errRes=new ErrorResponse(e.getErrCode(),e.getStatusCode(),e.getMessage(),e.getDetails());
		return resToEntity(errRes);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public static ResponseEntity<ErrorResponse> requestValidation(ConstraintViolationException e){
		ErrorResponse errRes =new ErrorResponse("Invalid Request Format",HttpStatus.BAD_REQUEST);
		ArrayList<String> errDetails=new ArrayList<>();
		e.getConstraintViolations().forEach(violation -> {
			String errorMessage = violation.getMessage();
			errDetails.add(errorMessage);
		});
		errRes.setDetails(errDetails);
		return resToEntity(errRes);
	}

	@ExceptionHandler(PSQLException.class)
	public static ResponseEntity<ErrorResponse> handlePSQLException(PSQLException e){
		if (PSQLState.UNIQUE_VIOLATION.getState().equals(e.getSQLState())) {
			ErrorResponse errRes=new ErrorResponse("Already Exists",HttpStatus.BAD_REQUEST);
			return resToEntity(errRes);
		}
		return handleUnknownError();
	}

	@ExceptionHandler(Exception.class)
	public static ResponseEntity<?> handleException(Exception e) throws Exception{
		System.out.println(e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
	}
	
	public static ResponseEntity<ErrorResponse> resToEntity(ErrorResponse errRes){
		return ResponseEntity.status(errRes.getStatusCode()).body(errRes);
	}

	public static ResponseEntity<ErrorResponse> handleUnknownError(){
		ErrorResponse errRes =new ErrorResponse("Unknown Error",HttpStatus.INTERNAL_SERVER_ERROR);
		return resToEntity(errRes);
	}
}
