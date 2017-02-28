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
    private int score = 0;
    
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
       //increase score
       score++;
   }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    public boolean removeItem(String itemToRemove){
        boolean removed = false;
        int j = 0; 
        //Scan through for item
        for(String itemsInInventory: userInventory)
        {
            
            if(itemsInInventory.equalsIgnoreCase(itemToRemove))
            {
                //remove item and html
                userInventory.remove(itemsInInventory);
                userInventoryHtml.remove(j);
                removed = true;
                break; //So only one is removed at a time 
            }
            else{ 
                j++;
            }
            
        }
        
        return removed;
    }
}


