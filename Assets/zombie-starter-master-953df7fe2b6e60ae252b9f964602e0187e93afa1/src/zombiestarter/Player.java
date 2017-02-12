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
public class Player {
    private List<String> userInventory = new ArrayList<>();
    private List <String> userInventoryHtml = new ArrayList<>();

    
    public Player(){
    
}
    public List<String> getUserInventory() {
        
        return userInventory;
    }

    public List<String> getUserInventoryHtml() {
        
        return userInventoryHtml;
    }
    
   public void addToInventory(String item, String html)
   {
       userInventory.add(item);
       userInventoryHtml.add(html);
       
   }
    
    
    public boolean removeItem(String itemToRemove){
        boolean removed = false;
        //Scan through for item
        for(String itemsInInventory: userInventory)
        {
            if(itemsInInventory.equalsIgnoreCase(itemToRemove))
            {
                //remove item and html
                userInventory.remove(itemsInInventory);
                userInventoryHtml.remove(itemsInInventory);
                removed = true;
                break; //So only one is removed at a time 
            }
            
        }
        
        return removed;
    }
}


