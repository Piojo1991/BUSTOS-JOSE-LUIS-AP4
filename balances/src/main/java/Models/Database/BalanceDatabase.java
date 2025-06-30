/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Database;

import Models.Balance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**S
 *
 * @author jlbustos
 */
public class BalanceDatabase extends Database implements RepositoryDatabase<Balance,Integer> {
    
       @Override
    public Integer guardar(Balance b) throws SQLException {
        return agregar(b);
    }
    
    @Override
    public void eliminar(Integer id) throws SQLException {
        eliminar(id);
    }

    /**
     * Inserta un nuevo balance en la base de datos.
     *
     * @param numero_expediente     String con el formato “xxxxx-xxxxxxx-x”
     * @param trimestre             Trimestre (1–4)
     * @param año                   Año (por ejemplo, 2025)
     * @param fecha_provisoria      Fecha de recepción provisoria (YYYY-MM-DD)
     * @param fecha_definitiva      Fecha de recepción definitiva (YYYY-MM-DD)
     * @param cantidad_rendiciones  Cantidad de rendiciones asociadas
     * @param jurisdiccion_id       ID de la jurisdicción (desde AppContext)
     * @return ID generado del nuevo balance
     * @throws SQLException si falla la inserción
     */
    public int agregar( String numero_expediente, int trimestre, int año, String fecha_provisoria,
                    String fecha_definitiva, int cantidad_rendiciones, int jurisdiccion_id ) throws SQLException{
        String sql = "INSERT INTO balances(numero_expediente,trimestre,año,fecha_provisoria,fecha_definitiva,cantidad_rendiciones, jurisdiccion_id)"
                + " VALUES('"+numero_expediente+"',"+trimestre+","+año+",'"+fecha_provisoria+"','"+fecha_definitiva+"',"+cantidad_rendiciones+","+jurisdiccion_id+")";
       return ejecutarInt(sql);
    }
     
    
    /**
     * Sobrecarga de agregar: usa un objeto Balance completo.
     *
     * @param b Objeto Balance con todos sus campos
     * @return ID generado
     * @throws SQLException si falla la inserción
     */
    public int agregar(Balance b) throws SQLException{
       return agregar(b.getNumeroExpediente(),b.getTrimestre(),
                b.getAño(),b.getFechaProvisoria(),
                b.getFechaDefinitiva(),b.getCantidadRendiciones(), b.getJurisdiccion());
    }
    
     /**
     * Actualiza los datos de un balance existente excepto jurisdicción. 
     *
     * @param id                    ID del balance a modificar
     * @param numero_expediente     Nuevo número de expediente
     * @param trimestre             Nuevo trimestre
     * @param año                   Nuevo año
     * @param fecha_provisoria      Nueva fecha provisoria
     * @param fecha_definitiva      (no se usa aquí)  
     * @param cantidad_rendiciones  Nueva cantidad de rendiciones
     */
    public void editar(int id,String numero_expediente, int trimestre, int año, String fecha_provisoria,
            String fecha_definitiva, int cantidad_rendiciones) {
        String sql = "UPDATE balances SET numero_expediente = '"+numero_expediente+"',trimestre = '"+trimestre+"',año = '"+año+"',fecha_provisoria = '"+fecha_provisoria+"',cantidad_rendiciones = '"+cantidad_rendiciones+"' WHERE id_balance = "+id+"";
        ejecutar(sql);
    }

    
    /**
     * Sobrecarga de editar: usa un objeto Balance.
     *
     * @param b Objeto Balance con ID y campos modificados
     */
    public void editar(Balance b) {
        editar(b.getId(),b.getNumeroExpediente(), b.getTrimestre(),
                b.getAño(), b.getFechaProvisoria(),
                b.getFechaDefinitiva(), b.getCantidadRendiciones());
    }
    
    /**
     * Elimina un balance por su ID.
     *
     * @param id ID del balance a eliminar
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM balances WHERE id_balance = " + id;
        ejecutar(sql);
    }
    
    /**
     * Registra la fecha definitiva de un balance, usando la provista.
     *
     * @param id                ID del balance
     * @param fecha_definitiva  Fecha definitiva (YYYY-MM-DD)
     */
    public void agregarFechaDefinitiva(int id,String fecha_definitiva) {
        String sql = "UPDATE balances SET fecha_definitiva = '" + fecha_definitiva + "' WHERE id_balance = "+id+"";
        ejecutar(sql);
    }
    
    
    /**
     * Sobrecarga: usa objeto Balance para fijar fecha definitiva.
     *
     * @param b Balance con ID y fecha provisoria
     */
    public void agregarFechaDefinitiva(Balance b) {
        agregarFechaDefinitiva(b.getId(), b.getFechaProvisoria());
    }

  /**
     * Busca balance por trimestre y año en la jurisdicción activa.
     *
     * @param jurisdiccion ID de jurisdicción
     * @param trimestre    Trimestre
     * @param año          Año
     * @return Balance encontrado o instancia vacía si no existe
     * @throws SQLException si falla la consulta
     */
    public Balance buscarBalanceTrimestrAñoJurisdicción(int jurisdiccion,int trimestre, int año) throws SQLException {
        String sql = "SELECT * FROM balances WHERE trimestre= " + trimestre + " AND año= "+ año;
        ResultSet rs = ejecutarQuery(sql);
        if(rs.next()){
            return datosBalance(rs);
        }else{
            return new Balance();
        } 
    }
    
    /**
     * Busca un balance por su número de expediente.
     *
     * @param expediente Número de expediente
     * @return Balance encontrado o instancia vacía si no existe
     * @throws SQLException si falla la consulta
     */
    public Balance buscarBalanceNroExpediente(String expediente) throws SQLException {
        String sql = "SELECT * FROM balances WHERE numero_expediente= '" + expediente +"'";
        ResultSet rs = ejecutarQuery(sql);
        
        if(rs.next()) return datosBalance(rs);
        else return new Balance();
    }
    
    /**
     * Lista todos los balances de la base de datos.
     *
     * @return Lista de objetos Balance
     * @throws SQLException si falla la consulta
     */
    public List<Balance> listarBalances() throws SQLException {
        String sql = "SELECT * FROM balances";
        ResultSet rs = ejecutarQuery(sql);
        List <Balance> balances = new ArrayList<>();
        
        while (rs.next()) { balances.add(datosBalance(rs)); }
        return balances;
    }
    
    /**
     * Mapea la fila actual del ResultSet a un objeto Balance.
     *
     * @param rs ResultSet posicionado en la fila
     * @return Objeto Balance poblado
     * @throws SQLException si no puede leer columnas
     */
    protected Balance datosBalance(ResultSet rs) throws SQLException{
        return new Balance( rs.getInt(1),rs.getString(3),  rs.getInt(4),  rs.getInt(5),
                            rs.getString(6), rs.getString(7),  rs.getInt(8),rs.getInt(2) );
    }

}