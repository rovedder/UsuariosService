package br.ufsm.usuarios.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorDTO {

	private String[] codes;
	private String message;
	
	public ApiErrorDTO(String code, String message) {
		this(new String[] { code }, message);
	}
	
}
