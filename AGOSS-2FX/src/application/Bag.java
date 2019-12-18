package application;

public class Bag {
	int potions;
	int boosters;
	int gold;
	String location;
	
	public Bag(int potions, int boosters,int weapon, String location) {
		
		this.potions = potions;
		this.boosters = boosters;
		this.gold = weapon;
		this.location = location;

	}
	
    public int getPotions() {
        return potions;
    }
    
    public void setPotions(int tmpPotions) {
        this.potions = tmpPotions;
    }
    
    public int getBoosters() {
        return boosters;
    }
    
    public void setBoosters(int tmpBoosters) {
        this.boosters = tmpBoosters;
    }
    
    public int getGold() {
    	return gold;
    }
    
	public void setGold(int tmpgold) {
		this.gold = tmpgold;
	}
    
    public String getLocation() {
    	return location;
    }
    
	public void setLocation(String tmplocation) {
		this.location = tmplocation;
	}

}

