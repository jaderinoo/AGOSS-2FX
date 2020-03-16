package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Interface {
	public String time = "";
	
	@FXML
	public TextField timerText;

	@FXML
    public void initialize() {
		
    }    
	
	public void timeUpdater(String timeString) {
		this.time = timeString;
	}

}
