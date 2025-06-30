/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jlbustos
 */
public class Database {
    // Nombre de la base de datos y credenciales de conexión
    String base = "bce_java";
    String url = "jdbc:mysql://localhost:3306/" + base;
    String user = "root";
    String password = "";

    /**
     * Obtiene una conexión activa a la base de datos.
     *
     * @return objeto Connection listo para usar
     * @throws SQLException si falla la conexión
     */
    public Connection getConexion() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
 
    /**
     * Ejecuta una sentencia SQL de acción (INSERT, UPDATE, DELETE).
     * Captura y muestra errores, pero no propaga la excepción.
     *
     * @param sql cadena SQL a ejecutar
     */
    void ejecutar(String sql){
        try{
            // Crear conexión y statement para ejecutar la instrucción
            Connection c = getConexion();
            Statement s = c.createStatement();
            s.execute(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Ejecuta una consulta y devuelve el ResultSet.
     * En caso de error, imprime el mensaje y retorna null.
     *
     * @param sql consulta SQL SELECT
     * @return ResultSet con los datos, o null si falla
     */
    ResultSet ejecutarQuery(String sql) {
        try {
            // Conexión y ejecución de consulta
            Connection c = getConexion();
            Statement s = c.createStatement();
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Ejecuta y retorna la clave generada automáticamente.
     *
     * @param sql sentencia INSERT con retorno de generated keys
     * @return el ID generado, o -1 si ocurre un error
     */
    int ejecutarInt(String sql) {
        try {
            // Preparar statement para obtener generated keys
            Connection c = getConexion();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            // Recuperar la clave generada
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if(rs.next()) id = rs.getInt(1);
            return id;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
 
 
}
