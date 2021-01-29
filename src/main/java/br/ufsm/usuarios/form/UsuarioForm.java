package br.ufsm.usuarios.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.caelum.stella.bean.validation.CPF;
import br.ufsm.usuarios.model.Usuario;

public class UsuarioForm {
	
	@NotNull @NotEmpty
	private String nome;

	@NotNull @NotEmpty @CPF
	private String cpf;
	
	@NotNull @NotEmpty
	private String email;
	
	@NotNull @NotEmpty
	private String senha;
	
	public Usuario converter(String senha) {
		return new Usuario(this.nome, this.cpf, this.email, senha);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}
}
