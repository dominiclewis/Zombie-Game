package zombiestarter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import world.WEntrance;
import world.WRoom;
import world.WorldLoader;
import world.WItem;
/*
 Author: Dominic Lewis
 */

public class World {   //Private Variables Public class so anything can use the getters and setters with private variables

    private String info;  //Stores the world info string
    private boolean quit = false;  //Stores whether or not we should quit
    private String startStringHTML; //String to be displayed to the user at the start of the game
    private List<String> roomNames = new ArrayList<String>();//This variable will be used to check when we should construct a new room instance
    private String start; //String that is used for the start room I think 
    private String currentRoom; //PLEASE ALWAYS UPDATE THIS VARIABLE WITH THE NAME (STRING) OF THE CURRENT ROOM WE ARE IN 
    private boolean startRan = false;
    private WorldLoader w1;
   private Map<String, String> roomStatusHashMap = new HashMap<String, String>();

    //Constructor (THIS IS THE INFO WE USE TO CONSTRUCT THE CLASS * CALLTIME)

    World(WorldLoader w1, String info, String startString, String start ) {

        //this.info refers to the class attribute while = refers to the paramater
        //Basically this says the attribute = whatever is passed in through the paramater
        this.startStringHTML = startString; //link the constructor to the attribute;
        this.info = info;
        this.start = start;
        this.w1 = w1;
    }



    public void setQuit(boolean quit) {
        //THIS means we're addressing the one in the class ddeclaration 
        this.quit = quit;
    }

    //Returns quit variable   
    public boolean getQuit() {
        return quit;
    }

    //
    //Right click inside the class, insert code, getter 
    public String getInfo() {
        return info;
    }

    //Getter for start string HTML
    public String getStartStringHTML() {
        return startStringHTML;
    }

    //Getter for Start Room
    public String getStart() {
        return start;

    }

    public boolean getStartRan() {
        return startRan;
    }

    public void setStartRan(boolean startRan) {
        this.startRan = startRan;

    }

    public String getCurrentRoom() {

        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    //FOR LOOK

    public String displayRoomName(String roomWeWant) {
        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("<b>You are in the " + room.getName() + "</b>");
            }

        }
        return textToReturn;
    }

    //FOR LOOK

    public String displayRoomDescription(String roomWeWant) {

        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("The Description of the room is: " + room.getDescription());
            }

        }
        return textToReturn;

    }

    //Get the roomWeWant from the class we use to find it in look
    //The directionWeWant will be from cmds 
    //we Want to scan every room till for find the room we want
    //then we want to getDirection till it returns the one we want
    //We then want to check if it's locked
    //If it's not then we move there by assigning the value

    public String findEntranceName(String roomWeWant, String directionWeWant) {
        String roomName = getCurrentRoom();
        for (WRoom room : w1) { //Look through worldloaders rooms
            if (room.getName().equals(roomWeWant)) { //FIND THE ROOM WE ARE IN
                //Room we want has been found
                if (room.getEntrances().size() > 0) { //CHECK THE ROOM WE ARE IN HAS ENTRANCES
                    for (WEntrance entrance : room.getEntrances()) { //LOOP THROUGH ALL ENTRANCES
                        //TURN TO LOWERCASE THEN CHECK IF IT EQUALS THE DIRECTION WE WANT
                        if (((entrance.getDirection().toLowerCase()).equals(directionWeWant)) == true) //If the direction we specify matches the Directions vaailable in the room(entrance variable which iterates through all rooms
                        {
                            //CHECK IF LOCKED IF NO THEN RETURN ENTRANCE IF YES THEN RETURN LOCKED AND CHECK IT

                            if (entrance.isLocked() == false) {
                                return entrance.getTo(); //Returns the name of the room
                            } else {
                                //Check for a key 
                                //If a key remove one and change the room 
                                //If no key
                                //Do not allow them to enter
                              return getCurrentRoom(); //return the string for the current room which means that we haven't changed room
                            }
                        }
                    }

                }

            }

        }
        return roomName;
    }

    //FOR LOOK

    public String displayEntranceDirection(String roomWeWant) {
        String textToReturn = "It has the following entrances:<br>";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) { //implicit tru
                //Room we want has been found
                if (room.getEntrances().size() > 0) {
                    for (WEntrance entrance : room.getEntrances()) {
                        textToReturn += "("
                                + entrance.getDirection() + " ,"
                                + entrance.getTo() + ")"
                                + (entrance.isLocked() ? " Locked" : " Unlocked") + "<br>";// is it locked or unlocke ? true or false

                    }

                }

            }

        }
        return textToReturn;

    }
