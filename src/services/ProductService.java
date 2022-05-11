package services;

import exception.InvalidDataException;
import order.Client;
import product.Products;
import repository.ProductsRepository;
import services.csv.ClientCSVService;
import services.csv.ProductsCSVService;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductsRepository productRepository = new ProductsRepository();


    public ArrayList<Products> getAllProducts() {

        ArrayList<Products> Products = new ArrayList<>();
        for(int i = 0; i < productRepository.getSize(); i++)
            Products.add(productRepository.get(i));

        return Products;
    }

    public void addNewProduct(Products product) throws InvalidDataException {
        if(product == null)
            throw new InvalidDataException("Product is null!");
        Products newProduct = new Products(product);
        productRepository.add(newProduct);
    }

    public void addNewProduct(String name, double price, int quantity) throws InvalidDataException{
        ////exceptii
        if(price < 0 )
            throw new InvalidDataException("Price must be positive!");

        if(quantity < 0)
            throw new InvalidDataException("Quantity must be positive!");

        Products newProduct = new Products(name, price, quantity);
        productRepository.add(newProduct);
    }


    @Override
    public String toString() {
        return "ProductService{" +
                "productRepository=" + productRepository +
                '}';
    }

    public void printP(){
        for(int i =0; i<productRepository.getSize();i++){
            System.out.println(productRepository.get(i));
        }
    }

    public void updateProduct(int id, Products product) {
        productRepository.update(id, product);
    }

    public void deleteProduct(int id)  {
        productRepository.delete(id);
    }


    public void getFromCSVFile(){
        ProductsCSVService csvFile = ProductsCSVService.getInstance();
        List<Products> products = new ArrayList<>(csvFile.read());
        for(Products prod : products) {
            productRepository.add(prod);
        }
    }
    public void listCSV(){
        ProductsCSVService prodCSV = ProductsCSVService.getInstance();
        ProductService productService = new ProductService();
        List<Products> prListCsv = new ArrayList<>(productService.getAllProducts());
        for(Products pr : prListCsv){
            prodCSV.write(pr);
        }
    }
}
