package de.lequangtinh.fm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Antrag {
	@Id
	@GeneratedValue
	private int id;
	
	private boolean istBearbeitet;
	
	private boolean istFreigegeben;
	
	private float kilometer;
	
	private String ziel;
	
	@ManyToOne
	@JoinColumn(name="FK_Flottenchef")
	private Benutzer flottenchef;
	
	@ManyToOne
	@JoinColumn(name="FK_Antragsteller")
	private Benutzer antragsteller;
	
	@ManyToOne
	@JoinColumn(name="FK_WunschFahrzeug")
	private Fahrzeug wunschFahrzeug;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIstBearbeitet() {
		return istBearbeitet;
	}

	public void setIstBearbeitet(boolean istBearbeitet) {
		this.istBearbeitet = istBearbeitet;
	}

	public boolean isIstFreigegeben() {
		return istFreigegeben;
	}

	public void setIstFreigegeben(boolean istFreigegeben) {
		this.istFreigegeben = istFreigegeben;
	}

	public float getKilometer() {
		return kilometer;
	}

	public void setKilometer(float kilometer) {
		this.kilometer = kilometer;
	}

	public String getZiel() {
		return ziel;
	}

	public void setZiel(String ziel) {
		this.ziel = ziel;
	}

	public Benutzer getFlottenchef() {
		return flottenchef;
	}

	public void setFlottenchef(Benutzer flottenchef) {
		this.flottenchef = flottenchef;
	}

	public Benutzer getAntragsteller() {
		return antragsteller;
	}

	public void setAntragsteller(Benutzer antragsteller) {
		this.antragsteller = antragsteller;
	}

	public Fahrzeug getWunschFahrzeug() {
		return wunschFahrzeug;
	}

	public void setWunschFahrzeug(Fahrzeug wunschFahrzeug) {
		this.wunschFahrzeug = wunschFahrzeug;
	}		
}
