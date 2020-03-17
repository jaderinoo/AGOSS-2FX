package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class OptionsSceneController
{
	int loadType = 1;
	String determined = "";
	public static String resType = "";

	@FXML
	public CheckBox loadMapBox;
	
	@FXML
	public CheckBox loadMapListBox;

	@FXML
	public TextField loadMapField;
	
	@FXML
	public TextField loadMapListField;
	
	@FXML
	public ChoiceBox<String> resChoice;
	
	@FXML
	public Button returnBtn;
	
	@FXML
	public void initialize() {

		resChoice.setValue("1280x720");
		resChoice.getItems().add("1280x720");
		resChoice.getItems().add("1920x1080");

		//Check Boxes
		loadMapBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadMapListBox.setSelected(false);
            	loadMapField.setDisable(false);
            	loadMapListField.setDisable(true);
            }
        });
		
		//Check Boxes
		loadMapListBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadMapBox.setSelected(false);
            	loadMapField.setDisable(true);
            	loadMapListField.setDisable(false);
            }
        });
		
		//Return to main menu
        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(loadMapBox.isSelected()) {
            		loadType = 2;
            		determined = loadMapField.getText();
            	}else if(loadMapListBox.isSelected()) {
            		loadType = 3;
            		determined = loadMapListField.getText();
            	}
            	resType = resChoice.getValue();
            	Main.menuScene(loadType,determined);
            }
        });
	}
}
