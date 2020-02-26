package de.lequangtinh.fm.repositories;

import de.lequangtinh.fm.entities.Antrag;
import org.springframework.data.jpa.repository.JpaRepository;
import de.lequangtinh.fm.entities.*;

public interface AntragRepository extends JpaRepository<Antrag, Integer> {

}
