package br.ufsm.usuarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.usuarios.form.UsuarioForm;
import br.ufsm.usuarios.model.Usuario;
import br.ufsm.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<Usuario> CadastrarUsuario(@RequestBody @Valid UsuarioForm form) {
		return service.CadastrarUsuario(form);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> ConsultarUsuario(@PathVariable Long id) {
		return service.ConsultarUsuario(id);
	}

}
