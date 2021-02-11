package br.ufsm.usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApplication {
	
	private static Logger logger = LoggerFactory.getLogger(UsuariosApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}

}
