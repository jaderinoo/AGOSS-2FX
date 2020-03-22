package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MoveSpaceMenu {
	
	static Rectangle arrow = null;
	static int arrowX, arrowY;
	public static int moveSpacePosition;
	static boolean isOn;
	static String tempName;
	
	// location and resources will be automatically injected by the FXML loader
	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;
	
	@FXML
	public Button moveTo;
	
	@FXML
	public Button returnTo;
	
	@FXML
	private void initialize() {
		arrowX = 0;
		arrowY = 0;
		
		moveTo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//do stuff
				System.out.println("Stop clicking me");
				printMap.moveSpaceMenu.setVisible(false);
				MapCursor.canMove = true;
				MapCursor.moveSequence.clear();
				
				//Check player status
  			   	GameVariables.checkRoundStatus();
			}
		});

		returnTo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//do stuff
				int tempPos = 0;
				for(int i = 0; i < printMap.shapes.size(); i++) {
					if(printMap.shapes.get(i).getId() == tempName) {
						System.out.println("Moving character: " + printMap.shapes.get(i).getId());
						tempPos = i;
					}  
				}

				//Reset to before the move
				printMap.resetMove(tempPos, tempName);
				printMap.moveSpaceMenu.setVisible(false);
				MapCursor.canMove = true;
				MapCursor.moveSequence.clear();

				//Reset temp vars
				tempName = "";
				tempPos = 0;
				printMap.postArrow("clear", "null", 0, 0);
				
			}
		});
	}
	   
	   
	public static void showMenu(boolean decider, Player player) {
		
		//Tempname
		tempName = player.getName();
		
		   if(decider == true) {
			   if(player.getMapX() == 0 && player.getMapY() == 0) {
				   printMap.moveSpaceMenu.relocate(printMap.horizontal * player.getMapX() + printMap.horizontal,printMap.vertical * player.getMapY() + printMap.vertical);
				   
			   }else if(player.getMapX() == 4 && player.getMapY() == 0) {
				   printMap.moveSpaceMenu.relocate(printMap.horizontal * player.getMapX() - printMap.horizontal,printMap.vertical * player.getMapY() - printMap.vertical); 
				   
			   }else if(player.getMapX() == 0 && player.getMapY() == 4) {
				   printMap.moveSpaceMenu.relocate(printMap.horizontal * player.getMapX() + printMap.horizontal,printMap.vertical * player.getMapY() - printMap.vertical); 
				   
			   }else if(player.getMapX() == 4 && player.getMapY() == 4) {
				   printMap.moveSpaceMenu.relocate(printMap.horizontal * player.getMapX() - printMap.horizontal,printMap.vertical * player.getMapY() - printMap.vertical); 	   
			   }
			   printMap.moveSpaceMenu.setVisible(true);
		   }
		   
		   if(decider == false) {
			   printMap.moveSpaceMenu.setVisible(false);
		   }
	   }
}
