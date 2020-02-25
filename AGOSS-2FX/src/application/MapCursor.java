package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;

	public static Pane init(int cols, int rows)
	{
		Rectangle sprite = null;
		cursor = new Image("application\\tilesets\\cursor\\cursor.gif");
        
        double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);

		sprite = new Rectangle(vertical * Adventure.playerListCurrent.get(0).getMapX(), horizontal * Adventure.playerListCurrent.get(0).getMapY(), vertical, horizontal);
		sprite.setFill(new ImagePattern(cursor));
		
		printMap.shapes.add(sprite);
		
		Pane cursorLayer = new Pane();
		cursorLayer.getChildren().add(sprite);
		
		return cursorLayer;
		
	}
}
