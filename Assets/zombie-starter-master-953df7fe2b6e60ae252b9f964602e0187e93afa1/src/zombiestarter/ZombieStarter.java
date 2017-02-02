/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ZombieServer;
import world.WorldLoader;


import world.WEntrance; 
import world.WRoom;
/**
 *
 * @author your details here
 */
public class ZombieStarter {    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        // use a try/catch block to handle the case when opening
        // a socket fails...
         try {
            // TODO you need to define classes to represent the world
            // then here you should use WorldLoader to load the world and 
            // convert it to your representation of the world to play 
            // the game with your version of ZombieBot.
             //Set up new world
             
             WorldLoader w1 = new WorldLoader();  //Create new instance of the WorldLoader class *Load the Json file 
              
             World world = new World(w1.getInfo(),w1.getInventoryHtml(),w1.getStartHtml(),w1.getStart()); //Inside the worldLoader class to access the methods inside
            
  // lets display all the items that can be found in the world
        //displayItems(w1.getItems());
       
       Item.displayItemsInRoom(w1.getItems());
       
        System.out.println("\n");
        
        // lets display all the rooms contained in the world
        
        // finally display which room is the start room
        System.out.println("Play begins in the room: " + w1.getStart());
        
             
            // create an instane of our server to commnicate with the
            // web frontend.
            InetAddress ip = ip = InetAddress.getLocalHost();
            
            // now connect to the server
            ZombieServer zs = new ZombieServer(
                    // get host address, rather than using 127.0.0.1, as this
                    // will then be displayed when server waits for connection
                    // which allows the address to then be typed into client.
                    ip.getHostAddress(),
                    8085,
                    new ZombieBot(world)
            );
         } catch (UnknownHostException ex) {
            Logger.getLogger(
                    ZombieStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    //Maybe for the constructors of WRoom, Witem and WEntrance we don't want to actually construct them but simply acess their getters and setters
    public static void displayItems(List<WItem> items) {
        System.out.println("The world contains the following some number of the items\n");
        for (WItem item: items) {
            System.out.println(item.getName() + " its HTML is " + item.getHtml());
        }
    }
*/
    
    public static void displayEntrances(List<WEntrance> entrances) {
        for (WEntrance e : entrances) {
            System.out.println(e.getDirection() + " -> " + e.getTo());
            if (e.isLocked()) {
                System.out.println("it is locked");
            }
            else {
                System.out.println("not locked");
            }
        }
    }
     public static void displayRooms(WorldLoader wl) {
        System.out.println("----------------------------------------------------------------");
        //DataType of what we're looking at  == WROOM 
        //room === The current variable
        //Loops over every wl index 
        //WHEN WE USE THE GETTERS HERE WE ARE ACESSING WROOM'S as we've declared that to be our datatype so can use the operations that come with it 
        for (WRoom room : wl) {
            System.out.println("The name of the room is: " + room.getName());
            System.out.println("and its description is  \"" + room.getDescription() + "\"");

            System.out.println("it has the following entrances\n");
            displayEntrances(room.getEntrances());

            System.out.println("the following items are placed around the room\n");
            for (String itemName: room.getItems()) {
                System.out.println(itemName);
            }

            System.out.println("\nthere are " + room.getZombieCount() + " Zombies in the room");
            System.out.println("----------------------------------------------------------------");
        }
    }
}
