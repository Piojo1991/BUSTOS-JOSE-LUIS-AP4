/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Database.BalanceDatabase;

import Models.Balance;
import Models.PaseBalance;
import Views.BalanceView;
import java.sql.SQLException;
import java.util.Date;
import Controllers.PaseBalanceController;

/**
 *
 * @author Usuario
 */
public class BalanceController {
    

    /** Vista encargada de la interfaz de usuario para balances. */
    private final BalanceView v;
    /** Objeto que maneja la conexión y operaciones sobre la base de datos de balances. */
    private final BalanceDatabase db = new BalanceDatabase();
    
    /**
     * Constructor que inicializa la vista de balances.
     */
    public BalanceController(){
        this.v = new BalanceView();
    }
    
     /**
     * Inicia el ciclo principal del controlador, mostrando el menú y
     * ejecutando la acción correspondiente a la opción seleccionada.
     * Se repite hasta que el usuario elige salir (opción 0).
     *
     * @throws Exception si ocurre un error durante la ejecución de alguna operación del menú.
     */
    public void iniciar() throws Exception {
        int opcion;
        do {
            opcion = v.menu();
            switch (opcion) {
                case 1 -> agregarBalance();
                case 2 -> recibirBalanceDefinitivamente();
                case 3 -> buscarBalance();
                case 4 -> listarBalances();
                case 5 -> modificarBalance();
                case 6 -> eliminarBalance();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción incorrecta. Intente de nuevo."); 
            }
        } while (opcion != 0);
    }
    
