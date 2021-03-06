package br.ufsm.usuarios.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufsm.usuarios.UsuariosApplication;
import br.ufsm.usuarios.controller.dto.UsuarioDTO;
import br.ufsm.usuarios.form.UsuarioForm;
import br.ufsm.usuarios.model.Usuario;
import br.ufsm.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static Logger logger = LoggerFactory.getLogger(UsuariosApplication.class);

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<UsuarioDTO> CadastrarUsuario(@RequestBody @Valid UsuarioForm form) {
		logger.info("Cadastrando novo usuario: " + form.getEmail());
		return service.CadastrarUsuario(form);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> ConsultarUsuario(@PathVariable Long id) {
		logger.info("Consultando usuario com id: " + id);
		return service.ConsultarUsuario(id);
	}

}
