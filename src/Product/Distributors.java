package Product;

import java.util.ArrayList;
import java.util.List;

public class Distributors {

    private int CUI;
    private String name;
    private String city;
    private List<Products> productsList = new ArrayList<Products>();

    public Distributors(){

    }

    public Distributors(int CUI, String name, String city, List<Products> list) {
        this.CUI = CUI;
        this.name = name;
        this.city = city;
        this.productsList = list;
    }
    public Distributors(Distributors d) {
        this.CUI = d.CUI;
        this.name = d.name;
        this.city = d.city;
        this.productsList = d.productsList;
    }


    public int getCUI() {
        return CUI;
    }

    public void setCUI(int CUI) {
        this.CUI = CUI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }
    public void addProduct(Products products){
        productsList.add(products);
    }
}
