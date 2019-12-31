package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import org.json.simple.parser.ParseException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CharCreateController
{
	static int points = 3;
	int total = 30;
	int currentStrength = 5;
	int currentAgility = 5;
	int currentArmor = 5;
	int currentHP = 50;
	int currentSpecial = 10;

    static ArrayList<Player> playerListResume = new ArrayList<Player>();
    static Bag bagResume = new Bag(0, 0, 0, null);
    
    @FXML
    public TextField remPoints;
    
    @FXML
    public Spinner<Integer> spinStrength;
    
    @FXML
    public Spinner<Integer> spinAgility;
    
    @FXML
    public Spinner<Integer> spinArmor;
    
    @FXML
    public Spinner<Integer> spinHP;
    
    @FXML
    public Spinner<Integer> spinSpecial;
    
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

	  	spinStrength.valueProperty().addListener((obs, oldValue, newValue) -> charPoints(oldValue,newValue));
	  	spinAgility.valueProperty().addListener((obs, oldValue, newValue) -> charPoints(oldValue,newValue));
	  	spinArmor.valueProperty().addListener((obs, oldValue, newValue) -> charPoints(oldValue,newValue));
	  	spinHP.valueProperty().addListener((obs, oldValue, newValue) -> charPoints(oldValue,newValue));
	  	spinSpecial.valueProperty().addListener((obs, oldValue, newValue) -> charPoints(oldValue,newValue));
	  	
		//Disables the continue button until the textfield is valid
		Continue.disableProperty().bind(Bindings.isEmpty(saveField.textProperty()));

		//Continue with load game
		Continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            		Player player = new Player(saveField.getText(), 5, 5, 5, 50, 10, 1, 0, 0);
            		
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
	
	public void charPoints(int newValue, int oldValue) {
  		if(newValue > oldValue) {
  			points++;
  		}else {
  			points--;
  		}
  		remPoints.setText(Integer.toString(points));
  		if(points == 0) {
  			spinStrength.valueFactory(0,0,spinStrength.getValue(),0);
  			spinAgility.setDisable(true);
  			spinArmor.setDisable(true);
  			spinHP.setDisable(true);
  			spinSpecial.setDisable(true);
  		}
	}
}
