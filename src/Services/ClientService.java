package Services;

import Order.Client;
import Repository.ClientRepository;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Vector;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();


    public Vector<Client> getAllClients() {

        Vector<Client> Clients = new Vector<>();
        for(int i= 0; i < clientRepository.getSize(); i++)
            Clients.add(clientRepository.get(i));

        return Clients;
    }

    public void addNewClient(Client Client) throws InvalidMidiDataException {
        if(Client == null)
            throw new InvalidMidiDataException("Client is null!");
        Client newClient = new Client(Client);
        clientRepository.add(newClient);
    }

    public void addNewClient(String firstname, String lastname, String phoneNumber, String email, String address) throws InvalidMidiDataException{
        ////exceptii
        if(phoneNumber.length() != 10 || !phoneNumber.matches("07[0-9]+"))
            throw new InvalidMidiDataException("Phone number must be 10 digits long and start with 0!");

        if(!email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z]+"))
            throw new InvalidMidiDataException("Email is invalid!");

        Client newClient = new Client(firstname,lastname, phoneNumber, email, address);
        clientRepository.add(newClient);
    }


    public void updateClient(int id, Client Client) {
        clientRepository.update(id, Client);
    }

    public void deleteBill(int id)  {
        clientRepository.delete(id);
    }

}
