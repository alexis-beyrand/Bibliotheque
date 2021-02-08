package fr.wither.view;

import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewAuthorFormController {
	@FXML
	private CheckBox cb_vivant;
	@FXML
	private TextField txf_prenom;
	@FXML
	private TextField txf_nom;
	@FXML
	private DatePicker date_naissance;
	@FXML
	private DatePicker date_mort;
	@FXML
	private Label lbl_date_mort;
	@FXML
	private Label lbl_err_prenom;
	@FXML
	private Label lbl_err_nom;
	@FXML
	private Label lbl_err_date;
	@FXML
	private Button btn_valider;
	@FXML
	public void initialize() {
		lbl_err_date.setVisible(false);
		lbl_err_nom.setVisible(false);
		lbl_err_prenom.setVisible(false);
		
		cb_vivant.setSelected(true);
		lbl_date_mort.setVisible(false);
		date_mort.setVisible(false);
		
	}
	// Event Listener on Button[#btn_valider].onAction
	@FXML
	public void sendAuthor(ActionEvent event) {
		boolean ok = true;
		String dateNaissance;
		String dateMort;
		if(date_naissance.getValue() != null) {
			lbl_err_date.setVisible(false);
		} else
		{
			ok = false;
			lbl_err_date.setVisible(true);
		}
		if(date_mort.getValue() != null) {
			dateMort = date_mort.getValue().toString();
		} else
		{
			dateMort = null;
		}
		
		if("".equals(txf_nom.getText().replace(" ", ""))) {
			lbl_err_nom.setVisible(true);
			ok = false;
		} else
		{
			lbl_err_nom.setVisible(false);
		}
		
		if("".equals(txf_prenom.getText().replace(" ", ""))) {
			lbl_err_prenom.setVisible(true);
			ok = false;
		} else
		{
			lbl_err_prenom.setVisible(false);
		}
		
		if(ok) {
			dateNaissance = date_naissance.getValue().toString();
			Stage stage = (Stage) btn_valider.getScene().getWindow();
			
			SQLCommon.addNewAuthor(Main.conn, txf_prenom.getText(), txf_nom.getText(), dateNaissance, dateMort);
			
			if(Common.bookPending) {
				SQLCommon.createNewBook(Main.conn, Common.tempBook.getTitre(), Common.tempBook.getAuteurNum(), Common.tempBook.getEtagère());
				Common.bookPending = false;
				Common.tempBook = null;
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Auteur enregistré !");
			alert.setHeaderText("Ajout de " + txf_prenom.getText() + " " + txf_nom.getText() + "réussi !");
			String str = "L'auteur(e) " +  txf_prenom.getText() + " " + txf_nom.getText() + " a été ajouté !";
			alert.setContentText(str);
			alert.showAndWait();
			Common.goBackToMain(stage);
		}
	}

	// Event Listener on CheckBox[#cb_vivant].onAction
	@FXML
	public void hideShowDeath(ActionEvent event) {
		if(cb_vivant.isSelected()) {
			lbl_date_mort.setVisible(false);
			date_mort.setVisible(false);
		} else
		{
			lbl_date_mort.setVisible(true);
			date_mort.setVisible(true);
		}
	}
}
