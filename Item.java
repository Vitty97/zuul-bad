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
    private boolean puedeCogerse;
    private boolean usable;
    private int modificador;
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String id, String descripcion, int peso, boolean puedeCogerse, boolean usable, int modificador){
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.puedeCogerse = puedeCogerse;
        this.usable = usable;
        this.modificador = modificador;
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
    
    public boolean puedeCogerse(){
        return puedeCogerse;
    }
    
    public String toString(){
        return "Id: " + id + " Descripcion: " + descripcion + " Peso: " + peso;
    }
    
    public int usar(){
        int mod = 0;
        if(usable){
            mod = modificador;
            if(mod > 0){
                System.out.println("Te sientes mas fuerte");
            }
            else{
                System.out.println("Te sientes mas debil");
            }
        }
        else{
            System.out.println("Este objeto no se puede usar");
        }
        return mod;
    }
}
