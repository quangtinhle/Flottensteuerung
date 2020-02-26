package de.lequangtinh.fm.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Benutzer {
	@Id
	@GeneratedValue
	private int id;
	
	private String login;
	
	private String nachname;
	
	private String passwort;
	
	private String vorname;
	
	private boolean istAntragsteller;
	
	private boolean istFlottenchef;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="flottenchef")
	@JsonIgnore
	private List<Antrag> bearbeiteteAntraege;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="antragsteller")
	@JsonIgnore
	private List<Antrag> antraege;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="flottenchef")
	@JsonIgnore
	private List<Fahrzeug> verwalteteFahrzeuge;

	
	public Benutzer() {
		
	}
	
	public Benutzer(String login, String passwort) {
		this.login = login;
		this.passwort = passwort;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public boolean isIstAntragsteller() {
		return istAntragsteller;
	}

	public void setIstAntragsteller(boolean istAntragsteller) {
		this.istAntragsteller = istAntragsteller;
	}

	public boolean isIstFlottenchef() {
		return istFlottenchef;
	}

	public void setIstFlottenchef(boolean istFlottenchef) {
		this.istFlottenchef = istFlottenchef;
	}

	public List<Antrag> getBearbeiteteAntraege() {
		return bearbeiteteAntraege;
	}

	public void setBearbeiteteAntraege(List<Antrag> bearbeiteteAntraege) {
		this.bearbeiteteAntraege = bearbeiteteAntraege;
	}

	public List<Antrag> getAntraege() {
		return antraege;
	}

	public void setAntraege(List<Antrag> antraege) {
		this.antraege = antraege;
	}

	public List<Fahrzeug> getVerwalteteFahrzeuge() {
		return verwalteteFahrzeuge;
	}

	public void setVerwalteteFahrzeuge(List<Fahrzeug> verwalteteFahrzeuge) {
		this.verwalteteFahrzeuge = verwalteteFahrzeuge;
	}

	
}
