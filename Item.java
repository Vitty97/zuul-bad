
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private int peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso){
        this.descripcion = descripcion;
        this.peso = peso;
    }

    public String getDescripcion(){
        return descripcion;
    }
    
    public int getPeso(){
        return peso;
    }
    
    public String toString(){
        return descripcion + "  Peso: " + peso;
    }
}
