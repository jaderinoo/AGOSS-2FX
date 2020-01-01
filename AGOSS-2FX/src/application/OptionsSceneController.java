package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class OptionsSceneController
{
	int loadType = 1;
	String determined = "";

	@FXML
	public CheckBox loadMapBox;
	
	@FXML
	public CheckBox loadMapListBox;

	@FXML
	public TextField loadMapField;
	
	@FXML
	public TextField loadMapListField;
	
	@FXML
	public Button returnBtn;
	
	@FXML
	private void initialize()
	{

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
            	Main.menuScene(loadType,determined);
            }
        });
	}
}
