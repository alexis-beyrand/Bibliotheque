package fr.wither.view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class SearchBookController {
	@FXML
	private Button btn_retour;
	@FXML
	private TextField txf_titre;
	@FXML
	private Button btn_rech;
	@FXML
	private Label lbl_do_not_exist;
	@FXML
	private Label lbl_exist;

	@FXML
	public void initialize() {
		lbl_exist.setVisible(false);
		lbl_do_not_exist.setVisible(false);
	}
	
	// Event Listener on Button[#btn_retour].onAction
	@FXML
	public void goBack(ActionEvent event) {
		 Stage stage = (Stage) btn_rech.getScene().getWindow();
		 Common.goBackToMain(stage);
		 
	}
	// Event Listener on Button[#btn_rech].onAction
	@FXML
	public void searchBook(ActionEvent event) {
		
		if("".equals(txf_titre.getText().replace(" ", ""))) {
			lbl_do_not_exist.setVisible(true);
			return;
		}
		
		if(SQLCommon.bookExist(Main.conn, txf_titre.getText())) {
			lbl_do_not_exist.setVisible(false);
			lbl_exist.setVisible(true);
		} else
		{
			lbl_exist.setVisible(false);
			lbl_do_not_exist.setVisible(true);
		}
	}
}
