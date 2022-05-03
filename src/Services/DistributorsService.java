package Services;

import Product.Distributors;
import Product.Products;
import Repository.DistributorsRepository;

import javax.sound.midi.InvalidMidiDataException;
import java.util.List;
import java.util.Vector;

public class DistributorsService {

    private DistributorsRepository distributorsRepository = new DistributorsRepository();


    public Vector<Distributors> getAllDistributors() {

        Vector<Distributors> distributors = new Vector<>();
        for(int i= 0; i < distributorsRepository.getSize(); i++)
            distributors.add(distributorsRepository.get(i));

        return distributors;
    }

    public void addNewDistributor(Distributors distributors) throws InvalidMidiDataException {
        if(distributors == null)
            throw new InvalidMidiDataException("Distributor is null!");
        Distributors newProduct = new Distributors(distributors);
        distributorsRepository.add(newProduct);
    }

    public void addNewDistributor(int CUI, String name, String city, List<Products> list) {
        ////exceptii


        Distributors newDistrib = new Distributors(CUI, name, city, list);
        distributorsRepository.add(newDistrib);
    }


    public void updateDistibutor(int id, Distributors distributors) {
        distributorsRepository.update(id, distributors);
    }

    public void deleteDistributor(int id)  {
        distributorsRepository.delete(id);
    }
}
