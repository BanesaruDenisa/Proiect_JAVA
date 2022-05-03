package Services;

import Order.Bill;
import Product.Products;
import Repository.BillRepository;
import Repository.ProductsRepository;


import javax.sound.midi.InvalidMidiDataException;
import java.util.List;
import java.util.Vector;

public class BillService {

    private BillRepository billRepository = new BillRepository();
    private ProductsRepository productsRepository = new ProductsRepository();

    public Vector<Bill> getAllBills() {

        Vector<Bill> bills = new Vector<>();
        for(int i= 0; i < billRepository.getSize(); i++)
            bills.add(billRepository.get(i));

        return bills;
    }

    public void addNewBill(Bill bill) throws InvalidMidiDataException {
        if(bill == null)
            throw new InvalidMidiDataException();
        Bill newBill = new Bill(bill);
        billRepository.add(newBill);
    }

    public void addNewBill(int idBill, List<Products> productsList, double totalPrice){
        ////exceptii
        Bill newBill = new Bill(idBill, productsList, totalPrice);
        billRepository.add(newBill);
    }

    public void addProducts(Products products){
        productsRepository.add(products);
    }

    public void updateBill(int id, Bill bill) {
        billRepository.update(id, bill);
    }

    public void deleteBill(int id)  {
        billRepository.delete(id);
    }

}
