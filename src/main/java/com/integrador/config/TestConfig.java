package com.integrador.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.integrador.entities.Bairro;
import com.integrador.entities.Categoria;
import com.integrador.entities.Cidade;
import com.integrador.entities.Endereco;
import com.integrador.entities.Estado;
import com.integrador.entities.Evento;
import com.integrador.entities.Interesse;
import com.integrador.entities.Pais;
import com.integrador.entities.Permissao;
import com.integrador.entities.Usuario;
import com.integrador.enums.TipoInteresse;
import com.integrador.repository.BairroRepository;
import com.integrador.repository.CategoriaRepository;
import com.integrador.repository.CidadeRepository;
import com.integrador.repository.EnderecoRepository;
import com.integrador.repository.EstadoRepository;
import com.integrador.repository.EventoRepository;
import com.integrador.repository.InteresseRepository;
import com.integrador.repository.PaisRepository;
import com.integrador.repository.PermissaoRepository;
import com.integrador.repository.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private InteresseRepository interesseRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(
						null, 
						"Patrick", 
						"patrickfreitas@gmail.com", 
						"path/path/",
						LocalDate.of(1998, Month.AUGUST, 10),
						passwordEncoder.encode("123456"));

		Usuario u2 = new Usuario(
						null, 
						"Sabrina", 
						"sabrina@gmail.com", 
						"path/path/2",
						LocalDate.of(1996, Month.JANUARY, 1),
						passwordEncoder.encode("123456"));

		Usuario u3 = new Usuario(
						null, 
						"Leticia", 
						"lelet@gmail.com", 
						"path/path/3",
						LocalDate.of(1994, Month.FEBRUARY, 25),
						null);

		Usuario u4 = new Usuario(
						null, 
						"Rafaela", 
						"rafas@gmail.com", 
						"path/path/3",
						LocalDate.of(1999, Month.MARCH, 18),
						null);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4));

		Permissao p1 = new Permissao(null, "ROLE_CLIENT");
		Permissao p2 = new Permissao(null, "ROLE_ADMIN");
		
		permissaoRepository.saveAll(Arrays.asList(p1, p2));

		
		Categoria c1 = new Categoria(null, "Balada");
		Categoria c2 = new Categoria(null, "Culto");
		Categoria c3 = new Categoria(null, "Show");
		Categoria c4 = new Categoria(null, "Teatro");
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		u1.getSeguidores().add(u2);
		u1.getSeguidores().add(u3);
		u2.getSeguidores().add(u3);
		
		u1.getPermissoes().add(p1);
		u2.getPermissoes().add(p1);
		u3.getPermissoes().add(p1);
		u4.getPermissoes().add(p1);

		u1.getPermissoes().add(p2);
		
		u1.getCategorias().add(c1);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		 // String x = Instant.parse(LocalDate.of(2019, Month.NOVEMBER, 15).toString();
		
		Pais ps1 = new Pais(1, "Brasil");
		Pais ps2 = new Pais(2, "Argentina");
		
		paisRepository.saveAll(Arrays.asList(ps1, ps2));
		
		Estado es1 = new Estado(1, "Minas Gerais",ps1);
		Estado es2 = new Estado(2, "Sao Paulo",ps1);
		
		estadoRepository.saveAll(Arrays.asList(es1, es2));
		
		Cidade cd1 = new Cidade(1, "Uberlandia",es1);
		Cidade cd2 = new Cidade(2, "Campinas",es2);
		
		cidadeRepository.saveAll(Arrays.asList(cd1, cd2));
		
		Bairro b1 = new Bairro(1, "Luizote",cd1);
		Bairro b2 = new Bairro(2, "Umuarama",cd2);
		
		bairroRepository.saveAll(Arrays.asList(b1, b2));
		
		Endereco e1 = new Endereco(null, "Rua Afonso Pena", 7,"Apartamento",b1);
		Endereco e2 = new Endereco(null, "Rua Joaquim Sabino", 5,"Casa",b2);
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Evento ev1 = new Evento(null, "Gustavo Lima", "Shopping",   Instant.now() , "Sertenejo", e1);
		Evento ev2 = new Evento(null, "Cinema Free", "Centro",   Instant.now()  , "Cinema", e2);
		Evento ev3 = new Evento(null, "Palestra do Google", "Shopping",   Instant.now() , "Google", e1);
		
		eventoRepository.saveAll(Arrays.asList(ev1,ev2,ev3 ));
		
		Interesse it1 = new Interesse(u1, ev1, TipoInteresse.MARCA_PRESENCA, Instant.now());
		Interesse it2 = new Interesse(u2, ev1, TipoInteresse.NAO_COMPARECEREI, Instant.now());
		Interesse it3 = new Interesse(u3, ev1, TipoInteresse.TENHO_INTERESSE, Instant.now());
		Interesse it4 = new Interesse(u1, ev1, TipoInteresse.INDIFERENTE, Instant.now());
		Interesse it5 = new Interesse(u3, ev1, TipoInteresse.TENHO_INTERESSE, Instant.now());
		
		interesseRepository.saveAll(Arrays.asList(it1,it2,it3,it4,it5 ));
		
	}

}
