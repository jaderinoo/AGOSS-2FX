package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
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
	public Label errorCatcher;
	
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

	@FXML
	private void initialize()
	{

		//Create the spinner listeners
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
            												//Change IDs when created.... eventually lmao
            		playerBags playerBag = new playerBags(0,0,0,0,0,0,0,0,0,0);
            		
            		//Create new player with stats selected																											//Create player bag and insert here v
            		Player player = new Player(saveField.getText(), spinStrength.getValue(), spinAgility.getValue(), spinArmor.getValue(), spinHP.getValue(), spinSpecial.getValue(), 1, 0, 0, 0, playerBag);
            																																						  //Choose player type here^
            		//Save file path
            		File file = new File("AGOSS-2FX\\src\\application\\saves\\" + player.getName());
            		
            		//If the file/folder doesn't already exist
            		if (!file.exists()) {
	            		//Create Folder for new player
	            		new File("AGOSS-2FX\\src\\application\\saves\\" + player.getName()).mkdir();
	            		ArrayList<Player> playerList = new ArrayList<Player>();
	            		
	            		//Add player to playerlist
	            		playerList.add(player);
	            		
	            		//Save playerlist 
						Updater.saveUpdater(playerList, saveField.getText());

						//Create new bag
		            	Bag bag = new Bag(0, "start");
	
						try {
							//Save Bag
							Updater.globalBagUpdater(bag, ((String) player.getName()));
	
							//Move to game
							Adventure.Resume(playerList, bag, 1, "baseGame");
						} catch (Exception e) {
							e.printStackTrace();
						}
            		}else {
            			errorCatcher.setText("A save with that name already exists!");
            			System.out.println("Directory already exists");
            		}
				} catch (IOException e) {
					e.printStackTrace();
				}
            	
            }
        });
        
		//Return to main menu
        ReturnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Main.menuScene(0, null);
            }
        });
        
      //Reset to default values
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
	
	//Compare old values to new
	public void charPoints(int newValue, int oldValue) {
  		if(newValue > oldValue) {
  			points++;
  		}else {
  			points--;
  		}
  		
  		//disable spinners if all points are used
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
