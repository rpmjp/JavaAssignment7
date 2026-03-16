import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadAndSearch {

    public static void writeFile() {
        try (FileWriter myWriter = new FileWriter("myFile.txt")) {
            myWriter.write("Kalahari, Gobi, Sahara, Kyzyl, Mojave, Karakum, Great Basin, Taklamakan");
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file.");
            e.printStackTrace();
        }
    }

    public static String[] readFile() {
        String[] filtered = new String[8];
        int count = 0;
        File myObj = new File("myFile.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] deserts = data.split(", ");
                for (String name : deserts) {
                    if (name.startsWith("G") || name.startsWith("K")) {
                        filtered[count] = name;
                        count++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // trim to exact size
        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = filtered[i];
        }
        return result;
    }

    public static void main(String[] args) {
        writeFile();
        String[] result = readFile();
        for (String name : result) {
            System.out.println(name);
        }
    }
}