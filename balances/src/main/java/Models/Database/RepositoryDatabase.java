/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Database;

/**
 *
 * @author Usuario
 */

import java.sql.SQLException;

/**
 * Contrato genérico para repositorios CRUD.
 *
 * @param <T>  Tipo de entidad gestionada.
 * @param <ID> Tipo de la clave primaria de la entidad.
 */
public interface RepositoryDatabase<T, ID> {

    /**
     * Persiste una entidad en la base de datos.
     * Si la entidad es nueva devuelve la clave generada; si existe, puede actualizarla.
     *
     * @param entity Entidad a guardar.
     * @return Clave primaria de la entidad guardada.
     * @throws SQLException En caso de error de acceso a datos.
     */
    ID guardar(T entity) throws SQLException;

    /**
     * Elimina la entidad cuya clave primaria coincida con la dada.
     *
     * @param id Clave primaria de la entidad a eliminar.
     * @throws SQLException En caso de error de acceso a datos.
     */
    void eliminar(ID id) throws SQLException;
}