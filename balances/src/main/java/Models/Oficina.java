/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author jlbustos
 */
public class Oficina {
    private int id_oficina;
    private String nombre;
    private int nivel;
    
    /**
     * Constructor vacío.   
     */
    public Oficina(){}
    
    /**
     * Constructor para crear una Oficina sin ID
     *
     * @param nombre nombre de la oficina
     */
    public Oficina( String nombre ){
        this.nombre = nombre;
    }
    
     /**
     * Constructor completo incluyendo ID
     *
     * @param id_oficina ID de la oficina
     * @param nombre     nombre de la oficina
     */
    public Oficina( int id_oficina, String nombre ){
        this.id_oficina = id_oficina;
        this.nombre = nombre;
    }
    
      /**
     * @return ID de la oficina
     */ 
    public int getId() {
       return id_oficina;
    }

     /**
     * @param id nuevo ID de la oficina
     */
    public void setId(int id) {
        this.id_oficina = id;
    }
    
      /**
     * @return nombre de la oficina
     */
    public String getNombre() {
        return nombre;
    }
    
     /**
     * @param nombre actualiza el nombre de la oficina
     */
    public void setNombre(String nombre) {
       this.nombre = nombre;
    }
    
        /**
     * @return nivel de la oficina
     */
    public int getNivel() {
       return nivel;
    }

     /**
     * @param nivel actualiza el nivel jerárquico
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}

