package services;

import exception.InvalidDataException;
import order.Client;
import order.Order;
import repository.ClientRepository;
import repository.OrderRepository;
import services.csv.ClientCSVService;
import services.csv.OrderCSVService;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public ArrayList<Order> getAllOrders() {

        ArrayList<Order> orders = new ArrayList<>();
        for(int i=0; i< orderRepository.getSize();i++){
            orders.add(orderRepository.get(i));
        }
        return orders;
    }

    public void addNewOrder(Order order) throws InvalidDataException {
        if(order == null){
            throw new InvalidDataException("Order is null!");
        }
        Order newOrder = new Order(order);
        orderRepository.add(newOrder);
    }

    public void addNewOrder(int idOrder, int idBill, int idDelComp, int clientindex){

        Client client =  clientRepository.get(clientindex);
        Order newOrder = new Order(idOrder, idBill, idDelComp, client);
        orderRepository.add(newOrder);
    }

    public void updateOrder(int id, Order order) {
        orderRepository.update(id, order);
    }

    public boolean updateClient(int ordIndex, int clientIndex) {
        if(ordIndex <0 || ordIndex >= orderRepository.getSize())
        {  return false;}

        Order oldOrd = new Order(orderRepository.get(ordIndex));
        Order newOrd = new Order(oldOrd);
        newOrd.setClient(clientRepository.get(clientIndex));
        orderRepository.delete(ordIndex);
        orderRepository.add(newOrd);
        return true;
    }


    public boolean deleteOrder(int id) {
        if (id >= 0 && id < orderRepository.getSize()) {
            orderRepository.delete(id);
            return true;
        }
        return false;
    }

    public void getFromCSVFile(){
        OrderCSVService csvFile = OrderCSVService.getInstance();
        List<Order> orders = new ArrayList<>(csvFile.read());
        for(Order order : orders) {
            orderRepository.add(order);
        }
    }
    public void listCSV(){
        OrderCSVService orderCSV = OrderCSVService.getInstance();
        OrderService orderService = new OrderService();
        ArrayList<Order> orListCsv = new ArrayList<>(orderService.getAllOrders());
        for(Order order : orListCsv){
            orderCSV.write(order);
        }
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }


    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public long getOrdersss(String email, List<Order> comenzi){
        return comenzi.stream()
                .filter(comanda -> comanda.getClient() != null &&
                        comanda.getClient().getEmail() != null &&
                        comanda.getClient().getEmail().equals(email))
                .count();
    }
}
