import java.util.Stack; 
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

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(Room habitacionInicial)
    {
        pilaHabitaciones = new Stack<>();
        currentRoom = habitacionInicial;
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
}
