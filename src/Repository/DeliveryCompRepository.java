package Repository;

import Courier.DeliveryCompany;

import java.util.Vector;

public class DeliveryCompRepository implements GenericRepository<DeliveryCompany> {

    private final Vector<DeliveryCompany> deliveryComp = new Vector<>();


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
