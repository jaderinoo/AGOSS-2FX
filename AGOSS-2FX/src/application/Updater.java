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
    		playerDetails.put("Type",playerList.get(i).getType());
    		
    		JSONObject playerBagDetails = new JSONObject();

    		playerBagDetails.put("Item_Id_0",playerList.get(i).playerBag.getItem_Id(0));
    		playerBagDetails.put("Item_Id_0_Usage",playerList.get(i).playerBag.getItem_Id_Usage(0));
    		
    		playerBagDetails.put("Item_Id_1",playerList.get(i).playerBag.getItem_Id(1));
    		playerBagDetails.put("Item_Id_1_Usage",playerList.get(i).playerBag.getItem_Id_Usage(1));
    		
    		playerBagDetails.put("Item_Id_2",playerList.get(i).playerBag.getItem_Id(2));
    		playerBagDetails.put("Item_Id_2_Usage",playerList.get(i).playerBag.getItem_Id_Usage(2));
    		
    		playerBagDetails.put("Item_Id_3",playerList.get(i).playerBag.getItem_Id(3));
    		playerBagDetails.put("Item_Id_3_Usage",playerList.get(i).playerBag.getItem_Id_Usage(3));
    		
    		playerBagDetails.put("Item_Id_4",playerList.get(i).playerBag.getItem_Id(4));
    		playerBagDetails.put("Item_Id_4_Usage",playerList.get(i).playerBag.getItem_Id_Usage(4));
			 
    		
    		JSONObject PlayerObject = new JSONObject(); 
    		PlayerObject.put("Character", playerDetails);
    		PlayerObject.put("Bag", playerBagDetails);
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
    
    static ArrayList<Player> playerList = new ArrayList<Player>();
    
    @SuppressWarnings("unchecked")
	public static ArrayList<Player> saveReader(String name) throws org.json.simple.parser.ParseException, IOException {
    	
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
        
    	return playerList;
    }
    
    private static void parsePlayerObject(JSONObject character, ArrayList<Player> playerList) 
    {
        //Get the player object for Character
        JSONObject playerObject = (JSONObject) character.get("Character");
         
        //Get employee first name
        String name = (String) playerObject.get("Name");    
         
        //Get stats
        int strength = Math.toIntExact((long) playerObject.get("Strength"));  
        int agility = Math.toIntExact((long) playerObject.get("Agility")); 
        int armor = Math.toIntExact((long) playerObject.get("Armor"));
        int maxHp = Math.toIntExact((long) playerObject.get("MaxHp"));
        int special = Math.toIntExact((long) playerObject.get("Special"));
        int level = Math.toIntExact((long) playerObject.get("Level"));
        int exp = Math.toIntExact((long) playerObject.get("Exp"));
        int currentHp = Math.toIntExact((long) playerObject.get("CurrentHp"));
        int type = Math.toIntExact((long) playerObject.get("Type"));
        
        //Get playerBag info
        JSONObject playerBagObject = (JSONObject) character.get("Bag");
        
        //Get playerBagInfo
        int Item_Id_0 = Math.toIntExact((long) playerBagObject.get("Item_Id_0"));
		int Item_Id_0_Usage = Math.toIntExact((long) playerBagObject.get("Item_Id_0_Usage"));
		
		int Item_Id_1 = Math.toIntExact((long) playerBagObject.get("Item_Id_1"));
		int Item_Id_1_Usage = Math.toIntExact((long) playerBagObject.get("Item_Id_1_Usage"));
		
		int Item_Id_2 = Math.toIntExact((long) playerBagObject.get("Item_Id_2"));
		int Item_Id_2_Usage = Math.toIntExact((long) playerBagObject.get("Item_Id_2_Usage"));
		
		int Item_Id_3 = Math.toIntExact((long) playerBagObject.get("Item_Id_3"));
		int Item_Id_3_Usage = Math.toIntExact((long) playerBagObject.get("Item_Id_3_Usage"));
		
		int Item_Id_4 = Math.toIntExact((long) playerBagObject.get("Item_Id_4"));
		int Item_Id_4_Usage = Math.toIntExact((long) playerBagObject.get("Item_Id_4_Usage"));
		
        //Initialize playerBag object and send it to player
        playerBags playerBag = new playerBags(Item_Id_0,Item_Id_0_Usage,Item_Id_1,Item_Id_1_Usage,Item_Id_2,Item_Id_2_Usage,Item_Id_3,Item_Id_3_Usage,Item_Id_4,Item_Id_4_Usage);
        
        //Create player here
        Player player = new Player(name,strength, agility, armor, maxHp, special, level, exp, currentHp, type, playerBag);
        playerList.add(player);
    }
	
	//Save bag state
    @SuppressWarnings("unchecked")
	public static void globalBagUpdater(Bag bag, String name) throws IOException {
    	
		JSONObject bagDetails = new JSONObject();
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
    
    public static Bag globalBagReader(String name) throws org.json.simple.parser.ParseException, IOException {
    	
    	JSONParser jsonParser = new JSONParser();
        
    	Bag bag = new Bag(0, "");
    	
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
