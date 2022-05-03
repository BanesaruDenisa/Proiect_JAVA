package Repository;

import Order.Bill;

import java.util.Vector;

public class BillRepository implements GenericRepository<Bill>{

    private final Vector<Bill> bills  = new Vector<>();

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
}
