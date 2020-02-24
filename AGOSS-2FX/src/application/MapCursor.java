package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;
	static double horizontalSetter = 0;
	static double verticalSetter = 0;

	public static Pane init(int cols, int rows)
	{
		Rectangle sprite = null;
		cursor = new Image("application\\tilesets\\cursor\\cursor.gif");
		horizontalSetter = (Main.window.getWidth()/cols);
        verticalSetter = (Main.window.getHeight()/rows);
        
        double horizontal = 32*(horizontalSetter/32), vertical = 32*(verticalSetter/32);
		sprite = new Rectangle( vertical, horizontal);
		
		sprite.setFill(new ImagePattern(cursor));
		
		printMap.shapes.add(sprite);
		
		Pane cursorLayer = new Pane();
		cursorLayer.getChildren().add(sprite);
		
		return cursorLayer;
		
	}
}
