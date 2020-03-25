package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.xml.internal.ws.util.StringUtils;

public class MobAi {
	
	static int i = 0;
	static int tempTimer = 0;
	//Go through the enemy list until all enemies have moved/tried moving.
	public static void cycleList() {
		
		Timer cycleTimer = new Timer();
		cycleTimer.scheduleAtFixedRate(new TimerTask(){
			ArrayList <String> mobMove = new ArrayList<>();
			@Override
			public void run() {
				char type = mapInitialization.mobList.get(i).getType();
				
				if(type == 'f') {
					mobMove = moveAi(mapInitialization.mobList.get(i), 1);
				}else if(type == 'k') {
					mobMove = moveAi(mapInitialization.mobList.get(i), 0);
				}
				
				//Send the arraylist to the moveSprite method
				printMap.moveSprite(mapInitialization.mobList.get(i), null, mobMove);
				
				try {
					Thread.sleep(mobMove.size()*250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Increment i and compare to total size
				i++;

				if(i == mapInitialization.mobList.size()) {
					i = 0;
					cycleTimer.cancel();
					GameVariables.roundReset();
					System.out.println("Ememy phase completed");
				}
			}
		}, 0, 100);
	}

	//Call enemy ai type
	public static ArrayList <String> moveAi(Mob1 mob, int type) {
		ArrayList <String> mobeMove = new ArrayList<>();
		switch(type) {
		//Aggressive ai
		case 0:
			mobeMove.add("up");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("left");
			mobeMove.add("left");
			mobeMove.add("left");
			mobeMove.add("left");
			mobeMove.add("left");

			System.out.println(mob.getName());
			break;
			//Passive ai
		case 1:
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("right");
			mobeMove.add("left");
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
