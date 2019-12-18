import java.util.concurrent.ThreadLocalRandom;

public class Player {

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
		String name;

		public Player(String name, int strength,int agility,int armor,int maxHp,int special, int level, int exp, int currentHp) {
	    	
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
		
		//Using items
		public int usePotion() {
			return this.currentHp;
		}
		
		public void useBooster() {
			addStrength(2);
		}
		
		public void resetBooster() {
			removeStrength(2);
		}
		
	    public void setWpnBonus(int weapon) {
			
	    	if(weapon == 0) {
	    		addStrength(0);
	    	}
	    	
	    	if(weapon == 1) {
	    		addStrength(1);
	    	}
	    	
	    	if(weapon == 2) {
	    		addStrength(2);
	    	}
	    	
	    	if(weapon == 3) {
	    		addStrength(3);
	    	}
	    }
	    
	    public void resetWpnBonus(int weapon) {
			
	    	if(weapon == 0) {
	    		removeStrength(0);
	    	}
	    	
	    	if(weapon == 1) {
	    		removeStrength(1);
	    	}
	    	
	    	if(weapon == 2) {
	    		removeStrength(2);
	    	}
	    	
	    	if(weapon == 3) {
	    		removeStrength(3);
	    	}
	    }
	    
	    public void setShieldBonus(int shield) {
			
	    	if(shield == 0) {
	    		addArmor(0);
	    	}
	    	
	    	if(shield == 1) {
	    		addArmor(1);
	    	}
	    	
	    	if(shield == 2) {
	    		addArmor(2);
	    	}
	    	
	    	if(shield == 3) {
	    		addArmor(3);
	    	}
	    }
	    
	    public void resetShieldBonus(int shield) {
			
	    	if(shield == 0) {
	    		removeArmor(0);
	    	}
	    	
	    	if(shield == 1) {
	    		removeArmor(1);
	    	}
	    	
	    	if(shield == 2) {
	    		removeArmor(2);
	    	}
	    	
	    	if(shield == 3) {
	    		removeArmor(3);
	    	}
	    }
		
		public void levelup(Mob1 attacker, Frame frame) {
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

