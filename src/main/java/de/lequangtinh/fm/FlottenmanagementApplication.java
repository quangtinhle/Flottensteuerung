package de.lequangtinh.fm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.lequangtinh.fm.entities.Benutzer;
import de.lequangtinh.fm.entities.Fahrzeug;
import de.lequangtinh.fm.entities.Hersteller;
import de.lequangtinh.fm.repositories.BenutzerRepository;
import de.lequangtinh.fm.repositories.FahrzeugRepository;
import de.lequangtinh.fm.repositories.HerstellerRepository;

@SpringBootApplication

public class
FlottenmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlottenmanagementApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner createDemoData(BenutzerRepository repoBenutzer, HerstellerRepository repoHersteller, FahrzeugRepository repoFahrzeug, PasswordEncoder encoder) {
		return args -> {
			Benutzer b1 = new Benutzer("admin", encoder.encode("admin"));
			b1.setVorname("Admin");
			b1.setNachname("Admin");
			b1.setIstFlottenchef(true);
			b1.setIstAntragsteller(true);
			repoBenutzer.save(b1);
			
			Benutzer b2 = new Benutzer("antragsteller", encoder.encode("123123"));
			b2.setVorname("Peter");
			b2.setNachname("Mustermann");
			b2.setIstFlottenchef(false);
			b2.setIstAntragsteller(true);
			repoBenutzer.save(b2);

			Benutzer b3 = new Benutzer("flottenchef", encoder.encode("123123"));
			b3.setVorname("Petra");
			b3.setNachname("Musterfrau");
			b3.setIstFlottenchef(true);
			b3.setIstAntragsteller(false);
			repoBenutzer.save(b3);
			
			Hersteller h1 = new Hersteller("Audi");
			repoHersteller.save(h1);

			Hersteller h2 = new Hersteller("BMW");
			repoHersteller.save(h2);

			Hersteller h3 = new Hersteller("Opel");
			repoHersteller.save(h3);

			Hersteller h4 = new Hersteller("Peugeot");
			repoHersteller.save(h4);
			
			
			Fahrzeug f1 = new Fahrzeug("FGN-4711", "DO W 111", 30, "Kombi", h1, b3);
			repoFahrzeug.save(f1);
			
			Fahrzeug f2 = new Fahrzeug("FGN-4712", "DO W 112", 30, "Limousine", h2, b3);
			repoFahrzeug.save(f2);

			Fahrzeug f3 = new Fahrzeug("FGN-4713", "DO W 113", 30, "Limousine", h3, b3);
			repoFahrzeug.save(f3);

			Fahrzeug f4 = new Fahrzeug("FGN-4714", "DO W 114", 30, "Limousine", h4, b3);
			repoFahrzeug.save(f4);

		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
