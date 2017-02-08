/*
 * Author: 
          -Dominic Lewis  
          - William Blackie
          - Jake Davies 
 * Desc: This file contains the heart of dynamic game play for Zombies. 
 *       you need to implement each of the methods, as per the game play,
 *       adding support for processing commands comming from the client.
    Testing URL: http://www.cems.uwe.ac.uk/~br-gaster/courses/2016-2017/OOSD1/
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author your details
 */

/**
 * class that implements the ZombieBot interface and plays the game
 * @author br-gaster
 */
public class ZombieBot implements world.ZombieBot {
    
    private World world; 
    private Inventory userInventory; //Variable to access the class  
    ZombieBot(World world, Inventory userItem) {
      this.world = world; 
      this.userInventory = userItem;
    }
    
    /**
     * should game quit 
     * @return return true if exit program, otherwise false 
     */ 
    @Override
            //This checks that the world quit attribute is set to true, if so it returns true
    public boolean shouldQuit() {  
      return world.getQuit(); //returns true if quit has been set, returns false anyway
    }  
    
    /**
     * prompt to be displayed to user
     * @return 
     */
    @Override
    public String begin() {
        return world.getStartStringHTML(); 
    }
    
    /**
     * compute current score
     * @return current score
     */
    @Override
    public int currentScore() {
        return 0;
    }

    /**
     * should timer be enabled? if should be enabled, then method returns true,
     * and goes back into state of not enable.
     * @return true if enable timer, otherwise false
     */
    public boolean enableTimer()  { 
        return false;
    }

    /**
     * should timer be disabled? if should be disabled, then method returns
     * true, and goes back into state of don't disable.
     * @return
     */
    public boolean disableTimer() {
        return false;
    }
  //Checks if we're in the start room
    public boolean startRoomCheck(){
           //if this is the first time we are setting the current room 
       //It is implicit that we are in the start room
      if(world.getStartRan() == false)
      {
          //May need a try/catch if it breaks 
       world.setCurrentRoom (world.getStart() ); //set the current room to be the start string  
         world.setStartRan(true); //set the start ran variable to be true
         return true; //True we are in the start toom 
      } else  //we aren't in the start room anymore
      {
          return false; //fase were not in the start room 
          
      }
    }
    //returns the currentRoom we are in as a String
    public String whatRoomAreWeIn()
    { //First what room are we in?
    //Are we in start?  
   boolean areWeInStart = startRoomCheck();
  
   //If were in start then then the current room is the start room
   if(areWeInStart == true)
   {
       world.setCurrentRoom(world.getStart());
   } else{
       //We are not in the start room
   }
   
       return world.getCurrentRoom();
    }
    

    
    /**
     * process player commands
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();
        
        String[] cmds = cmd.split(" "); // split cmd by space //So when whitespace is detected it is the secound element in the array
        
        switch(cmds[0]) {
            case "info":
                result.add(world.getInfo());
                break;
            case "look":      
                /*
                //Some of this is done so stupidly because I didn't realise I could line break with BR I thought I had to invoke the add method to print across multiple lines 
                result.add(world.displayRoomName(whatRoomAreWeIn()));
               //result.add(world.displayRoomDescription(whatRoomAreWeIn())); //do we actually need this part in
                result.add(world.displayEntranceDirection(whatRoomAreWeIn()));
                result.add(world.displayZombieCount(whatRoomAreWeIn()));
                //Items in the room 
                //Create a list of the indexes of the items in the room 
                 List itemIndex =world.itemIndexInSpecRoom(whatRoomAreWeIn());
                //Get the number of indexes in the initial array
                 int noOfItems = itemIndex.size();
                 //Loop for however many times needed
              
                 for(int i =0; i< noOfItems; i++){
                 //Get item name
                    
                     result.add(world.displayItemName(whatRoomAreWeIn(), i));                    
                     //get item resource
                     //I know this is ugly but I think it works
                     result.add(world.displayItemResource(world.displayItemName(whatRoomAreWeIn(),i) ) );
                 }
                */
                   
                world.setUpNewRoom();
                 result.add(world.look());
                
                 
                break;
            case "move":
                //Move the direction to the directionString
                world.setCurrentRoom(world.findEntranceName(whatRoomAreWeIn(),cmds[1]));
                //Try to access the room 
                
                //MOVE DIRECTION(NE,SE etc)
                //eg move NE
               //Is the door locked? Check for key reject if necessary  
               
                break;
            case "pickup":  
                String item = cmds[1];
                result.add(userInventory.pickUp(item));//Pass in the second paramater passed to cmds / entered 
               //Needs to remove the item from the hashmap , build a generic add & remove function for the hashmap remebering that it's a string 
                //Maybe use scanner to parse it
                
                break;
            case "kill":
                result.add("handle kill command");
                break;
            case "drop":
                result.add("handle drop command");
                break;
            case "timerexpired":
                result.add("handle timeexpired command");
                break;
            case "quit":
                //This is where we alter the world quit attribute with the setter
                 world.setQuit(true); 
                break;
            case "inventory":
                 result.add(userInventory.getInventoryHtml());
                 //html ggot
                 //Construct the inventory now
               result.add(userInventory.constructPlayerInventoryString());  
                break;
            case "blank":
                result.add("I beg your pardon?");
                break;
            case "":
                break;
                //DELETE THIS "test" 
            case "test":
                   result.add("No suitable test");
                break; 
            default:
                result.add("<b>That's not a verb I recognise.</b>");
        }
        
        return result;
     }
}