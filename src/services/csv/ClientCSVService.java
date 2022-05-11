package services.csv;

import order.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClientCSVService implements GenericCSV<Client> {

    private static final ClientCSVService INSTANCE = new ClientCSVService();

    private ClientCSVService() {
    }

    public static ClientCSVService getInstance() {
//        if (INSTANCE == null) {
//            ClientCSV INSTANCE = new ClientCSV();
//            return INSTANCE;
//        }
        return  INSTANCE;
    }

    @Override
    public void write(Client client) {

        try(FileWriter fileWriter = new FileWriter("files/clients.csv", true)) {
            fileWriter.write(client.getFirstName()+ "," + client.getLastName() + ","
                    + client.getPhonenumber() + "," + client.getEmail() + ","
                    + client.getAddress() + "," + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Can't write to file!");
        }

    }

    @Override
    public ArrayList<Client> read() {
        ArrayList<Client> clients = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/clients.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                Client client = new Client(words[0], words[1], words[2], words[3], words[4]);
                clients.add(client);
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file!");
        }
        return clients;
    }
}

