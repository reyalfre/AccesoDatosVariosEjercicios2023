package repasoFicheros;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ActualizarFicheroMaestro {
    public static void main(String[] args) {
        try {
            // Leer datos del fichero maestro
            List<String> maestroData = new ArrayList<>();
            try (BufferedReader maestroReader = new BufferedReader(new FileReader("src/main/resources/maestro.txt"))) {
                maestroData = maestroReader.lines().collect(Collectors.toList());
            }

            // Leer datos del fichero de movimientos
            Map<String, List<Integer>> alumnosNotas = new HashMap<>();
            try (BufferedReader movimientosReader = new BufferedReader(new FileReader("src/main/resources/movimientos.txt"))) {
                String line;
                while ((line = movimientosReader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length != 2) {
                        // Ignorar líneas mal formateadas
                        continue;
                    }
                    String matricula = parts[0];
                    int nota = Integer.parseInt(parts[1]);
                    if (alumnosNotas.containsKey(matricula)) {
                        alumnosNotas.get(matricula).add(nota);
                    } else {
                        List<Integer> notas = new ArrayList<>();
                        notas.add(nota);
                        alumnosNotas.put(matricula, notas);
                    }
                }
            }

            // Actualizar el fichero maestro con las notas promedio
            for (int i = 0; i < maestroData.size(); i++) {
                String[] parts = maestroData.get(i).split(" ");
                String matricula = parts[0];
                if (alumnosNotas.containsKey(matricula)) {
                    List<Integer> notas = alumnosNotas.get(matricula);
                    double promedio = calcularPromedio(notas);
                    maestroData.set(i, matricula + " " + String.format("%04.2f", promedio));
                }
            }

            // Agregar nuevos alumnos que no estén en el fichero maestro
            for (Map.Entry<String, List<Integer>> entry : alumnosNotas.entrySet()) {
                String matricula = entry.getKey();
                if (maestroData.stream().noneMatch(linea -> linea.startsWith(matricula))) {
                    List<Integer> notas = entry.getValue();
                    double promedio = calcularPromedio(notas);
                    maestroData.add(matricula + " " + String.format("%04.2f", promedio));
                }
            }

            // Ordenar el fichero maestro por número de matrícula
            maestroData.sort(Comparator.comparing(linea -> linea.split(" ")[0]));

            // Escribir los datos actualizados en el fichero maestro
            try (BufferedWriter maestroWriter = new BufferedWriter(new FileWriter("maestro.txt"))) {
                for (String updatedLine : maestroData) {
                    maestroWriter.write(updatedLine);
                    maestroWriter.newLine();
                }
            }

            // Mostrar cómo queda el fichero maestro
            System.out.println("Fichero Maestro actualizado:");
            for (String updatedLine : maestroData) {
                System.out.println(updatedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double calcularPromedio(List<Integer> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (int nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }
}
