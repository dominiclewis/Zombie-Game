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
public class Inventory {
    private String inventoryHtml; 
      
    List<WItem> itemsAvailable = new ArrayList<WItem>(); 
            
 List<String> playerInventory = new ArrayList<String>(); //List for player inventory 
 String playerInventoryString; //This is actually string which will be returned 
 //This is just a string of item names
//Inventory outputs as the inventory html, then the images of the items
   
    Inventory(String inventoryHtml, List itemsAvailable){
        this.inventoryHtml = inventoryHtml; 
        this.itemsAvailable = itemsAvailable; 
    };
    
    
    //getter for the inventory html
    
    public String getInventoryHtml(){
        
        return inventoryHtml; 
    }
    //this method adds the(STRING) name of an item to be added to the attribute array of the players inventory 
public String pickUp(String itemNameToPickUp){
    //This needs to validated 
     playerInventory.add(itemNameToPickUp);
     return itemNameToPickUp +" picked up";
}

public String constructPlayerInventoryString(){
    String stringToReturn = "";
    //check list isnt' empty 
    int amountInInventory = playerInventory.size();
    if(amountInInventory != 0) //if the inventory isn't empty
    {
        for(int i = 0; i<amountInInventory; i++ ){
          //get the html for the corresponding item
         
          stringToReturn+= ((getHtmlForItem(playerInventory.get(i),itemsAvailable)) +" ");
        }
        
    } 
    return stringToReturn; 
}
//This function returns the html for th eitem we are looking for 
String getHtmlForItem(String nameOfItemSought, List <WItem> items){
   String stringToReturn="";
   
 for (WItem item: items)
 {
  if(nameOfItemSought.toLowerCase().equals(item.getName().toLowerCase())){
      return item.getHtml(); //returns the html 
  }   
 }
   
   return stringToReturn;
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
