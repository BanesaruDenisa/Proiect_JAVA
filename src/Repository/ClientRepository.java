package Repository;

import Order.Client;

import java.util.Vector;

public class ClientRepository implements GenericRepository<Client>{

    private final Vector<Client> clients = new Vector<>();


    @Override
    public void add(Client entity) {
        clients.add(entity);
    }

    @Override
    public Client get(int id) {
        return clients.get(id);
    }

    @Override
    public void update(int index, Client entity) {
        clients.set(index, entity);
    }

    @Override
    public void delete(int index) {
        clients.remove(index);
    }

    @Override
    public int getSize() {
        return clients.size();
    }
}
