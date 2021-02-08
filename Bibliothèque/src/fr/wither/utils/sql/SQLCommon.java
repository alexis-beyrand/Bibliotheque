package fr.wither.utils.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.wither.utils.Common;
import fr.wither.utils.types.CD;
import fr.wither.utils.types.Cassettes;
import fr.wither.utils.types.DVD;
import fr.wither.utils.types.TableBook;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class SQLCommon {

	
	public static Connection createNewDatabase(String folder, String dbName, Connection conn) throws SQLException{

		String url = "jdbc:sqlite:" + folder + "/" + dbName;
		conn = DriverManager.getConnection(url);
		if (conn != null) {
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Le nom du driver est " + meta.getDriverName());
			System.out.println("Une nouvelle BD a été créée.");
		}
		
		return conn;
	}
	
	public static void addTables(Connection conn) {
		try {
			PreparedStatement q = conn.prepareStatement("CREATE TABLE IF NOT EXISTS auteurs(" + 
					"    auteurNum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + 
					"	 prenom VARCHAR(255) NOT NULL," + 
					"    nom VARCHAR(255) NOT NULL," + 
					"    dateNaissance DATE NOT NULL," + 
					"    dateMort DATE," + 
					"    urlWikipedia VARCHAR(255) NOT NULL" + 
					")");
			q.execute();
			q.close();
			q = conn.prepareStatement("CREATE TABLE IF NOT EXISTS livres(" + 
					"livreNum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + 
					"titre VARCHAR(255) NOT NULL," + 
					"auteur INTEGER NOT NULL," + 
					"section INTEGER NOT NULL," + 
					"FOREIGN KEY (auteur) REFERENCES auteurs(auteurNum)" + 
					")");
			q.execute();
			q.close();
			
			q = conn.prepareStatement("CREATE TABLE IF NOT EXISTS cd(" + 
					"cdNum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + 
					"titre VARCHAR(255) NOT NULL" + 
					")");
			q.execute();
			q.close();
			
			q = conn.prepareStatement("CREATE TABLE IF NOT EXISTS dvd(" + 
					"dvdNum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + 
					"titre VARCHAR(255) NOT NULL" + 
					")");
			q.execute();
			q.close();
			
			q = conn.prepareStatement("CREATE TABLE IF NOT EXISTS cassettes(" + 
					"cassetteNum INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + 
					"titre VARCHAR(255) NOT NULL" + 
					")");
			q.execute();
			q.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void addCD(Connection conn, String titre) {
		try {
			PreparedStatement q = conn.prepareStatement("INSERT INTO cd(titre) VALUES (?)");
			q.setString(1, titre);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addDVD(Connection conn, String titre) {
		try {
			PreparedStatement q = conn.prepareStatement("INSERT INTO dvd(titre) VALUES (?)");
			q.setString(1, titre);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void addCassette(Connection conn, String titre) {
		try {
			PreparedStatement q = conn.prepareStatement("INSERT INTO cassettes(titre) VALUES (?)");
			q.setString(1, titre);
			q.execute();
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void addNewAuthor(Connection conn, String prenom, String nom, String dateNaissance, String dateMort) {
		String urlWikipedia = Common.createWikipediaLink(prenom, nom);
		
		if(dateMort != null) {
			try {
				PreparedStatement q = conn.prepareStatement("INSERT INTO auteurs(prenom,nom,dateNaissance,dateMort,urlWikipedia) VALUES (?,?,?,?,?)");
				q.setString(1, prenom);
				q.setString(2, nom);
				q.setString(3, dateNaissance);
				q.setString(4, dateMort);
				q.setString(5, urlWikipedia);
				
				q.execute();
				q.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			try {
				PreparedStatement q = conn.prepareStatement("INSERT INTO auteurs(prenom,nom,dateNaissance,dateMort,urlWikipedia) VALUES (?,?,?,null,?)");
				q.setString(1, prenom);
				q.setString(2, nom);
				q.setString(3, dateNaissance);
				q.setString(4, urlWikipedia);
				
				q.execute();
				q.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void createNewBook(Connection conn, String titre, int auteur, int section) {
		try {
			PreparedStatement q = conn.prepareStatement("INSERT INTO livres(titre,auteur,section) VALUES (?,?,?)");
			q.setString(1, titre.toLowerCase());
			q.setInt(2, auteur);
			q.setInt(3, section);
			
			q.execute();
			q.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fillCDList(Connection conn, ObservableList<CD> list) {
		try {
			PreparedStatement q = conn.prepareStatement("SELECT titre FROM cd ORDER BY titre ASC");
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				CD tempCd = new CD(rs.getString("titre"));
				list.add(tempCd);
			}
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void fillDVDList(Connection conn, ObservableList<DVD> list) {
		try {
			PreparedStatement q = conn.prepareStatement("SELECT titre FROM dvd ORDER BY titre ASC");
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				DVD tempDvd = new DVD(rs.getString("titre"));
				list.add(tempDvd);
			}
			q.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void fillCassettesList(Connection conn, ObservableList<Cassettes> list) {
		try {
			PreparedStatement q = conn.prepareStatement("SELECT titre FROM cassettes ORDER BY titre ASC");
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				Cassettes tempCassette = new Cassettes(rs.getString("titre"));
				list.add(tempCassette);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int getAuthorID(Connection conn, String prenom, String nom) {
		int id = -1;
		try {
			PreparedStatement q = conn.prepareStatement("SELECT auteurNum FROM auteurs WHERE nom = ? AND prenom = ?");
			q.setString(1, nom);
			q.setString(2, prenom);
			ResultSet rs = q.executeQuery();
			while (rs.next()) {
				 id = rs.getInt("autuerNum");
			}
			q.execute();
			q.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	public static int fillAuthor(ComboBox<String> c, Connection conn, int from) {
		try {
			PreparedStatement q = conn.prepareStatement("SELECT auteurNum, prenom, nom FROM auteurs");
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				String nom = rs.getString("prenom") + " " + rs.getString("nom");
				c.getItems().add(from, nom);
				from++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return from;
	}

	public static void fillList(Connection conn, ObservableList<TableBook> list) {
		try {
			PreparedStatement q = conn.prepareStatement("SELECT titre, section FROM livres ORDER BY titre ASC");
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				TableBook tempBook = new TableBook(rs.getString("titre"), rs.getInt("section"));
				list.add(tempBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean bookExist(Connection conn, String titre) {
		int existe = 0;
		try {
			PreparedStatement q = conn.prepareStatement("SELECT COUNT(*) AS nb FROM livres WHERE titre = ?");
			q.setString(1, titre.toLowerCase());
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				existe = rs.getInt("nb");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existe > 0;
	}

	public static String getAuthorName(Connection conn, int selected_id) {
		String name = "";
		try {
			PreparedStatement q = conn.prepareStatement("SELECT nom, prenom FROM auteurs WHERE auteurNum = ?");
			q.setInt(1, selected_id);
			
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				name = rs.getString("prenom") + " " + rs.getString("nom"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public static String getAuthorBirthDate(Connection conn, int selected_id) {
		String date = "";
		
		try {
			PreparedStatement q = conn.prepareStatement("SELECT dateNaissance FROM auteurs WHERE auteurNum = ?");
			q.setInt(1, selected_id);
			ResultSet rs = q.executeQuery();
			
			while(rs.next()) {
				date = rs.getString("dateNaissance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String getAuthorDeathDate(Connection conn, int selected_id) {
		String date = "auteur(e) toujours en vie";
		
		try {
			PreparedStatement q = conn.prepareStatement("SELECT dateMort FROM auteurs WHERE auteurNum = ?");
			q.setInt(1, selected_id);
			ResultSet rs = q.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("dateMort") != null ) {
					date = rs.getString("dateMort");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return date;
	}

	public static String getAuthorLink(Connection conn, int selected_id) {
		String link = "";
		try {
			PreparedStatement q = conn.prepareStatement("SELECT urlWikipedia FROM auteurs WHERE auteurNum = ?");
			q.setInt(1, selected_id);
			
			ResultSet rs = q.executeQuery();
			while(rs.next()) {
				link = rs.getString("urlWikipedia"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return link;
	}
}
