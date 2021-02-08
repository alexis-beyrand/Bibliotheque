package fr.wither.utils.types;

public class Book {
	private String titre;
	private int etag�re;
	private int auteurNum;
	
	public Book(String titre, int etag�re, int auteurNum) {
		this.titre = titre;
		this.etag�re = etag�re;
		this.auteurNum = auteurNum;
	}

	public String getTitre() {
		return titre;
	}

	public int getEtag�re() {
		return etag�re;
	}

	public int getAuteurNum() {
		return auteurNum;
	}
}
