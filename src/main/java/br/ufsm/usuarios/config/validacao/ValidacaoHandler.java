package br.ufsm.usuarios.config.validacao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ufsm.usuarios.controller.dto.ApiErrorDTO;

@RestControllerAdvice
public class ValidacaoHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ApiErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
		return ex.getBindingResult()
				.getAllErrors().stream()
				.map(err -> new ApiErrorDTO(err.getCodes(), err.getDefaultMessage()))
				.collect(Collectors.toList());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public List<ApiErrorDTO> handleNotFoundExceptions(UsernameNotFoundException ex) {
        return Collections.singletonList(new ApiErrorDTO("user.notfound", ex.getMessage()));
    }
}
