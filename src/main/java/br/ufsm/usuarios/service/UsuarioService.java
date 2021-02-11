package br.ufsm.usuarios.service;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.usuarios.UsuariosApplication;
import br.ufsm.usuarios.controller.dto.UsuarioDTO;
import br.ufsm.usuarios.form.UsuarioForm;
import br.ufsm.usuarios.model.Usuario;
import br.ufsm.usuarios.repository.UsuarioRepository;

@Slf4j
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuariosRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public ResponseEntity<UsuarioDTO> CadastrarUsuario(UsuarioForm form) {
		String encodedSenha = bCryptPasswordEncoder.encode(form.getSenha());
		
		Usuario usuarioNovo = form.converter(encodedSenha);
		
		usuariosRepository.save(usuarioNovo);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioNovo.getId()).toUri();
		
		log.info("Usuario cadastrado com sucesso.");
		
		UsuarioDTO response = new UsuarioDTO(usuarioNovo);
		
		return ResponseEntity.created(uri).body(response);
	}

	public ResponseEntity<UsuarioDTO> ConsultarUsuario(Long id) {
		try {
			Usuario usuario = usuariosRepository.findById(id).get(); 
			UsuarioDTO response = new UsuarioDTO(usuario);
			log.info("Usuario encontrado: " + response.getId());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch(Exception ex) {
			ex.getStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
