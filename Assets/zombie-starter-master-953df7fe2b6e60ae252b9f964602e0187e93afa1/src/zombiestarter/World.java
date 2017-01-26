package zombiestarter; 

/*
Author: Dominic Lewis
*/

public class World{   //Private Variables Public class so anything can use the getters and setters with private variables
    
    private String info;  //Stores the world info string
    private boolean quit;  //Stores whether or not we should quit
    private String displayInventory; //HTML used for formatting the inventory?
    private String startStringHTML; //String to be displayed to the user at the start of the game
    private String start; //String that is used for the start room I think 
    //Constructor (THIS IS THE INFO WE USE TO CONSTRUCT THE CLASS * CALLTIME)
World(String info, String displayInventory, String startString,String start){
    
    //this.info refers to the class attribute while = refers to the paramater
    //Basically this says the attribute = whatever is passed in through the paramater
    
    this.startStringHTML = startString; //link the constructor to the attribute;
    this.info = info;     
    this.displayInventory = displayInventory; 
    this.start = start; 
}    

//Getter for displayInventory
public String getDisplayInventory(){
   //Returns class attribute
    return displayInventory;
}
//SETSQUIT
    public void setQuit(boolean quit) {
        //THIS means we're addressing the one in the class ddeclaration 
        this.quit = quit;
    }
    //Returns quit variable   
    public boolean getQuit()
    {
        return quit; 
    }
    
    //Right click inside the class, insert code, getter 
    public String getInfo() {
        return info;
    }
   //Getter for start string HTML
    public String getStartStringHTML()
    {
        return startStringHTML;
    } 
    
    //Getter for Start Room
    public String getStart(){
        return start; 
        
    }
           
}