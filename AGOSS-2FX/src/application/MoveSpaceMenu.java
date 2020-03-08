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
	
	public static double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);
	static Rectangle arrow = null;
	static int arrowX, arrowY;
	public static int moveSpacePosition;
	static boolean isOn;

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
	public void init() {
		arrowX = 0;
		arrowY = 0;
		
		moveTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		//do stuff
            }
        });
		
		returnTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//do stuff
            }
        });
	}
	   
	   
	public static void showMenu(boolean decider, Player player) {
		   if(decider == true) {
			   if(player.getMapX() == 0 && player.getMapY() == 0) {
				   printMap.moveSpaceMenu.relocate(vertical * player.getMapX() + vertical,horizontal * player.getMapY() + horizontal);
				   
			   }else if(player.getMapX() == 4 && player.getMapY() == 0) {
				   printMap.moveSpaceMenu.relocate(vertical * player.getMapX() - vertical,horizontal * player.getMapY() - horizontal); 
				   
			   }else if(player.getMapX() == 0 && player.getMapY() == 4) {
				   printMap.moveSpaceMenu.relocate(vertical * player.getMapX() + vertical,horizontal * player.getMapY() - horizontal); 
				   
			   }else if(player.getMapX() == 4 && player.getMapY() == 4) {
				   printMap.moveSpaceMenu.relocate(vertical * player.getMapX() - vertical,horizontal * player.getMapY() - horizontal); 
				   
			   }
			   
			   printMap.moveSpaceMenu.setVisible(true);
		   }
		   
		   if(decider == false) {
			   printMap.moveSpaceMenu.setVisible(false);
		   }
	   }
}
