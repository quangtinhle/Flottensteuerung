package de.lequangtinh.fm.security;

import java.util.ArrayList;
import java.util.List;

import de.lequangtinh.fm.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import de.lequangtinh.fm.entities.Benutzer;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BenutzerRepository repository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if (name == null || password == null)
			throw new BadCredentialsException("Authentifizierung fehlgeschlagen");
	
		Benutzer benutzer = repository.findByLogin(name);
		if (benutzer == null)
			throw new BadCredentialsException("Authentifizierung fehlgeschlagen, da der Benutzer gefunden werden konnte.");
		
		if (passwordEncoder.matches(password, benutzer.getPasswort())) {
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			if (benutzer.isIstAntragsteller())
				grantedAuthorities.add(new SimpleGrantedAuthority("Antragsteller"));
			if (benutzer.isIstFlottenchef())
				grantedAuthorities.add(new SimpleGrantedAuthority("Flottenchef"));
			
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
			return auth;

		} else {
			throw new BadCredentialsException("Authentifizierung fehlgeschlagen");			
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
