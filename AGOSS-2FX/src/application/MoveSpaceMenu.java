package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	public void init() {
		arrowX = 0;
		arrowY = 0;


	}
	   
	   
	public static void showMenu(boolean decider, Player player) {
		   if(decider == true) {
			   printMap.moveSpaceMenu.relocate(player.getMapX()+25,player.getMapY()+25);
			   printMap.moveSpaceMenu.setVisible(true);
		   }
		   
		   if(decider == false) {
			   printMap.moveSpaceMenu.setVisible(false);
		   }
	   }
}
