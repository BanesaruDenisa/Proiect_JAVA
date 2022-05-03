package Services;

import Courier.Courier;
import Order.Bill;
import Product.Products;
import Repository.BillRepository;
import Repository.CourierRepository;
import Repository.ProductsRepository;

import javax.sound.midi.InvalidMidiDataException;
import java.util.List;
import java.util.Vector;

public class CourierService {

    private CourierRepository courierRepository = new CourierRepository();


    public Vector<Courier> getAllCouriers() {

        Vector<Courier> couriers = new Vector<>();
        for(int i= 0; i < courierRepository.getSize(); i++)
            couriers.add(courierRepository.get(i));

        return couriers;
    }

    public void addNewCourier(Courier courier) throws InvalidMidiDataException {
        if(courier == null)
            throw new InvalidMidiDataException();
        Courier newCourier = new Courier(courier);
        courierRepository.add(newCourier);
    }

    public void addNewCourier(String firstname, String lastname, String phoneNumber, int idCourier) throws InvalidMidiDataException {
        ////exceptii
        if(phoneNumber.length() != 10 || !phoneNumber.matches("07[0-9]+"))
            throw new InvalidMidiDataException("Phone number must be 10 digits long and start with 0!");
        Courier newCourier = new Courier(firstname,lastname, phoneNumber, idCourier);
        courierRepository.add(newCourier);
    }


    public void updateCourier(int id, Courier courier) {
        courierRepository.update(id, courier);
    }

    public void deleteBill(int id)  {
        courierRepository.delete(id);
    }
}
