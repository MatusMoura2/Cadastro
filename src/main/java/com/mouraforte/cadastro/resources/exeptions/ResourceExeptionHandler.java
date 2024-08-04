package com.mouraforte.cadastro.resources.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mouraforte.cadastro.service.exceptions.DataIntegrityViolationException;
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

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException divex,
			HttpServletRequest exceptionRequest) {

		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"data breach", divex.getMessage(), exceptionRequest.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException manvex,
			HttpServletRequest exceptionRequest) {

		ValidationError validationError = new ValidationError(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(), "Validation error", "Erro na validação dos campos",
				exceptionRequest.getRequestURI());
		for (FieldError x : manvex.getBindingResult().getFieldErrors()) {
			validationError.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}

}
