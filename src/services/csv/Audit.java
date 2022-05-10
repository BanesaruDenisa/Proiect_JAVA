package services.csv;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {
    public void write(String action, String date) {
        try(FileWriter fileWriter = new FileWriter("files/audit.csv", true)) {
            fileWriter.write(action + "," + date + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Error writing to file!");
        }
    }
}
