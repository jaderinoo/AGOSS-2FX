package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Interface {
	public String time = "";
	
	@FXML
	public static AnchorPane mainPane;

	@FXML
    public static Group initialize() throws IOException {
		
		Group group = new Group();

	    VBox leftBar =  FXMLLoader.load(printMap.class.getResource("scenes\\sidebarUI.fxml"));
	    group.getChildren().add(leftBar);
	    
	    VBox rightBar =  FXMLLoader.load(printMap.class.getResource("scenes\\sidebarUI.fxml"));
	    group.getChildren().add(rightBar);
	    
	    VBox bottomBar =  FXMLLoader.load(printMap.class.getResource("scenes\\sidebarUI.fxml"));
	    group.getChildren().add(bottomBar);
		
		return group;
    }    
	
	public void timeUpdater(String timeString) {
		this.time = timeString;
	}

}
