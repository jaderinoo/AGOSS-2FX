package application;

import java.io.File;
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
	public Button ResetBtn;
	
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
            		//Create new player with stats selected
            		Player player = new Player(saveField.getText(), spinStrength.getValue(), spinAgility.getValue(), spinArmor.getValue(), spinHP.getValue(), spinSpecial.getValue(), 1, 0, 0);
            		
            		//Create Folder for new player
            		new File("AGOSS-2FX\\src\\application\\saves\\" + player.getName()).mkdir();
            		ArrayList<Player> playerList = new ArrayList<Player>();
            		
            		//Add player to playerlist
            		playerList.add(player);
            		
            		//Save playerlist 
					Updater.saveUpdater(playerList, saveField.getText());
					
					//Create new bag
	            	Bag bag = new Bag(0, 0, 0, "start");

					try {
						//Save Bag
						Updater.bagUpdater(bag, ((String) player.getName()));

						//Move to game
						//Adventure.Resume(playerList, bag, 1, frame);
					} catch (IOException e) {
						//CREATE A POP UP for invalid name
						e.printStackTrace();
					}

				} catch (IOException e) {
					//CREATE A POP Up
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
        
      //Return to main menu
        ResetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	spinStrength.getValueFactory().setValue(5);
            	spinAgility.getValueFactory().setValue(5);
            	spinArmor.getValueFactory().setValue(5);
            	spinHP.getValueFactory().setValue(50);
            	spinSpecial.getValueFactory().setValue(10);
            	
            	points = 3;
            	remPoints.setText(Integer.toString(points));
            	
      			spinStrength.setDisable(false);
      			spinAgility.setDisable(false);
      			spinArmor.setDisable(false);
      			spinHP.setDisable(false);
      			spinSpecial.setDisable(false);
            }
        });
        
	}
	
	public void charPoints(int newValue, int oldValue) {
  		if(newValue > oldValue) {
  			points++;
  		}else {
  			points--;
  		}
  		
  		//(0,0,spinStrength.getValue(),0)
  		remPoints.setText(Integer.toString(points));
  		if(points == 0) {
  			spinStrength.setDisable(true);
  			spinAgility.setDisable(true);
  			spinArmor.setDisable(true);
  			spinHP.setDisable(true);
  			spinSpecial.setDisable(true);
  		}
	}
}
