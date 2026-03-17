import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

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

    public static String[] addName(String[] arr, String name) {
        String[] newArr = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length - 1] = name;
        return newArr;
    }

    public static int binarySearch(String[] arr, String name) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;

            if (arr[mid].equals(name)) {
                return mid;
            } else if (arr[mid].compareTo(name) < 0) {
                leftIndex = mid + 1;
            } else {
                rightIndex = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        writeFile();
        String[] result = readFile();

        System.out.println("This is the data list before adding new names:");
        for (String name : result) {
            System.out.println(name);
        }

        result = addName(result, "Thar");
        result = addName(result, "Namib");

        System.out.println("\nThis is the data list after adding new names:");
        for (String name : result) {
            System.out.println(name);
        }

        Arrays.sort(result);

        int index = binarySearch(result, "Gobi");
        System.out.println("\nGobi was found at index: " + index);

        index = binarySearch(result, "Kalahari");
        System.out.println("\nKalahari was found at index: " + index + "\n");

        for (String name : result) {
            System.out.println(name);
        }
    }

}