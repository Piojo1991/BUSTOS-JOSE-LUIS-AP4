/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import java.util.Scanner;
import Models.Jurisdiccion;
import java.util.List;


/**
 *
 * @author jlbustos
 */
public class JurisdiccionView {
     // Scanner para lecturas por consola; se inicializa en el constructor
    private final Scanner scanner;
    
    /**
     * Constructor.
     * Inicializa el Scanner para toda la instancia.
     */
    public JurisdiccionView() {
       this.scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra la lista de jurisdicciones y lee la opción del usuario.
     *
     * @param j lista de objetos Jurisdiccion
     * @return opcion seleccionada por el usuario
     */
    public int seleccionarJurisdiccion(List <Jurisdiccion> j) {
        // 1. Despliega menú
        listarJurisdicciones(j);
        
        // 2. Lee opción, validando que sea un entero  TODO: Validar rango
        System.out.print("Opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next(); // descarta entrada inválida
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();// limpia el salto de línea pendiente
        
        return opcion;
    }
    
    /**
     * Imprime en consola cabecera y cada jurisdicción formateada.
     *
     * @param jurisdicciones lista de jurisdicciones a mostrar
     */
    public void listarJurisdicciones(List <Jurisdiccion> jurisdicciones) {
       System.out.println("________________________________________");
       System.out.println("Selccione una jurisdicción:");
       System.out.println("________________________________________");
       System.out.println("ID | Nombre");
       for (Jurisdiccion j: jurisdicciones) mostrarJurisdiccionInLine(j);
    }
    
    /**
     * Muestra los datos de la jurisdicción en una sola línea.
     *
     * @param j objeto Jurisdiccion a imprimir
     */
     public void mostrarJurisdiccionInLine(Jurisdiccion j) {
       System.out.println( j.getId() + " | " + j.getNombre() );
    }
}
