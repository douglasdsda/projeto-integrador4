package com.integrador;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.integrador.entities.Usuario;
import com.integrador.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(
						null, 
						"Patrick", 
						"patrickfreitas@gmail.com", 
						"path/path/",
						LocalDate.of(1998, Month.AUGUST, 10));

		Usuario u2 = new Usuario(
						null, 
						"Sabrina", 
						"sabrina@gmail.com", 
						"path/path/2",
						LocalDate.of(1996, Month.JANUARY, 1));

		Usuario u3 = new Usuario(
						null, 
						"Leticia", 
						"lelet@gmail.com", 
						"path/path/3",
						LocalDate.of(1994, Month.FEBRUARY, 25));

		Usuario u4 = new Usuario(
						null, 
						"Rafaela", 
						"rafas@gmail.com", 
						"path/path/3",
						LocalDate.of(1999, Month.MARCH, 18));
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		u1.getSeguidores().add(u2);
		u1.getSeguidores().add(u3);

		u2.getSeguidores().add(u3);
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
	}

}
