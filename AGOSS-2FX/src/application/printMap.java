package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
	static Pane moveSpaceMenu = new Pane();
	public static double horizontalSetter = 0, verticalSetter = 0, horizontal = 0 , vertical = 0;
	static List <Shape> shapes = new ArrayList<>();
	public static int currentPlayerHover = 0;
	
	   public static void mapPrinter(GridSpace[][] map, int rows, int cols) throws InterruptedException, IOException {
	        
	        Scene scene = new Scene(root, Main.window.getHeight(), Main.window.getWidth());
	        horizontalSetter = (Main.window.getWidth()/cols);
	        verticalSetter = (Main.window.getHeight()/rows);
	        
	        horizontal = 32*(horizontalSetter/32); 
	        vertical = 32*(verticalSetter/32);
	        
	        Rectangle rect = null;
	      
			//Print map
			for (int y=0; y < rows; y++) {
			    for (int x=0; x < cols; x++) {
	                rect = new Rectangle(vertical * x, horizontal * y, vertical, horizontal);
	                rect.setStroke(new Color(1f,1f,1f,.05f));
	                System.out.println("application\\tilesets\\tilesets\\" + map[x][y].getType() + ".png");
		    		Image img = new Image("application\\tilesets\\tilesets\\" + map[x][y].getType() + ".png");
		    		rect.setFill(new ImagePattern(img));
	                root.getChildren().add(rect);
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
	            	if(Arrow.isOn == true) {
	            		printMap.moveSprite(null, Adventure.playerListCurrent.get(currentPlayerHover), MapCursor.moveSequence);
	            		Arrow.toggleArrow(false);
	            		MapCursor.resetCursor();
	            	}else{
	            		if(Adventure.playerListCurrent.get(currentPlayerHover).getHasMoved() == false) {
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
				    		sprite = new Rectangle(vertical * x, horizontal * y, vertical, horizontal);
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
				    		sprite = new Rectangle(vertical * x, horizontal * y, vertical, horizontal);
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
		   root.getChildren().add(spriteLayer);
		   
		   //Loads cursor layer and pastes icon
		   Pane cursorLayer = MapCursor.init();
		   root.getChildren().add(cursorLayer);
		   
		   //Loads the arrow layer
		   Pane arrowLayer = Arrow.init();
		   root.getChildren().add(arrowLayer);
		   
		   //Loads the MovespaceMenu
		   moveSpaceMenu =  FXMLLoader.load(printMap.class.getResource("scenes\\moveSpaceMenu.fxml"));
		   root.getChildren().add(moveSpaceMenu);
		   

		   
		   printMap.shapes.get(Arrow.arrowTipPosition).setVisible(false);
		   moveSpaceMenu.setVisible(false);
		   
		   
	   }
	   
	   static int pos = 0;
	   
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
			   path.getElements().add(new MoveTo(vertical * mob.getMapX() + (vertical/2), horizontal * mob.getMapY() + (horizontal/2)));
			   
		   }else if (mob == null) {
			   id = player.getMapId();
			   path.getElements().add(new MoveTo((vertical * player.getMapX()) + (vertical/2), horizontal * player.getMapY() + (horizontal/2)));
			   
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
					   path.getElements().add (new LineTo (vertical * mob.getMapX() + (vertical/2), horizontal * mob.getMapY() + (horizontal/2))); 
 	
				   }else if (mob == null) {
					   player.setMapX(player.getMapX()-1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((vertical * player.getMapX()) + (vertical/2), horizontal * player.getMapY() + (horizontal/2)));  
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
					   path.getElements().add (new LineTo (vertical * mob.getMapX() + (vertical/2), horizontal * mob.getMapY() + (horizontal/2))); 	
				   }else if (mob == null) {
					   player.setMapX(player.getMapX()+1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((vertical * player.getMapX()) + (vertical/2), horizontal * player.getMapY() + (horizontal/2)));   
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
					   path.getElements().add (new LineTo (vertical * mob.getMapX() + (vertical/2), horizontal * mob.getMapY() + (horizontal/2))); 	
				   }else if (mob == null) {
					   player.setMapY(player.getMapY()-1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((vertical * player.getMapX()) + (vertical/2), horizontal * player.getMapY() + (horizontal/2)));  
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
					   path.getElements().add (new LineTo (vertical * mob.getMapX() + (vertical/2), horizontal * mob.getMapY() + (horizontal/2))); 	
				   }else if (mob == null) {
					   player.setMapY(player.getMapY()+1);
					   System.out.println(player.getMapX() + ", " + player.getMapY());
					   path.getElements().add (new LineTo ((vertical * player.getMapX()) + (vertical/2), horizontal * player.getMapY() + (horizontal/2)));
				   }
			   }
		   }


		   //setting path for the path transition   
		   pathTransition.setPath(path);
		   //Playing path transition   
		   pathTransition.play();  
		   resetImg(id);
		   
		   //Presents the moveSpaceMenu after animation is finished
		   Timer myTimer = new Timer();
	          myTimer.schedule(new TimerTask(){
	            @Override
	            public void run() {
	            	System.out.println("Im setting to true: MENU");
	            	MoveSpaceMenu.showMenu(true, player);
	            	

	     	       //Sets the moved player to HasMoved true
	     		   if(mob == null) {
	     			   System.out.println("Im setting to true");
	     			   player.setHasMoved(true);
	     		   }
	     		   
	            	
	            }
	          }, directions.size()*250);

		   //Clears the previous move sequence
		   MapCursor.moveSequence.clear();

		   //Allow movement and return;
		   MapCursor.canMove = true;

		   return;
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
