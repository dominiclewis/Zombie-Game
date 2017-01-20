package zombiestarter; 

/*
Author: Dominic Lewis
*/

public class World{
    //Attributes
    private String info; 
    
    //Constructor/Setter 
World(String info){
    
    this.info = info;  //this forces class info as opposed to passed one    

}    

    //Right click inside the class, insert code, getter 
    public String getInfo() {
        return info;
    }
   
}