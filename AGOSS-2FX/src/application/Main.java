package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application
{
	static Scene menuScene;
	static Scene loadScene;
	static Stage window;
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException
	{
		window = stage;
		
	    //Setup scenes   
		VBox menuVBox = (VBox) FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		VBox loadVbox = (VBox) FXMLLoader.load(getClass().getResource("loadScene.fxml"));
        
		// Create the Scene
		menuScene = new Scene(menuVBox);
		loadScene = new Scene(loadVbox);
		
		// Set the Scene to the Stage
		window.setScene(menuScene);
		// Set the Title to the Stage
		window.setTitle("JavaFx Testing Environment");
		// Display the Stage
		window.show();
	}
	
	public static void loadScene() {
		window.setScene(loadScene);
	}
	
	public static void menuScene() {
		window.setScene(menuScene);
	}

}
