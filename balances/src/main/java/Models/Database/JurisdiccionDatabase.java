/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Database;

import Models.Jurisdiccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlbustos
 */
public class JurisdiccionDatabase extends Database{
    
    /**
     * Obtiene todas las jurisdicciones registradas en la base de datos.
     *
     * @return Lista de objetos Jurisdiccion
     * @throws SQLException en caso de error al ejecutar la consulta
     */
    public List<Jurisdiccion> listarJurisdicciones() throws SQLException {
        // 1. Definir la sentencia SQL
        String sql = "SELECT * FROM jurisdicciones";
        // 2. Ejecutar la consulta y obtener el ResultSet
        ResultSet rs = ejecutarQuery(sql);
        // 3. Recorrer cada fila y convertirla en objeto Jurisdiccion
        List <Jurisdiccion> jurisdiccion = new ArrayList<>();
        while (rs.next()) { jurisdiccion.add(datosJurisdiccion(rs)); }
        // 4. Devolver la lista completa    
        return jurisdiccion;
    }
    
    /**
     * Construye un objeto Jurisdiccion a partir de la fila actual del ResultSet.
     *
     * @param rs ResultSet posicionado en una fila válida
     * @return Objeto Jurisdiccion con id y nombre
     * @throws SQLException en caso de error al leer columnas
     */
    protected Jurisdiccion datosJurisdiccion(ResultSet rs) throws SQLException{
        return new Jurisdiccion( rs.getInt(1), rs.getString(2) );
    }

}
