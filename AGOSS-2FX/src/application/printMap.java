package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


public class printMap {
	static AnchorPane root = new AnchorPane();
	static ScrollPane scrollLayer = new ScrollPane();
	static Pane gameLayer = new Pane();
	static Pane moveSpaceMenu = new Pane();
	static Pane gameInterface = new Pane();
	static Pane arrowLayer = new Pane();
	static int rowsCompare, colsCompare;
	public static double horizontal = 0 , vertical = 0;
	static List <Shape> shapes = new ArrayList<>();
	static List <Shape> tempArrow = new ArrayList<>();
	public static int currentPlayerHover = 0;
	public static boolean isOnPlayer = false;
	   public static void mapPrinter(GridSpace[][] map, int rows, int cols) throws InterruptedException, IOException {
		   colsCompare = rows-1;
		   rowsCompare = cols-1;
		   
		   System.out.println("Amount of cols: " + colsCompare);
		   System.out.println("Amount of rows: " + rowsCompare);
		   Scene scene = new Scene(root, 1280, 720);
		   
		   //Setup multiple video resolutions
		   System.out.println("ResType" + OptionsSceneController.resType);
	        if (OptionsSceneController.resType == "1280x720" || OptionsSceneController.resType == "") {
	        	Main.window.setWidth(1280);
	        	Main.window.setHeight(720);
		        
		        vertical = 64;
		        horizontal = vertical;
		        
	        }else if(OptionsSceneController.resType == "1920x1080") {
	        	Main.window.setWidth(1920);
	        	Main.window.setHeight(1080);
	        	
		        vertical = 48;
		        horizontal = vertical;
	        }
	        
	        //Adds a variable padding to the left side of the screen
	        scrollLayer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        scrollLayer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        scrollLayer.prefHeight(512);
	        scrollLayer.setMaxHeight(512);
	        scrollLayer.setMinHeight(512);
	        scrollLayer.prefWidth(960);
	        scrollLayer.setMaxWidth(960);
	        scrollLayer.setMinWidth(960);
	        scrollLayer.setLayoutX(vertical*3);
	        Rectangle rect = null;
	      
			//Print map
	        for (int y=0; y < cols; y++) {
			    for (int x=0; x < rows; x++) {
	                rect = new Rectangle(horizontal * x, vertical * y, horizontal, vertical);
	                rect.setStroke(new Color(1f,1f,1f,.05f));
	                System.out.println("application\\tilesets\\tilesets\\" + map[x][y].getType() + ".png");
		    		Image img = new Image("application\\tilesets\\tilesets\\" + map[x][y].getType() + ".png");
		    		rect.setFill(new ImagePattern(img));
		    		gameLayer.getChildren().add(rect);
			    }
			    System.out.println();
			}
	        
			//Print map foreground
			spriteLayer(map,rows,cols);
			
			//Setup keypress handlers 
			scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
	            System.out.println("Key pressed");
	            for(int i = 0; i != Adventure.playerListCurrent.size(); i++) {
	    			if(MapCursor.cursorX == Adventure.playerListCurrent.get(i).getMapX() && MapCursor.cursorY == Adventure.playerListCurrent.get(i).getMapY()) {
	    				currentPlayerHover = i;
	    				System.out.println("playerfound in previous tile");
	    				System.out.println(Adventure.playerListCurrent.get(currentPlayerHover).getName());
	    	            System.out.println(currentPlayerHover);
	    			}
	            }
	            
	            if (event.getCode() == KeyCode.UP) {
	                MapCursor.moveUp();
	            }
	            
	            if (event.getCode() == KeyCode.DOWN) {
	            	MapCursor.moveDown();
	            }
	            
	            if (event.getCode() == KeyCode.LEFT) {
	            	MapCursor.moveLeft();
	            }
	            
	            if (event.getCode() == KeyCode.RIGHT) {
	            	MapCursor.moveRight();
	            }
	            
	            if (event.getCode() == KeyCode.ESCAPE) {
	                System.out.println("ESCAPE");
	            }
	            
	            if (event.getCode() == KeyCode.ENTER) {
	            	//Checks and makes sure that the cursor is on the same tile as the player
	            	if(MapCursor.cursorX == Adventure.playerListCurrent.get(currentPlayerHover).getMapX() && MapCursor.cursorY == Adventure.playerListCurrent.get(currentPlayerHover).getMapY()) {
	            		isOnPlayer = true;
	            		System.out.println(isOnPlayer);
	            	}else {
	            		isOnPlayer = false;
	            	}
	            	
	            	if(Arrow.isOn == true) {
	            		moveSprite(null, Adventure.playerListCurrent.get(currentPlayerHover), MapCursor.moveSequence);
	            		Arrow.toggleArrow(false);
	            		MapCursor.resetCursor(currentPlayerHover);
	            	}else{
	            		if(Adventure.playerListCurrent.get(currentPlayerHover).getHasMoved() == false && isOnPlayer == true && MapCursor.canMove == true) {
	            			MapCursor.checkSpace(currentPlayerHover);
	            		}
	            	}
	            }	
	            event.consume();
	        });

			//Set the scene
	        Main.window.setScene(scene);
	         
	   }
  
	   public static void spriteLayer(GridSpace[][] map, int rows, int cols) throws InterruptedException, IOException {
		   Pane spriteLayer = new Pane(); 
	       Rectangle sprite = null;
	       
	       for (int y=0; y < rows; y++) {
			    for (int x=0; x < cols; x++) {
			    	for (int i = 0; i < Adventure.playerListCurrent.size(); i++) {
				    	if(x == Adventure.playerListCurrent.get(i).getMapX() && y == Adventure.playerListCurrent.get(i).getMapY()) {
				    		sprite = new Rectangle(horizontal * x, vertical * y, horizontal, vertical);
				    		sprite.setFill(new ImagePattern(Adventure.playerListCurrent.get(i).getImg()));
				    		//Sets the Sprites ID 
				    		sprite.setId(Adventure.playerListCurrent.get(i).getName());
				    		Adventure.playerListCurrent.get(i).setMapId(sprite.getId());
				    		
				    		//Adds the sprite to the layer and arraylist
				    		System.out.println("Sprite Id: " + sprite.getId());
				    		shapes.add(sprite);
				    		spriteLayer.getChildren().add(sprite);
				    	}
			    	}
			    	
			    	for (int i = 0; i < mapInitialization.mobList.size(); i++) {
				    	if(x == mapInitialization.mobList.get(i).getMapX() && y == mapInitialization.mobList.get(i).getMapY()) {
				    		sprite = new Rectangle(horizontal * x, vertical * y, horizontal, vertical);
				    		sprite.setFill(new ImagePattern(mapInitialization.mobList.get(i).getImg()));
				    		
				    		//Sets the Sprites ID 
				    		sprite.setId(mapInitialization.mobList.get(i).getName());
				    		mapInitialization.mobList.get(i).setMapId(sprite.getId());
				    		
				    		//Adds the sprite to the layer and arraylist
				    		System.out.println("Sprite Id: " + sprite.getId());
				    		shapes.add(sprite);
				    		spriteLayer.getChildren().add(sprite);
				    	}
			    	}
			    }
	       }
	       
	       Group groupLayers = new Group();
		   
	       //Adds gameLayer to the bottom of te list
	       groupLayers.getChildren().add(gameLayer);
	       
	       //Adds sprites ontop
	       groupLayers.setLayoutX(vertical*3);
	       groupLayers.getChildren().add(spriteLayer);
		   
		   //Loads cursor layer and pastes icon
		   Pane cursorLayer = MapCursor.init();
		   groupLayers.getChildren().add(cursorLayer);
		   
		   //Loads the arrow layer
		   arrowLayer = Arrow.init();
		   groupLayers.getChildren().add(arrowLayer);

		   //Adds all the layers to the scrollLayer
	       scrollLayer.setContent(groupLayers);
	       //Adds scrollLayer to root
	       root.getChildren().add(scrollLayer);


	       //Loads the Interface and adds it ontop of game layers
		   Group UILayer = new Group();
		   UILayer = Interface.initialize();
	       root.getChildren().add(UILayer);

	       
		   //Loads the MovespaceMenu
		   moveSpaceMenu =  FXMLLoader.load(printMap.class.getResource("scenes\\moveSpaceMenu.fxml"));
		   moveSpaceMenu.setLayoutX(vertical*3);
		   root.getChildren().add(moveSpaceMenu);
		   
		   //Setup Arrowcursor
		   printMap.shapes.get(Arrow.arrowTipPosition).setVisible(false);
		   moveSpaceMenu.setVisible(false);
		   
		   
	   }
	   
	   static int pos = 0;
	   public static int oldX, oldY;
	   
	   public static void moveSprite( Mob1 mob, Player player, ArrayList<String> directions) {
		   MapCursor.canMove = false;
		   pos = 0;
		   String id = null;
		   
		     //Setting up the path   
		     Path path = new Path();  
		     PathTransition pathTransition = new PathTransition();  
		     
		     //Set duration to be tile based
		     pathTransition.setDuration(Duration.millis(directions.size()*250));  
		     
		     //Display path
		     //root.getChildren().add(path);
		     
		   if(player == null) {
			   id = mob.getMapId();
			   path.getElements().add(new MoveTo(horizontal * mob.getMapX() + (horizontal/2), vertical * mob.getMapY() + (vertical/2)));
			   
		   }else if (mob == null) {
			   id = player.getMapId();
			   path.getElements().add(new MoveTo((horizontal * player.getMapX()) + (horizontal/2), vertical * player.getMapY() + (vertical/2)));
			   oldX = player.getMapX();
			   oldY = player.getMapY();
		   }
		   
		   //Clean the id for shape search
		   String cleanID = id.replaceAll("\\d","");
		   
		   //Finds the sprite that needs to be moved by initially grabbing its ID
		   for(int i = 0; i < shapes.size(); i++) {
			   if(shapes.get(i).getId() == id) {
				   System.out.println("Moving character: " + shapes.get(i).getId());
				   pos = i;
				   pathTransition.setNode(shapes.get(pos));  
			   }  
		   }
		   
		   shapes.get(pos).setLayoutX(0);
		   shapes.get(pos).setLayoutY(0);
		   //Depending on direction, move and change sprite
		   for (int i = 0; i < directions.size(); i++) {
			   if(directions.get(i) == "left") {
				   try {
					   Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_left.gif");
					   shapes.get(pos).setFill(new ImagePattern(img));
				   } catch (Exception e) {
					   System.out.println("Image not found");
				   }	
				   
				   System.out.println(directions.get(i));
				   if(player == null) {
					   mob.setMapX(mob.getMapX()-1);
					   path.getElements().add (new LineTo (horizontal * mob.getMapX() + (horizontal/2), vertical * mob.getMapY() + (vertical/2))); 
 	
				   }else if (mob == null) {
					   player.setMapX(player.getMapX()-1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((horizontal * player.getMapX()) + (horizontal/2), vertical * player.getMapY() + (vertical/2)));  
				   }
			   }

			   if(directions.get(i) == "right") {
				   try {
					   Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_right.gif");
					   shapes.get(pos).setFill(new ImagePattern(img));
				   } catch (Exception e) {
					   System.out.println("Image not found");
				   }	
				   System.out.println(directions.get(i));
				   if(player == null) {
					   mob.setMapX(mob.getMapX()+1);
					   path.getElements().add (new LineTo (horizontal * mob.getMapX() + (horizontal/2), vertical * mob.getMapY() + (vertical/2))); 	
				   }else if (mob == null) {
					   player.setMapX(player.getMapX()+1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((horizontal * player.getMapX()) + (horizontal/2), vertical * player.getMapY() + (vertical/2)));   
				   }
			   }

			   if(directions.get(i) == "up") {
				   try {
					   Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_up.gif");
					   shapes.get(pos).setFill(new ImagePattern(img));
				   } catch (Exception e) {
					   System.out.println("Image not found");
				   }	
				   System.out.println(directions.get(i));
				   if(player == null) {
					   mob.setMapY(mob.getMapY()-1);
					   path.getElements().add (new LineTo (horizontal * mob.getMapX() + (horizontal/2), vertical * mob.getMapY() + (vertical/2))); 	
				   }else if (mob == null) {
					   player.setMapY(player.getMapY()-1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((horizontal * player.getMapX()) + (horizontal/2), vertical * player.getMapY() + (vertical/2)));  
				   }
			   }

			   if(directions.get(i) == "down") {
				   try {
					   Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_down.gif");
					   shapes.get(pos).setFill(new ImagePattern(img));
				   } catch (Exception e) {
					   System.out.println("Image not found");
				   }
				   System.out.println(directions.get(i));
				   if(player == null) {
					   mob.setMapY(mob.getMapY()+1);
					   path.getElements().add (new LineTo (horizontal * mob.getMapX() + (horizontal/2), vertical * mob.getMapY() + (vertical/2))); 	
				   }else if (mob == null) {
					   player.setMapY(player.getMapY()+1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((horizontal * player.getMapX()) + (horizontal/2), vertical * player.getMapY() + (vertical/2)));
				   }
			   }
		   }

		   //setting path for the path transition   
		   pathTransition.setPath(path);
		   //Playing path transition   
		   pathTransition.play();  
		   resetImg(id);
		   
		   //Clear the follow arrow
		   printMap.postArrow("clear", "null", 0, 0);
		   
		   //Presents the moveSpaceMenu after animation is finished
		   Timer myTimer = new Timer();
	          myTimer.schedule(new TimerTask(){
	            @Override
	            public void run() {
	     	       //Sets the moved player to HasMoved true
	     		   if(mob == null) {
		            	System.out.println("Im setting to true: MENU");
		            	MoveSpaceMenu.showMenu(true, player);
		            	System.out.println("Im setting to true");
		            	player.setHasMoved(true);
	     		   }
	            }
	          }, directions.size()*250);
	          
		   return;
	   }
	   
	   public static void resetMove(int shapePos, String tempName) {
		   int playerPos = 0;
		   
		   for(int i = 0; i < Adventure.playerListCurrent.size(); i++) {
			   if(Adventure.playerListCurrent.get(i).getMapId() == tempName) {
				   playerPos = i;
				   System.out.println("Reseting: " + Adventure.playerListCurrent.get(i).getName());
			   }  
			}
		   
		   //Reset the players location
		   Adventure.playerListCurrent.get(playerPos).setMapX(oldX);
		   Adventure.playerListCurrent.get(playerPos).setMapY(oldY);
		   
		   //Reset and relocate the sprite back to its original position
		   shapes.get(shapePos).setTranslateX(0);
		   shapes.get(shapePos).setTranslateY(0);
		   
		   shapes.get(shapePos).relocate(0,0);
		   
		   shapes.get(shapePos).setTranslateX(horizontal * oldX);
		   shapes.get(shapePos).setTranslateY(vertical * oldY);

		   //Reset player positions
		   Adventure.playerListCurrent.get(playerPos).setHasMoved(false);

		   MapCursor.resetCursor(playerPos);
		   
		   //Reset olds
		   oldX = 0;
		   oldY = 0;
		   playerPos = 0;
	   }
  
	   
	   //Posts an arrow that follows the arrowCursor
	   public static void postArrow(String type, String direction, int x, int y) {
		   Image img = new Image("application\\tilesets\\unknown.png");
		   switch(type) {
		   		case "post":
		   			
		   			//Determines which arrow path to use
		   			switch(direction) {
		   				case "up": case "down":
		   					img = new Image("application\\tilesets\\arrow\\arrow_path_vertical.png");
	
		   					//Makes sure the arrow is behind the cursor
		   					if(direction == "up") {
		   						y++;
		   					}else {
		   						y--;
		   					}
		   					
		   					break;

		   				case "left": case "right":
		   					img = new Image("application\\tilesets\\arrow\\arrow_path_horizontal.png");
		   					
		   					//Makes sure the arrow is behind the cursor
		   					if(direction == "right") {
		   						x--;
		   					}else {
		   						x++;
		   					}
		   					
		   					break;
		   			}
		   			
		   			//Catches corners coming from horizontal plain
   					System.out.println(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2));
   					if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "right" && direction == "up") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_left_up.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "right" && direction == "down") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_left_down.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "left" && direction == "down") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_right_down.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "left" && direction == "up") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_right_up.png");
   					}
   					
   					//Catches corners coming from vertical plain
   					if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "down" && direction == "left") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_left_up.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "down" && direction == "right") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_right_up.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "up" && direction == "left") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_left_down.png");
   					} else if(MapCursor.moveSequence.get(MapCursor.moveSequence.size() -2) == "up" && direction == "right") {
   						img = new Image("application\\tilesets\\arrow\\arrow_path_right_down.png");
   					}
   					
		   			//Posts the arrow
		   			Rectangle arrowLine = new Rectangle(horizontal * x ,vertical * y, horizontal, vertical);
		   			arrowLine.setFill(new ImagePattern(img));
		   			arrowLayer.getChildren().add(arrowLine);
		    		tempArrow.add(arrowLine);
		   			break;
		   			
		   		case "hide":
		   			System.out.println("moveSize" + MapCursor.moveSequence.size());
		   			tempArrow.get(tempArrow.size() - 1).setVisible(false);
		    		tempArrow.remove(tempArrow.size() - 1);
		   			break;
		   			
		   		case "clear":
		   			//Hides the list
		   			System.out.println("CLEARING ARROWLIST");
		   			for(int i = 0; i < tempArrow.size(); i++) {
		   				//Removes the shapes from the layer
		   				arrowLayer.getChildren().remove(tempArrow.get(i));
		   			}
		   			tempArrow.clear();
		   			break;		
		   }
	   }
	   
	   public static void resetImg(String Id) {
		 //Searches and resets the sprite icon - Enemylist
 		   for(int i = 0; i < mapInitialization.mobList.size(); i++) {
			   if(mapInitialization.mobList.get(i).getMapId() == Id) {
				   System.out.println("Resetting: " + Id);
				   shapes.get(pos).setFill(new ImagePattern(mapInitialization.mobList.get(i).getImg()));
			   }  
			}
 		   
 		   //Searches and resets the sprite icon - playerlist
 		  for(int i = 0; i < Adventure.playerListCurrent.size(); i++) {
			   if(Adventure.playerListCurrent.get(i).getMapId() == Id) {
				   System.out.println("Resetting: " + Id);
				   shapes.get(pos).setFill(new ImagePattern(Adventure.playerListCurrent.get(i).getImg()));
			   }  
 		  }
	   }
}
