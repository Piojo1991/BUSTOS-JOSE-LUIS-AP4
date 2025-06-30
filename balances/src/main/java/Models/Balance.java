/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Usuario
 */
public class Balance {
    private int id_balance;
    private String numero_expediente;
    private int trimestre;
    private int año;
    private String fecha_provisoria;
    private String fecha_definitiva;
    private int cantidad_rendiciones;
    private int jurisdiccion_id;
    
    /* Constructor de Balance nulo */
    
    public Balance(){}
    
    /* Constructor de Balance sin ID */
    public Balance( String numero_expediente, int trimestre, int año, String fecha_provisoria,
                    String fecha_definitiva, int cantidad_rendiciones ){
        this.numero_expediente = numero_expediente;
        this.trimestre = trimestre;
        this.año = año;
        this.fecha_provisoria = fecha_provisoria;
        this.fecha_definitiva = fecha_definitiva;
        this.cantidad_rendiciones = cantidad_rendiciones;
        this.jurisdiccion_id = jurisdiccion_id;
    }
    
    /* Constructor de Balance con ID */
    public Balance(int id, String numero_expediente, int trimestre, int año, String fecha_provisoria,
            String fecha_definitiva, int cantidad_rendiciones, int jurisdiccion_id) {
        this.id_balance = id;
        this.numero_expediente = numero_expediente;
        this.trimestre = trimestre;
        this.año = año;
        this.fecha_provisoria = fecha_provisoria;
        this.fecha_definitiva = fecha_definitiva;
        this.cantidad_rendiciones = cantidad_rendiciones;
        this.jurisdiccion_id = jurisdiccion_id;
    }
    
    public int getId() {
        return id_balance;
    }

    public void setId(int id) {
        this.id_balance = id;
    }
    
    public String getNumeroExpediente() {
        return numero_expediente;
    }

    public void setNumeroExpediente(String numero_expediente) {
        this.numero_expediente = numero_expediente;
    }
    
    public int getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(int trimestre) {
        this.trimestre = trimestre;
    }
    
    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    public String getFechaProvisoria() {
        return fecha_provisoria;
    }

    public void setFechaProvisoria(String fecha_provisoria) {
        this.fecha_provisoria = fecha_provisoria;
    }
    
    public String getFechaDefinitiva() {
        if(fecha_definitiva == null || "0000-00-00".equals(fecha_definitiva)) { return "-"; }
        return fecha_definitiva;
    }

    public void setFechaDefinitiva(String fecha_definitiva) {
        this.fecha_definitiva = fecha_definitiva;
    }
    
    public int getCantidadRendiciones() {
        return cantidad_rendiciones;
    }

    public void setCantidadRendiciones(int cantidad_rendiciones) {
        this.cantidad_rendiciones = cantidad_rendiciones;
    }
    
    public int getJurisdiccion() {
        return jurisdiccion_id;
    }

    public void setJurisdiccion(int id) {
        this.jurisdiccion_id = id;
    }
}
