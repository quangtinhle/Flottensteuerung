package de.lequangtinh.fm.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.lequangtinh.fm.entities.Hersteller;
import de.lequangtinh.fm.repositories.HerstellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class HerstellerController {
	@Autowired
	private HerstellerRepository repository;
	
	@GetMapping("/hersteller")
	public List<Hersteller> GetAll() {

		return repository.findAll();
	}
	
	@GetMapping("/hersteller/{id}")
	public Hersteller Get(@PathVariable int id) {
		Optional<Hersteller> ret = repository.findById(id);
		if (ret.isPresent())
			return ret.get();
		return null;
	}
	
	@DeleteMapping("/hersteller/{id}")
	public void Delete(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/hersteller")
	public ResponseEntity<Hersteller> Create(@RequestBody Hersteller subject) {
		Hersteller savedSubject = repository.save(subject);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSubject.getId()).toUri();
		return ResponseEntity.created(location).body(savedSubject);
		
	}
	
	@PutMapping("/hersteller/{id}")
	public ResponseEntity<Hersteller> Update(@RequestBody Hersteller subject, @PathVariable int id) {
	
		Optional<Hersteller> ret = repository.findById(id);
		if (!ret.isPresent())
			return ResponseEntity.notFound().build();
		
		subject.setId(id);
		repository.save(subject);
		
		return ResponseEntity.noContent().build();
	}
}
