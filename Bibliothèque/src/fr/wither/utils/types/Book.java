package fr.wither.utils.types;

public class Book {
	private String titre;
	private int etagère;
	private int auteurNum;
	
	public Book(String titre, int etagère, int auteurNum) {
		this.titre = titre;
		this.etagère = etagère;
		this.auteurNum = auteurNum;
	}

	public String getTitre() {
		return titre;
	}

	public int getEtagère() {
		return etagère;
	}

	public int getAuteurNum() {
		return auteurNum;
	}
}
