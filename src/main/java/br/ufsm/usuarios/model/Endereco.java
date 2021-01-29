package br.ufsm.usuarios.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty @NotNull
	private String rua;

	@NotEmpty @NotNull
	private int numero;
	
	@NotNull
	private String complemento;
	
	@NotEmpty @NotNull
	private String bairro;
	
	@NotEmpty @NotNull
	private String cidade;
	
	@NotEmpty @NotNull
	private String estado;
	
	@NotEmpty @NotNull
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Usuario usuario;
}
