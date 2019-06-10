import java.util.Stack; 
import java.util.ArrayList; 
/**
 * Write a description of class Jugador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jugador
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> pilaHabitaciones;
    private ArrayList<Item> mochila;

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(Room habitacionInicial)
    {
        pilaHabitaciones = new Stack<>();
        currentRoom = habitacionInicial;
        mochila = new ArrayList<>();
    }
    
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExits(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            pilaHabitaciones.push(currentRoom);
            currentRoom = nextRoom;            
            mirar();
        }
    }
    
    public void mirar() {  
        System.out.println(currentRoom.getLongDescription());
    }
    
    public void volver(){
        if(!pilaHabitaciones.empty()){
            currentRoom = pilaHabitaciones.pop();
            mirar();
        }
        else{
            System.out.println("No puedes volver mas");
        }
    }
    
    public void comer(){
        System.out.println("Acabas de comer y ya no tienes hambre");
    }
    
    public void coger(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("¿Coger que?");
            return;
        }

        String id = command.getSecondWord();
        Item objeto = currentRoom.getObjeto(id);

        if(objeto != null) {
            if(objeto.puedeCogerse()){
                mochila.add(objeto);
            }
            else{
                System.out.println("No parece que puedas llevarte esto");
            }
        }
        else{
            System.out.println("No puedes encontrar ese objeto en la habitacion");
        }
    }
}
