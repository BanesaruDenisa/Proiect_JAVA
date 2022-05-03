package Services;

import Product.Products;
import Repository.ProductsRepository;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Vector;

public class ProductService {

    private ProductsRepository productRepository = new ProductsRepository();


    public Vector<Products> getAllProducts() {

        Vector<Products> Products = new Vector<>();
        for(int i= 0; i < productRepository.getSize(); i++)
            Products.add(productRepository.get(i));

        return Products;
    }

    public void addNewProduct(Products product) throws InvalidMidiDataException {
        if(product == null)
            throw new InvalidMidiDataException("Product is null!");
        Products newProduct = new Products(product);
        productRepository.add(newProduct);
    }

    public void addNewProduct(String name, double price, int quantity) throws InvalidMidiDataException{
        ////exceptii
        if(price < 0 )
            throw new InvalidMidiDataException("Price must be positive!");

        if(quantity < 0)
            throw new InvalidMidiDataException("Quantity must be positive!");

        Products newProduct = new Products(name, price, quantity);
        productRepository.add(newProduct);
    }


    public void updateProduct(int id, Products product) {
        productRepository.update(id, product);
    }

    public void deleteProduct(int id)  {
        productRepository.delete(id);
    }
}
