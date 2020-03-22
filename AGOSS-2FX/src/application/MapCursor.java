package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MapCursor {
	private static Image cursor;
	private static int cursorPosition;
	static int cursorX = Adventure.playerListCurrent.get(0).getMapX(), cursorY = Adventure.playerListCurrent.get(0).getMapY();
	public static float horizontalScrollVariable = 0, verticalScrollVariable = 0;
	public static boolean canMove = true;
	public static ArrayList<String> moveSequence = new ArrayList<String>();
	
	public static Pane init()
	{
		//Initialize the amount needed to move across the board
		int tempx = printMap.colsCompare-14;
		int tempy = printMap.rowsCompare-7;
		
		
		horizontalScrollVariable = (float) ((double)1/tempx);
		verticalScrollVariable = (float) ((double)1/tempy);
		
		System.out.println("vertscrollvar: " + tempy);
		System.out.println("vertscrollvar: " + verticalScrollVariable);
		
		Rectangle sprite = null;
		cursor = new Image("application\\tilesets\\cursor\\cursor.gif");
		
		sprite = new Rectangle(printMap.horizontal * cursorX, printMap.vertical * cursorY, printMap.horizontal, printMap.vertical);
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
		printMap.shapes.get(cursorPosition).relocate(printMap.horizontal * Adventure.playerListCurrent.get(tempPos).getMapX(), printMap.vertical * Adventure.playerListCurrent.get(tempPos).getMapY());
		cursorX = Adventure.playerListCurrent.get(tempPos).getMapX();
		cursorY = Adventure.playerListCurrent.get(tempPos).getMapY();
	}
	
	
	
	//Referred to in printMap()
	//			V
	//Map scroll movement + Cursor and arrow movement all done here.
	//Listeners check for map boundaries using location of cursor/arrow at the time and multiply them by the decided scroll vars.
	
	public static void moveUp() 
    {
		if(cursorY > 0) {
			if(canMove == true) {
				cursorY--;
				System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
				printMap.shapes.get(cursorPosition).relocate(printMap.horizontal * cursorX, printMap.vertical * cursorY);
				
				if(cursorY * verticalScrollVariable < printMap.scrollLayer.getVvalue() + (verticalScrollVariable * 3)) {
					printMap.scrollLayer.setVvalue(printMap.scrollLayer.getVvalue() - verticalScrollVariable);
				}
			}
			
			if(Arrow.isOn == true && Arrow.arrowY > 0) {
				Arrow.arrowY--;
				printMap.shapes.get(Arrow.arrowTipPosition).relocate(printMap.horizontal * Arrow.arrowX, printMap.vertical * Arrow.arrowY);
				if(moveSequence.size() != 0) {
					if (moveSequence.get(moveSequence.size() - 1) != "down") {
						moveSequence.add("up");
						printMap.postArrow("post", "up", Arrow.arrowX,Arrow.arrowY);
					} else {
						moveSequence.remove(moveSequence.size() - 1);
						printMap.postArrow("hide", "up", Arrow.arrowX,Arrow.arrowY);
					}
				} else {
					moveSequence.add("up");
				}

				if(Arrow.arrowY * verticalScrollVariable < printMap.scrollLayer.getVvalue() + (verticalScrollVariable * 3)) {
				printMap.scrollLayer.setVvalue(printMap.scrollLayer.getVvalue() - verticalScrollVariable);
				}
			}
		}
    }
	
	public static void moveDown() 
    {
		if(cursorY < printMap.rowsCompare) {
			if(canMove == true) {
				cursorY++;
				System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
				printMap.shapes.get(cursorPosition).relocate(printMap.horizontal * cursorX, printMap.vertical * cursorY);
				
				if(cursorY * verticalScrollVariable > printMap.scrollLayer.getVvalue() + (verticalScrollVariable * 4)) {
					printMap.scrollLayer.setVvalue(printMap.scrollLayer.getVvalue() + verticalScrollVariable);
				}
			}
			
			if(Arrow.isOn == true && Arrow.arrowY < printMap.rowsCompare) {
				Arrow.arrowY++;
				printMap.shapes.get(Arrow.arrowTipPosition).relocate(printMap.horizontal * Arrow.arrowX, printMap.vertical * Arrow.arrowY);
				if(moveSequence.size() != 0) {
					if (moveSequence.get(moveSequence.size() - 1) != "up") {
						moveSequence.add("down");
						printMap.postArrow("post", "down", Arrow.arrowX,Arrow.arrowY);
					} else {
						moveSequence.remove( moveSequence.size() - 1);
						printMap.postArrow("hide", "down", Arrow.arrowX,Arrow.arrowY);
					}
				} else {
					moveSequence.add("down");
				}

				if(Arrow.arrowY * verticalScrollVariable > printMap.scrollLayer.getVvalue() + (verticalScrollVariable * 4)) {
					printMap.scrollLayer.setVvalue(printMap.scrollLayer.getVvalue() + verticalScrollVariable);
				}
			}
		}
    }
	
	public static void moveLeft() 
    {
		if(cursorX > 0) {
			if(canMove == true) {
				cursorX--;
				System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
				printMap.shapes.get(cursorPosition).relocate(printMap.horizontal * cursorX, printMap.vertical * cursorY);
				
				if(cursorX * horizontalScrollVariable < printMap.scrollLayer.getHvalue() + (horizontalScrollVariable * 3)) {
					printMap.scrollLayer.setHvalue(printMap.scrollLayer.getHvalue() - horizontalScrollVariable);
				}
			}
			
			if(Arrow.isOn == true && Arrow.arrowX > 0) {
				Arrow.arrowX--;
				printMap.shapes.get(Arrow.arrowTipPosition).relocate(printMap.horizontal * Arrow.arrowX, printMap.vertical * Arrow.arrowY);
				if(moveSequence.size() != 0) {
					if (moveSequence.get(moveSequence.size() - 1) != "right") {
						moveSequence.add("left");
						printMap.postArrow("post", "left", Arrow.arrowX,Arrow.arrowY);
					} else {
						moveSequence.remove( moveSequence.size() - 1);
						printMap.postArrow("hide", "left", Arrow.arrowX,Arrow.arrowY);
					}
				} else {
					moveSequence.add("left");
				}
				
				if(Arrow.arrowX * horizontalScrollVariable < printMap.scrollLayer.getHvalue() + (horizontalScrollVariable * 3)) {
					printMap.scrollLayer.setHvalue(printMap.scrollLayer.getHvalue() - horizontalScrollVariable);
				}
			}
		}
    }
	
	public static void moveRight() 
    {
		if(cursorX < printMap.colsCompare) {
			if(canMove == true) {
				cursorX++;
				System.out.println("CursorX: " + cursorX + " ,CursorY: " + cursorY );
				printMap.shapes.get(cursorPosition).relocate(printMap.horizontal * cursorX, printMap.vertical * cursorY);
				
				if(cursorX * horizontalScrollVariable > printMap.scrollLayer.getHvalue() + (horizontalScrollVariable * 11)) {
					printMap.scrollLayer.setHvalue(printMap.scrollLayer.getHvalue() + horizontalScrollVariable);
				}
			}
			
			if(Arrow.isOn == true && Arrow.arrowX < printMap.colsCompare) {
				Arrow.arrowX++;
				printMap.shapes.get(Arrow.arrowTipPosition).relocate(printMap.horizontal * Arrow.arrowX, printMap.vertical * Arrow.arrowY);
				if(moveSequence.size() != 0) {
					if (moveSequence.get(moveSequence.size() - 1) != "left") {
						moveSequence.add("right");
						printMap.postArrow("post", "right", Arrow.arrowX,Arrow.arrowY);
					} else {
						moveSequence.remove( moveSequence.size() - 1);
						printMap.postArrow("hide", "right", Arrow.arrowX,Arrow.arrowY);
					}
				} else {
					moveSequence.add("right");
				}
				
				if(Arrow.arrowX * horizontalScrollVariable > printMap.scrollLayer.getHvalue() + (horizontalScrollVariable * 11)) {
					printMap.scrollLayer.setHvalue(printMap.scrollLayer.getHvalue() + horizontalScrollVariable);
				}
			}
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
