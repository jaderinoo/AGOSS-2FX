package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application
{
	static Scene menuScene;
	static Scene loadScene;
	static Scene charCreateScene;
	static Stage window;
	static Timeline timeline = new Timeline();
	static int loadType;
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException
	{
		window = stage;
		
	    //Setup scenes   
		VBox menuVBox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\MenuScene.fxml"));
		VBox loadVbox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\loadScene.fxml"));
		VBox charCreateVbox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\CharCreateScene.fxml"));
		
		// Create the Scene
		menuScene = new Scene(menuVBox, 500, 500);
		loadScene = new Scene(loadVbox, 500, 500);
		charCreateScene = new Scene(charCreateVbox, 500, 500);
		
		// Set the Scene to the Stage
		window.setScene(menuScene);
		// Set the Title to the Stage
		window.setTitle("JavaFx Testing Environment");
		// Display the Stage
		window.show();

	}
	
	public static void loadGameWithType(ArrayList<Player> playerList, Bag bag) {
		try {
			Adventure.Resume(playerList, bag, loadType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadScene(int x) {
        loadType = x;
        
		KeyFrame key = new KeyFrame(Duration.millis(500),new KeyValue (window.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae) -> {
        	window.setScene(loadScene);
        	fadeOut();
        }); 
        timeline.play();
	}
	
	public static void menuScene() {
        KeyFrame key = new KeyFrame(Duration.millis(500),new KeyValue (window.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae) -> {
        	window.setScene(menuScene);
        	fadeOut();
        }); 
        timeline.play();
	}
	
	public static void charScene() {
        KeyFrame key = new KeyFrame(Duration.millis(500),new KeyValue (window.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae) -> {
        	window.setScene(charCreateScene);
        	fadeOut();
        }); 
        timeline.play();
	}
	
	public static void quitGame() {
        KeyFrame key = new KeyFrame(Duration.millis(500),new KeyValue (window.getScene().getRoot().opacityProperty(), 0)); 
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae) -> {
    		Platform.exit();
    		System.exit(0);
        }); 
        timeline.play();
	}
	
	public static void fadeOut() {
        Timeline timelineOut = new Timeline();
        KeyFrame key2 = new KeyFrame(Duration.millis(500),
                new KeyValue (window.getScene().getRoot().opacityProperty(), 1)); 
        timelineOut.getKeyFrames().add(key2); 
        timelineOut.play();
	}

}
