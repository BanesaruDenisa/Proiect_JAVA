package repository;

import order.Bill;

import java.util.ArrayList;


public class BillRepository implements GenericRepository<Bill>{

    private final ArrayList<Bill> bills  = new ArrayList<>();

    @Override
    public void add(Bill entity) {
        bills.add(entity);
    }

    @Override
    public Bill get(int id) {
        return bills.get(id);
    }

    @Override
    public void update(int index, Bill entity) {
        bills.set(index, entity);
    }

    @Override
    public void delete(int index) {
        bills.remove(index);
    }

    @Override
    public int getSize() {
        return bills.size();
    }

    @Override
    public String toString() {
        return "BillRepository{" +
                "bills=" + bills +
                '}';
    }
}
