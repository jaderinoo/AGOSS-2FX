package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

public class GameVariables {
	public static int round = 0;
	
	public static void checkRoundStatus() {
		int tempCheck = 0;
		for(int i = 0; i != Adventure.playerListCurrent.size(); i++) {
			if(Adventure.playerListCurrent.get(i).getHasMoved() == true) {
				tempCheck++;
			}
		}
		
		if(tempCheck == Adventure.playerListCurrent.size()) {
			round++;
			System.out.println("Round#: " + round);
			
			//Move to enemy turn
			MobAi.cycleList();
			
			//After enemy move finishes, reset vars if necessary
			
			//Move to "other" turn
			
			//If no other exist, reset phase to 0
		}
	}
	
	public static int currentTime = 0;
	public static String timeString = "";
	
	public static void timer() {
		   Timer myTimer = new Timer();
	          myTimer.scheduleAtFixedRate(new TimerTask(){
	            @Override
	            public void run() {
	            	currentTime++;
	            	timeString = secondsToString(currentTime);
	            	System.out.println("Game time: " + timeString);
	            	//Interface.timeUpdater(timeString);
	            }
	          }, 1000, 1000);
	}
	
	//Convert the int to a string
	private static String secondsToString(int pTime) {
	    return String.format("%02d:%02d", pTime / 60, pTime % 60);
	}
}
