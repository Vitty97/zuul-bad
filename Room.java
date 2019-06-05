import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private HashMap<String, Room> salidas;
    private String description;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room northWest, Room southEast) 
    {
        if(north != null)
            salidas.put("north", north);
        if(east != null)
            salidas.put("east", east);
        if(south != null)
            salidas.put("south", south);
        if(west != null)
            salidas.put("west", west);
        if(northWest != null)
            salidas.put("northWest", northWest);   
        if(southEast != null)
            salidas.put("southEast", southEast);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public Room getExits(String direction)
    {
        Room habitacionADevolver = null;
        if(direction.equals("north")) {
            habitacionADevolver = salidas.get("north");
        }
        if(direction.equals("east")) {
            habitacionADevolver = salidas.get("east");
        }
        if(direction.equals("south")) {
            habitacionADevolver = salidas.get("south");
        }
        if(direction.equals("west")) {
            habitacionADevolver = salidas.get("west");
        }
        if(direction.equals("northWest")) {
            habitacionADevolver = salidas.get("northWest");
        }
        if(direction.equals("southEast")) {
            habitacionADevolver = salidas.get("southEast");
        }
        return habitacionADevolver;
    }
    
    public String getExitsString(){
        String aDevolver = "";
        if(salidas.get("north") != null) {
            aDevolver += "north ";
        }
        if(salidas.get("east") != null) {
            aDevolver += "east ";
        }
        if(salidas.get("south") != null) {
            aDevolver += "south ";
        }
        if(salidas.get("west") != null) {
            aDevolver += "west ";
        }
        if(salidas.get("northWest") != null) {
            aDevolver += "northWest ";
        }
        if(salidas.get("southEast") != null) {
            aDevolver += "southEast ";
        }
        return aDevolver;
    }
}
