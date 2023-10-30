import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FicheroTexto {
    public static void main(String[] args) {
        List<String> frases = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input phrases or don't write to finish: ");
            String phrase = scanner.nextLine();
            if (phrase.isEmpty()) {
                break;
            }
            frases.add(phrase);
        }


        String nameFile = "phrases.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile))) {
            for (String frase : frases) {
                writer.write(frase);
                writer.newLine();
            }
            System.out.println("The phrases was saved in the file '" + nameFile + "'");
        } catch (IOException e) {
            System.err.println("Problem to write in the file: " + e.getMessage());
        } finally {
            scanner.close();
        }

    }
}
