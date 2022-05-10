package repository;

import courier.DeliveryCompany;

import java.util.ArrayList;
import java.util.List;


public class DeliveryCompRepository implements GenericRepository<DeliveryCompany> {

    private final List<DeliveryCompany> deliveryComp = new ArrayList<>();


    @Override
    public void add(DeliveryCompany entity) {
        deliveryComp.add(entity);
    }

    @Override
    public DeliveryCompany get(int id) {
        return deliveryComp.get(id);
    }

    @Override
    public void update(int index, DeliveryCompany entity) {
        deliveryComp.set(index, entity);
    }

    @Override
    public void delete(int index) {
        deliveryComp.remove(index);
    }

    @Override
    public int getSize() {
        return deliveryComp.size();
    }


}
