
package zombiestarter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Lewis
 */

/**
 * class that implements the ZombieBot interface and plays the game
 * @author br-gaster
 */
public class ZombieBot implements world.ZombieBot {
    
    private World world; 
    private List<Room> roomList;
    private List<String> roomName;
    private Player player;
    ZombieBot(World world, List<Room> roomList, List<String> roomName, Player player ) {
      this.world = world; 
      this.roomList = roomList;
      this.roomName = roomName; 
      this.player = player;
    }
    
    /**
     * should game quit 
     * @return return true if exit program, otherwise false 
     */ 
    @Override
    
    public boolean shouldQuit() {
        return world.getQuit();
    }  
    
    /**
     * prompt to be displayed to user
     * @return 
     */
    @Override
    public String begin() {
        return world.getStartHTML();
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
    
    /**
     * process player commands
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();
        
        String[] cmds = cmd.split(" "); // split cmd by space
        
        
        
        switch(cmds[0].toLowerCase()) {
            case "info":
                result.add(world.getInfo());
                break;
            case "look":
                result.add(roomList.get(findRoomIndex()).Look()); //Looks at the current room
                break;
            case "move":
                //Direction
                List roomInfo = canIMove(cmds[1]);
                if(!roomInfo.isEmpty())
                {
                if(roomInfo.get(3).equals(true)) //if we can move
                {
                    world.setCurrentRoom(roomInfo.get(0).toString());
                    result.add("Moved to " + (roomInfo.get(0).toString()));
                }
                else{
                    if(roomInfo.get(4).equals(true))
                    {
                        result.add("You need a key to enter");
                    } 
                }
                }
                else{
                    result.add("No such room!");
                }
                break;
            case "pickup":  
                result.add("handle pickup command");
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
                   world.setQuit(true);
                break;
            case "inventory":
                String backPackHtml = world.getInventoryHtml();
                List <String> inventory = player.getUserInventory();
                List <String> inventoryHtml = player.getUserInventoryHtml();
                String toOutPut = backPackHtml;
                
             int i = 0;
                for(String index: inventory)
                {
                    
                    toOutPut+= " ";
                    //Add item
                    toOutPut+=index;
                    
                    toOutPut += "";
                    //add it's html
                    toOutPut+= inventoryHtml.get(i);
                    i++;
                }
                
                //output
                result.add(toOutPut);
                break;
            case "blank":
                result.add("I beg your pardon?");
                break;
            case "":
                break;
            case "test":
                //Room temp = roomList.get(2);
                //result.add(temp.Look());
                
                break; 
            default:
                result.add("<b>That's not a verb I recognise.</b>");
        }
        
        return result;
     }
    //MAPS AN ENTRANCE DIRECTION TO A ROOM
    public List entranceToRoom(String direction)
    {
        String room = "No Room Found";
        int curRoomIndex = findRoomIndex();
        //Get a rooms info
        // IT IS NOW EMPTY IF IT'S NEVER FOUND 
        //0. Name
        //1. Is it locked
        //2. Is it ever found
        List roomInfo = roomList.get(curRoomIndex).mapEntranceToRoomInfo(direction);
  
        return roomInfo; 
    }
    //CONTROLLS THE MOVE 
    //RETURNS IF POSSIBLE OR NOT
    public List canIMove(String direction){
        boolean canIMove = false;
        boolean noKey = false;
        //Find what Room I'm in
        List roomInfo = entranceToRoom(direction); //returns the roomInfo
        
        
        if(!roomInfo.isEmpty()) //if room info isn't empty 
        {
        if(roomInfo.get(2).equals(true))//Object comparison not primitive?
        {
         canIMove = true;    
        } 
        
        //If I can move due to finding the room but the rooms locked        
        if( (canIMove == true) && (roomInfo.get(1).equals(true) ) )  {
            //try to remove a key
            if((player.removeItem("key")) == true){ //key sucesffuly removed 
                canIMove = true;
                //Change door to be unlocked
                roomList.get(findRoomIndex()).setDoorToUnlocked(direction);
            }
            else{
                noKey = true;
                canIMove = false; 
            }
        } 
        else if ( (canIMove == true ) && (roomInfo.get(1).equals(false)))
        {
            //Door is not locked
            canIMove = true;
        }
        //add record 3 to match canIMove
        roomInfo.add(3,canIMove);
        //Add record 4 only if there's no key
        if(noKey == true)
        {
            roomInfo.add(4,true);
        }
        }
        return roomInfo;
    }
    //FINDS ROOM INDEX
    public int findRoomIndex(){
        int roomIndex = 0; 
        for(String room: roomName){
           
            if(room.equalsIgnoreCase(world.getCurrentRoom())) 
            {
            //if the name of the room in the RoomName is the same as the currentRoom then we have the index 
            //So end loop
            break; 
            }
            roomIndex++;
        }
        
        
    return roomIndex;     
    }
}