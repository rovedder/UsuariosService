package br.ufsm.usuarios.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.ufsm.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {
	
	@NotBlank
	private String nome;

	@NotBlank @Email
	private String email;
	
	@NotBlank
	private String senha;
	
	public Usuario converter(String senha) {
		return new Usuario(this.nome, this.email, senha);
	}

}
