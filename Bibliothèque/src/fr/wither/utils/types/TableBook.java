package fr.wither.utils.types;

import fr.wither.utils.Common;

public class TableBook {
	private String titre, emplacement;
	
	public TableBook(String titre, int emplacement) {
		this.titre = titre;
		this.emplacement = Common.emplacement.get(emplacement);
	}

	public String getTitre() {
		return titre;
	}

	public String getEmplacement() {
		return emplacement;
	}
}
