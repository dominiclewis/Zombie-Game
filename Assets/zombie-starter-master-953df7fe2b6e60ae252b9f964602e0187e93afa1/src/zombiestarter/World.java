package zombiestarter;

import java.util.ArrayList;
import java.util.List;
import world.WEntrance;
import world.WRoom;
import world.WorldLoader;

/*
 Author: Dominic Lewis
 */
public class World {   //Private Variables Public class so anything can use the getters and setters with private variables

    private String info;  //Stores the world info string
    private boolean quit;  //Stores whether or not we should quit
    private String displayInventory; //HTML used for formatting the inventory?
    private String startStringHTML; //String to be displayed to the user at the start of the game
    private String start; //String that is used for the start room I think 
    private String currentRoom;
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

//  
//    //Methods which output the ting
//     public String displayRooms(String roomWeWant) {
//         String textToReturn = "";
//        //DataType of what we're looking at  == WROOM 
//        //room === The current variable
//        //Loops over every w1 index 
//        //WHEN WE USE THE GETTERS HERE WE ARE ACESSING WROOM'S as we've declared that to be our datatype so can use the operations that come with it 
//        for (WRoom room : w1) {
//             if(room.getName() .equals(roomWeWant) )
//             {
//      /*   DONE   // System.out.println("The name of the room is: " + room.getName());
//           DONE System.out.println("and its description is  \"" + room.getDescription() + "\"");
//            
//         DONE   System.out.println("it has the following entrances\n");
//         DONE   displayEntrances(room.getEntrances());
//
//            System.out.println("the following items are placed around the room\n");
//            for (String itemName: room.getItems()) {
//                System.out.println(itemName);
//            }
//
//      DONE      System.out.println("\nthere are " + room.getZombieCount() + " Zombies in the room");
//            System.out.println("----------------------------------------------------------------");
//*/
//          DONE   textToReturn = ("The name of the room is:" + room.getName() +"It has the following enrances" +room.getEntrances());
//             
//        }
//    }
//         
//        return textToReturn;
//     }
    public String displayRoomName(String roomWeWant) {
        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("The name of the room is: " + room.getName());
            }

        }
        return textToReturn;
    }

    public String displayRoomDescription(String roomWeWant) {

        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("The Description of the room is: " + room.getDescription());
            }

        }
        return textToReturn;

    }

    public String displayEntranceDirection(String roomWeWant) {
        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("It has the following entrances: " + room.getEntrances());
            }

        }
        return textToReturn;

    }

    public String displayZombieCount(String roomWeWant) {
        String textToReturn = "";
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {
                textToReturn = ("Zombies in the room: " + room.getZombieCount());
            }

        }
        return textToReturn;

    }

    public List itemIndexInSpecRoom(String roomWeWant) {

        List itemIndex = new ArrayList<Integer>();

        int noToReturn = 0; //Counts how many items there are in a room so we can loop the outputter the correct amount of times
        for (WRoom room : w1) {
            if (room.getName().equals(roomWeWant)) {

                for (String itemName : room.getItems()) {
                    System.out.println("we are here 2");
                    //add the index to the list 
                    itemIndex.add(itemName);
                }

            }
        }

//              
        return itemIndex;

    }

    //return the items

    public String displayItemName(String roomWeWant, int index) {
        String itemName = "";
        for (WRoom room : w1) {

            if (room.getName().equals(roomWeWant)) {
                System.out.println("we are here 3");
                itemName = room.getItems().get(index); //this accesses the room.getItemslist and inside that it gets finds the name of the value we want to outpout

            }
        }
        System.out.println("are we ever here? 3 " +itemName);
        return itemName;
    }

    //Displays status of the entrances (locked or unlocked)

    public void displayEntrances(List<WEntrance> entrances) {
        for (WEntrance e : entrances) {
            System.out.println(e.getDirection() + " -> " + e.getTo());
            if (e.isLocked()) {
                System.out.println("it is locked");
            } else {
                System.out.println("not locked");
            }
        }
    }

}
