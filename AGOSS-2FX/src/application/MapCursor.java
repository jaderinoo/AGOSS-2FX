package application;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;
	private static int cursorPosition;
	public static double horizontal = 32*(printMap.horizontalSetter/32), vertical = 32*(printMap.verticalSetter/32);
	private static int cursorX = Adventure.playerListCurrent.get(0).getMapX(), cursorY = Adventure.playerListCurrent.get(0).getMapY();
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
			System.out.println("UP \nCursorY: " + cursorY);
			cursorY--;
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void moveDown() 
    {
		if(canMove == true) {
			System.out.println("UP \nCursorY: " + cursorY);
			cursorY++;
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void moveLeft() 
    {
		if(canMove == true) {
			System.out.println("UP \nCursorY: " + cursorY);
			cursorX--;
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void moveRight() 
    {
		if(canMove == true) {
			System.out.println("UP \nCursorY: " + cursorY);
			cursorX++;
			printMap.shapes.get(cursorPosition).relocate(vertical * cursorX, horizontal * cursorY);
		}
    }
	
	public static void checkSpace() {
		
		for(int i = 0; i != Adventure.playerListCurrent.size(); i++) {
			if(cursorX == Adventure.playerListCurrent.get(i).getMapX() && cursorY == Adventure.playerListCurrent.get(i).getMapY()) {
				System.out.println("playerfound");
				System.out.println(Adventure.playerListCurrent.get(i).getName());
				/*
				 * String[] testMove = {"left"};
				 * 
				 * //Test move characters sprite ID Direction X Y
				 * printMap.moveSprite(mapInitialization.mobList.get(i).getMapId(),testMove,
				 * Adventure.playerListCurrent.get(i).getMapX(),
				 * Adventure.playerListCurrent.get(i).getMapY());
				 * 
				 * //Change characters location
				 * Adventure.playerListCurrent.get(i).setMapX(Adventure.playerListCurrent.get(i)
				 * .getMapX()-1);
				 */
			}
		}
		
		for(int i = 0; i != mapInitialization.mobList.size(); i++) {
			if(cursorX == mapInitialization.mobList.get(i).getMapX() && cursorY == mapInitialization.mobList.get(i).getMapY()) {
				System.out.println("enemyfound");
				System.out.println(mapInitialization.mobList.get(i).getName());
				
				String[] testMove = {"up"};
				  
				//Test move characters sprite 				ID 					Direction 				X 										Y
				printMap.moveSprite(mapInitialization.mobList.get(i).getMapId(), testMove, mapInitialization.mobList.get(i).getMapX(), mapInitialization.mobList.get(i).getMapY());
				  
				//Change characters location
				mapInitialization.mobList.get(i).setMapY(mapInitialization.mobList.get(i).getMapY()-1);
				 
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
