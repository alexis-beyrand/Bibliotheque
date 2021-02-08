package fr.wither.view;

import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addCDController {
	@FXML
	private TextField txf_cd;
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
		if("".equals(txf_cd.getText().replace(" ", ""))) {
			lbl_err_titre.setVisible(true);
			ok = false;
		} else {
			lbl_err_titre.setVisible(false);
		}
		
		
		if(ok) {
			Stage stage = (Stage)btn_add.getScene().getWindow();
			SQLCommon.addCD(Main.conn, txf_cd.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("CD enregistré !");
			alert.setHeaderText("Ajout du CD");
			String str = "Ajout du CD " + txf_cd.getText() + " réussi !";
			alert.setContentText(str);
			alert.showAndWait();
			Common.goBackToMain(stage);
		}
	}
}
