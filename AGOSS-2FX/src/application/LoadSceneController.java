package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class LoadSceneController
{
	
    static ArrayList<Player> playerListResume = new ArrayList<Player>();
    static Bag bagResume = new Bag(0, 0, 0, null);
    
	@FXML
	public Button ReturnBtn;
	
	@FXML
	public Button Continue;
	
	@FXML
	public TextField saveField;
	
	// location and resources will be automatically injected by the FXML loader
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;
	
	@FXML
	private void initialize()
	{

		//Disables the continue button until the textfield is valid
		Continue.disableProperty().bind(Bindings.isEmpty(saveField.textProperty()));

		//Continue with load game
		Continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
					playerListResume = Updater.saveReader(saveField.getText());
				} catch (ParseException | IOException e) {
					//CREATE A POP UP
					e.printStackTrace();
				}
				try {
					bagResume = Updater.bagReader(saveField.getText());
				} catch (ParseException | IOException e) {
					//CREATE A POP UP
					e.printStackTrace();
				}

            }
        });
        
		//Return to main menu
        ReturnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.menuScene();
            }
        });
	}
}
