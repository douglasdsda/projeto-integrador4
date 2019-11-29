package com.integrador.services;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.dto.CredentialsDTO;
import com.integrador.dto.TokenDTO;
import com.integrador.entities.Evento;
import com.integrador.entities.Usuario;
import com.integrador.security.JWTUtil;
import com.integrador.repository.UsuarioRepository;
import com.integrador.services.exceptions.JWTAuthenticationException;
import com.integrador.services.exceptions.JWTAuthorizationException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class AuthService {
	private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UsuarioRepository userRepository;

	@Transactional(readOnly = true)
	public TokenDTO authenticate(CredentialsDTO dto) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(),
					dto.getPassword());
			authenticationManager.authenticate(authToken);
			String token = jwtUtil.generateToken(dto.getEmail());
			Long id = userRepository.findByEmail(dto.getEmail()).getId();
			return new TokenDTO(dto.getEmail(), token, id);
		} catch (AuthenticationException e) {
			throw new JWTAuthenticationException("Bad credentials");
		}

	}

	public Usuario authenticated() {
		try {
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userRepository.findByEmail(userDetails.getUsername());
		} catch (Exception e) {
			throw new JWTAuthorizationException("Access denied");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		Usuario user = authenticated();
		if(user == null || !user.getId().equals(userId) && !user.hasPermissao("ROLE_ADMIN")) {
			throw new JWTAuthorizationException("Access denied");
		}
	}
	
	public void validadeOwnEventOrAdmin(Evento evento) {
	}
	
	public TokenDTO refreshToken() {
		Usuario user = authenticated();
		Long id = user.getId();
		return new TokenDTO(user.getEmail(), jwtUtil.generateToken(user.getEmail()),id);
	}
	
	@Transactional
	public void sendNewPassword(String email) {
		Usuario user = userRepository.findByEmail(email);
		if(user == null) {
			throw new ResourceNotFoundException("Email not found");
		}
		
		String newPassword = newPassword();
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		
		LOG.info("New password " + newPassword);
	}
	
	private String newPassword() {
		char[] vect = new char[10];
		for(int i=0; i<10; i++) {
			vect[i] = randomChar();
		}
		return new String(vect);
	}
	
	private char randomChar() {
		Random rand = new Random();
		int opt = rand.nextInt(3);
		if(opt == 0) { // digit
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // uppercase letter
			return (char) (rand.nextInt(26) + 65);			
		} else { // lowercase letter
			return (char) (rand.nextInt(26) + 97);			
		}
	}
}