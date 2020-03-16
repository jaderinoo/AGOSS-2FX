package application;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Interface {
	public String time = "";
	
	@FXML
	public AnchorPane mainPane;

	@FXML
    public void initialize() {
		System.out.println(Main.window.getWidth());
		Rectangle rect = new Rectangle(0, Main.window.getHeight(), Main.window.getWidth(), 150);
		rect.setFill(Color.BEIGE);

		mainPane.getChildren().add(rect);
		
    }    
	
	public void timeUpdater(String timeString) {
		this.time = timeString;
	}

}
