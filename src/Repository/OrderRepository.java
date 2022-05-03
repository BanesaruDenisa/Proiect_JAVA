package Repository;

import Order.Order;

import java.util.Vector;

public class OrderRepository implements GenericRepository<Order>{

    private final Vector<Order> orders = new Vector<>();


    @Override
    public void add(Order entity) {
        orders.add(entity);
    }

    @Override
    public Order get(int id) {
        return orders.get(id);
    }

    @Override
    public void delete(int index) {
        orders.remove(index);
    }

    @Override
    public int getSize() {
        return orders.size();
    }

    @Override
    public void update(int index, Order entity) {
        orders.set(index, entity);
    }
}
