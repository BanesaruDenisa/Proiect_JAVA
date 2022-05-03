package Repository;

import Product.Products;

import java.util.Vector;

public class ProductsRepository implements GenericRepository<Products> {

    private final Vector<Products> products = new Vector<>();


    @Override
    public void add(Products entity) {
        products.add(entity);
    }

    @Override
    public Products get(int id) {
        return products.get(id);
    }

    @Override
    public void update(int index, Products entity) {
        products.set(index, entity);
    }

    @Override
    public void delete(int index) {
        products.remove(index);
    }

    @Override
    public int getSize() {
        return products.size();
    }
}
