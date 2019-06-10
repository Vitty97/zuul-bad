import java.util.HashMap;
import java.util.ArrayList;
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
    private ArrayList<Item> items;

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
        items = new ArrayList<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setSalidas(String direccion, Room salida) 
    {
        salidas.put(direccion, salida);
    }
    
    public void addItem(Item item) 
    {
        items.add(item);
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
        return salidas.get(direction);
    }

    public String getExitsString(){
        String aDevolver = "";
        for(String habitacionActual : salidas.keySet()){
            aDevolver += habitacionActual + " ";
        }
        return aDevolver;
    }

    /**
     * Devuelve un texto con la descripcion larga de la habitacion del tipo:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return Una descripcion de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        String aDevolver = "Te encuentras en " + description + "\n";
        if(!items.isEmpty()){
            aDevolver += "Objetos: ";
            for(Item itemActual : items){
                aDevolver += itemActual.getDescripcion() + " ";
            }
        }            
        aDevolver += "\nSalidas: " + getExitsString();   
        return aDevolver;
    }
    
    public Item getObjeto(String id){
        Item objetoABuscar = null;
        boolean buscando = true;
        int cont = 0;
        while(buscando && cont < items.size()){
            Item objetoActual = items.get(cont);
            if(objetoActual.getId().equals(id)){
                objetoABuscar = objetoActual;
                buscando = false;
            }
            cont++;
        }
        return objetoABuscar;
    }
}
