import java.util.Stack; 
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Jugador jugador;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        jugador = new Jugador(createRooms());
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room comedorCentral, habitaciones, servicios, descansillo, bodega, salaDeMaquinas, salaDeControl, habitacionCapitan, salaMaquinasSup;
        Room currentRoom;
        // create the rooms
        comedorCentral = new Room("La sala principal de dirigible, las paredes son de madera, el suelo esta enmoquetado de color purpura,\n " +
            "el techo cubierto por bonitos frescos y adornado con lamparas de ara�a completa una habitacion que es la representacion misma\n " +
            "del lujo y la ostentacion.");
        habitaciones = new Room("No son las habitaciones mas lujosas del dirigible pero cuentan con cama, armario, mesita y lampara propias.");
        servicios = new Room("Unos lujosos servicios tanto para caballeros como para damas equipados con grandes espejos y amueblados de marmol");
        descansillo = new Room("Un pasillo decorados con cuadros que conecta varias salas.");
        bodega = new Room("Aqui se guardan los equipajes y otras cosas, nada especial, en principio...");
        salaDeMaquinas = new Room("La sala mas importante del dirigible ya que es la que os mantiene en el aire, y por lo tanto tambien la mas peligrosa");
        salaDeControl = new Room("Desde aqui se controla el dirigible y actualmente esta ocupada por los bandidos");
        habitacionCapitan = new Room("Por alguna razon no puedes salir, la hizo un mago");
        salaMaquinasSup = new Room("Aqui se encuentran varias maquinas de refrigeracion");
        // initialise room exits
        comedorCentral.setSalidas("norte", servicios);
        comedorCentral.setSalidas("este", salaDeControl);
        comedorCentral.setSalidas("sur", habitaciones);
        comedorCentral.setSalidas("oeste", descansillo);
        habitaciones.setSalidas("norte", comedorCentral);
        habitaciones.setSalidas("sureste", habitacionCapitan);
        servicios.setSalidas("sur", comedorCentral);
        descansillo.setSalidas("norte", salaDeMaquinas);
        descansillo.setSalidas("este", comedorCentral);
        descansillo.setSalidas("sur", bodega);
        bodega.setSalidas("norte", descansillo);
        salaDeMaquinas.setSalidas("sur", descansillo);
        salaDeMaquinas.setSalidas("noroeste", salaMaquinasSup);
        salaDeControl.setSalidas("oeste", comedorCentral);
        
        salaDeMaquinas.addItem(new Item("llaveInglesa", "Llave Inglesa", 5, true, false, 0));
        salaDeMaquinas.addItem(new Item("tuberia", "Tuberia", 6, true, false, 0));
        habitaciones.addItem(new Item("baul", "Baul", 40, false, false, 0));
        comedorCentral.addItem(new Item("cerveza", "Una jarra de cerveza", 2, true, true, 5));
        
        currentRoom = comedorCentral;  // start game outside
        return currentRoom;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        jugador.mirar();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            jugador.goRoom(command);
        }
        else if (commandWord.equals("mirar")) {
            jugador.mirar();
        }
        else if (commandWord.equals("volver")) {
            jugador.volver();
        }
        else if (commandWord.equals("comer")) {
            jugador.comer();
        }
        else if (commandWord.equals("coger")) {
            jugador.coger(command);
        }
        else if (commandWord.equals("mochila")) {
            jugador.mochila();
        }
        else if (commandWord.equals("soltar")) {
            jugador.soltar(command);
        }
        else if (commandWord.equals("usar")) {
            jugador.usar(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Los comandos validos son: ");
        System.out.println(parser.showCommands());
        System.out.println();        
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }   
}
