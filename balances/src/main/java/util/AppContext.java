/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Usuario
 */
public class AppContext {
    private static int jurisdiccion_id;
    private AppContext() { }
    public static int getJurisdiccionId() { return jurisdiccion_id; }
    public static void setJurisdiccionId(int id) { jurisdiccion_id = id; }
}
