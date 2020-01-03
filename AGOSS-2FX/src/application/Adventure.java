package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {
	
	//Creates a player list for the current session
	public static ArrayList<Player> playerListCurrent = new ArrayList<Player>();
	
	static Scanner scanner = new Scanner(System.in);
	static boolean winStatus = false;
	static ArrayList<String> listOfLines = new ArrayList<>();

	public static void Resume(ArrayList<Player> passedPlayerList, Bag bag, int type, String determined) throws Exception {
		
		//Sets the current session with the passed list
		playerListCurrent = passedPlayerList;
		
		//Decide next selection based off menu button press
		switch(type) {
			
			//Load player selected Map
			case 2:
				String mapName = determined;
				System.out.println("Selection type: " + type + "\nMapname: " + mapName);
				
				//Load map
				mapInitialization.init(mapName);
				
				break;
			
				
			//Load mapLists
			case 3:	
			case 1:
				String mapList = determined;

				//Load BASE game
				if(type == 1) {
					System.out.println("Selection type: " + type + "\nMapList name: " + mapList);
					System.out.println(playerListCurrent.get(0).getName() + " " + playerListCurrent.get(1).getName());
				
					//Load CUSTOM mapList
					
				}else if(type == 3) {
					System.out.println("Selection type: " + type + "\nMapList name: " + mapList);
					
					
					//Use player determined map list
					
					
				}
				break;
		}
	}
		
	
	
	public static ArrayList<String> saveMapList(String mapList) throws Exception {
		//Checks if Map List exists
		if (new File("src\\maplists\\" + mapList + "_mapList.txt").exists()){
			//Continue if it does
			listOfLines = inputToString("src\\maplists\\" + mapList + "_mapList.txt");
			
			//Loop through the list
			for(int i = 0; i != listOfLines.size();) {
				return listOfLines;
			}
		}else {
			System.out.println("Missing MapList .txt");
		}
		return listOfLines;
	}
    
	public static ArrayList<String> inputToString(String fileName)throws Exception 
	{ 
		//Initializes the buffered reader
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

	    String line = reader.readLine();
	    while (line != null) {
	      listOfLines.add(line);
	      line = reader.readLine();
	    }
		reader.close();
		return listOfLines;
	} 
	
}
