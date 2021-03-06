package product;


import java.util.ArrayList;

public class Products extends ArrayList<Products> implements Comparable<Products> {

    private String name;
    private double price;
    private int quantity;

    public Products (){

    }

    public Products(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Products(Products prod) {
        this.name = prod.name;
        this.price = prod.price;
        this.quantity = prod.quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(Products o) {

        int compQuan =((Products)o).getQuantity();
        return this.quantity - compQuan;
    }



    //    public void add(Vector<Products> products) {
//        for(int i=0; i< products.size(); i++){
//            this.products = products.get(i);
//        }
//    }
}
