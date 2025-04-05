package az.developia.full_stack_ecommerce.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.developia.full_stack_ecommerce.exception.InvalidCredentialsException;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.response.ExceptionResponse;
import az.developia.full_stack_ecommerce.response.ValidationResponse;

@RestControllerAdvice
public class ExceptionHandlers {
	
	@ExceptionHandler(exception = InvalidCredentialsException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ExceptionResponse handleInvalidCredentialsException(InvalidCredentialsException exc) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(exc.getMessage());
		return response;
	}

	@ExceptionHandler(exception = OurRuntimeException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handler(OurRuntimeException exc) {
		ExceptionResponse response = new ExceptionResponse();
		
		BindingResult br = exc.getBr();
		if (br == null) {
			
		}else {
			List<FieldError> fieldErrors = br.getFieldErrors();
			List<ValidationResponse> validations = new ArrayList<ValidationResponse>();
			for(FieldError fieldError:fieldErrors) {
				ValidationResponse validation = new ValidationResponse();
				validation.setField(fieldError.getField());
				validation.setErrorMessage(fieldError.getDefaultMessage());
				validations.add(validation);
			}
			response.setValidations(validations);
		}
		
		response.setMessage(exc.getMessage());
		return response;
	}
}
