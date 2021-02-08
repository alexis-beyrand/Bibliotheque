package fr.wither.view;

import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AuthorDetailsController {
	@FXML
	private ComboBox<String> cb_auteurs;
	@FXML
	private Button btn_aff;
	@FXML
	private Label lbl_nom_fixe;
	@FXML
	private Label lbl_nom_var;
	@FXML
	private Label lbl_dates_fixe;
	@FXML
	private Label lbl_date_naissance;
	@FXML
	private Label lbl_date_mort;
	@FXML
	private Hyperlink hyper_link;
	@FXML
	private Button btn_back;
	
	
	private String link;

	
	@FXML
	public void initialize() {
		cb_auteurs.getItems().add(0, "Selectionner auteur(e)");
		SQLCommon.fillAuthor(cb_auteurs, Main.conn, 1);
		
		cb_auteurs.getSelectionModel().select(0);
		
		lbl_nom_fixe.setVisible(false);
		lbl_date_mort.setVisible(false);
		lbl_date_naissance.setVisible(false);
		lbl_dates_fixe.setVisible(false);
		lbl_nom_var.setVisible(false);
		hyper_link.setVisible(false);
	}

	// Event Listener on Button[#btn_aff].onAction
	@FXML
	public void showAuthor(ActionEvent event) {
		int selected_id = cb_auteurs.getSelectionModel().getSelectedIndex();
		if(selected_id != 0) {
			String name = SQLCommon.getAuthorName(Main.conn, selected_id);
			String naissance = SQLCommon.getAuthorBirthDate(Main.conn, selected_id);
			String mort = SQLCommon.getAuthorDeathDate(Main.conn, selected_id);
			link = SQLCommon.getAuthorLink(Main.conn, selected_id);
			
			lbl_nom_fixe.setVisible(true);
			lbl_date_mort.setText(mort);
			lbl_date_mort.setVisible(true);
			lbl_date_naissance.setText(naissance);
			lbl_date_naissance.setVisible(true);
			lbl_dates_fixe.setVisible(true);
			lbl_nom_var.setText(name);
			lbl_nom_var.setVisible(true);
			hyper_link.setVisible(true);
		}
	}
	// Event Listener on Button[#btn_back].onAction
	@FXML
	public void goBackToMenu(ActionEvent event) {
		Stage stage = (Stage) btn_back.getScene().getWindow();
		Common.goBackToMain(stage);
	}
	// Event Listener on Hyperlink[#hyper_link].onAction
	@FXML
	public void linkCliked(ActionEvent event) {
		 Main instance = new Main();
		instance .getHostServices().showDocument(link);
	}
}