    /**
     * Crea un nuevo balance a partir de los datos ingresados por el usuario,
     * valida su contenido, lo persiste en la base de datos y genera el pase inicial.
     * Muestra un mensaje de confirmación o de error según corresponda.
     *
     * @throws Exception si la validación de datos falla o hay un error al agregar el balance.
     */
    public void agregarBalance() throws Exception{
        Balance b = v.nuevoBalance();
        int oficina_id = 1;
        try {
             // Validar y guardar los cambios
            validarDatosBalance(b);
            int balance_id = db.agregar(b);
            PaseBalance p = new PaseBalance(balance_id,oficina_id,0,"","");
            new PaseBalanceController().elevarBalance(p);
            System.out.println("El balance fue agregado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
        /**
     * Modifica un balance existente
     * @throws Exception si ocurre un error durante la validación o la edición del balance.
     */
      public void modificarBalance() throws Exception{
        // Obtener el balance a modificar
        Balance b = buscarBalanceNroExpediente();
        //Corroborar que exista el balance
        if (b == null) {
                 System.out.println("Balance no encontrado.");
                return;
            }
        //Si tiene fecha definitiva no se puede modificar
        if(estaRecibidoDefinitivamente(b)) {
            System.out.println("Los balances con fecha de recepción definitiva no pueden modificarse.");
                return;
        }
        
        // Solicitar al usuario los nuevos datos    
        v.modificarBalance(b);
        try {
             // Validar y guardar los cambios
            validarDatosBalance(b);
            db.editar(b);
            System.out.println("Los datos del balance fueron modificado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
        /**
     * Permite al usuario buscar un balance mediante dos opciones:
     * 1. Por trimestre, año y jurisdicción.
     * 2. Por número de expediente.
     * Muestra el resultado si se encuentra o un mensaje de error en caso contrario.
     *
     * @throws Exception si ocurre un error durante la búsqueda.
     */
    public void buscarBalance() throws Exception {
        Balance b = new Balance();
        int tipo_busqueda = v.buscarBalance();
        if(tipo_busqueda == 1) b = buscarBalanceTrimestreAñoJurisdiccion();
        else b = buscarBalanceNroExpediente();
        
        if(b.getId() > 0) v.mostrarBalance(b);
        else System.out.println("No se encontró balance con esos datos.");
    }
    
    
    /**
     * Busca un balance en la base de datos según trimestre, año y jurisdicción.
     *
     * @return El balance encontrado o uno vacío si no existe o hubo error de SQL.
     * @throws Exception si ocurre un error genérico durante la operación.
     */
    public Balance buscarBalanceTrimestreAñoJurisdiccion() throws Exception {
        Balance b = new Balance();
        int datos[] = v.buscarBalanceTrimestreAñoJurisdiccion();
        try {
            return db.buscarBalanceTrimestrAñoJurisdicción(datos[0],datos[1],datos[2]);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
    }
    
     /**
     * Busca un balance en la base de datos por número de expediente.
     *
     * @return El balance encontrado o uno vacío si no existe o hubo error de SQL.
     * @throws Exception si ocurre un error genérico durante la operación.
     */
    public Balance buscarBalanceNroExpediente() throws Exception {
        Balance b = new Balance();
        String nro_expediente = v.buscarBalanceNroExpediente();
        try {
            return db.buscarBalanceNroExpediente(nro_expediente);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
    }
    
    /**
     * Marca un balance como recibido definitivamente:
     * 1. Busca el balance por número de expediente.
     * 2. Si existe, llama a recibirDefinitivamente.
     * 3. Si no, informa que no se encontró.
     *
     * @throws Exception Propaga errores de búsqueda o de marcado.
     */
    public void recibirBalanceDefinitivamente() throws Exception {
        try {
            Balance b = buscarBalanceNroExpediente();
            if (b.getId() > 0) recibirDefinitivamente(b);
            else System.out.println("No se encontró balance con nro. de expediente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     /**
     * Lista todos los balances disponibles:
     * 1. Recupera la lista desde la base de datos.
     * 2. La pasa a la vista.
     */
    public void listarBalances(){
        try {
            v.listarBalances(db.listarBalances());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Registra la fecha definitiva de un balance si aún no la tiene:
     * 1. Verifica que no se haya recibido definitivamente.
     * 2. Agrega la fecha definitiva en la base de datos.
     *
     * @param b El balance a confirmar como definitivo.
     * @throws Exception Si hay error al validar o actualizar.
     */
    protected void recibirDefinitivamente(Balance b) throws Exception{
        if(! estaRecibidoDefinitivamente(b)){
            db.agregarFechaDefinitiva(b);
            System.out.println("El balance se recibió correctamente.");
        }else {
            System.out.println("El balance ya cuenta con fecha de recepción definitiva.");
        }
    }

    /**
     * Valida los campos de un balance antes de guardarlo:
     * - Número de expediente: exactamente 15 caracteres.
     * - Fecha provisoria: formato 'aaaa-mm-dd' (10 caracteres).
     * - Trimestre: entre 1 y 4.
     *
     * @param b El balance cuyos datos se van a validar.
     * @throws Exception Si alguno de los campos no cumple el formato o rango.
     */
    protected void validarDatosBalance(Balance b) throws Exception {
          if(b.getNumeroExpediente().length() != 15) throw new Exception("El número de expediente debe estar compuesto por 15 caracteres.");
          if(b.getFechaProvisoria().length() != 10) throw new Exception("La fecha de recepción provisoria debe ser de la forma: aaaa-mm-dd");
          if(b.getTrimestre() > 5) throw new Exception("El número de trimestre o puede ser mayor a 4");
          if(b.getTrimestre() < 0) throw new Exception("El número de trimestre o puede ser mernor a 0");
    }
    
     /**
     * Comprueba si un balance ya tiene fecha de recepción definitiva:
     * Compara la fecha definitiva con la provisoria-
     *
     * @param b El balance a verificar.
     * @return true si ya está recibido definitivamente; false en caso contrario.
     * @throws Exception Si alguna fecha es null o ocurre error durante la comparación.
     */
    protected boolean estaRecibidoDefinitivamente(Balance b) throws Exception {
        return b.getFechaDefinitiva().equalsIgnoreCase(b.getFechaProvisoria());
    }
    
     /**
     * Elimina un balance tras confirmación del usuario:
     * 1. Busca y verifica que exista.
     * 2. Comprueba que no tenga fecha definitiva.
     * 3. Solicita confirmación de eliminación.
     * 4. Elimina el pase asociado y luego el balance de la base de datos.
     */
    public void eliminarBalance() {
        try {
            // 1. Buscar el balance
            Balance b = buscarBalanceNroExpediente();
            // 2. Corroborar que exista
            if (b == null) {
                System.out.println("Balance no encontrado.");
                return;
            }
            // 3. Si tiene fecha definitiva no se puede modificar
            if(estaRecibidoDefinitivamente(b)) {
                System.out.println("Los balances con fecha de recepción definitiva no pueden modificarse.");
                return;
            }

            // 4. Confirmar
            if (!v.confirmarEliminacion(b.getNumeroExpediente())) {
                System.out.println("Eliminación cancelada.");
                return;
            }

            // 5) Ejecutar eliminación. Primero se debe eliminar el pase asociado.
            new PaseBalanceController().eliminarPasePorBlance(b.getId());
            db.eliminar(b.getId());
            System.out.println("Balance eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar balance.");
        }
    }
    
}
