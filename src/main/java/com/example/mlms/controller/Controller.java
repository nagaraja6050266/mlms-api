package com.example.mlms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.mlms.dto.ActionDto;

public class Controller
{
	public static ResponseEntity<?> successRes() throws Exception
	{
		ActionDto ad=new ActionDto(1, "Action Success");
		return okResponse(ad);
	}

	public static ResponseEntity<?> response(Object object, HttpStatus httpStatus) throws Exception{
		return ResponseEntity.status(httpStatus).body(object);
	}

	public static ResponseEntity<?> okResponse(Object object) throws Exception
	{
		return response(object,HttpStatus.OK);
	}

	public static ResponseEntity<?> createdRes(Object object) throws Exception
	{
		return response(object,HttpStatus.CREATED);
	}
}
