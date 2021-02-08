package fr.wither.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class addCassetteController {
	@FXML
	private TextField txf_cassette;
	@FXML
	private Button btn_add;
	@FXML
	private Label lbl_err_titre;
	
	@FXML
	public void initialize() {
		lbl_err_titre.setVisible(false);
	}
	
	// Event Listener on Button[#btn_add].onAction
	@FXML
	public void addCD(ActionEvent event) {
		boolean ok = true;
		if("".equals(txf_cassette.getText().replace(" ", ""))) {
			lbl_err_titre.setVisible(true);
			ok = false;
		} else {
			lbl_err_titre.setVisible(false);
		}
		
		
		if(ok) {
			Stage stage = (Stage)btn_add.getScene().getWindow();
			SQLCommon.addCassette(Main.conn, txf_cassette.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cassette enregistrée !");
			alert.setHeaderText("Ajout de la cassette !");
			String str = "Ajout de la cassette " + txf_cassette.getText() + " réussi !";
			alert.setContentText(str);
			alert.showAndWait();
			Common.goBackToMain(stage);
		}
	}
}
