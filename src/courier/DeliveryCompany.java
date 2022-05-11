package courier;

import java.util.SortedSet;
import java.util.TreeSet;

public class DeliveryCompany {

    private static Integer countComp = 0;
    private String name;
    //private int idCourier;
    private SortedSet<Courier> couriers = new TreeSet<Courier>();


    public DeliveryCompany(String name, SortedSet<Courier> couriers) {

        countComp++;
        this.name = name;
        //this.idCourier = idCourier;
        this.couriers = couriers;
    }

    public DeliveryCompany(String compName) {
        this.name = compName;
    }

    public DeliveryCompany() {

    }

    public DeliveryCompany(DeliveryCompany deliveryCompany) {
        countComp++;
        this.name = deliveryCompany.name;
       // this.idCourier = deliveryCompany.idCourier;
        this.couriers = deliveryCompany.couriers;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public int getIdCourier() {return idCourier;}

   // public void setIdCourier(int idCourier) {this.idCourier = idCourier;}

    public SortedSet<Courier> getCouriers(Courier courier) {
        return couriers;
    }

    public void setCouriers(SortedSet<Courier> couriers) {
        this.couriers = couriers;
    }

    public void addCourier(Courier c){
       // c.setIdCourier(idCourier);
        couriers.add(c);
    }

}
