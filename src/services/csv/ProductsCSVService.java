package services.csv;

import product.Products;

import java.io.IOException;
import java.util.List;

public class ProductsCSVService implements GenericCSV<Products> {

    @Override
    public void write(Products object) throws IOException {

    }

    @Override
    public List<Products> read() throws IOException {
        return null;
    }
}
