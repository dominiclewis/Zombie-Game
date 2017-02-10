/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Dominic
 */
public class Room {
private String roomName;
private String roomDescription;
int zombieCount; 
//Indexes should be shared
List<String> entranceDirection = new ArrayList<>();
List<String> leadsTo = new ArrayList<>();
List<Boolean> locked = new ArrayList<>();
int numRooms = entranceDirection.size();
//Indexes should be shared/corresponding
List itemName = new ArrayList<>();
List<String> itemHtml = new ArrayList<>(); 
int numItems = itemName.size();


    public Room(String roomName, String roomDescription,int zombieCount, 
            List<String> entranceDirection, List <String> leadsTo, List <Boolean> locked,
            List itemName, List<String> itemHtml) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.zombieCount = zombieCount;
        this.entranceDirection = entranceDirection;
        this.leadsTo = leadsTo;
        this.locked = locked;
        this.itemName = itemName;
        this.itemHtml = itemHtml; 
        
    }

    public int getNumRooms() {
        return numRooms;
    }

    public int getNumItems() {
        return numItems;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public int getZombieCount() {
        return zombieCount;
    }

    public void setZombieCount(int zombieCount) {
        this.zombieCount = zombieCount;
    }

    public List<String> getEntranceDirection() {
        return entranceDirection;
    }

    public void setEntranceDirection(List<String> entranceDirection) {
        this.entranceDirection = entranceDirection;
    }

    public List<String> getLeadsTo() {
        return leadsTo;
    }

    public void setLeadsTo(List<String> leadsTo) {
        this.leadsTo = leadsTo;
    }

    public List<Boolean> getLocked() {
        return locked;
    }

    public void setLocked(List<Boolean> locked) {
        this.locked = locked;
    }

    public List<String> getItemName() {
        return itemName;
    }

    public void setItemName(List itemName) {
        this.itemName = itemName;
    }

    public List<String> getItemHtml() {
        return itemHtml;
    }

    public void setItemHtml(List<String> itemHtml) {
        this.itemHtml = itemHtml;
    }

    //LOOK
    public String Look(){
        String toReturn ="Room Name: ";
        
         String name = getRoomName();
         toReturn += name;
         toReturn+="<br>Zombies: "; //Space
         int numZombies = getZombieCount();
         toReturn += (Integer.toString(numZombies));//Turn numOfZombies to a String
         
         //Loop
         for(int i = 0; i< getNumRooms(); i++)
         {     
             
         String entrance = entranceDirection.get(i); //accessing directly as local to method
         toReturn += ("<br>" + entrance);
         //Space
         String roomLedTo = leadsTo.get(i);
         toReturn += (" leads to " + roomLedTo);
         //Space
         Boolean isRoomLocked = locked.get(i);
         if(isRoomLocked == true){
             toReturn+= (" is locked" +isRoomLocked);
         } else{
             toReturn+= (" isn't locked" +isRoomLocked);
         }
         
          //BR?
         }
         //Items 
         //Loop
         toReturn+="<br>Items<br>";
         for(int i =0; i<numItems; i++)
         {
         String itemNameString = (String)itemName.get(i);
         toReturn += itemNameString;
         //Space
         toReturn += "< ";
         String itemHtmlString = itemHtml.get(i);
         toReturn += itemHtmlString;
         toReturn += "  ";
         //BR
        }
         
        
        System.out.println(toReturn);
        return toReturn; 
    }
    
}
