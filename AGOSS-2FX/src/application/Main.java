package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application
{
	static Scene menuScene;
	static Scene loadScene;
	static Scene CharCreateScene;
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
		VBox menuVBox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\MenuScene.fxml"));
		VBox loadVbox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\loadScene.fxml"));
		VBox CharCreateVbox = (VBox) FXMLLoader.load(getClass().getResource("scenes\\CharCreateScene.fxml"));
		
		// Create the Scene
		menuScene = new Scene(menuVBox, 500, 500);
		loadScene = new Scene(loadVbox, 500, 500);
		CharCreateScene = new Scene(CharCreateVbox, 500, 500);
		
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
	
	public static void CharScene() {
		window.setScene(CharCreateScene);
	}

}
