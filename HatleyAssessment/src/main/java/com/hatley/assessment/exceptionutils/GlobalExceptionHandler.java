package com.hatley.assessment.exceptionutils;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {
	private  HttpHeaders httpHeaders= new HttpHeaders();
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<ErrorDetails> handleIllegalIPv4AddressException(TransactionSystemException e , WebRequest request ){
		if(e.contains(IllegalArabicNameException.class)) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMostSpecificCause().getMessage(), request.getDescription(false)) ;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ErrorDetails>(errorDetails ,httpHeaders, HttpStatus.NOT_ACCEPTABLE ) ;}
		return null ;
	}
	@ExceptionHandler(value= LimitReachedException.class)
	public ResponseEntity<ErrorDetails> handleLimitReachedException(LimitReachedException e , WebRequest request ){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false)) ;
		return new ResponseEntity<ErrorDetails>(errorDetails , HttpStatus.NOT_ACCEPTABLE ) ;
	}
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(NoSuchElementException e , WebRequest request ){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "no gateway with that identifier", request.getDescription(false)) ;
		return new ResponseEntity<ErrorDetails>(errorDetails , HttpStatus.NOT_ACCEPTABLE ) ;
	}

}
