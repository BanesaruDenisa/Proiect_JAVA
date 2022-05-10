package services;

import exception.InvalidDataException;
import order.Bill;
import product.Products;
import repository.BillRepository;


import java.util.ArrayList;


public class BillService {

    private BillRepository billRepository = new BillRepository();
   // private ProductsRepository products = new ProductsRepository();
    private ArrayList<Products> products = new ArrayList<>();

    public ArrayList<Bill> getAllBills() {
        ArrayList<Bill> Bills = new ArrayList<>();
        for(int i =0; i < billRepository.getSize(); i++) {
           Bills.add(billRepository.get(i));
        }
        return Bills;

    }

    public void addNewBill(Bill bill) throws InvalidDataException {
        if(bill == null)
            throw new InvalidDataException("Bill is null");

        Bill newBill = new Bill(bill);
        billRepository.add(newBill);
    }

    public void addNewBill(int idBill, ArrayList<Products> productsList, double totalPrice) throws InvalidDataException {

        products.addAll(productsList);
        Bill newBill = new Bill(idBill, products, totalPrice);
        billRepository.add(newBill);
    }


    public void updateBill(int id, Bill bill) {
        billRepository.update(id, bill);
    }

    @Override
    public String toString() {
        return "BillService{" +
                "billRepository=" + billRepository +
                ", products=" + products +
                '}';
    }

    public void deleteBill(int id)  {
        billRepository.delete(id);
    }

}