//FOR LOOK

    public String displayZombieCount(String roomWeWant) {
        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("Zombies in the room: " + room.getZombieCount());
            }

        }
        return textToReturn;

    }

    //FOR LOOK

    public String displayItemResource(String nameOfItemWeWant) {
        //iterate through every item in w1 
        //The datatypes were using is just a WItem list (just a list that is bound to a class if that makes sense
        String itemResource = "";
        for (WItem curItemVar : w1.getItems()) {
            if (curItemVar.getName().equals(nameOfItemWeWant)) //Get the name of the current index of getItems we're looking
            {// If the name at this index equals what we want
                //then set item resource to be the HTML value at the corresponsing index
                itemResource = curItemVar.getHtml();

            }
        }
        //return the html
        return itemResource;
    }

    //FOR LOOK

    public List itemIndexInSpecRoom(String roomWeWant) {

        List itemIndex = new ArrayList<>();

        int noToReturn = 0; //Counts how many items there are in a room so we can loop the outputter the correct amount of times
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {

                for (String itemName : room.getItems()) {
                    //add the index to the list 
                    itemIndex.add(itemName);
                }

            }
        }

        return itemIndex;

    }

    //FOR LOOK
    //return the items

    public String displayItemName(String roomWeWant, int index) {
        String itemName = "";
        for (WRoom room : w1) {

            if (room.getName().equals(roomWeWant)) {

                itemName = room.getItems().get(index); //this accesses the room.getItemslist and inside that it gets finds the name of the value we want to outpout

            }
        }

        return itemName;
    }
public void setUpNewRoom()
{
  boolean newRoom = areWeInANewRoom();  
  String roomInfo = "";
  if(newRoom == true)
  {
  roomInfo += " " +displayEntranceDirection(currentRoom);
  roomInfo += " " +displayZombieCount(currentRoom) +"<br>";
  //Create a list of the items in the specified room
  List itemIndex = itemIndexInSpecRoom(currentRoom);
  int noOfItems = itemIndex.size();
for(int i =0; i< noOfItems; i++){
    roomInfo += " " + displayItemName(currentRoom,i);
    roomInfo += " " + displayItemResource(displayItemName(currentRoom,i));
}
 roomStatusHashMap.put(currentRoom, roomInfo);
  }
 
 
}
    //This will check for a new room if so returns true if else returns false
    public boolean areWeInANewRoom(){
        boolean areWe = false; 
       //If we are in the start room 
       //The linkedList should be empty
       if(roomNames.isEmpty() == true)
       {
           //Add the start room name to the list 
           roomNames.add(start);
           //set the current room to be the start room also
           currentRoom = start;
           return true; 
       }
       else
           //Check all elements of the roomName to find out if curRoomName is in it
       {
           for(int i = 0; i<roomNames.size(); i++)
           {
            if(roomNames.get(i).equalsIgnoreCase(currentRoom))
            {//The room is not new
                return false;
                //No break is required 
            } else{             
                areWe = true;
            }
                
           }
           
       }
       //Add the new room to the list 
       if(areWe == true)
       {
           roomNames.add(currentRoom);
       }
    return areWe; 
    }
    
public String look(){
    String roomInfo = ""; 
    String roomName = "";
    //Loop throught the hash
    for (Map.Entry<String, String> entry : roomStatusHashMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        if(currentRoom.equalsIgnoreCase(entry.getKey())) //If the room we want equals the key name            
        {
    roomName = entry.getKey();
    roomInfo = entry.getValue();
        }
}
    return (roomName + "<br>" +roomInfo);
}
}




