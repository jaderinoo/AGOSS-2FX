package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {
	
    @FXML
    public Text txtLabel;
    
    public void initialize() {

    	txtLabel.setText("Foo");
    }
}
