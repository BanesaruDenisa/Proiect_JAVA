package repository;

import order.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderRepository implements GenericRepository<Order>{

    private final List<Order> orders = new ArrayList<>();


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
