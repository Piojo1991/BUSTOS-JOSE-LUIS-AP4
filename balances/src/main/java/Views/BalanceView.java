/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import java.util.Scanner;
import Models.Balance;
import java.util.List;
import util.AppContext;

/**
 *
 * @author jlbustos
 */
public class BalanceView {
    // Scanner para lecturas por consola; se inicializa en el constructor
    private final Scanner scanner;
    
    /**
     * Constructor.
     * Inicializa el Scanner para toda la instancia.
     */
    public BalanceView() {
       this.scanner = new Scanner(System.in);
    }
    
  /**
     * Muestra el menú principal de balances y devuelve la opción elegida.
     *
     * @return opción numérica seleccionada por el usuario
     */
    public int menu() {
        System.out.println("________________________________________");
        System.out.println("Opciones de Balances:");
        System.out.println("________________________________________");
        System.out.println("1. Agregar nuevo balance");
        System.out.println("2. Recepción definitiva del balance");
        System.out.println("3. Buscar balances");
        System.out.println("4. Listar balances");
        System.out.println("5. Modificar datos de balance");
        System.out.println("6. Eliminar balance");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        
        // Validación de entrada
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor ingrese un número válido: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return opcion;
    }
    
    /**
     * Solicita y crea un Balance nuevo a partir de los datos ingresados.
     *
     * @return objeto Balance con los campos cargados
     */
    public Balance nuevoBalance() {
        Balance b = new Balance();
        System.out.println("________________________________________");
        System.out.println("Agregar balance:");
        System.out.println("________________________________________");
        
        System.out.print("Ingrese el número de expediente (xxxxx-xxxxxxx-x): ");
        b.setNumeroExpediente(scanner.nextLine());
        
        System.out.print("Ingrese el trimestre: ");
        b.setTrimestre(scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Ingrese el año (aaaa): ");
        b.setAño(scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Ingrese la fecha de recepción provisoria (aaaa-mm-dd): ");
        b.setFechaProvisoria(scanner.nextLine());
        
        System.out.print("Ingrese la cantidad de rendiciones: ");
        b.setCantidadRendiciones(scanner.nextInt());
        
        // Obtiene jurisdicción del contexto
        b.setJurisdiccion(AppContext.getJurisdiccionId());
        return b;
    }
    
    /**
     * Permite modificar los campos seleccionados de un Balance existente.
     *    Dejar vacío conserva el valor actual.
     *
     * @param b Balance original a editar
     * @return mismo Balance con campos actualizados
     */
    public Balance modificarBalance(Balance b) {
        mostrarBalance(b);
        System.out.println("________________________________________");
        System.out.println("Deje vacío para conservar el valor actual.\n");
        
        System.out.print("Nuevo trimestre (" + b.getTrimestre() + "): ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            b.setTrimestre(Integer.parseInt(input));
        }
        
        System.out.print("Nuevo año (" + b.getAño() + "): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            b.setAño(Integer.parseInt(input));
        }
        
        System.out.print("Nueva fecha provisoria (" + b.getFechaProvisoria() + "): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            b.setFechaProvisoria(input);
        }

        System.out.print("Nueva cantidad de rendiciones (" + b.getCantidadRendiciones() + "): ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            b.setCantidadRendiciones(Integer.parseInt(input));
        }
        
        return b;
    }
    
    /**
     * Solicita al usuario el criterio de búsqueda de balances.
     *
     * @return 1 para buscar por trimestre/año, 2 para buscar por número de expediente
     */
    public int buscarBalance() {
        int tipo_busqueda;
        System.out.println("________________________________________");
        System.out.println("Buscar balance:");
        System.out.println("________________________________________");
        
        System.out.println("Selecciona una opción de búsqueda");
        System.out.println("1. Búsqueda de balance por trimestre/año");
        System.out.println("2. Búsqueda de balance por nro. de expediente");
        tipo_busqueda = (scanner.nextInt());
        scanner.nextLine();
        
        return tipo_busqueda;
    }
    
    /**
     * Recopila LOS parámetros de búsqueda por trimestre, año y jurisdicción.
     *
     * @return array [jurisdicción, trimestre, año]
     */
    public int[] buscarBalanceTrimestreAñoJurisdiccion() {
        int datos[] = new int[3];
        System.out.print("Ingrese la jurisidicción del balance: ");
        datos[0] = (scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Ingrese el trimestre: ");
        datos[1] = (scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Ingrese el año: ");
        datos[2] =  (scanner.nextInt());
        scanner.nextLine();

        return datos;
    }
    
    /**
     * Pide el número de expediente para búsqueda.
     *
     * @return String ingresado por el usuario
     */
    public String buscarBalanceNroExpediente() {
        String nro_expediente;
        System.out.println("Ingrese el nro. de expediente (xxxxx-xxxxxxx-x): ");
        nro_expediente = (scanner.nextLine());
        return nro_expediente;
    }
    
    /**
     * Muestra todos los detalles de un Balance.
     *
     * @param b Balance a mostrar
     */
    public void mostrarBalance(Balance b) {
        System.out.println("________________________________________");
        System.out.println("Datos del balance:");
        System.out.println("________________________________________");
        
        System.out.println("ID: "+b.getId());
        System.out.println("Num. Expediente: "+b.getNumeroExpediente());
        System.out.println("Trimestre: "+b.getTrimestre());
        System.out.println("Año: "+b.getAño());
        System.out.println("Fecha Recep. Provisoria: "+b.getFechaProvisoria());
        System.out.println("Fecha Recep. Definitiva: "+b.getFechaDefinitiva());
        System.out.println("Cantidad Rendiciones: "+b.getCantidadRendiciones());
    }
    
    /**
     * Lista varios balances tabulados
     *
     * @param balances lista de objetos Balance
     */
    public void listarBalances(List <Balance> balances) {
        System.out.println("________________________________________");
        System.out.println("Balances recibidos:");
        System.out.println("________________________________________");
        System.out.println("ID | Nº Expte. | Trim. | Año | Fecha Recp. Prov.| Fecha Recp. Def. | Cant. Rendiciones");
        
        for (Balance b: balances){
            mostrarBalanceInLine(b);
        }
    }
    
    
    /**
     * Muestra los datos de un sólo balance en una línea.
     *
     * @param b Balance a mostrar
     */
    public void mostrarBalanceInLine(Balance b) {
        System.out.println( b.getId() + " | " + b.getNumeroExpediente() + " | " +
                            b.getTrimestre() + " | " + b.getAño() + " | " + 
                            b.getFechaProvisoria()+ " | " + b.getFechaDefinitiva()+ " | " + 
                            b.getCantidadRendiciones() );
    }
    
    /**
     * Confirmación del usuario antes de eliminar un balance.
     *
     * @param expediente número de expediente que se va a eliminar
     * @return true si confirma (S/Si), false en otro caso
     */
    public boolean confirmarEliminacion(String expediente) {
        System.out.print("¿Confirma la eliminación del balance “" 
            + expediente + "”? (S/N): ");
        String resp = scanner.nextLine().trim().toUpperCase();
        return resp.equals("S") || resp.equals("SI");
    }
        
}
