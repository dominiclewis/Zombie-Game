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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ZombieServer;
import world.WEntrance;
import world.WItem;
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

            World world = new World(w1.getInfo(), w1.getStart(), w1.getEnd(), w1.getStartHtml(), w1.getItems(), w1.getInventoryHtml()); //Inside the worldLoader class to access the getInfo method //WORLD ONLY IS USED TO STORE worldloader stuff
            List <String> roomName = new ArrayList<String>(); //This will be used to store the roomNames 
            //It will be passed to world to use the indexes etc..
            List<Room> roomList = new ArrayList<Room>();
            //Load rooms instances up inside this array 

            //This loops over everything and does the magic and adds hopfully I wiill test in the morning 
            //This loop should cycle over every room *Hopefully*
            for (WRoom room : w1) {
                String name = room.getName();
                roomName.add(name); //For indexes
                
                String description = room.getDescription();
                int zombieCount = room.getZombieCount();

                //This is for below
                List<String> entranceDirection = new ArrayList<>();
                List<String> leadsTo = new ArrayList<>();
                List<Boolean> locked = new ArrayList<>();
                
                //Now attempt to get entrances
                if (room.getEntrances().size() > 0) {
                    //multiple for each room so can be declared local to for loop not nested however
                    for (WEntrance entrance : room.getEntrances()) //WROOMs get entrance method requires a list of WEntrances which is why it's the datatype
                    {
                        entranceDirection.add(entrance.getDirection());
                        leadsTo.add(entrance.getTo());
                        locked.add(entrance.isLocked());
                        
                          
                    }
                    
                    
                    }//end if
                    
                //Items 
                List itemName = new ArrayList<>();
                List<String> itemHtml = new ArrayList<>();

                for (WRoom roomVar : w1) {
                    

                    if (roomVar.getName().equalsIgnoreCase(name))  { 
                        //The room is the currentRoom
                        //Scanner reads the items that are contained in the currentRoom
                         Scanner scan = new Scanner(roomVar.getItems().toString());
                         //Loop scanner
                       while(scan.hasNext())
                       {
                           String temp = scan.next();
                           //Start scrolling through all the items in the game
                        for (WItem item : w1.getItems()) {
                            //if the string in the room(temp) matches the the name of the item then add it get it's name
                            if(temp.contains(item.getName()))
                            {
                          itemName.add(item.getName());
                                
                          itemHtml.add(item.getHtml());
                                
                                  
                            }
                                  
                    }
                       }

                    }
                }
                
                roomList.add(new Room(name,description,zombieCount,entranceDirection
                ,leadsTo , locked ,itemName, itemHtml ));

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
                    new ZombieBot(world,roomList,roomName));
        } catch (UnknownHostException ex) {
            Logger.getLogger(
                    ZombieStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
