package uk.ac.aston.oop.javafx.prep;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import uk.ac.aston.oop.javafx.prep.model.Database;
import uk.ac.aston.oop.javafx.prep.model.Item;

public class ListController {
	private final Database model;
	@FXML
	private ListView<Item> listItems;

	public ListController(Database lc) {
		model = lc;

	}

	@FXML
	public void initialize() {
		listItems.setItems(model.itemsProperty());

	}

	@FXML
	public void shufflePressed() {
		FXCollections.shuffle(model.itemsProperty());
	}

	@FXML
	public void quitPressed() {
		Scene scene = listItems.getScene();
		Window window = scene.getWindow();
		window.hide();
	}

	@FXML
	public void removePressed() {
		MultipleSelectionModel<Item> selModel = listItems.getSelectionModel();
		int selIndex = selModel.getSelectedIndex();
		if (selIndex >= 0) {
			selModel.getSelectedItem();

			try {
				FXMLLoader loader = new FXMLLoader();
				URL fxmllocation = getClass().getResource("RemoveItem.fxml");
				loader.setLocation(fxmllocation);
				RemoveController rc = new RemoveController(selModel.getSelectedItem());
				loader.setController(rc);

				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);

				VBox root = loader.load();
				Scene scene = new Scene(root, 250, 200);
				stage.setScene(scene);
				stage.showAndWait();

				if (rc.isConfirmed()) {
					model.itemsProperty().remove(selIndex);
				}
			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
