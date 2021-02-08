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

public class addDVDController {
	@FXML
	private TextField txf_dvd;
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
		if("".equals(txf_dvd.getText().replace(" ", ""))) {
			lbl_err_titre.setVisible(true);
			ok = false;
		} else {
			lbl_err_titre.setVisible(false);
		}
		
		
		if(ok) {
			Stage stage = (Stage)btn_add.getScene().getWindow();
			SQLCommon.addDVD(Main.conn, txf_dvd.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("DVD enregistré !");
			alert.setHeaderText("Ajout du DVD");
			String str = "Ajout du DVD " + txf_dvd.getText() + " réussi !";
			alert.setContentText(str);
			alert.showAndWait();
			Common.goBackToMain(stage);
		}
	}
}
