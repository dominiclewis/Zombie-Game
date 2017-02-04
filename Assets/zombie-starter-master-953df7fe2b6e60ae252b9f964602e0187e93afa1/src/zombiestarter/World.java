package zombiestarter;

import java.util.ArrayList;
import java.util.List;
import world.WEntrance;
import world.WRoom;
import world.WorldLoader;
import world.WItem;
/*
 Author: Dominic Lewis
 */

public class World {   //Private Variables Public class so anything can use the getters and setters with private variables

    private String info;  //Stores the world info string
    private boolean quit;  //Stores whether or not we should quit
    private String displayInventory; //HTML used for formatting the inventory?
    private String startStringHTML; //String to be displayed to the user at the start of the game
    private String start; //String that is used for the start room I think 
    private String currentRoom; //PLEASE ALWAYS UPDATE THIS VARIABLE WITH THE NAME (STRING) OF THE CURRENT ROOM WE ARE IN 
    private boolean startRan = false;
    private WorldLoader w1;
    //Constructor (THIS IS THE INFO WE USE TO CONSTRUCT THE CLASS * CALLTIME)

    World(WorldLoader w1, String info, String displayInventory, String startString, String start) {

        //this.info refers to the class attribute while = refers to the paramater
        //Basically this says the attribute = whatever is passed in through the paramater
        this.startStringHTML = startString; //link the constructor to the attribute;
        this.info = info;
        this.displayInventory = displayInventory;
        this.start = start;
        this.w1 = w1;
    }

//Getter for displayInventory
    public String getDisplayInventory() {
        //Returns class attribute
        return displayInventory;
    }
//SETSQUIT

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
                textToReturn = ("The name of the room is: " + room.getName());
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
  //FOR LOOK
    public String displayEntranceDirection(String roomWeWant) {
        String textToReturn = "It has the following entrances:<br>";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                //Room we want has been found
                if (room.getEntrances().size() > 0) {
                    for (WEntrance entrance : room.getEntrances()) {
                        textToReturn += "("
                                + entrance.getDirection() + " ,"
                                + entrance.getTo() + ")"
                                + (entrance.isLocked() ? " Locked" : " Unlocked")+ "<br>";// is it locked or unlocke ? true or false

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

}
