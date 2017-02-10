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
            List <String> itemName, List<String> itemHtml) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.zombieCount = zombieCount;
        this.entranceDirection = entranceDirection;
        this.leadsTo = leadsTo;
        this.locked = locked;
        this.itemName = itemName;
        this.itemHtml = itemHtml; 
        
    }

 

    Room(){
       
   }

    
}
