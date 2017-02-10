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
//Indexes should be shared/corresponding
List<String> itemName = new ArrayList<>();
List<String> itemHtml = new ArrayList<>(); 



    public Room(String roomName, String roomDescription,int zombieCount, 
            List<String> entranceDirection, List <String> leadsTo, List <Boolean> locked,
            List<String> itemName, List<String> itemHtml) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.zombieCount = zombieCount;
        this.entranceDirection = entranceDirection;
        this.leadsTo = leadsTo;
        this.locked = locked;
        this.itemName = itemName;
        this.itemHtml = itemHtml; 
        
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

    public void setItemName(List<String> itemName) {
        this.itemName = itemName;
    }

    public List<String> getItemHtml() {
        return itemHtml;
    }

    public void setItemHtml(List<String> itemHtml) {
        this.itemHtml = itemHtml;
    }
    public int numRooms()
    {
        System.out.println(entranceDirection.size());
        return entranceDirection.size();
    }
    public int numItems(){
        System.out.println(itemName.size());
        return itemName.size();
    }
    //LOOK
    public String Look(){
        String toReturn ="Room Name: ";
        
         String name = getRoomName(); //works
         String description = getRoomDescription();         
         toReturn += name;
         toReturn += ("<br>Description: " + description);
         toReturn+="<br>Zombies: "; //Space //Works
         int numZombies = getZombieCount();
         toReturn += (Integer.toString(numZombies));//Turn numOfZombies to a String
         
         //Loop
         for(int i = 0; i< numRooms(); i++)
         {     
             
         String entrance = entranceDirection.get(i); //accessing directly as local to method
             System.out.println("Entrance test"+ entrance);
         toReturn += ("<br>" + entrance);
         //Space
         String roomLedTo = leadsTo.get(i);
         toReturn += (" : " + roomLedTo);
         //Space
         Boolean isRoomLocked = locked.get(i);
         if(isRoomLocked == true){
             toReturn+= (" : Locked!");
         } else{
             toReturn+= (": Unlocked" );
         }
         
          //BR?
         }
         //Items 
         //Loop
         toReturn+="<br>Items<br>";
         for(int i =0; i< numItems(); i++)
         {
         String itemNameString = (String)itemName.get(i);
         toReturn += itemNameString;
         //Space
         toReturn += " ";
         String itemHtmlString = itemHtml.get(i);
         toReturn += itemHtmlString;
         toReturn += "  ";
         //BR
        }
         
        
        System.out.println(toReturn);
        return toReturn; 
    }
    
}
