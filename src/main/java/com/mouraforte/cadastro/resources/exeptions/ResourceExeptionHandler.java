package com.mouraforte.cadastro.resources.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExeptionHandler {

	@ExceptionHandler(ObjectNotFoundExeception.class)
	public ResponseEntity<StandardError> objectNotFoundExeception(ObjectNotFoundExeception onfex,
			HttpServletRequest exceptionRequest) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object Not Found", onfex.getMessage(), exceptionRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
