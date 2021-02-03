package br.ufsm.usuarios.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.ufsm.usuarios.UsuariosApplication;
import br.ufsm.usuarios.config.security.TokenService;
import br.ufsm.usuarios.controller.dto.TokenDTO;
import br.ufsm.usuarios.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	private static Logger logger = LoggerFactory.getLogger(UsuariosApplication.class);

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form) {
		logger.info("Iniciando tentativa de autenticacao com o usuario" + form.getEmail());
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);
			
			logger.info("Usuario autenticado com sucesso");
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));	
		} catch (AuthenticationException ex) {
			logger.warn("Ocorreu uma excecao na autenticacao: " + ex.getMessage());
			
			return ResponseEntity.badRequest().build();	
		}
	}

}
