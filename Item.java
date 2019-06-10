
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String id;
    private String descripcion;
    private int peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String id, String descripcion, int peso){
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
    }

    public String getDescripcion(){
        return descripcion;
    }
    
    public String getId(){
        return id;
    }
    
    public int getPeso(){
        return peso;
    }
}
