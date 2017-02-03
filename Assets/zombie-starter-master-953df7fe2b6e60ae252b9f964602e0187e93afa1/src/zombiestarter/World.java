package zombiestarter; 

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
World(WorldLoader w1,String info, String displayInventory, String startString,String start){
    
    //this.info refers to the class attribute while = refers to the paramater
    //Basically this says the attribute = whatever is passed in through the paramater
    
    this.startStringHTML = startString; //link the constructor to the attribute;
    this.info = info;     
    this.displayInventory = displayInventory; 
    this.start = start; 
    this.w1 = w1; 
}    

//Getter for displayInventory
public String getDisplayInventory(){
   //Returns class attribute
    return displayInventory;
}
//SETSQUIT
    public void setQuit(boolean quit) {
        //THIS means we're addressing the one in the class ddeclaration 
        this.quit = quit;
    }
    //Returns quit variable   
    public boolean getQuit()
    {
        return quit; 
    }
    //
    //Right click inside the class, insert code, getter 
    public String getInfo() {
        return info;
    }
   //Getter for start string HTML
    public String getStartStringHTML()
    {
        return startStringHTML;
    } 
    
    //Getter for Start Room
    public String getStart(){
        return start; 
        
    }
    public boolean getStartRan(){
        return startRan;
    }
    
    public void setStartRan(boolean startRan){
        this.startRan = startRan;
 
    }
    
    public String getCurrentRoom(){
        
       return currentRoom;
    }
    
    public void setCurrentRoom(String currentRoom)
    {
        this.currentRoom = currentRoom;
    }
    //Methods which output the ting
     public void displayRooms(String roomWeWant) {
        System.out.println("----------------------------------------------------------------");
        //DataType of what we're looking at  == WROOM 
        //room === The current variable
        //Loops over every w1 index 
        //WHEN WE USE THE GETTERS HERE WE ARE ACESSING WROOM'S as we've declared that to be our datatype so can use the operations that come with it 
        for (WRoom room : w1) {
             if(room.getName() .equals(roomWeWant) )
             {
             System.out.println("The name of the room is: " + room.getName());
            System.out.println("and its description is  \"" + room.getDescription() + "\"");

            System.out.println("it has the following entrances\n");
            displayEntrances(room.getEntrances());

            System.out.println("the following items are placed around the room\n");
            for (String itemName: room.getItems()) {
                System.out.println(itemName);
            }

            System.out.println("\nthere are " + room.getZombieCount() + " Zombies in the room");
            System.out.println("----------------------------------------------------------------");
            
          
        }
    }
     }
     
      //Displays status of the entrances (locked or unlocked)
       public void displayEntrances(List<WEntrance> entrances) {
        for (WEntrance e : entrances) {
            System.out.println(e.getDirection() + " -> " + e.getTo());
            if (e.isLocked()) {
                System.out.println("it is locked");
            }
            else {
                System.out.println("not locked");
            }
        }
    }
   
}