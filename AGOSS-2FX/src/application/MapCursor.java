package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;
	private static int cursorPosition;
	public static double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);
	
	public static Pane init(int cols, int rows)
	{
		Rectangle sprite = null;
		cursor = new Image("application\\tilesets\\cursor\\cursor.gif");
        
        

		sprite = new Rectangle(vertical * Adventure.playerListCurrent.get(0).getMapX(), horizontal * Adventure.playerListCurrent.get(0).getMapY(), vertical, horizontal);
		sprite.setFill(new ImagePattern(cursor));
		sprite.setId("cursor");
		
		printMap.shapes.add(sprite);
		cursorPosition = printMap.shapes.size() - 1;
		
		Pane cursorLayer = new Pane();
		cursorLayer.getChildren().add(sprite);
		
		return cursorLayer;
		
	}
	
	public static void resetCursor() {
		System.out.println("Reseting cursor position");
		printMap.shapes.get(cursorPosition).relocate(vertical * Adventure.playerListCurrent.get(0).getMapX(), horizontal * Adventure.playerListCurrent.get(0).getMapY());
	}
}
