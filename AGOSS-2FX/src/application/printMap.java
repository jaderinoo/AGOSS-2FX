package application;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class printMap {
	   
	   public static void mapPrinter(GridSpace[][] map, int rows, int cols, ArrayList<Player> playerList,ArrayList<Mob1> mobList) {
	        AnchorPane root = new AnchorPane();
	        Scene scene = new Scene(root, Main.window.getWidth(), Main.window.getHeight());
	        double horizontalSetter = (Main.window.getWidth()/cols);
	        double verticalSetter = (Main.window.getHeight()/rows);
	        
	        double horizontal = 32*(horizontalSetter/32), vertical = 32*(verticalSetter/32);
	        Rectangle rect = null;
	      
			//Print map
			for (int y=0; y < rows; y++) {
			    for (int x=0; x < cols; x++) {
	                rect = new Rectangle(horizontal * y, vertical * x, horizontal, vertical);
	                rect.setStroke(new Color(1f,1f,1f,.05f));
	                System.out.println("application\\tilesets\\" + map[x][y].getType() + ".png");
		    		Image img = new Image("application\\tilesets\\" + map[x][y].getType() + ".png");
		    		rect.setFill(new ImagePattern(img));
	                root.getChildren().add(rect);
			    }
			    System.out.println();
			}
			//Set the scene
	        Main.window.setScene(scene);
	}
}
