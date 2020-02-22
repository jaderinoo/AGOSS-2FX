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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class printMap {
	static AnchorPane root = new AnchorPane();
	static double horizontalSetter = 0;
	static double verticalSetter = 0;
	static List <Shape> shapes = new ArrayList<>();
	
	   public static void mapPrinter(GridSpace[][] map, int rows, int cols) {
	        
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
	                System.out.println("application\\tilesets\\" + map[x][y].getType() + ".png");
		    		Image img = new Image("application\\tilesets\\" + map[x][y].getType() + ".png");
		    		rect.setFill(new ImagePattern(img));
	                root.getChildren().add(rect);
			    }
			    System.out.println();
			}
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
	   }
	   
	   static int pos = 0;
	   public static void moveSprite(String Id, String[] directions, int x, int y) {
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
			    private int mapX = x;
			    private int mapY = y;
			    private int i;
		        @Override
		        public void run() { 
		            if (seconds < MAX_SECONDS) {
		                System.out.println("Seconds = " + seconds);
		                seconds++;

		                if(directions[i] == "left") {
		                	setMapX(getMapX() - 1);
		                }
		                
		                if(directions[i] == "right") {
		                	setMapX(getMapX() + 1);
		                }
		                
		                if(directions[i] == "up") {
		                	setMapY(getMapY() - 1);
		                }
		                
		                if(directions[i] == "down") {
		                	setMapY(getMapY() + 1);
		                }
		                
		                i++;
		                System.out.println("X = " + getMapX() + "Y = " + getMapY());
		                shapes.get(pos).relocate(horizontal*getMapX(),vertical*getMapY());
		            } else {
		                // stop the timer
		                cancel();
		            }
		        }
				public int getMapX() {
					return mapX;
				}
				public void setMapX(int mapX) {
					this.mapX = mapX;
				}
				public int getMapY() {
					return mapY;
				}
				public void setMapY(int mapY) {
					this.mapY = mapY;
				}
		    };
		    timer.schedule(task, 0, 1000);
	   }
}
