/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Database;

import Models.PaseBalance;

/**
 *
 * @author jlbustos
 */
public class PaseBalanceDatabase extends Database{
    
    /**
        * Agrega un nuevo pase de balance a la base de datos.
        *
        * @param balance_id           Identificador del balance.
        * @param oficina_origen_id    Identificador de la oficina origen.
        * @param oficina_destino_id   Identificador de la oficina destino (0 → NULL).
        * @param fecha_elevado        Fecha en que se eleva el pase (YYYY-MM-DD) o vacío.
        * @param fecha_recepcion      Fecha en que se recepciona el pase (YYYY-MM-DD) o vacío.
        */
    public void elevar( int balance_id, int oficina_origen_id, int oficina_destino_id, String fecha_elevado, String fecha_recepcion ){
        // 1. Formatear fechas para SQL o asignar NULL cuando estén vacías
        fecha_elevado = (fecha_elevado != null && !fecha_elevado.isEmpty())
            ? "'" + fecha_elevado + "'"
            : "NULL";
        fecha_recepcion = (fecha_recepcion != null && !fecha_recepcion.isEmpty())
            ? "'" + fecha_recepcion + "'"
            : "NULL";
         // 2. Convertir oficina_destino_id = 0 en literal SQL NULL
        String sz_oficina_destino_id = (oficina_destino_id != 0)
            ? String.valueOf(oficina_destino_id)
            : "NULL";
        
        // 3. Construir y ejecutar la sentencia INSERT
        String sql = "INSERT INTO pase_balance(balance_id,oficina_origen_id,oficina_destino_id,fecha_pase_elevado,fecha_pase_recibido)"
            + " VALUES('"+balance_id+"',"+oficina_origen_id+","+sz_oficina_destino_id+","+fecha_elevado+","+fecha_recepcion+")";

            ejecutar(sql);
    }
    
    /**
     * Sobrecarga: eleva pase usando un objeto PaseBalance.
     *
     * @param p Objeto que contiene los datos del pase.
     */
    public void elevar(PaseBalance p){
        elevar(p.getBalanceId(),p.getOficinaOrigen(), p.getOficinaDestino(),p.getFechaElevado(), p.getFechaRecepcion());
    }
    
    /**
     * Registra la recepción de un balance en la base de datos.
     *
     * @param id                 Identificador del registro de pase.
     * @param fecha_recepcion    Fecha de recepción (YYYY-MM-DD).
     */
    public void recibir(int id, String fecha_recepcion) {
        String sql = "UPDATE pase_balance SET fecha_pase_recibido = '" + fecha_recepcion + "' WHERE id_pase_balance = " + id + "";
        ejecutar(sql);
    }
    
    /**
     * Sobrecarga: marca como recibido usando un objeto PaseBalance.
     *
     * @param p * @param p Objeto que contiene los datos del pase.
     */
    public void recibir(PaseBalance p) {
        recibir(p.getId(), p.getFechaRecepcion());
    }

    /**
     * Elimina un pase de balance de la base de datos.
     *
     * @param id Identificador del registro de pase a borrar.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM pase_balance WHERE id_pase_balance = " + id + "";
        ejecutar(sql);
    }
    
    /**
     * Elimina un pase de balance de la base de datos.
     *
     * @param balance_id Identificador del ID del balance al que pertenece.
     */
    public void eliminarPorBalance(int balance_id) {
        String sql = "DELETE FROM pase_balance WHERE balance_id = " + balance_id + "";
        ejecutar(sql);
    }
}
