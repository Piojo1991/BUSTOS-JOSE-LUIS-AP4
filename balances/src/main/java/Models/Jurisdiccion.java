/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Usuario
 */
public class Jurisdiccion {
    private int id_jurisdiccion;
    private String nombre;
    
    /* Constructor nulo */
    public Jurisdiccion(){}
    
    /* Constructor de Jurisdiccion sin ID */
    public Jurisdiccion( String nombre ){
        this.nombre = nombre;
    }
    
     /* Constructor de Jurisdiccion con ID */
    public Jurisdiccion( int id_jurisdiccion, String nombre ){
        this.id_jurisdiccion = id_jurisdiccion;
        this.nombre = nombre;
    }
    
    public int getId() {
       return id_jurisdiccion;
    }

    public void setId(int id) {
        this.id_jurisdiccion = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nomre) {
       this.nombre = nombre;
    }
}
