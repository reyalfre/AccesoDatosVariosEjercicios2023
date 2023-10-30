package repasoFicheros;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta es una clase donde almacena los datos del vehículo en un fichero llamado vehiculos.txt
 */
public class AlmacenarDatosVehiculo {
    private static String matricula;
    private static String marca;
    private static String modelo;
    private static double tamanoDeposito;

    public static void main(String[] args) {
        // Scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Configura el Scanner para utilizar punto como separador decimal

        try {
            // Abrir el archivo en modo de apertura para agregar
            FileWriter fileWriter = new FileWriter("src/main/resources/vehiculos.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Solicitar los datos al usuario
            System.out.print("Matrícula: ");
            matricula = scanner.nextLine();

            System.out.print("Marca: ");
            marca = scanner.nextLine();
            //Obligar al usuario a que introduzca un double para el tamaño del depósito
            tamanoDeposito = 0.0;
            boolean tamanoDepositoValido = false;
            while (!tamanoDepositoValido) {
                System.out.print("Tamaño del depósito: ");
                try {
                    tamanoDeposito = Double.parseDouble(scanner.nextLine());
                    tamanoDepositoValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido con decimales si es necesario separado con punto.");
                }
            }

            System.out.print("Modelo: ");
            modelo = scanner.nextLine();

            // Escribir los datos en el archivo
            bufferedWriter.write("Matrícula: " + matricula + ", Marca: " + marca + ", TamañoDeposito: " + tamanoDeposito + ", Modelo: " + modelo);
            bufferedWriter.newLine();

            // Cerrar el archivo
            bufferedWriter.close();

            System.out.println("Datos del vehículo almacenados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            // Cerrar el Scanner
            scanner.close();
        }
    }
}

/*
 * Ejemplo de como quedaría el fichero vehiculos.txt
 *
 * Matrícula: 4968JFP, Marca: Renault, TamañoDeposito: 4.5, Modelo: ford
 * Matrícula: 8315KTK, Marca: Ford, TamañoDeposito: 6.7, Modelo: Renault
 * Matrícula: 8315KTK, Marca: sol, TamañoDeposito: 54.53, Modelo: renault
 */

