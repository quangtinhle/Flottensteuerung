package de.lequangtinh.fm.repositories;

import de.lequangtinh.fm.entities.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lequangtinh.fm.entities.*;

public interface BenutzerRepository extends JpaRepository<Benutzer, Integer>{

	Benutzer findByLogin(String login);
}
