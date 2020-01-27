package application;

public class Bag {
	int gold;
	String location;
	
	public Bag(int gold, String location) {
		
		this.gold = gold;
		this.location = location;

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
