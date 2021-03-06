package services;

import courier.Courier;
import exception.InvalidDataException;
import order.Client;
import repository.CourierRepository;
import services.csv.ClientCSVService;
import services.csv.CourierCSVService;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CourierService {

    private CourierRepository courierRepository = new CourierRepository();


    public ArrayList<Courier> getAllCouriers() {

        ArrayList<Courier> couriers = new ArrayList<>();
        for(int i= 0; i < courierRepository.getSize(); i++)
            couriers.add(courierRepository.get(i));

        return couriers;
    }

    public void addNewCourier(Courier courier) throws InvalidDataException {
        if(courier == null)
            throw new InvalidDataException("Courier is null!");
        Courier newCourier = new Courier(courier);
        courierRepository.add(newCourier);
    }

    public void addNewCourier(String firstname, String lastname, String phoneNumber, int idCourier) throws InvalidDataException {
        ////exceptii
        if(phoneNumber.length() != 10 || !phoneNumber.matches("07[0-9]+"))
            throw new InvalidDataException("Phone number must be 10 digits long and start with 0!");
        Courier newCourier = new Courier(idCourier, firstname,lastname, phoneNumber);
        courierRepository.add(newCourier);
    }


    public void updateCourier(int id, Courier courier) {
        courierRepository.update(id, courier);
    }

    public void deleteBill(int id)  {
        courierRepository.delete(id);
    }


    public void getFromCSVFile(){
        CourierCSVService csvFile = CourierCSVService.getInstance();
        ArrayList<Courier> couriers = new ArrayList<>(csvFile.read());
        for(Courier courier : couriers) {
            courierRepository.add(courier);
        }
    }

    public void listCSV(){
        CourierCSVService courierCSV = CourierCSVService.getInstance();
        CourierService courierService = new CourierService();
        ArrayList<Courier> crListCsv = new ArrayList<>(courierService.getAllCouriers());
        for(Courier courier : crListCsv){
            courierCSV.write(courier);
        }
    }
}
