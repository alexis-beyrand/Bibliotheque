package fr.wither.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainScreenController {
	@FXML
	private Button add_author;
	@FXML
	private Button add_book;
	@FXML
	private Button list_authors;
	@FXML
	private Button list_book;
	@FXML
	private Button search_book;
	@FXML
	private Button btn_add_cd;
	@FXML
	private Button btn_list_cd;
	@FXML
	private Button btn_add_dvd;
	@FXML
	private Button btn_list_dvd;
	@FXML
	private Button btn_add_cassette;
	@FXML
	private Button btn_list_cassette;
	// Event Listener on Button[#add_author].onAction
		@FXML
		public void openAddAuthor(ActionEvent event) {
			 Stage stage = (Stage) add_author.getScene().getWindow();
			 try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/NewAuthorForm.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Event Listener on Button[#add_book].onAction
		@FXML
		public void openAddBook(ActionEvent event) {
			 Stage stage = (Stage) add_book.getScene().getWindow();
			 try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/NewBookForm.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Event Listener on Button[#list_authors].onAction
		@FXML
		public void openAuthorList(ActionEvent event) {
			 Stage stage = (Stage) list_authors.getScene().getWindow();
			 try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/AuthorDetails.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Event Listener on Button[#list_book].onAction
		@FXML
		public void openBookList(ActionEvent event) {
			 Stage stage = (Stage) list_book.getScene().getWindow();
			 try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/ListBooks.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Event Listener on Button[#search_book].onAction
		@FXML
		public void openSearchBook(ActionEvent event) {
			 Stage stage = (Stage) search_book.getScene().getWindow();
			 try {
				AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/SearchBook.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	
	// Event Listener on Button[#btn_add_cd].onAction
	@FXML
	public void openAddCD(ActionEvent event) {
		Stage stage = (Stage) btn_add_cd.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/addCD.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btn_list_cd].onAction
	@FXML
	public void openListCD(ActionEvent event) {
		Stage stage = (Stage) btn_list_cd.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/listCd.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btn_add_dvd].onAction
	@FXML
	public void openAddDVD(ActionEvent event) {
		Stage stage = (Stage) btn_add_dvd.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/addDVD.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btn_list_dvd].onAction
	@FXML
	public void openListDVD(ActionEvent event) {
		Stage stage = (Stage) btn_list_dvd.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/listDvd.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btn_add_cassette].onAction
	@FXML
	public void openAddCassette(ActionEvent event) {
		Stage stage = (Stage) btn_add_cassette.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/addCassette.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btn_list_cassette].onAction
	@FXML
	public void openListCassette(ActionEvent event) {
		Stage stage = (Stage) btn_list_cassette.getScene().getWindow();
		 try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fr/wither/view/listCassette.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
