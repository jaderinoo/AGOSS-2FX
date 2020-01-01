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
	String determined = "";
	
	@FXML
	public Button LoadGameBtn;
	
	@FXML
	public Button NewGameBtn;
	
	@FXML
	public Button optionBtn;
	
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
		
		optionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.optionsScene();
            }
        });
	
		LoadGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		Main.loadScene();
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
