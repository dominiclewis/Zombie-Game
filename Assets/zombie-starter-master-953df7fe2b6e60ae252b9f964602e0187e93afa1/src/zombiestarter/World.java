/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.util.List;
import world.WItem;

/**
 *
 * @author Dominic //This Class will be used to store Info from the worldloader
 * about the world and implement the methods in the zombie bot if necessary
 * Nothing more
 */
public class World {

    private boolean quit = false;
    private boolean disableTimer;
    final private String info;
    final private String start; //start room
    final private String end; //end room
    final private String startHTML;
    final private List<WItem> items;
    final private String inventoryHtml;
    private String currentRoom; //This string will store the current room 
    private boolean finishQuit;

    //Contructor
    World(String info, String start, String end, String startHTML, List<WItem> items, String inventoryHtml) {
        this.info = info; //assign info to paramater
        this.start = start;
        this.end = end;
        this.startHTML = startHTML;
        this.items = items;
        this.inventoryHtml = inventoryHtml;

    }
    public boolean getFinishQuit()
    {
        return finishQuit;
    }
    public void setFinishQuit(boolean finishQuit)
    {
        this.finishQuit = finishQuit;
    }
    public boolean getDisableTimer() {
        return disableTimer;
    }

    public boolean getfinishQuit() {
        return disableTimer;
    }

    public void setfinishQuit(boolean finishQuit) {
        this.finishQuit = finishQuit;
    }

    public void setDisableTimer(boolean disableTimer) {
        this.disableTimer = disableTimer;
    }

    public String getCurrentRoom() {

        return currentRoom;
    }

    public void setCurrentRoom(String currentRoomUpdate) {
        this.currentRoom = currentRoomUpdate;
    }

    //START STRING
    public String getStart() {
        return start;
    }

    //END STRING
    public String getEnd() {
        return end;
    }

    //START HTML 
    public String getStartHTML() {
        return startHTML;
    }

    //Sets quit with param //QUIT
    public void setQuit(boolean quitValue) {
        this.quit = quitValue;
    }

    //Gets quit with param  
    //QUIT FUNCTION
    public boolean getQuit() {

        return quit;

    }

//INFO
//RETURNS INFO STRING
    public String getInfo() {
        return info;
    }

    //QUIT ROOM
    public boolean getQuitRoom() {
        return quit;
    }

    public List<WItem> getItems() {
        return items;
    }

    public String getInventoryHtml() {
        return inventoryHtml;
    }

}
