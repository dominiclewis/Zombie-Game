/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

import java.util.Iterator;
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
    private String info;
    private String start; //start room
    private String end; //end room
    private String startHTML; 
    private List <WItem> items;
    private String inventoryHtml;
    private Iterator WRoomIterator; 

    //Contructor
    World(String info,String start, String end,String startHTML,List<WItem> items,String inventoryHtml,Iterator WRoomIterator) {
        this.info = info; //assign info to paramater
        this.start = start;
        this.end = end;
        this.startHTML = startHTML;
        this.items = items;
        this.inventoryHtml = inventoryHtml;
        this.WRoomIterator = WRoomIterator;
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
    //QUIT
    public boolean getQuit() {

        return quit;

    }
 
//INFO
//RETURNS INFO STRING
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setItems(List<WItem> items) {
        this.items = items;
    }

    public void setInventoryHtml(String inventoryHtml) {
        this.inventoryHtml = inventoryHtml;
    }

    public void setWRoomIterator(Iterator WRoomIterator) {
        this.WRoomIterator = WRoomIterator;
    }

}
