package services.csv;



import order.Client;
import order.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class OrderCSVService implements GenericCSV<Order> {

    private static OrderCSVService INSTANCE = new OrderCSVService();

    private OrderCSVService() {
    }

    public static OrderCSVService getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new OrderCSVService();
        }
        return  INSTANCE;
    }

    @Override
    public void write(Order orders) {


        try(FileWriter fileWriter = new FileWriter("files/orders.csv", true)) {
            Client client = new Client(orders.getClient());


            fileWriter.write(orders.getIdOrder() + "," + orders.getIdBill() + ","
                                + orders.getIdDelComp()  + "," +
                                client.getFirstName() + "," + client.getLastName() + "," +
                                client.getPhonenumber() + "," + client.getEmail() + "," + client.getAddress() + "\n") ;
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Can't write to file!");
        }

    }

    @Override
    public List<Order> read() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/orders.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                Client client = new Client(words[0], words[1], words[2], words[3], words[4]);
                Order ord = new Order(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]), client);
                orders.add(ord);
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file!");
        }
        return orders;
    }
}
