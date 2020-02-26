package de.lequangtinh.fm.repositories;


import de.lequangtinh.fm.entities.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lequangtinh.fm.entities.*;

public interface FahrzeugRepository extends JpaRepository<Fahrzeug, Integer> {

}
