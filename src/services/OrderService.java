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

    public void deleteOrder(int id)  {
        orderRepository.delete(id);
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
}
