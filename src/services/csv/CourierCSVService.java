package services.csv;

import courier.Courier;
import order.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourierCSVService implements GenericCSV<Courier> {
    private static final CourierCSVService INSTANCE = new CourierCSVService();

    private CourierCSVService() {
    }

    public static CourierCSVService getInstance() {

        return  INSTANCE;
    }

    @Override
    public void write(Courier courier) {

        try(FileWriter fileWriter = new FileWriter("files/couriers.csv", true)) {
            fileWriter.write(courier.getFirstName()+ "," + courier.getLastName() + ","
                    + courier.getPhonenumber() + "," + courier.getIdCourier() + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Can't write to file!");
        }

    }

    @Override
    public List<Courier> read() {
        List<Courier> couriers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/couriers.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                Courier courier = new Courier(words[0], words[1], words[2], Integer.parseInt(words[3]));
                couriers.add(courier);
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file!");
        }
        return couriers;
    }
}
