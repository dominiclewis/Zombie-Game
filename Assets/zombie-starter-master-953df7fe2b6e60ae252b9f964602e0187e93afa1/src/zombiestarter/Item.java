/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;
import world.WItem;
/**
 *
 * @author Dominic
 */
public class Item {
    private String inventoryHtml; 
    
 List<String> playerInventory = new ArrayList<String>(); //List for player inventory
//Inventory outputs as the invenotry html, then the images 
   
    Item(String inventoryHtml){
        this.inventoryHtml = inventoryHtml; 
        
    }
    
    
    //getter for the inventory html
    
    public String getInventoryHtml(){
        
        return inventoryHtml; 
    }
    /*
//Why is this here 
       public static void displayItemsInRoom(List<WItem> items) {
        System.out.println("The Room contains the following some number of the items\n");
        for (WItem item: items) {
            System.out.println(item.getName() + " its HTML is " + item.getHtml());
        }
*/
 //   }
    
    
    
    
}
