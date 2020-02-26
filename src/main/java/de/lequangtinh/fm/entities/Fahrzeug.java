package de.lequangtinh.fm.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fahrzeug {
	@Id
	@GeneratedValue
	private int id;
	
	private String fahrgestellnummer;
	
	private String kennzeichen;
	
	private int kostenJeKilometer;
	
	private String typbezeichnung;
	
	@ManyToOne
	@JoinColumn(name="FK_Hersteller")
	private Hersteller hersteller;
	
	@ManyToOne
	@JoinColumn(name="FK_Flottenchef")
	private Benutzer flottenchef;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="wunschFahrzeug")
	@JsonIgnore
	private List<Antrag> antraege;

	
	public Fahrzeug() {
		
	}
	
	public Fahrzeug(String fahrgestellNummer, String kennzeichen, int kosten, String typ, Hersteller hersteller, Benutzer chef) {
		this.fahrgestellnummer = fahrgestellNummer;
		this.kennzeichen = kennzeichen;
		this.kostenJeKilometer = kosten;
		this.typbezeichnung = typ;
		this.hersteller = hersteller;
		this.flottenchef = chef;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFahrgestellnummer() {
		return fahrgestellnummer;
	}

	public void setFahrgestellnummer(String fahrgestellnummer) {
		this.fahrgestellnummer = fahrgestellnummer;
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public int getKostenJeKilometer() {
		return kostenJeKilometer;
	}

	public void setKostenJeKilometer(int kostenJeKilometer) {
		this.kostenJeKilometer = kostenJeKilometer;
	}

	public String getTypbezeichnung() {
		return typbezeichnung;
	}

	public void setTypbezeichnung(String typbezeichnung) {
		this.typbezeichnung = typbezeichnung;
	}

	public Hersteller getHersteller() {
		return hersteller;
	}

	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}

	public Benutzer getFlottenchef() {
		return flottenchef;
	}

	public void setFlottenchef(Benutzer flottenchef) {
		this.flottenchef = flottenchef;
	}

	public List<Antrag> getAntraege() {
		return antraege;
	}

	public void setAntraege(List<Antrag> antraege) {
		this.antraege = antraege;
	} 
}
