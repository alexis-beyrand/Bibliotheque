package fr.wither.view;

import java.io.IOException;

import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import fr.wither.utils.types.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewBookFormController {
	@FXML
	private AnchorPane main_pane;
	@FXML
	private ComboBox<String> cb_auteurs;
	@FXML
	private Button ok_button;
	@FXML
	private Label lbl_err_auteur;
	@FXML
	private ChoiceBox<String> section_selector;
	@FXML
	private TextField txf_titre;
	@FXML
	private Label lbl_err_titre;
	@FXML
	private Label lbl_err_etagere;
	private int newAuthor;

	@FXML
	public void initialize() {
		cb_auteurs.getItems().add(0, "---===Choississez un(e) auteur(e)===---");
		newAuthor = SQLCommon.fillAuthor(cb_auteurs, Main.conn, 1);
		cb_auteurs.getItems().add(newAuthor, "Nouvel(le) auteur(e)");
		cb_auteurs.setVisibleRowCount(2);
		cb_auteurs.getSelectionModel().select(0);

		section_selector.getItems().add(0, "Selectionnez");
		section_selector.getItems().add(1, "Gauche");
		section_selector.getItems().add(2, "Milieu (tiroir)");
		section_selector.getItems().add(3, "Milieu");
		section_selector.getItems().add(4, "Droite");

		section_selector.getSelectionModel().select(0);

		lbl_err_auteur.setVisible(false);
		lbl_err_etagere.setVisible(false);
		lbl_err_titre.setVisible(false);
		
	}

	// Event Listener on Button[#ok_button].onAction
	@FXML
	public void registerBook(ActionEvent event) {
		
		boolean ok = true;
		
		int num_auteur = cb_auteurs.getSelectionModel().getSelectedIndex();
		int num_etagere = section_selector.getSelectionModel().getSelectedIndex();
		
		
		if ("".equals(txf_titre.getText().replace(" ", ""))) {
			lbl_err_titre.setVisible(true);
			ok = false;
		} else
		{
			lbl_err_titre.setVisible(false);
		}

		if (num_auteur == 0) {
			lbl_err_auteur.setVisible(true);
			ok = false;
		} else
		{
			lbl_err_auteur.setVisible(false);
		}
		
		
		
		if (num_auteur == newAuthor) {
			Common.tempBook = new Book(txf_titre.getText(), num_etagere, num_auteur);
			Common.bookPending = true;
			ok = false;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nouvel auteur demandé");
			alert.setHeaderText("Veuillez renseigner les info de l'auteur");
			String str = "Le livre sera ajouté après que les informations de l'auteur(e) aient été entrées.";
			alert.setContentText(str);
			alert.showAndWait();
			Stage stage = (Stage) ok_button.getScene().getWindow();
			 try {
					AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/NewAuthorForm.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		if(num_etagere == 0) {
			lbl_err_etagere.setVisible(true);
			ok = false;
		} else
		{
			lbl_err_etagere.setVisible(false);
		}
		
		
		if(ok) {
			Stage stage = (Stage) ok_button.getScene().getWindow();
			SQLCommon.createNewBook(Main.conn, txf_titre.getText(), num_auteur, num_etagere);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Livre enregistré !");
			alert.setHeaderText("Ajout de " +  txf_titre.getText() + "réussi !");
			String str = "Le livre " + txf_titre.getText() + " a été ajouté !";
			alert.setContentText(str);
			alert.showAndWait();
			Common.goBackToMain(stage);
		}
	}
}
