package application;

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
	
	public static void resetCursor() {
		System.out.println("Reseting cursor position to: " + Adventure.playerListCurrent.get(0).getMapX() + "," + Adventure.playerListCurrent.get(0).getMapY());
		printMap.shapes.get(cursorPosition).relocate(vertical * Adventure.playerListCurrent.get(0).getMapX(), horizontal * Adventure.playerListCurrent.get(0).getMapY());
		cursorX = Adventure.playerListCurrent.get(0).getMapX();
		cursorY = Adventure.playerListCurrent.get(0).getMapY();
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
    }
	
	public static void moveDown() 
    {
		if(canMove == true) {
			cursorY++;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void moveLeft() 
    {
		if(canMove == true) {
			cursorX--;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void moveRight() 
    {
		if(canMove == true) {
			cursorX++;
			System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void checkSpace() {
		
		for(int i = 0; i != Adventure.playerListCurrent.size(); i++) {
			if(cursorX == Adventure.playerListCurrent.get(i).getMapX() && cursorY == Adventure.playerListCurrent.get(i).getMapY()) {
				System.out.println("playerfound");
				System.out.println(Adventure.playerListCurrent.get(i).getName());
				
				String[] testMove = {"up"};
				  
				//Test move characters sprite 								Direction 
				printMap.moveSprite(null, Adventure.playerListCurrent.get(i), testMove);
			}
		}
		
		for(int i = 0; i != mapInitialization.mobList.size(); i++) {
			if(cursorX == mapInitialization.mobList.get(i).getMapX() && cursorY == mapInitialization.mobList.get(i).getMapY()) {
				System.out.println("enemyfound");
				System.out.println(mapInitialization.mobList.get(i).getName());
				
				String[] testMove = {"up","down","down","left"};
				  
				//Test move characters sprite 								Direction 
				printMap.moveSprite(mapInitialization.mobList.get(i), null, testMove);
				 
			}

		}
		
		

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
