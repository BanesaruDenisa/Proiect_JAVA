package repository;

import courier.Courier;
import product.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


public class CourierRepository implements GenericRepository<Courier> {

    private final ArrayList<Courier> courierList = new ArrayList<>();


    @Override
    public void add(Courier entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must be not null");
        }
        courierList.add(entity);
    }

    @Override
    public Courier get(int id) {
        return courierList.get(id);
    }


    @Override
    public int getSize() {
        return courierList.size();
    }

    @Override
    public void delete(int index) {
        courierList.remove(index);
    }

    @Override
    public void update(int index, Courier entity) {
        courierList.set(index, entity);
    }

    public void add(ArrayList<Courier> entity) {
        courierList.addAll(entity);
    }

    //@Override
//    public void update(Courier entity) {
////
////        if (entity == null) {
////            throw new IllegalArgumentException("Entity must be not null");
////        }
////
////        Scanner sc = new Scanner(System.in);
////        System.out.println("Choose the field to modify: (1- first name, 2- last name, 3- phonenumber, 4- idcourier");
////
////        int opt;
////        opt = sc.nextInt();
////        switch (opt){
////            case 1:
////                String fname;
////                fname = sc.next();
////                entity.setFirstname(fname);
////            case 2:
////                String lname;
////                lname = sc.next();
////                entity.setLastname(lname);
////            case 3:
////                String phnumber;
////                phnumber = sc.next();
////                entity.setPhonenumber(phnumber);
////            case 4:
////                int idcourier;
////                idcourier = sc.nextInt();
////                entity.setIdCourier(idcourier);
////
////        }
////
////    }


}
