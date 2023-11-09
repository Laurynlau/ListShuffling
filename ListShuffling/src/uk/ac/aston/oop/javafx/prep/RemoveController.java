package uk.ac.aston.oop.javafx.prep;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Window;
//import uk.ac.aston.oop.javafx.scene.control.Label;
import uk.ac.aston.oop.javafx.prep.model.Item;

public class RemoveController {

	@FXML
	private Label lblItem;
	private Item selectedItem;
	private boolean confirmed = false;

	public RemoveController(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	@FXML
	public void initialize() {
		lblItem.setText(selectedItem.toString());

	}

	public boolean isConfirmed() {
		return confirmed;
	}

	@FXML
	public void cancelPressed() {
		confirmed = false;
		Window window = lblItem.getScene().getWindow();
		window.hide();

	}

	@FXML
	public void confirmPressed() {
		confirmed = true;
		Window window = lblItem.getScene().getWindow();
		window.hide();

	}
}
