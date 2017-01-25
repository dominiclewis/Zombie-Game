/*
 * Author: 
          -Dominic Lewis  
          - William Blackie
          - Jake Davies 
 * Desc: This file contains the heart of dynamic game play for Zombies. 
 *       you need to implement each of the methods, as per the game play,
 *       adding support for processing commands comming from the client.
    Testing URL: http://www.cems.uwe.ac.uk/~br-gaster/courses/2016-2017/OOSD1/
 */
package zombiestarter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author your details
 */

/**
 * class that implements the ZombieBot interface and plays the game
 * @author br-gaster
 */
public class ZombieBot implements world.ZombieBot {
    
    private World world; 
    
    ZombieBot(World world) {
      this.world = world; 
    }
    
    /**
     * should game quit 
     * @return return true if exit program, otherwise false 
     */ 
    @Override
            //This checks that the world quit attribute is set to true, if so it returns true
    public boolean shouldQuit() {
  
       if(world.getQuit() == true)
       {
           return true; 
       } 
       //Quit hasn't been entered yet so do not quit
       else{ 
        return false;
       }
    }  
    
    /**
     * prompt to be displayed to user
     * @return 
     */
    @Override
    public String begin() {
        return "this is the message that gets displayed when game begins";
    }
    
    /**
     * compute current score
     * @return current score
     */
    @Override
    public int currentScore() {
        return 0;
    }

    /**
     * should timer be enabled? if should be enabled, then method returns true,
     * and goes back into state of not enable.
     * @return true if enable timer, otherwise false
     */
    public boolean enableTimer()  { 
        return false;
    }

    /**
     * should timer be disabled? if should be disabled, then method returns
     * true, and goes back into state of don't disable.
     * @return
     */
    public boolean disableTimer() {
        return false;
    }
    
    /**
     * process player commands
     * @param cmd to be processed
     * @return output to be displayed
     */
    @Override
    public List<String> processCmd(String cmd) {
        ArrayList<String> result = new ArrayList<>();
        
        String[] cmds = cmd.split(" "); // split cmd by space
        
        switch(cmds[0]) {
            case "info":
                result.add(world.getInfo());
                break;
            case "look":
                result.add("handle look command");
                break;
            case "move":
                result.add("handle move command");
                break;
            case "pickup":  
                result.add("handle pickup command");
                break;
            case "kill":
                result.add("handle kill command");
                break;
            case "drop":
                result.add("handle drop command");
                break;
            case "timerexpired":
                result.add("handle timeexpired command");
                break;
            case "quit":
                //This is where we alter the world quit attribute with the setter
                 world.setQuit(true); 
                break;
            case "inventory":
                result.add("handle inventory command");
                break;
            case "blank":
                result.add("I beg your pardon?");
                break;
            case "":
                break;
            default:
                result.add("<b>That's not a verb I recognise.</b>");
        }
        
        return result;
     }
}