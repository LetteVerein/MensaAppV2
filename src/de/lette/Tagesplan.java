package de.lette;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import de.lette.mensaplan.server.SpeiseArt;
import de.lette.mensaplan.server.Zusatzstoff;

public class Tagesplan {
	private Date datum;
	private List<Speise> speisen;
	
	public Tagesplan(Date datum) {
		this.datum = datum;
		speisen = new ArrayList<Speise>();
	}
	
	public Tagesplan(Date datum, List<Speise> speisen) {
		this.datum = datum;
		this.speisen = speisen;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public List<Speise> getSpeisen() {
		return speisen;
	}

	public void setSpeisen(List<Speise> speisen) {
		this.speisen = speisen;
	}
	
	public boolean addSpeise(Speise s) {
		return speisen.add(s);
	}
}

class Speise {
	private String name;
	private SpeiseArt art;
	private boolean isDiät;
	private BigDecimal preis;
	private String beachte;
	private int kcal;
	private int eiweiß;
	private int fett;
	private int kohlenhydrate;
	private Set<Zusatzstoff> zusatzstoffe;

	public Speise(String name, SpeiseArt art, boolean isDiät, BigDecimal preis, String beachte, int kcal, int eiweiß, int fett, int kohlenhydrate,
			Set<Zusatzstoff> zusatzstoffe) {
		this.name = name;
		this.art = art;
		this.isDiät = isDiät;
		this.preis = preis;
		this.beachte = beachte;
		this.kcal = kcal;
		this.eiweiß = eiweiß;
		this.fett = fett;
		this.kohlenhydrate = kohlenhydrate;
		this.zusatzstoffe = zusatzstoffe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpeiseArt getArt() {
		return art;
	}

	public void setArt(SpeiseArt art) {
		this.art = art;
	}

	public boolean isDiät() {
		return isDiät;
	}

	public void setDiät(boolean isDiät) {
		this.isDiät = isDiät;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public String getBeachte() {
		return beachte;
	}

	public void setBeachte(String beachte) {
		this.beachte = beachte;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getEiweiß() {
		return eiweiß;
	}

	public void setEiweiß(int eiweiß) {
		this.eiweiß = eiweiß;
	}

	public int getFett() {
		return fett;
	}

	public void setFett(int fett) {
		this.fett = fett;
	}

	public int getKohlenhydrate() {
		return kohlenhydrate;
	}

	public void setKohlenhydrate(int kohlenhydrate) {
		this.kohlenhydrate = kohlenhydrate;
	}

	public Set<Zusatzstoff> getZusatzstoffe() {
		return zusatzstoffe;
	}

	public void setZusatzstoffe(Set<Zusatzstoff> zusatzstoffe) {
		this.zusatzstoffe = zusatzstoffe;
	}
}