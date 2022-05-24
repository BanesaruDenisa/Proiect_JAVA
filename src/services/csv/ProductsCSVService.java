package services.csv;

import product.Products;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsCSVService implements GenericCSV<Products> {

    private static ProductsCSVService INSTANCE = new ProductsCSVService();

    private ProductsCSVService() {
    }

    public static ProductsCSVService getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new ProductsCSVService();
        }

        return  INSTANCE;
    }

    @Override
    public void write(Products product) {

        try(FileWriter fileWriter = new FileWriter("files/products.csv", true)) {
            fileWriter.write(product.getName()+ "," + product.getPrice() + ","
                    + product.getQuantity()  + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println("Can't write to file!");
        }

    }

    @Override
    public List<Products> read() {
        List<Products> products = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files/products.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                Products product = new Products(words[0], Double.parseDouble(words[1]), Integer.parseInt(words[2]));
                products.add(product);
            }
        } catch (IOException ex) {
            System.out.println("Error reading from file!");
        }
        return products;
    }
}
