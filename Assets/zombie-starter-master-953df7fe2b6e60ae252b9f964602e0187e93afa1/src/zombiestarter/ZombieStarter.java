/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ZombieServer;
import world.WRoom;
import world.WorldLoader;

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
              
             World world = new World(w1.getInfo(),w1.getStart(),w1.getEnd(),w1.getStartHtml(),w1.getItems(),w1.getInventoryHtml()); //Inside the worldLoader class to access the getInfo method //WORLD ONLY IS USED TO STORE worldloader stuff
             
             
            
             
             List<Room> roomList = new ArrayList<Room>();
             //Load rooms instances up inside this array 
            for (WRoom everyRoom : w1)
            {
                //This loop should cycle over every room *Hopefully*
               //STORE ALL THE INFO ABOUT THE ROOM THEN PASS IT IN
                roomList.add(new Room());
                
            }
//           

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
                    new ZombieBot(world));
         } catch (UnknownHostException ex) {
            Logger.getLogger(
                    ZombieStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}