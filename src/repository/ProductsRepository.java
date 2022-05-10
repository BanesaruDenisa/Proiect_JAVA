package repository;

import product.Products;

import java.util.ArrayList;


public class ProductsRepository implements GenericRepository<Products> {

    private final ArrayList<Products> products = new ArrayList<>();


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


    public void add(ArrayList<Products> entity) {
        products.addAll(entity);
    }

    public void afis() {
        for(int i=0; i< products.size(); i++){
            System.out.println(products.get(i));
        }
    }
    @Override
    public String toString() {
        return "ProductsRepository{" +
                "products=" + products +
                '}';
    }



}
