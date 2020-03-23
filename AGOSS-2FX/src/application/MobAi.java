package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MobAi {
	static int i = 0;
	//Go through the enemy list until all enemies have moved/tried moving.
	public static void cycleList() {
		Timer myTimer = new Timer();

		myTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				ArrayList <String> mobMove = new ArrayList<>();
				char type = mapInitialization.mobList.get(i).getType();
				
				//Get the moveset
				if(type == 'f') {
					mobMove = moveAi(mapInitialization.mobList.get(i), 0);
				}else if(type == 'k') {
					mobMove = moveAi(mapInitialization.mobList.get(i), 0);
				}

				//Send the arraylist to the moveSprite method
				printMap.moveSprite(mapInitialization.mobList.get(i), null, mobMove);
				
				//Increment i and compare to total size
				i++;

				if(i == mapInitialization.mobList.size()) {
					i = 0;
					myTimer.cancel();
					mobMove.clear();
					GameVariables.roundReset();
				}
			}
		}, 1000, 1000);
	}

	//Call enemy ai type
	public static ArrayList <String>  moveAi(Mob1 mob, int type) {
		ArrayList <String> mobeMove = new ArrayList<>();
		switch(type) {
		//Aggressive ai
		case 0:
			mobeMove.add("up");
			mobeMove.add("up");
			mobeMove.add("left");

			System.out.println(mob.getName());
			break;
			//Passive ai
		case 1:
			System.out.println(mob.getName());
			break;
			//Defensive ai
		case 2:
			System.out.println(mob.getName());
			break;
			//static ai
		case 3:
			System.out.println(mob.getName());
			break;

		default:

		}
		return mobeMove;
	}
}
