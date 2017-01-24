package zombiestarter; 

/*
Author: Dominic Lewis
*/

public class World{   //ACTUAL CLASS, anything can look at these variables however we should use getters and setters
    //Attributes
    private String info; 
    private boolean quit; 
    
    //Constructor (THIS IS THE INFO WE USE TO CONSTRUCT THE CLASS * CALLTIME)
World(String info){
    
    this.info = info;  //this forces class info as opposed to passed one    
 
    
}    
//SETSQUIT
    public void setQuit(boolean quit) {
        //THIS means we're addressing the one in the class ddeclaration 
        this.quit = quit;
    }
    //Returns quit result 
    public boolean getQuit()
    {
        return quit; 
    }
    
    //Right click inside the class, insert code, getter 
    public String getInfo() {
        return info;
    }
   
}