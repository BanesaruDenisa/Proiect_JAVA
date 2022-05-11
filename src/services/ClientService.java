package services;

import exception.InvalidDataException;
import order.Client;
import repository.ClientRepository;
import services.csv.ClientCSVService;


import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private ClientRepository clientRepository = new ClientRepository();

    //ClientCSVService csvFile = ClientCSVService.getInstance();


    public ArrayList<Client> getAllClients() {

        ArrayList<Client> Clients = new ArrayList<>();
        for(int i= 0; i < clientRepository.getSize(); i++)
            Clients.add(clientRepository.get(i));

        return Clients;
    }

    public void addNewClient(Client Client) throws InvalidDataException {
        if(Client == null)
            throw new InvalidDataException("Client is null!");
        Client newClient = new Client(Client);
        clientRepository.add(newClient);
    }

    public void addNewClient(String firstname, String lastname, String phoneNumber, String email, String address) throws InvalidDataException{
        ////exceptii
        if(phoneNumber.length() != 10 || !phoneNumber.matches("07[0-9]+"))
            throw new InvalidDataException("Phone number must be 10 digits long and start with 0!");

        if(!email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z]+"))
            throw new InvalidDataException("Email is invalid!");

        Client newClient = new Client(firstname,lastname, phoneNumber, email, address);
        clientRepository.add(newClient);
    }


    public void updateClient(int id, Client Client) {
        clientRepository.update(id, Client);
    }

    public void deleteBill(int id)  {
        clientRepository.delete(id);
    }

    public void getFromCSVFile(){
        ClientCSVService csvFile = ClientCSVService.getInstance();
        List<Client> clients = new ArrayList<>(csvFile.read());
        for(Client client : clients) {
            clientRepository.add(client);
        }
    }
    public void listCSV(){
        ClientCSVService clientCSV = ClientCSVService.getInstance();
        ClientService clientService = new ClientService();
        ArrayList<Client> clListCsv = new ArrayList<>(clientService.getAllClients());
        for(Client client : clListCsv){
            clientCSV.write(client);
        }
    }



}
