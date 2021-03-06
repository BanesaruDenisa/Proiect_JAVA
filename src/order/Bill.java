package order;

import product.Products;

import java.util.ArrayList;
import java.util.List;


public class Bill {

    private Integer idBill;
    private ArrayList<Products> products = new ArrayList<>();
    private double totalPrice = 0.0d;

    public Bill(Integer idBill, ArrayList<Products> products, double totalPrice) {
        this.idBill = idBill;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Bill(Bill bill) {
        this.idBill = bill.idBill;
        this.totalPrice = bill.totalPrice;
        this.products = bill.products;
    }



//    public Bill(int idBill, Vector<ProductsRepository> products, double totalPrice) {
//        this.idBill = idBill;
//        this.products = products;
//        this.totalPrice = totalPrice;
//    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public List<Products> getProducts(Products products) {
        return this.products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }

    /////pret total pe factura
//    public double calculatePrice(ArrayList<Products> prod){
//        products.add(prod);
//        return totalPrice = totalPrice + (prod.getPrice()) * prod.getQuantity();
//
//    }

}
