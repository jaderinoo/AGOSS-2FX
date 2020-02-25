package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class printMap {
	static AnchorPane root = new AnchorPane();
	public static double horizontalSetter = 0;
	public static double verticalSetter = 0;
	static List <Shape> shapes = new ArrayList<>();
	
	   public static void mapPrinter(GridSpace[][] map, int rows, int cols) throws InterruptedException {
	        
	        Scene scene = new Scene(root, Main.window.getHeight(), Main.window.getWidth());
	        horizontalSetter = (Main.window.getWidth()/cols);
	        verticalSetter = (Main.window.getHeight()/rows);
	        
	        double horizontal = 32*(horizontalSetter/32), vertical = 32*(verticalSetter/32);
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
	            event.consume();
	        });
			
			
			
			//Set the scene
	        Main.window.setScene(scene);
	         
	   }
	   
	   static int seconds = 0;
	   static Timer timer = new Timer();
	   public static void spriteLayer(GridSpace[][] map, int rows, int cols) throws InterruptedException {
		   Pane spriteLayer = new Pane(); 
	       Rectangle sprite = null;
	       double horizontal = 32*(horizontalSetter/32), vertical = 32*(verticalSetter/32);
	       
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
		   
		   
		   
	   }
	   
	   static int pos = 0;
	   public static void moveSprite(String Id, String[] directions, int x, int y) {
		   MapCursor.canMove = false;
		   pos = 0;
		   double horizontal = 32*(horizontalSetter/32), vertical = 32*(verticalSetter/32);
		   TimerTask task; 

		   //Finds the sprite that needs to be moved by initially grabbing its ID
		   for(int i = 0; i < shapes.size(); i++) {
			   if(shapes.get(i).getId() == Id) {
				   System.out.println("Moving character: " + shapes.get(i).getId());
				   pos = i;
			   }  
			}

		   task = new TimerTask() {
		        private final int MAX_SECONDS = directions.length;
			    private double mapX = x;
			    private double mapY = y;
			    private int i;
			    private double mover;
			    String cleanID = Id.replaceAll("\\d","");
		        @Override
		        public void run() { 
		            if (seconds < MAX_SECONDS*20) {
		                System.out.println("Seconds = " + seconds);
		                
		                if(directions[i] == "left") {
		                	if(mover == 0) {
		                		Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_left.gif");
		                		shapes.get(pos).setFill(new ImagePattern(img));
		                	}
		                	setMapX(getMapX() - .05);
		                	mover += .05;
		                }
		                
		                if(directions[i] == "right") {
		                	if(mover == 0) {
		                		Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_right.gif");
		                		shapes.get(pos).setFill(new ImagePattern(img));
		                	}
  		                	setMapX(getMapX() + .05);
  		                	mover += .05;
		                }
		                
		                if(directions[i] == "up") {
		                	if(mover == 0) {
		                		Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_up.gif");
		                		shapes.get(pos).setFill(new ImagePattern(img));
		                	}
		                	setMapY(getMapY() - .05);
  		                	mover += .05;	
		                }
		                
		                if(directions[i] == "down") {
		                	if(mover == 0) {
		                		Image img = new Image("application\\tilesets\\" + cleanID + "\\" + cleanID + "_down.gif");
		                		shapes.get(pos).setFill(new ImagePattern(img));
		                	}
		                	setMapY(getMapY() + .05);
  		                	mover += .05;
		                }
		                
		                if(mover >= .99) {
		                	mover = 0;
			                i++;
		                }
		                seconds++;
		                System.out.println("X" + getMapX());
		                System.out.println("Y" + getMapY());
				        shapes.get(pos).relocate(vertical*getMapX(),horizontal*getMapY());
		                
		            } else {
		            	
		            	resetImg(Id);
		            	MapCursor.resetCursor();
		            	MapCursor.canMove = true;
		                // stop the timer
		                cancel();
		            }
		        }
				public double getMapX() {
					return mapX;
				}
				public void setMapX(double mapX) {
					this.mapX = mapX;
				}
				public double getMapY() {
					return mapY;
				}
				public void setMapY(double mapY) {
					this.mapY = mapY;
				}
		    };
		    timer.schedule(task, 0, 20);
	   }
	   
	   public static void resetImg(String Id) {
		 //Searches and resets the sprite icon - Enemylist
 		   for(int i = 0; i < mapInitialization.mobList.size(); i++) {
			   if(mapInitialization.mobList.get(i).getMapId() == Id) {
				   System.out.println(Id);
				   shapes.get(pos).setFill(new ImagePattern(mapInitialization.mobList.get(1).getImg()));
			   }  
			}
 		   
 		   //Searches and resets the sprite icon - playerlist
 		  for(int i = 0; i < Adventure.playerListCurrent.size(); i++) {
			   if(Adventure.playerListCurrent.get(i).getMapId() == Id) {
				   System.out.println(Id);
				   shapes.get(pos).setFill(new ImagePattern(Adventure.playerListCurrent.get(1).getImg()));
			   }  
			}
	   }
}
