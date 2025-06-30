/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Database.JurisdiccionDatabase;
import Views.JurisdiccionView;
import java.sql.SQLException;



/**
 *
 * @author Usuario
 */
public class JurisdiccionController {
    private final JurisdiccionView v;
    private final JurisdiccionDatabase db = new JurisdiccionDatabase();
    
    public JurisdiccionController(){
        this.v = new JurisdiccionView();
    }
    public int seleccionarJurisdiccion(){
        int opcion = 0;
        try {
           opcion = v.seleccionarJurisdiccion(db.listarJurisdicciones());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return opcion;
    }
}
