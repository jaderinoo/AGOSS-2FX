package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class MenuSceneController
{
	
	int loadType = 1;
	@FXML
	public Button LoadGameBtn;
	
	@FXML
	public Button NewGameBtn;
	
	@FXML
	public CheckBox loadMapBox;
	
	@FXML
	public CheckBox loadMapListBox;
	
	@FXML
	public Button quitBtn;
	
	// location and resources will be automatically injected by the FXML loader
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;
	
	@FXML
	private void initialize()
	{
		quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		Main.quitGame();
            }
        });

		LoadGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(loadMapBox.isSelected()) {
            		loadType = 2;
            	}else if(loadMapListBox.isSelected()) {
            		loadType = 3;
            	}
        		Main.loadScene(loadType);
            }
        });
        
		NewGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.charScene();
            }
        });
	}
}
