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
          	moveAi(mapInitialization.mobList.get(i), 0);
          	i++;
			if(i == mapInitialization.mobList.size()) {
				i = 0;
				myTimer.cancel();
				GameVariables.roundReset();
			}
				//if(type == 'f') {
				//	moveAi(mapInitialization.mobList.get(i), 0);
				//}else if(type == 'k') {
				//	moveAi(mapInitialization.mobList.get(i), 0);
				//}
			}
		}, 1000, 1000);

		
		
		
		
		
		//for(i = 0; i < mapInitialization.mobList.size(); i++) {
		//	char type = mapInitialization.mobList.get(i).getType();

		//}
	}

	//Call enemy ai type
	public static void moveAi(Mob1 mob, int type) {

		switch(type) {
		//Aggressive ai
		case 0:
			ArrayList <String> mobeMove = new ArrayList<>();
			mobeMove.add("up");
			mobeMove.add("up");
			mobeMove.add("left");
			
			printMap.moveSprite(mob, null, mobeMove);


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
	}
}
