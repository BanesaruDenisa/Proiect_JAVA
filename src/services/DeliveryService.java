package services;

import courier.Courier;
import courier.DeliveryCompany;
import exception.InvalidDataException;
import repository.DeliveryCompRepository;


import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class DeliveryService {

    private DeliveryCompRepository deliveryCompRepository = new DeliveryCompRepository();
    private SortedSet<Courier> couriersList = new TreeSet<>();


    public ArrayList<DeliveryCompany> getAllDeliverycomp() {

        ArrayList<DeliveryCompany> delivery = new ArrayList<>();
        for(int i= 0; i < deliveryCompRepository.getSize(); i++)
            delivery.add(deliveryCompRepository.get(i));

        return delivery;
    }

    public void addNewDelivery(DeliveryCompany deliveryCompany) throws InvalidDataException {
        if(deliveryCompany == null)
            throw new InvalidDataException("Delivery is null!");
        DeliveryCompany newDelivery = new DeliveryCompany(deliveryCompany);
        deliveryCompRepository.add(newDelivery);
    }

    public void addNewDelivery(String name, int idcourier,  SortedSet<Courier> list) {
        ////exceptii

        couriersList.addAll(list);
        DeliveryCompany newDelivery = new DeliveryCompany(name, idcourier, list);
        deliveryCompRepository.add(newDelivery);
    }


    public void updateDelivery(int id, DeliveryCompany deliveryCompany) {
        deliveryCompRepository.update(id, deliveryCompany);
    }

    public void deleteDelivery(int id)  {
        deliveryCompRepository.delete(id);
    }
}





