/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Database.PaseBalanceDatabase;
import Models.PaseBalance;

/**
 *
 * @author Usuario
 */
public class PaseBalanceController {
    private final PaseBalanceDatabase db = new PaseBalanceDatabase();
    
    public PaseBalanceController(){
    }
    
        
    public void elevarBalance(PaseBalance p) throws Exception{
        try {
            db.elevar(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void recibirBalance(PaseBalance p) throws Exception {
        try {
            db.recibir(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void eliminar(PaseBalance p) throws Exception{
        try {
            db.eliminar(p.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPasePorBlance(int balance_id) throws Exception{
        try {
            db.eliminarPorBalance(balance_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
