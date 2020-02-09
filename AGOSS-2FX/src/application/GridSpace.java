package application;

public class GridSpace {
	char type;
	int typeEffect;
	int objective;
	
	public GridSpace(char tempType, int tempObjective) {
		this.type = tempType;
		this.objective = tempObjective;
		
		this.typeEffect = setTypeEffect();
		System.out.println(typeEffect);
	}
	
	public void setType(char mapChar) {
		this.type = mapChar;
	}
	
	public char getType() {
		return this.type;
	}
	
	public int setTypeEffect() {
		
		switch(this.type) {	
			case 'p':
				return 1;
				
			case 'h':
				return -1;
				
			case 'm':
				return -2;
				
			case 'r':
				//Disable movement into this tile by setting it super low
				return -100;
				
			case 'w':
				//Disable movement into this tile by setting it super low
				return -100;
		}
		
		// type isnt valid return default 0
		return 0;
	}
	
	public void setObjective(int tempobjective) {
		this.objective = tempobjective;
	}
	
	public int getObjective() {
		return this.objective;
	}
}
