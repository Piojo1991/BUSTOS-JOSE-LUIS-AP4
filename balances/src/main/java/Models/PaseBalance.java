/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author jlbustos
 */
public class PaseBalance {
    // Atributos
    private int id_pase_balance;
    private int balance_id;
    private int oficina_origen_id;
    private int oficina_destino_id;
    private String fecha_pase_elevado;
    private String fecha_pase_recibido;
    
    /**
     * Constructor vacío.   
     */
    public PaseBalance(){}
    
       /**
     * Constructor principal.
     * Crea un pase con fecha de elevación y oficina destino opcional.
     *
     * @param balance_id           ID del balance asociado
     * @param oficina_origen_id    ID de la oficina que eleva
     * @param oficina_destino_id   ID de la oficina destino (0 si aún no asignado)
     * @param fecha_pase_elevado   Fecha en que se eleva el pase (YYYY-MM-DD)
     * @param fecha_pase_recibido  Ignorado en este constructor; se inicia en null
     */
    public PaseBalance( int balance_id, int oficina_origen_id, int oficina_destino_id, String fecha_pase_elevado, String fecha_pase_recibido ){
        this.balance_id = balance_id;
        this.oficina_origen_id = oficina_origen_id;
        this.oficina_destino_id = oficina_destino_id;
        this.fecha_pase_elevado = fecha_pase_elevado;
        this.fecha_pase_recibido = null; // Al crear, no hay recepción
    }
    
    /**
     * @return ID del pase (PK)
     */
    public int getId() {
        return id_pase_balance;
    }
    
    /**
     * @param id nuevo ID
     */
    public void setId(int id) {
        this.id_pase_balance = id;
    }
    
    /**
     * @return ID del balance asociado
     */
    public int getBalanceId() {
        return balance_id;
    }

    /**
     * @param id establece el balance asociado
     */
    public void setBalanceId(int id) {
        this.balance_id = id;
    }
    
    /**
     * @return ID de la oficina origen
     */
    public int getOficinaOrigen() {
        return oficina_origen_id;
    }

    /**
     * @param oficina_id establece la oficina origen
     */
    public void setOficinaOrigen(int oficina_id) {
        this.oficina_origen_id = oficina_id;
    }
    
    /**
     * @return ID de la oficina destino (0 si no asignado)
     */
    public int getOficinaDestino() {
        return oficina_destino_id;
    }

    
     /**
     * @param oficina_id establece la oficina destino
     */
    public void setOficinaDestino(int oficina_id) {
        this.oficina_destino_id = oficina_id;
    }
    
    /**
     * @return Fecha en que se elevó el pase
     */
    public String getFechaElevado() {
        return fecha_pase_elevado;
    }

    /**
     * @param fecha_pase_elevado actualiza la fecha de elevación
     */
    public void setFechaElevado(String fecha_pase_elevado) {
        this.fecha_pase_elevado = fecha_pase_elevado;
    }
    
    /**
     * @return Fecha en que se recepcionó el pase (null si aún no)
     */
    public String getFechaRecepcion() {
        return fecha_pase_recibido;
    }

    /**
     * @param fecha_pase_recibido marca la fecha de recepción
     */
    public void setFechaRecepcion(String fecha_pase_recibido) {
        this.fecha_pase_recibido = fecha_pase_recibido;
    }
}
