package application;

public class GridSpace {
	char type;
	int objective;
	
	public GridSpace(char tempType, int tempObjective) {
		this.type = tempType;
		this.objective = tempObjective;
	}
	
	public void setType(char mapChar) {
		this.type = mapChar;
	}
	
	public char getType() {
		return this.type;
	}
	
	public void setObjective(int tempobjective) {
		this.objective = tempobjective;
	}
	
	public int getObjective() {
		return this.objective;
	}
}
