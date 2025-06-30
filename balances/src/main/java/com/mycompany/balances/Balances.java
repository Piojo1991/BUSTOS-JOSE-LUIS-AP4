/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.balances;

import Controllers.BalanceController;
import Controllers.JurisdiccionController;
import java.sql.SQLException;
import util.AppContext;

/**
 *
 * @author jlbustos
 */
public class Balances {

    public static void main(String[] args) throws SQLException, Exception {
        
        // 1. Selecciona la jurisdicción antes de cualquier operación
        int id_jurisdiccion =   new JurisdiccionController().seleccionarJurisdiccion();
        // 2. Guarda la jurisdicción activa en el contexto para usarla en todos los controladores
        AppContext.setJurisdiccionId(id_jurisdiccion);
        
        // 3. Inicia el menú principal para gestionar balances
        new BalanceController().iniciar();
        
        
        
        
        
        //Database conecta = new Database();
        //Connection con = conecta.getConexion();
        //Connection c = new Database().getConexion();
        
        //BalanceDatabase a = new BalanceDatabase();
        //a.agregar("aa",1,1,"2024-10-10","2024-10-10",50);
        
        //BalanceController c = new BalanceController();
        //c.agregarBalance();
        //c.buscarBalance();
        //c.recibirBalanceDefinitivamente();
        //c.listarBalances();
    }
}
