package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Updater {
    @SuppressWarnings("unchecked")
	public static void saveUpdater(ArrayList<Player> playerList, String name) throws IOException {
    	
    	//Create the jsonArray
    	JSONArray objList = new JSONArray();
    	
    	//Loop all information into jsonObject
    	for(int i = 0; i != playerList.size(); i++) {

    		JSONObject playerDetails = new JSONObject();
    		playerDetails.put("Name",playerList.get(i).getName());
    		playerDetails.put("Strength",playerList.get(i).getStrength());
    		playerDetails.put("Agility",playerList.get(i).getAgility());
    		playerDetails.put("Armor",playerList.get(i).getArmor());
    		playerDetails.put("MaxHp",playerList.get(i).getMaxHp());
    		playerDetails.put("Special",playerList.get(i).getSpecial());
    		playerDetails.put("Level",playerList.get(i).getLevel());
    		playerDetails.put("Exp",playerList.get(i).getExp());
    		playerDetails.put("CurrentHp",playerList.get(i).getCurrentHp());
    		JSONObject PlayerObject = new JSONObject(); 
    		PlayerObject.put("Character", playerDetails);
    		objList.add(PlayerObject);
    	}
    	
    	//Write JSON file
        try (FileWriter file = new FileWriter("AGOSS-2FX\\src\\application\\saves\\" + name + "\\" + name + ".json")) {
 
            file.write(objList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    @SuppressWarnings("unchecked")
	public static ArrayList<Player> saveReader(String name) throws org.json.simple.parser.ParseException, IOException {
    	ArrayList<Player> playerList = new ArrayList<Player>();
    	
    	JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("AGOSS-2FX\\src\\application\\saves\\" + name + "\\" + name + ".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray characterList = (JSONArray) obj;
            System.out.println(characterList);
             
            //Iterate over employee array
            characterList.forEach( emp -> parsePlayerObject( (JSONObject) emp, playerList ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ////////LOAD CHARACTER FROM HERE
        //frame.add(Frame.loadCharacter(playerList.get(0)));
    	return playerList;
    }
    
    private static void parsePlayerObject(JSONObject character, ArrayList<Player> playerList) 
    {
        //Get the player object for Character
        JSONObject playerObject = (JSONObject) character.get("Character");
         
        //Get employee first name
        String name = (String) playerObject.get("Name");    
         
        //Get strength
        int strength = Math.toIntExact((long) playerObject.get("Strength"));  
        
        //Get agility
        int agility = Math.toIntExact((long) playerObject.get("Agility")); 
        
        //Get armor
        int armor = Math.toIntExact((long) playerObject.get("Armor"));
        
        //Get maxHp
        int maxHp = Math.toIntExact((long) playerObject.get("MaxHp"));
        
        //Get special
        int special = Math.toIntExact((long) playerObject.get("Special"));
        
        //Get level
        int level = Math.toIntExact((long) playerObject.get("Level"));
        
        //Get exp
        int exp = Math.toIntExact((long) playerObject.get("Exp"));
        
        //Get currentHp
        int currentHp = Math.toIntExact((long) playerObject.get("CurrentHp"));

        Player player = new Player(name,strength, agility, armor, maxHp, special, level, exp, currentHp);
        playerList.add(player);
    }
	
	//Save bag state
    @SuppressWarnings("unchecked")
	public static void bagUpdater(Bag bag, String name) throws IOException {
    	
		JSONObject bagDetails = new JSONObject();
		bagDetails.put("Potions", bag.getPotions());
		bagDetails.put("Boosters", bag.getBoosters());
		bagDetails.put("Gold", bag.getGold());
		bagDetails.put("Current Location", bag.getLocation());


		JSONObject bagObject = new JSONObject(); 
		bagObject.put("BagItems", bagDetails);
    	
    	
    	//Write JSON file
        try (FileWriter file = new FileWriter("AGOSS-2FX\\src\\application\\saves\\" + name + "\\" + name + "_Bag.json")) {
 
            file.write(bagObject.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    @SuppressWarnings("unchecked")
	public static Bag bagReader(String name) throws org.json.simple.parser.ParseException, IOException {
    	
    	JSONParser jsonParser = new JSONParser();
        
    	Bag bag = new Bag(0, 0, 0, "");
    	
        try (FileReader reader = new FileReader("AGOSS-2FX\\src\\application\\saves\\" + name + "\\" + name + "_Bag.json"))
        {
            //Read JSON file
            Object bagItems = jsonParser.parse(reader);
 
            //Debugging
            System.out.println(bagItems);
             
            //Create a temp casted json object
            JSONObject tempBagObject = (JSONObject) bagItems;
            
            //Get the player object for Character
            JSONObject bagObject = (JSONObject) tempBagObject.get("BagItems");

            //Get employee first name
            bag.setLocation((String) bagObject.get("Current Location")); 

            //Get strength
            bag.setPotions(Math.toIntExact((long) bagObject.get("Potions")));  
            
            //Get agility
            bag.setBoosters(Math.toIntExact((long) bagObject.get("Boosters"))); 
            
            //Get armor
            bag.setGold(Math.toIntExact((long) bagObject.get("Gold")));
            //Iterate over employee array
            //bagList.forEach( emp -> parseBagObject( (JSONObject) emp, bag ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return bag;
    }
}
