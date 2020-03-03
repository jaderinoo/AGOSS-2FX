package application;
import java.util.concurrent.ThreadLocalRandom;

import javafx.scene.image.Image;

public class Player {
	
		String name;

	    int strength;
		int agility;
		int armor;
		int maxHp;
		int special;
		int level;
		int exp;
		int currentHp;
		int mapX;
		int mapY;
		int type;
		int posCounter;
		String mapId;
		Image charImg;
		playerBags playerBag;
		boolean hasMoved;
		
		public Player(String name, int strength,int agility,int armor,int maxHp,int special, int level, int exp, int currentHp, int type, playerBags playerBag) {
	    	
	    	//Use this as a ledger for player.txt 
	    	this.name = name;
	    	this.strength = strength;
	    	this.agility = agility;
	    	this.armor = armor;
	    	this.maxHp = maxHp;
	    	this.special = special;
	    	this.level = level;
	    	this.exp = exp;
	    	this.currentHp = currentHp;
	    	this.playerBag = playerBag;
	    	this.type = type;
	    	this.hasMoved = false;
	    	setImg(name);
	   }
		
		public void setImg(String name) {
			if(Updater.playerList.size() == 0) {
				this.charImg = new Image("application\\tilesets\\MainCharacter\\MainCharacter.png");	
			}else {
				this.charImg = new Image("application\\tilesets\\" + name + "\\" + name + ".png");
			}
		}

		public void setHasMoved(boolean decider) {
			this.hasMoved = decider;
		}
		
		public boolean getHasMoved() {
			return this.hasMoved;
		}
		
		public void setMapId(String Id) {
			this.mapId = Id;
		}
		
		public String getMapId() {
			return this.mapId;
		}
		
		public Image getImg() {
			return charImg;
		}
		
		public String getName() {
	        return name;
	    }
		
	    public int getStrength() {
	        return strength;
	    }
	    
	    public void setStrength(int charCreVal) {
	        this.strength = charCreVal;
	    }
	    
	    public int getAgility() {
	        return agility;
	    }
	    
	    public void setAgility(int charCreVal) {
	        this.agility = charCreVal;
	    }
	    
	    public int getArmor() {
	        return armor;
	    }
	    
	    public void setArmor(int charCreVal) {
	        this.armor = charCreVal;
	    }
	    
	    public int getMaxHp() {
	        return maxHp;
	    }
	    
	    public void setMaxHp(int charCreVal) {
	        this.maxHp = charCreVal;
	        this.currentHp = maxHp;
	    }
	    
	    public int getSpecial() {
	        return special;
	    }
	    
	    public void setSpecial(int charCreVal) {
	        this.special = charCreVal;
	    }
	    
	    public int getLevel() {
	        return level;
	    }
	    
	    public int getExp() {
	        return exp;
	    }

	    public int getCurrentHp() {
	    	return currentHp;
	    }
	    
	    public int getType() {
	    	return type;
	    }
	    
	    
		public int addStrength(int i) {
			for(int x = 0; x != i; x++) {
				this.strength++;
			}
			return this.strength;
		}
		
		public int removeStrength(int i) {
			for(int x = 0; x != i; x++) {
				this.strength--;
			}
			return this.strength;
		}
		
		public int addAgility() {
			this.agility++;
			return this.agility;
		}
		
		public int addArmor(int i) {
			for(int x = 0; x != i; x++) {
				this.armor++;
			}
			return this.armor;
		}
		
		public int removeArmor(int i) {
			for(int x = 0; x != i; x++) {
				this.armor--;
			}
			return this.armor;
		}
		
		public int addmaxHp() {
			this.maxHp += 10;
			return this.maxHp;
		}
		
		public int addSpecial() {
			this.special += 2;
			return this.special;
		}
		
		//
		// Map location Stats
		//
		public int setMapX(int x) {
			this.mapX = x;
			return this.mapX;
		}
		
		public int setMapY(int y) {
			this.mapY = y;
			return this.mapY;
		}
		
		//get map locations
		public int getMapX() {
			return this.mapX;
		}
		
		public int getMapY() {
			return this.mapY;
		}
		
		
		public void levelup(Mob1 attacker) {
			//Reward EXP
			this.exp = this.exp + attacker.getLevel()*4;
			System.out.println("You gained "+ attacker.getLevel()*4 +" EXP!");
			//If the players exp is = to its current level*2
			if(this.exp >= this.level*50) {
				//frame.console.append("\t\t~LEVEL UP!~");
				this.exp = 0;
				this.level++;
				int x = 0;
				//Randomly selects 3 stats to boost on level up
			    while(x != 3) {
					switch(ThreadLocalRandom.current().nextInt(1, 5)) {
					case 1:
						this.addStrength(1);
						x++;
						//frame.console.append("\t\tStrength +1");
						break;
						
					case 2:
						this.addAgility();
						x++;
						//frame.console.append("\t\tAgility +1");
						break;
						
					case 3:
						this.addArmor(1);
						x++;
						//frame.console.append("\t\tArmor +1");
						break;
						
					case 4:
						this.addmaxHp();
						x++;
						//frame.console.append("\t\tMaxHp +1");
						break;
						
					case 5:
						this.addSpecial();
						x++;
						//frame.console.append("\t\tSpecial +1");
						break;
					}
				}
			}
		}
}

