package application;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class LoadSceneController
{
	
    static ArrayList<Player> playerListResume = new ArrayList<Player>();
    static Bag bagResume = new Bag(0, 0, 0, null);
    @FXML
    public ListView<String> saveList;
    
    @FXML
    public Button refreshList;
    
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
		populateList();
		
		//Disables the continue button until the textfield is valid
		Continue.disableProperty().bind(Bindings.isEmpty(saveList.getSelectionModel().getSelectedItems()));

		//Continue with load game
		Continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
					playerListResume = Updater.saveReader(saveList.getSelectionModel().getSelectedItem());
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
				try {
					bagResume = Updater.bagReader(saveList.getSelectionModel().getSelectedItem());
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}

            }
        });
        
		//Return to main menu
        refreshList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	populateList();
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
	
	public void populateList() {
		
		File file = new File("AGOSS-2FX\\src\\application\\saves\\");
		
		String[] directories = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		
		ObservableList<String> data = FXCollections.observableArrayList();
		
		for(int i = 0; i != directories.length; i++) {
			data.add(directories[i]);
		}
		saveList.setItems(data);
	}
}
