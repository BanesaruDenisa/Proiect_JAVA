package Order;

import Product.Products;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private Integer idBill;
    private List<Products> products = new ArrayList<>();
    private double totalPrice = 0.0d;

    public Bill(Integer idBill, List<Products> products, double totalPrice) {
        this.idBill = idBill;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Bill(Bill bill) {
        this.idBill = bill.idBill;
        this.totalPrice = bill.totalPrice;
        this.products = bill.products;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double calculatePrice(Products prod){
        products.add(prod);
        return totalPrice = totalPrice + (prod.getPrice()) * prod.getQuantity();

    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
