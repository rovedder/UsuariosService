package br.ufsm.usuarios.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.ufsm.usuarios.form.UsuarioForm;
import br.ufsm.usuarios.model.Usuario;
import br.ufsm.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuariosRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public ResponseEntity<Usuario> CadastrarUsuario(UsuarioForm form) {
		String encodedSenha = bCryptPasswordEncoder.encode(form.getSenha());
		
		Usuario usuarioNovo = form.converter(encodedSenha);
		
		usuariosRepository.save(usuarioNovo);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioNovo.getId()).toUri();

		return ResponseEntity.created(uri).body(usuarioNovo);
	}

	public ResponseEntity<Usuario> ConsultarUsuario(Long id) {
		try {
			Usuario usuario = usuariosRepository.findById(id).get(); 
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} catch(Exception ex) {
			ex.getStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
