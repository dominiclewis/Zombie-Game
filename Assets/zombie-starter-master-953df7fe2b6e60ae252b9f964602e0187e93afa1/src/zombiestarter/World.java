/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombiestarter;

/**
 *
 * @author Dominic //This Class will be used to store Info from the worldloader
 * about the world and implement the methods in the zombie bot if necessary
 * Nothing more
 */
public class World {

    private boolean quit = false;
    private String info;

    //Contructor
    World(String info) {
        this.info = info; //assign info to paramater

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

}
