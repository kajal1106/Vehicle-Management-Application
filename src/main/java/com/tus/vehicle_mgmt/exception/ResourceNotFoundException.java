package com.tus.vehicle_mgmt.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
	public ResourceNotFoundException(String message) {
		
		//because it extends exception we use super()
		super(message);
	}
	
}
