package fr.wither.view;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import fr.wither.utils.types.TableBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class ListBooksController {
	@FXML
	private TableView<TableBook> tableView;
	@FXML
	private TableColumn<TableBook, String> colTitre;
	@FXML
	private TableColumn<TableBook, String> colEmplacement;
	@FXML
	private Button btn_back;

	@FXML
	public void initialize() {
		ObservableList<TableBook> list = FXCollections.observableArrayList();

		colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		colEmplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
		
		SQLCommon.fillList(Main.conn, list);
		
		tableView.setItems(list);
	}
	// Event Listener on Button[#btn_back].onAction
	@FXML
	public void goBack(ActionEvent event) {
		 Stage stage = (Stage) btn_back.getScene().getWindow();
		 Common.goBackToMain(stage);
	}
}
