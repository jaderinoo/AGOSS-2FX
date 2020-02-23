package application;

import javafx.scene.image.Image;

public class Mob1 {

	    int strength;
		int agility;
		int armor;
		int maxHp;
		int special;
		int level;
		int currentHp;
		int getMapY;
		int getMapX;
		char type;
		String mapId;
		Image imgMob;
		String name;

		public Mob1(String name, int strength,int agility,int armor,int maxHp,int special,int level,int currentHp,int getMapX, int getMapY, char type) {

	    	this.getMapX = getMapX;
	    	this.getMapY = getMapY;
	    	this.name = name;
	    	this.strength = strength;
	    	this.agility = agility;
	    	this.armor = armor;
	    	this.maxHp = maxHp;
	    	this.special = special;
	    	this.level = level;
	    	this.currentHp = maxHp;
	    	this.type = type;
	   }
		
		
		public void setImg(String name) {
			this.imgMob = new Image("application\\tilesets\\" + name + ".gif");
		}
		
		public Image getImg() {
			return imgMob;
		}
	
		public void setMapId(String Id) {
			this.mapId = Id;
		}
		
		public String getMapId() {
			return this.mapId;
		}
		
		public char getType() {
	        return type;
	    }
		
		public int getMapY() {
	        return getMapY;
	    }
		
		public int getMapX() {
	        return getMapX;
	    }
		
		public int setMapX(int x) {
			this.getMapX = x;
			return this.getMapX;
		}
		
		public int setMapY(int y) {
			this.getMapY = y;
			return this.getMapY;
		}
		
		public String getName() {
	        return name;
	    }
		
	    public int getStrength() {
	        return strength;
	    }
	    
	    public int getAgility() {
	        return agility;
	    }
	    
	    public int getArmor() {
	        return armor;
	    }
	    
	    public int getMaxHp() {
	        return maxHp;
	    }
	    
	    public int getSpecial() {
	        return special;
	    } 
	    
	    public int getLevel() {
	        return level;
	    } 
	    
	    public int getCurrentHp() {
	    	return currentHp;
	    }
	    
}

