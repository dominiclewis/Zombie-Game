/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;




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
        
        return entranceDirection.size();
    }
    public int numItems(){
        
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
        
         toReturn += ("<br>" + entrance);
         //Space
         String roomLedTo = leadsTo.get(i);
         toReturn += (" : " + roomLedTo);
         //Space
         Boolean isRoomLocked = locked.get(i);
         if(isRoomLocked == true){
             toReturn+= (": Locked!");
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
         
        
        
        return toReturn; 
    }
    
    
    public List mapEntranceToRoomInfo(String direction){
        List roomInfo = new ArrayList();
        
        boolean found = false; 
        
        //Loop through room till found
         for(int i = 0; i< numRooms(); i++)
         {
             //Room found
             if(entranceDirection.get(i).equalsIgnoreCase(direction)){
                 roomInfo.add(0,leadsTo.get(i)); //0 element in arrayList
                 roomInfo.add(1, locked.get(i)); //1 element in the arrayList
                 roomInfo.add(2,true);//This indicates that it was found
                 found = true;
                 break;
             }    
          
         }
         
         //if never found
         if(found == false )
         {
            //Never found leave empty!
         }
        
        
     return roomInfo;   
    }
    
    public void setDoorToUnlocked(String direction){
         for(int i = 0; i< numRooms(); i++)
         {
             //Room 
             if(entranceDirection.get(i).equalsIgnoreCase(direction)){
               locked.set(i, false);
                 
             }
             
             }
    }
     
    public boolean removeItemFromRoom(String itemToRemove){
        boolean removed = false;
        //Scan through for item
        for(String room: itemName)
        {
            if(room.equalsIgnoreCase(itemToRemove))
            {
                //remove item and html
               // roomNum.remove(itemsInInventory);
                itemName.remove(room);
                itemHtml.remove(room);
                
                removed = true;
                break; //So only one is removed at a time 
            }
            
        }
        
        return removed;
    }
    
    
    
}
