package zombiestarter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dominic Lewis
 */
/**
 * class that implements the ZombieBot interface and plays the game
 *
 * @author br-gaster
 */
public class ZombieBot implements world.ZombieBot {

    private World world;
    private List<Room> roomList;
    private List<String> roomName;
    private Player player;

    ZombieBot(World world, List<Room> roomList, List<String> roomName, Player player) {
        this.world = world;
        this.roomList = roomList;
        this.roomName = roomName;
        this.player = player;
    }

    /**
     * should game quit
     *
     * @return return true if exit program, otherwise false
     */
    @Override

    public boolean shouldQuit() {
        return world.getQuit();
    }

    /**
     * prompt to be displayed to user
     *
     * @return
     */
    @Override
    public String begin() {
        return world.getStartHTML();
    }

    /**
     * compute current score
     *
     * @return current score
     */
    @Override
    public int currentScore() {
        return player.getScore();
    }

    /**
     * should timer be enabled? if should be enabled, then method returns true,
     * and goes back into state of not enable.
     *
     * @return true if enable timer, otherwise false
     */
    //change timer run to false just before leaving room
    public boolean enableTimer() {
        if (roomList.get(findRoomIndex()).getZombieCount() != 0 && (roomList.get(findRoomIndex()).getTimerRun()) == false) {
            roomList.get(findRoomIndex()).setTimerRun(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * should timer be disabled? if should be disabled, then method returns
     * true, and goes back into state of don't disable.
     *
     * @return
     */
    public boolean disableTimer() {
        if (world.getDisableTimer() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * process player commands
     *
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();
        //Doesn't get here with no space 
        try {
            String[] cmds = cmd.split(" "); // split cmd by space

            switch (cmds[0].toLowerCase()) {

                case "help":
                    result.add("Info: Returns an information string <br> Look: Shows the current view of the room you are in <br> Move: attempts to move to a room, if locked will attempt to unlock it if you have a key <br> Pickup: attempts to pickup an item in the room<br> kill: Attempts to slay a zombie if you have a daisy or a chainsaw in your inventory <br> Drop: Attempts to drop an item <br> Quit: Quits the game <br> Inventory: Shows the current inventory ");
                    break;
                case "info":
                    result.add(world.getInfo());
                    break;
                case "look":
                    result.add(roomList.get(findRoomIndex()).Look()); //Looks at the current room
                    break;
                case "move":
                    //Direction
                    world.setDisableTimer(true);
                    disableTimer();
                    world.setDisableTimer(false);

                    List roomInfo = canIMove(cmds[1]);
                    if (!roomInfo.isEmpty()) {
                        if (roomInfo.get(3).equals(true)) //if we can move
                        {
                            //Toggle the timer back for the current room so it runs again
                            //if return to room with zombies in 
                            roomList.get(findRoomIndex()).setTimerRun(false);

                            world.setCurrentRoom(roomInfo.get(0).toString());
                            result.add("Moved to " + (roomInfo.get(0).toString()));
                        } else if (roomInfo.get(4).equals(true)) {
                            result.add("You need a key to enter");
                        }
                    } else {
                        result.add("No such room!");
                    }
                    /*
                       if (finalRoomController() == true)
                       {
                           System.out.println("test");
                           result.add("Congratulations your completed zombieBots");
                           System.out.println("test");
                              world.setQuit(true);
                       }
*/
                       
                    break;
                case "pickup":

                    boolean sucessful = pickUp(cmds[1]);
                    if (sucessful == true) {
                        result.add(cmds[1] + " successfully picked up");
                    } else {
                        result.add("Could not pick up " + cmds[1]);
                    }
                    /*
                    if (finalRoomController() == true)
                       {
                           result.add("Congratulations you've completed zombieBots");
                           world.setQuit(true);
                       }
*/
                    break;
                case "kill":

                    //If status is 0
                    //Output no zombies in the room
                    //if status is 1 
                    //Pause timer and output zombie killed
                    //If status is 2 
                    //Zombies present but no weapon in inventory
                    switch (kill()) {
                        case 0:
                            result.add("No Zombies in room");
                            break;
                        case 1:

                            result.add("Zombie killed!");
                            if (roomList.get(findRoomIndex()).getZombieCount() == 0) {
                                world.setDisableTimer(true);
                            }
                            break;

                        case 2:
                            result.add("No suitable weapon in inventory");
                            break;
                    }
                    /*
                    if (finalRoomController() == true)
                       {
                           result.add("Congratulations you've comleted zombieBots");
                           world.setQuit(true);
                       }
*/
                    break;
                case "drop":
                    boolean sucessfull = drop(cmds[1].toLowerCase());
                    if (sucessfull == true) {
                        result.add(cmds[1] + " dropped!");
                    } else {
                        result.add(cmds[1] + " is not in inventory");
                    }
                    if (finalRoomController() == true)
                       {
                           result.add("Congratulations you've comleted zombieBots");
                           world.setQuit(true);
                           
                       }
                    break;
                case "timerexpired":
                    world.setQuit(true);

                    break;
                case "quit":
                    world.setQuit(true);
                    break;
                case "inventory":
                    String backPackHtml = world.getInventoryHtml();
                    List<String> inventory = player.getUserInventory();
                    List<String> inventoryHtml = player.getUserInventoryHtml();
                    String toOutPut = backPackHtml;
                    int i = 0;
                    for (String index : inventory) {

                        toOutPut += " ";
                        //Add item
                        toOutPut += index;

                        toOutPut += " ";
                        //add it's html
                        toOutPut += inventoryHtml.get(i);
                        i++;
                    }
                    result.add(toOutPut);
                    break;
                case "blank":
                    result.add("I beg your pardon?");
                    break;
                case "":
                    break;
                default:
                    result.add("<b>That's not a verb I recognise.</b>");
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBounds) {
            result.add("Try again!");
        }

        return result;
    }

    //MAPS AN ENTRANCE DIRECTION TO A ROOM
    public List entranceToRoom(String direction) {

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
    public List canIMove(String direction) {
        boolean canIMove = false;
        boolean noKey = false;
        //Find what Room I'm in
        List roomInfo = entranceToRoom(direction); //returns the roomInfo

        if (!roomInfo.isEmpty()) //if room info isn't empty 
        {
            if (roomInfo.get(2).equals(true))//Object comparison not primitive?
            {
                canIMove = true;
            }

            //If I can move due to finding the room but the rooms locked        
            if ((canIMove == true) && (roomInfo.get(1).equals(true))) {
                //try to remove a key
                if ((player.removeItem("key")) == true) { //key sucesffuly removed 
                    canIMove = true;
                    //Change door to be unlocked
                    roomList.get(findRoomIndex()).setDoorToUnlocked(direction);
                } else {
                    noKey = true;
                    canIMove = false;
                }
            } else if ((canIMove == true) && (roomInfo.get(1).equals(false))) {
                //Door is not locked
                canIMove = true;
            }
            //add record 3 to match canIMove
            roomInfo.add(3, canIMove);
            //Add record 4 only if there's no key
            if (noKey == true) {
                roomInfo.add(4, true);
            }
        }
        return roomInfo;
    }

    //FINDS ROOM INDEX
    public int findRoomIndex() {
        int roomIndex = 0;
        for (String room : roomName) {

            if (room.equalsIgnoreCase(world.getCurrentRoom())) {
                //if the name of the room in the RoomName is the same as the currentRoom then we have the index 
                //So end loop
                break;
            }
            roomIndex++;
        }

        return roomIndex;
    }

    public boolean drop(String item) {
        boolean found = false;

        //Check if item is in inventory 
        List<String> playerInventory = player.getUserInventory();
        List<String> invHtml = player.getUserInventoryHtml();
        int i = 0;
        String itemName = "";
        String itemHtml = "";
        //this is really just to get the indexes
        for (String invValue : playerInventory) {

            if (invValue.equalsIgnoreCase(item)) {
                itemName = invValue;
                itemHtml = invHtml.get(i);
                found = true;
                break;
            } else {

                //else just for fun 
                i++;
            }

        }
        //remove item from inventory if found
        player.removeItem(item);
        //add it to the room 
        roomList.get(findRoomIndex()).addItemToRoom(itemName, itemHtml);
        return found;
    }

    public boolean pickUp(String item) {
        //Check if item is in room
        //Get the lists
        List<String> roomItems = roomList.get(findRoomIndex()).getItemName();
        List<String> roomHtml = roomList.get(findRoomIndex()).getItemHtml();
        //Vars for below loop
        int i = 0;
        boolean found = false;
        for (String roomItem : roomItems) {

            //Loop through items and find match if possible
            if (roomItem.equalsIgnoreCase(item)) {

                found = true;
                player.addToInventory(item, roomHtml.get(i));

                roomList.get(findRoomIndex()).removeItemFromRoom(item);
                break;
            } else {

                i++;
            }
        }

        return found;
    }

    public int kill() {
        boolean succesfull = false, removed = false; //can someone check if these are actually used?
        int staticNumZomb = roomList.get(findRoomIndex()).getZombieCount();
        int status = 0;

        switch (staticNumZomb) {

            case 0:
                status = 0; //No zombies to kill
                succesfull = false;

                break;

            //Every number except no zombies
            default:
                //Try to remove 
                //daisy or chainsaw
                removed = player.removeItem("chainsaw");
                if (removed == false) { //No chainsaw present
                    succesfull = false;
                    //try to remoe daisy
                    removed = player.removeItem("daisy");

                }
                if (removed == true) {
                    status = 1; //Chainsaw or daisy found 
                    //Daisy or chainsaw found 
                    succesfull = true;
                    //Kill the zombie 
                    roomList.get(findRoomIndex()).killZombieInRoom();
                } else {
                    status = 2;//Could not remove the item (No daisy or chainsaw available)
                }
                break;

        }
        //If status is 0
        //Output no zombies in the room

        //if status is 1 
        //Pause timer and output zombie killed
        //If status is 2 
        //Zombies present but no weapon in inventory
        return status;
    }
    //Detects if the final room has been found
    public boolean canQuitRoomFound(){
        boolean finalRoom = false;
        //check if room name is the same as the end room name
        if(world.getCurrentRoom().equalsIgnoreCase(world.getEnd()))
        {
            finalRoom = true;
        }
        return finalRoom; 
    }
    public boolean zombiesInTheRoom()
    {
      boolean zombieFound = true; 
      if(roomList.get(findRoomIndex()).getZombieCount() == 0) //if the zombie count of the current room is 0 
      {
          zombieFound = false;
      }
      return zombieFound; 
              
    }
    
    public boolean itemsInTheRoom(){
        
        boolean itemsInRoom = true; 
        List itemNamesInFinalRoom = roomList.get(findRoomIndex()).getItemName();
        if (itemNamesInFinalRoom.isEmpty() == true) //if the list is empty 
        {
            itemsInRoom = false;
        }
        return itemsInRoom;
    }
    //check if theres no zombies in the room
    
    //false if can't quit true if can
    public boolean finalRoomController()
    {
        boolean overall = false;
        if(canQuitRoomFound() == true)
        {
            if ( (itemsInTheRoom() && zombiesInTheRoom()) == false)
            {
                overall = true;
            }
        }
        return overall;
    }
    
}
