package services;

import courier.Courier;
import exception.InvalidDataException;
import product.Distributors;
import product.Products;
import repository.DistributorsRepository;

import javax.sql.rowset.serial.SerialArray;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


public class DistributorsService {

    private DistributorsRepository distributorsRepository = new DistributorsRepository();
    private ArrayList<Products> products = new ArrayList<>();


    public ArrayList<Distributors> getAllDistributors() {

        ArrayList<Distributors> distributors = new ArrayList<>();
        for(int i= 0; i < distributorsRepository.getSize(); i++)
            distributors.add(distributorsRepository.get(i));

        return distributors;
    }

    public void addNewDistributor(Distributors distributors) throws InvalidDataException {
        if(distributors == null)
            throw new InvalidDataException("Distributor is null!");
        Distributors newDistrib = new Distributors(distributors);
        distributorsRepository.add(newDistrib);
    }

    public void addNewDistributor(int CUI, String name, String city, ArrayList<Products> productsList) {
        ////exceptii

        products.addAll(productsList);
        Distributors newDistrib = new Distributors(CUI, name, city, products);
        distributorsRepository.add(newDistrib);
    }


    public void updateDistibutor(int id, Distributors distributors) {
        distributorsRepository.update(id, distributors);
    }

    public void deleteDistributor(int id)  {
        distributorsRepository.delete(id);
    }
}
