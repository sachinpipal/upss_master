package com.verizon.upss.demo.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.verizon.upss.demo.commons.util.MessageResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ResponseBody
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResult errorResult = new ErrorResult();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResult.getFieldErrors()
					.add(new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		MessageResponse messageResponse = new MessageResponse("failure", errorResult);
		return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UpssBaseException.class)
	@ResponseBody
	public ResponseEntity<MessageResponse> handleUpssException(final Exception exception,
			final HttpServletRequest request) {
		MessageResponse messageResponse = new MessageResponse("failure", exception.getMessage());
		return new ResponseEntity<>(messageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Getter
	@NoArgsConstructor
	static class ErrorResult {
		private final List<FieldValidationError> fieldErrors = new ArrayList<>();

		ErrorResult(String field, String message) {
			this.fieldErrors.add(new FieldValidationError(field, message));
		}
	}

	@Getter
	@AllArgsConstructor
	static class FieldValidationError {
		private String field;
		private String message;
	}

}
