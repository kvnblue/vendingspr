package com.vendingmachine.exceptionhandler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vendingmachine.exception.ProductNotFoundException;
import com.vendingmachine.to.ErrorResponse;

/**
 * Title : RestExceptionHandler
 * 
 * Description: Custom exception handler which catches {@link RuntimeException}
 * and throws custom response with error code
 * 
 * @author Kalai
 *
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger EXCEPTION_HANDLER_LOGGER = Logger.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside RestExceptionHandler.handleProductNotFoundException " + ex);
		ErrorResponse errorDetails = new ErrorResponse(ex.getMessage(), request.getDescription(false),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles input field validation exception
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside RestExceptionHandler.handleMethodArgumentNotValid " + ex);
		ErrorResponse errorDetails = new ErrorResponse(extractCustomMessage(ex), request.getDescription(false),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Extracts the default validation message for field which is having
	 * validation issues and returns
	 * 
	 * @param methodArgumentNotValidException
	 * @return {@link String} Validation messages separated by comma
	 */
	protected String extractCustomMessage(MethodArgumentNotValidException methodArgumentNotValidException) {
		EXCEPTION_HANDLER_LOGGER
				.info("Inside RestExceptionHandler.extractCustomMessage " + methodArgumentNotValidException);
		StringBuilder stringBuilder = new StringBuilder();
		if (methodArgumentNotValidException != null && methodArgumentNotValidException.getBindingResult().hasErrors()) {
			for (ObjectError objectError : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
				stringBuilder.append(objectError.getDefaultMessage() + ",");
			}
		}
		return stringBuilder.substring(0, stringBuilder.toString().length() - 1);
	}

	/**
	 * Handles general exceptions and returns custom response
	 * 
	 * @param ex
	 * @param request
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside general exception handler " + ex);
		ErrorResponse errorDetails = new ErrorResponse("Technical Exception", request.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Catches {@link NoHandlerFoundException} and returns with custom response
	 * object response
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside RestExceptionHandler.handleNoHandlerFoundException " + ex);
		ErrorResponse errorDetails = new ErrorResponse("Service You are trying to reach is not available",
				request.getDescription(false), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Catches {@link HttpRequestMethodNotSupportedException} and throws custom
	 * response
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside RestExceptionHandler.handleHttpRequestMethodNotSupported " + ex);
		ErrorResponse errorDetails = new ErrorResponse(ex.getLocalizedMessage(), request.getDescription(false),
				HttpStatus.METHOD_NOT_ALLOWED.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Handles invalid request Catches {@link HttpMessageNotReadableException}
	 * and throws custom exception
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		EXCEPTION_HANDLER_LOGGER.info("Inside RestExceptionHandler.handleHttpMessageNotReadable " + ex);
		ErrorResponse errorDetails = new ErrorResponse("Malformed JSON request", request.getDescription(false),
				HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
