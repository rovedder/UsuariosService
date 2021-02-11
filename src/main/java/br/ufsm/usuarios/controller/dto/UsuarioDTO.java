package br.ufsm.usuarios.controller.dto;

import br.ufsm.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Long id;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
	}
	
}
