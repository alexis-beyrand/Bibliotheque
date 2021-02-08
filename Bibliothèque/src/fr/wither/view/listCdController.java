package fr.wither.view;

import fr.wither.application.Main;
import fr.wither.utils.Common;
import fr.wither.utils.sql.SQLCommon;
import fr.wither.utils.types.CD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class listCdController {
	@FXML
	private TableView<CD> table;
	@FXML
	private TableColumn<CD,String> col_titre;
	@FXML
	private Button btn_back;
	
	@FXML
	public void initialize() {
		ObservableList<CD> list = FXCollections.observableArrayList();
		col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		SQLCommon.fillCDList(Main.conn, list);
		table.setItems(list);
	}
	
	// Event Listener on Button[#btn_back].onAction
	@FXML
	public void goBack(ActionEvent event) {
		Stage stage = (Stage) btn_back.getScene().getWindow();
		Common.goBackToMain(stage);
	}
}
