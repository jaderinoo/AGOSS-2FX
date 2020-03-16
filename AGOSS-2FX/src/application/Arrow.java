package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Arrow {
	private static Image tempArrow;
	public static int arrowTipPosition;
	public static double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);
	static Rectangle arrow = null;
	static int arrowX, arrowY;
	static boolean isOn;
	
	static Pane arrowLayer = new Pane();
	
	public static Pane init() {
		
		arrowX = 0;
		arrowY = 0;
		
		tempArrow = new Image("application\\tilesets\\cursor\\arrow.gif");
		
		arrow = new Rectangle(horizontal * arrowX, vertical * arrowY, horizontal, vertical);
		arrow.setFill(new ImagePattern(tempArrow));
		arrow.setId("arrowTip");

		
		arrowLayer.getChildren().add(arrow);
		
		printMap.shapes.add(arrow);
		arrowTipPosition = printMap.shapes.size() - 1;
		
		return arrowLayer;
	}
	
	   public static void toggleArrow(boolean decider) {
		   if(decider == true) {
			   printMap.shapes.get(arrowTipPosition).setVisible(true);
			   isOn = true;
		   }
		   
		   if(decider == false) {
			   printMap.shapes.get(arrowTipPosition).setVisible(false);
			   isOn = false;
		   }
	   }
	   
	   public static void setLocation(int x, int y) {
		   arrowX = x;
		   arrowY = y;
		   printMap.shapes.get(arrowTipPosition).relocate(horizontal * x, vertical * y);
	   }
	   
}
