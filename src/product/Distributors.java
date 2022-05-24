package product;

import java.util.ArrayList;
import java.util.List;

public class Distributors implements Comparable<Distributors>{

    private int CUI;
    private String name;
    private String city;
    private ArrayList<Products> productsList = new ArrayList<Products>();

    public Distributors(){

    }

    public Distributors(int CUI, String name, String city, ArrayList<Products> list) {
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

    public void setProductsList(ArrayList<Products> productsList) {
        this.productsList = productsList;
    }
    public void addProduct(Products products){
        productsList.add(products);
    }

    @Override
    public String toString() {
        return "Distributors{" +
                "CUI=" + CUI +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", productsList=" + productsList +
                '}';
    }

    @Override
    public int compareTo(Distributors d) {

        int compNo =((Distributors)d).getProductsList().size();
        return this.productsList.size() - compNo;
    }
}
