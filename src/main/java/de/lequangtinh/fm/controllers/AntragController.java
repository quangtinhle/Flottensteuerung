package de.lequangtinh.fm.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.lequangtinh.fm.entities.Antrag;
import de.lequangtinh.fm.entities.Fahrzeug;
import de.lequangtinh.fm.repositories.AntragRepository;
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
public class AntragController {

	@Autowired
	private AntragRepository repository;
	
	@GetMapping("/antraege")
	public List<Antrag> GetAll() {

		return repository.findAll();
	}
	
	@GetMapping("/antraege/{id}")
	public Antrag Get(@PathVariable int id) {
		Optional<Antrag> ret = repository.findById(id);
		if (ret.isPresent())
			return ret.get();
		return null;
	}
	
	@DeleteMapping("/antraege/{id}")
	public void Delete(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/antraege")
	public ResponseEntity<Antrag> Create(@RequestBody Antrag subject) {
		Antrag savedSubject = repository.save(subject);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSubject.getId()).toUri();
		return ResponseEntity.created(location).body(savedSubject);
		
	}
	
	@PutMapping("/antraege/{id}")
	public ResponseEntity<Fahrzeug> Update(@RequestBody Antrag subject, @PathVariable int id) {
	
		Optional<Antrag> ret = repository.findById(id);
		if (!ret.isPresent())
			return ResponseEntity.notFound().build();
		
		subject.setId(id);
		repository.save(subject);
		
		return ResponseEntity.noContent().build();
	}
}
