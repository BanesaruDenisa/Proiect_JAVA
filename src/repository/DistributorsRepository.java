package repository;

import product.Distributors;

import java.util.ArrayList;
import java.util.List;


public class DistributorsRepository implements GenericRepository<Distributors>{

    private final List<Distributors> distributors = new ArrayList<>();

    @Override
    public void add(Distributors entity) {
        distributors.add(entity);
    }

    @Override
    public Distributors get(int id) {
        return distributors.get(id);
    }

    @Override
    public void update(int index, Distributors entity) {
        distributors.set(index, entity);
    }

    @Override
    public void delete(int index) {
        distributors.remove(index);
    }

    @Override
    public int getSize() {
        return distributors.size();
    }
}
