package fr.wither.utils;

import java.io.IOException;
import java.util.HashMap;

import fr.wither.application.Main;
import fr.wither.utils.types.Book;
import fr.wither.utils.types.TableBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Common {
	@SuppressWarnings("serial")
	public static final HashMap<Integer, String> emplacement = new HashMap<Integer, String>(){

        {
        	put(0, "Inconnu");
            put(1, "Gauche");
            put(2, "Milieu (tiroir)");
            put(3, "Milieu");
            put(4,"Droite");
        }

        ;
    };
	
    public static final TableBook test1 = new TableBook("Test1", 1);
    public static final TableBook test2 = new TableBook("Test2", 2);
    public static final TableBook test3 = new TableBook("Test3", 3);
    public static final TableBook test4 = new TableBook("Test4", 4);
    
	public static Book tempBook;
	public static boolean bookPending;
	
	public static String createWikipediaLink(String prenom, String nom) {
		String link = "https://fr.m.wikipedia.org/wiki/";
		
		String formatedPrenom = prenom.replace(" ", "_");
		String formatedNom = nom.replace(" ", "_");
		
		link += formatedPrenom + "_" + formatedNom;
		
		return link;
	}
	
	
	public static void goBackToMain(Stage stage) {
		Main main = new Main();
		try {
			AnchorPane root = FXMLLoader.load(main.getClass().getResource("/fr/wither/view/MainScreen.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
