package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;
	private static int cursorPosition;
	public static double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);
	static int cursorX = Adventure.playerListCurrent.get(0).getMapX(), cursorY = Adventure.playerListCurrent.get(0).getMapY();
	public static boolean canMove = true;
	public static ArrayList<String> moveSequence = new ArrayList<String>();
	
	public static Pane init()
	{
		Rectangle sprite = null;
		cursor = new Image("application\\tilesets\\cursor\\cursor.gif");
		
		sprite = new Rectangle(vertical * cursorX, horizontal * cursorY, vertical, horizontal);
		sprite.setFill(new ImagePattern(cursor));
		sprite.setId("cursor");
		
		printMap.shapes.add(sprite);
		cursorPosition = printMap.shapes.size() - 1;
		
		Pane cursorLayer = new Pane();
		cursorLayer.getChildren().add(sprite);
		
		System.out.println("Cursor created.");
		System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
		return cursorLayer;
	}
	
	public static void resetCursor(int tempPos) {
		System.out.println(tempPos);
		System.out.println("Reseting cursor position to: " + Adventure.playerListCurrent.get(tempPos).getMapX() + "," + Adventure.playerListCurrent.get(tempPos).getMapY());
		printMap.shapes.get(cursorPosition).relocate(vertical * Adventure.playerListCurrent.get(tempPos).getMapX(), horizontal * Adventure.playerListCurrent.get(tempPos).getMapY());
		cursorX = Adventure.playerListCurrent.get(tempPos).getMapX();
		cursorY = Adventure.playerListCurrent.get(tempPos).getMapY();
	}
	
	
	
	//Referred to in printMap()
	//			V
	
	public static void moveUp() 
    {
		if(canMove == true) {
			cursorY--;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
		
		if(Arrow.isOn == true) {
			Arrow.arrowY--;
			printMap.shapes.get(Arrow.arrowTipPosition).relocate(vertical * Arrow.arrowX, horizontal * Arrow.arrowY);
			moveSequence.add("up");
		}
    }
	
	public static void moveDown() 
    {
		if(canMove == true) {
			cursorY++;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
		
		if(Arrow.isOn == true) {
			Arrow.arrowY++;
			printMap.shapes.get(Arrow.arrowTipPosition).relocate(vertical * Arrow.arrowX, horizontal * Arrow.arrowY);
			moveSequence.add("down");
		}
    }
	
	public static void moveLeft() 
    {
		if(canMove == true) {
			cursorX--;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
		
		if(Arrow.isOn == true) {
			Arrow.arrowX--;
			printMap.shapes.get(Arrow.arrowTipPosition).relocate(vertical * Arrow.arrowX, horizontal * Arrow.arrowY);
			moveSequence.add("left");
		}
    }
	
	public static void moveRight() 
    {
		if(canMove == true) {
			cursorX++;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
		
		if(Arrow.isOn == true) {
			Arrow.arrowX++;
			printMap.shapes.get(Arrow.arrowTipPosition).relocate(vertical * Arrow.arrowX, horizontal * Arrow.arrowY);
			moveSequence.add("right");
		}
    }
	
	public static int isOnMover = 0;
	
	public static void checkSpace(int current) {

		if(Arrow.isOn == false) {
			canMove = false;
			Arrow.toggleArrow(true);
		} else { //Setup space check here for available tiles / enemies / whatever
			canMove = true;
			Arrow.toggleArrow(false);
			isOnMover = 0;
		}

		Arrow.setLocation(Adventure.playerListCurrent.get(current).getMapX(), Adventure.playerListCurrent.get(current).getMapY());
	}
}


/*
 * 	        		String[] testMove = {"left","left","up","down","down"};
	        		
	        		//Test move characters		sprite ID		Direction			X						Y
	        		printMap.moveSprite(mapInitialization.mobList.get(1).getMapId(),testMove, mapInitialization.mobList.get(1).getMapX(), mapInitialization.mobList.get(1).getMapY());
	        		
	        		//Change characters location
	        		mapInitialization.mobList.get(1).setMapX(mapInitialization.mobList.get(1).getMapX()-2);
	        		mapInitialization.mobList.get(1).setMapY(mapInitialization.mobList.get(1).getMapY()+1);
	        		*/
