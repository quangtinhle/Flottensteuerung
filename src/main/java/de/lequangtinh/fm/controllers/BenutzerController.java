package de.lequangtinh.fm.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.lequangtinh.fm.entities.Benutzer;
import de.lequangtinh.fm.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.lequangtinh.fm.entities.*;
import de.lequangtinh.fm.repositories.*;


@RestController
public class BenutzerController {
	@Autowired
	private BenutzerRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/benutzer")
	public List<Benutzer> GetAll() {

		return repository.findAll();
	}
	
	@GetMapping("/benutzer/{id}")
	public Benutzer Get(@PathVariable int id) {
		Optional<Benutzer> ret = repository.findById(id);
		if (ret.isPresent())
			return ret.get();
		return null;
	}
	
	@DeleteMapping("/benutzer/{id}")
	public void Delete(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	
	@PostMapping("/benutzer")
	public ResponseEntity<Benutzer> Create(@RequestBody Benutzer subject) {		
		subject.setPasswort(passwordEncoder.encode(subject.getPasswort()));		
		Benutzer savedSubject = repository.save(subject);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSubject.getId()).toUri();
		return ResponseEntity.created(location).body(savedSubject);
		
	}
	
	@PutMapping("/benutzer/{id}")
	public ResponseEntity<Benutzer> Update(@RequestBody Benutzer subject, @PathVariable int id) {
		Optional<Benutzer> ret = repository.findById(id);
		if (!ret.isPresent())
			return ResponseEntity.notFound().build();

		if (!subject.getPasswort().equals(ret.get().getPasswort())) {
			subject.setPasswort(passwordEncoder.encode(subject.getPasswort()));					
		}
				
		subject.setId(id);
		repository.save(subject);
		
		return ResponseEntity.noContent().build();
	}
}
