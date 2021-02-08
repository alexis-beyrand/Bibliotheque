package fr.wither.application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import fr.wither.utils.sql.SQLCommon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {


	public static Connection conn;
	private static double xOffset = 0;
	private static double yOffset = 0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/MainScreen.fxml"));
			//AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/SearchBook.fxml"));
			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = primaryStage.getX() - event.getScreenX();
	                yOffset = primaryStage.getY() - event.getScreenY();
	            }
	        });
			
			//Déplacement de la fenêtre avec click maintenu et deplacement de la souris
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                primaryStage.setX(event.getScreenX() + xOffset);
	                primaryStage.setY(event.getScreenY() + yOffset);
	            }
	        });
			
			//Création d'une Scene pour permettre l'affichage
			Scene scene = new Scene(root);
			//Interdiction de redimentionner la fenêtre
			primaryStage.setResizable(false);
			//Ajout de la Scene au Stage, permettant ainsi l'affichage
			primaryStage.setScene(scene);
			//Instanciation du nom de la fenêtre en "Launcher Avalgan"
			primaryStage.setTitle("Bibliothèque");
			primaryStage.getIcons().add(new Image("/fr/wither/view/ressources/icon.png"));
			//Affichage de la fenêtre
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		String desktop = userHome + "/Desktop/base_livre";
		File file = new File(desktop);

		if (!file.exists()) {
			if (!file.mkdir()) {
				System.out.println("Erreur !");
			}
		}

		try {
			conn = SQLCommon.createNewDatabase(desktop, "livres.db", conn);
			SQLCommon.addTables(conn);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		
		
		launch(args);
	}
}
