package repasoFicheros;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalculoBeca {
    public static void main(String[] args) {
        try {
            // Para usar punto como separador decimal
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);

            // Flujo de entrada binaria para leer el archivo
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("datosbeca.bin"));

            while (true) {
                try {
                    // Leer los datos del archivo
                    String nombreApellido = inputStream.readUTF();
                    char sexo = inputStream.readChar();
                    int edad = inputStream.readInt();
                    int suspensos = inputStream.readInt();
                    String residencia = inputStream.readUTF();
                    double ingresosFamilia = inputStream.readDouble();

                    // Calcular la cuantía de la beca
                    double cuantia = 1500; // Base fija

                    if (ingresosFamilia <= 12000) {
                        cuantia += 500; // Incremento por ingresos bajos
                    }

                    if (edad < 23) {
                        cuantia += 200; // Gratificación por edad
                    }

                    if (suspensos == 0) {
                        cuantia += 500; // Gratificación por no suspensos
                    } else if (suspensos == 1) {
                        cuantia += 200;
                    }

                    if (residencia.equals("NO")) {
                        cuantia += 1000; // Gratificación por no residencia familiar
                    }

                    // Se muestra en la terminal solo si el becario tiene beca
                    if (cuantia > 1500) {
                        System.out.println("Nombre del becario: " + nombreApellido);
                        System.out.println("Cuantía de la beca: " + decimalFormat.format(cuantia) + "€");
                    }
                } catch (EOFException e) {
                    System.out.println("Excepción al leer los datos: "+e);
                    // Fin del archivo
                    break;
                }
            }

            // Cerrar el flujo de entrada
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

