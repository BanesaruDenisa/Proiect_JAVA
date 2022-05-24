package services;

import config.DBConnection;
import config.DbRepository.ClientDbRepo;
import config.DbRepository.CourierDbRepo;
import config.DbRepository.OrderDbRepo;
import config.DbRepository.ProductDbRepo;
import courier.Courier;
import courier.DeliveryCompany;
import exception.InvalidDataException;
import order.Bill;
import order.Client;
import order.Order;
import product.Distributors;
import product.Products;
import services.csv.Audit;
import services.csv.ClientCSVService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Services {

    Scanner scanner = new Scanner(System.in);

    private BillService billService = new BillService();
    private CourierService courierService = new CourierService();
    private ClientService clientService = new ClientService();
    private ProductService productService = new ProductService();
    private DeliveryService deliveryService = new DeliveryService();
    private DistributorsService distributorsService = new DistributorsService();
    private OrderService orderService = new OrderService();

    //10
    List<Order> orders = orderService.getAllOrders();


    public Services(){}




    private void Menu() {

        System.out.println("******* Managing the orders of a store *******");
        System.out.println("--- What do you want to do? ---");
        System.out.println();
        System.out.println("1. Read ");
        System.out.println("2. Print ");
        System.out.println("3. Update order");
        System.out.println("4. Update bill ");
        System.out.println("5. Delete client ");
        System.out.println("6. Delete order");
        System.out.println("7. Delete product");
        System.out.println("8. Sort products by quantity. (ascendent)");
        System.out.println("9. Update quantity of product.");
        System.out.println("10. Sort distributors by number of products. (ascendent)");
        System.out.println("0. Exit. ");


    }

    private void MenuDB() {

        System.out.println("******* Managing the orders of a store *******");
        System.out.println("--- What do you want to do? ---");
        System.out.println();
        System.out.println("-- Print from MySQL --");
        System.out.println("1. Clients ");
        System.out.println("2. Couriers ");
        System.out.println("3. Orders ");
        System.out.println("4. Products ");
        System.out.println("-- Read and save in MySQL --");
        System.out.println("5. Client  ");
        System.out.println("6. Courier ");
        System.out.println("7. Product ");
        System.out.println("8. Order ");
        System.out.println("0. Exit. ");


    }

    public int executeOptionDB() throws InvalidDataException, SQLException, ClassNotFoundException {

        DBConnection db = new DBConnection("jdbc:mysql://localhost:3306/store", "root", "bananaverde.ro20");

        Scanner sc = new Scanner(System.in);
        int option = 0;
        MenuDB();
        System.out.println(" Choose an option: ");

        option = sc.nextInt();
        if (option >= 0 && option < 9) {

            switch (option) {
                case 1 :
                    ClientDbRepo clDB = new ClientDbRepo(db);
                    List<Client> clientList = clDB.getAllClients();
                    for(Client client: clientList){
                        System.out.println(client);
                    }
                    break;
                case 2 :
                    CourierDbRepo crDB = new CourierDbRepo(db);
                    List<Courier> courierList = crDB.getAllCouriers();
                    for(Courier courier: courierList){
                        System.out.println(courier);
                    }
                    break;
                case 3 :
                    OrderDbRepo orDb = new OrderDbRepo(db);
                    List<Order> orderList = orDb.getAllOrders();
                    for(Order order: orderList){
                        System.out.println(order);
                    }
                    break;
                case 4 :
                    ProductDbRepo prDb = new ProductDbRepo(db);
                    List<Products> productsList = prDb.getAllProducts();
                    for(Products prod: productsList){
                        System.out.println(prod);
                    }
                    break;
                case 5 :
                    List<Client> cl = new ArrayList<>();

                    System.out.println("First name: ");
                    String fname = scanner.next();
                    System.out.println("Last name: ");
                    String lname = scanner.next();
                    System.out.println("Phone number: ");
                    String phno = scanner.next();
                    System.out.println("Email: ");
                    String email = scanner.next();
                    System.out.println("Address: ");
                    scanner.useDelimiter("\n");
                    String address = scanner.next();
                    scanner.reset();

                    cl.add(new Client(fname, lname, phno, email, address));
                    ClientDbRepo cr = new ClientDbRepo(db);
                    cr.save(cl);
                    break;
                case 6 :
                    List<Courier> couriers = new ArrayList<>();

                    System.out.println("First name: ");
                    String ftname = scanner.next();
                    System.out.println("Last name: ");
                    String lsname = scanner.next();
                    System.out.println("Phone number: ");
                    String phnor = scanner.next();
                    System.out.println("Id courier: (must be uniq)");
                    int idc = scanner.nextInt();

                    couriers.add(new Courier(idc, ftname, lsname, phnor));
                    CourierDbRepo crepo = new CourierDbRepo(db);
                    crepo.save(couriers);
                    break;
                case 7 :
                    List<Products> pr = new ArrayList<>();

                    System.out.println("Name: ");
                    String name = scanner.next();
                    System.out.println("Price: ");
                    double price = scanner.nextDouble();
                    System.out.println("Quantity: ");
                    int quantity = scanner.nextInt();

                    pr.add(new Products(name, price, quantity));
                    ProductDbRepo prepo = new ProductDbRepo(db);
                    prepo.save(pr);
                    break;

                case 8 :
                    List<Order> or = new ArrayList<>();

                    System.out.println("Id bill: ");
                    int idbill = scanner.nextInt();
                    System.out.println("Id delivery company: ");
                    int idDelc = scanner.nextInt();
                    Client client = new Client();
                    System.out.println("First name: ");
                    String fname1 = scanner.next();
                    System.out.println("Last name: ");
                    String lname1 = scanner.next();
                    System.out.println("Phone number: ");
                    String phno1 = scanner.next();
                    System.out.println("Email: ");
                    String email1 = scanner.next();
                    System.out.println("Address: ");
                    scanner.useDelimiter("\n");
                    String address1 = scanner.next();
                    client = new Client(fname1, lname1, phno1, email1, address1);

                    or.add(new Order(idbill, idDelc, client));
                    OrderDbRepo orepo = new OrderDbRepo(db);
                    orepo.save(or);

                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }
        else
            System.out.println("Invalid Option! Try again.");
        return executeOptionDB();
    }

    public int executeOption() throws InvalidDataException, SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        int option = 0;
        Menu();
        System.out.println(" Choose an option: ");

        option = sc.nextInt();
        if(option >= 0 && option < 11) {

            switch (option) {
                case 1: {
                    String clasa;
                    System.out.println("Choose the class: (Courier/DeliveryC/Bill/Client/Order/Distributors/Products)");
                    clasa = sc.next();
                    switch (clasa) {
                        case "Bill": {
                            readBill();
                            audit("Added new bill!");
                            break;
                        }
                        case "Courier": {
                            readCourier();
                            audit("Added new courier!");
                            break;
                        }
                        case "Client": {
                            readClient();
                            audit("Added new client!");
                            break;
                        }
                        case "Products": {
                            readProduct();
                            audit("Added new product!");
                            break;
                        }
                        case "DeliveryC": {
                            readDeliveryC();
                            audit("Added new delivery company!");
                            break;
                        }
                        case "Order": {
                            readOrder();
                            audit("Added new order!");
                            break;
                        }
                        case "Distributors": {
                            readDistributor();
                            audit("Added new distributor!");
                            break;
                        }

                    }
                    break;

                }

                case 2: {
                    String clasa;
                    System.out.println("Choose the class: (Courier/DeliveryC/Bill/Client/Order/Distributors/Products)");
                    clasa = sc.next();
                    switch (clasa) {
                        case "Bill": {
                            printAllBills();
                            break;
                        }
                        case "Courier": {
                            printAllCouriers();
                            break;
                        }
                        case "Client": {
                            printAllClients();
                            break;
                        }
                        case "Products": {
                            printAllProducts();
                            break;
                        }
                        case "Oder": {
                            printAllOrders();
                            break;
                        }
                        case "DeliveryC": {
                            printAllDeliveryC();
                            break;
                        }

                    }
                }
                break;


                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option!");

            case 3:
                updateOrder();
                audit("Order updated!");
                break;

            case 5:
                System.out.println("Delete the client with the id: ");
                int idc = sc.nextInt();
                sc.nextLine();
                clientService.deleteClient(idc);
                audit("Client deleted");
                break;

            case 6:
                System.out.println("Delete the order with the id: ");
                int id = sc.nextInt();
                sc.nextLine();
                orderService.deleteOrder(id);
                audit("Order deleted");
                break;

            case 7:
                System.out.println("Delete the product with the id: ");
                int ind = sc.nextInt();
                productService.deleteProduct(ind);
                audit("Product deleted");
                break;

            case 8:
                List<Products> prodVector = productService.getAllProducts();

                Collections.sort(prodVector, Products::compareTo);
                System.out.println(List.of(prodVector));

                break;

            case 9:
                System.out.println("Index prod: ");
                int inp = sc.nextInt();
                System.out.println("New quantity: ");
                int newQ = sc.nextInt();

                productService.updateProduct(newQ, inp);
                audit("Product quantity updated!");
                break;


            case 10:
                List<Distributors> disVect = distributorsService.getAllDistributors();

                Collections.sort(disVect, Distributors::compareTo);
                System.out.println(List.of(disVect));
                break;
            }

        }

        else
        System.out.println("Invalid Option! Try again.");
        return executeOption();
        }



    public void loadCSVFILES() {

        clientService.getFromCSVFile();
        productService.getFromCSVFile();
        orderService.getFromCSVFile();
        courierService.getFromCSVFile();

    }

    public void listCSVFILES() {

        clientService.listCSV();
        productService.listCSV();
        orderService.listCSV();
        courierService.listCSV();

    }

    private void audit(String action){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime timeNow = LocalDateTime.now();
        Audit auditServices = new Audit();
        auditServices.write(action, dtf.format(timeNow));
    }

    private void readOrder() throws InvalidDataException {

        System.out.println("Id order: ");
        int idorder = scanner.nextInt();
        System.out.println("Id bill: ");
        int idbill = scanner.nextInt();
        System.out.println("Id delivery company: ");
        int idDelc = scanner.nextInt();
        System.out.println("Client index: ");
        int clIndex = scanner.nextInt();

        orderService.addNewOrder(idorder, idbill, idDelc, clIndex);


    }

    public void readDeliveryC() throws InvalidDataException {

        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Number of couriers: ");
        int noCouriers = scanner.nextInt();
        SortedSet<Courier> couriers = new TreeSet<Courier>();

        for(int i=0; i< noCouriers; i++){

            System.out.println("First name: ");
            String fname = scanner.next();
            System.out.println("Last name: ");
            String lname = scanner.next();
            System.out.println("Phone number: ");
            String phno = scanner.next();
            System.out.println("Id courier: (must be uniq)");
            int idc = scanner.nextInt();
            Courier c = new Courier(idc, fname, lname, phno);

            couriers.add(c);
        }
        deliveryService.addNewDelivery(name, couriers);
    }


    public void readBill() throws InvalidDataException {

        System.out.println("Id bill: ");
        int idbill = scanner.nextInt();
        System.out.println("Number of products: ");
        int noprod = scanner.nextInt();
        ArrayList<Products> list = new ArrayList<>();

        for(int i=0; i < noprod; i++)
        {
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Price: ");
            double price = scanner.nextDouble();
            System.out.println("Quantity: ");
            int quantity = scanner.nextInt();

            Products p = new Products(name, price, quantity);
            list.add(p);



        }
        //double totalPrice = calculatePrice(list, noprod);
        double totalPrice=0;
        System.out.println("Total price is: ");
        totalPrice = scanner.nextDouble();
        for(int i=0; i < noprod; i++){
            //    totalPrice = totalPrice + list.price(i) * list.quantity(i);
        }
        billService.addNewBill(idbill, list, totalPrice);


//        System.out.println("Total price: ");
//        double totalPrice = scanner.nextDouble();
        //totalPrice = setTotalPrice(list.calculatePrice(prod)) ;
        //double totalPrice = calculatePrice(list,noprod );
        //billService.addNewBill(idbill, list, totalPrice);


    }

    private void readDistributor() {
        System.out.println("CUI: ");
        int cui = scanner.nextInt();
        System.out.println("Name of distributor: ");
        String nameD = scanner.next();
        System.out.println("City : ");
        String city = scanner.next();
        System.out.println("Number of products: ");
        int noprod = scanner.nextInt();
        ArrayList<Products> list = new ArrayList<>();

        for(int i=0; i < noprod; i++)
        {
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Price: ");
            double price = scanner.nextDouble();
            System.out.println("Quantity: ");
            int quantity = scanner.nextInt();

            Products p = new Products(name, price, quantity);
            list.add(p);

        }
        distributorsService.addNewDistributor(cui, nameD, city, list);
    }

    public double calculatePrice(ArrayList<Products> prod, int noprod){
        double totalPrice=0;
        for(int i=0; i < noprod; i++){
        //    totalPrice = totalPrice + prod.getPrice(i) * prod.getQuantity(i);
        }
        return totalPrice ;

    }

    void readCourier() throws InvalidDataException {
        System.out.println("First name: ");
        String fname = scanner.next();
        System.out.println("Last name: ");
        String lname = scanner.next();
        System.out.println("Phone number: ");
        String phno = scanner.next();
        System.out.println("Id courier: (must be uniq)");
        int idc = scanner.nextInt();

        courierService.addNewCourier(fname, lname, phno, idc);
        System.out.println("Courier added!");
    }

    void readClient() throws InvalidDataException {
        System.out.println("First name: ");
        String fname = scanner.next();
        System.out.println("Last name: ");
        String lname = scanner.next();
        System.out.println("Phone number: ");
        String phno = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Address: ");
        scanner.useDelimiter("\n");
        String address = scanner.next();
        scanner.reset();

        clientService.addNewClient(fname, lname, phno, email, address);
        System.out.println("Client added!");
    }

    public void readProduct() throws InvalidDataException {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Quantity: ");
        int quantity = scanner.nextInt();

        productService.addNewProduct(name, price, quantity);
        System.out.println("New product added!");


    }


    void printAllBills(){
        ArrayList<Bill> billVector = billService.getAllBills();
        ArrayList<Products> prodVector = productService.getAllProducts();
        if(billVector.size() == 0)
            System.out.println("No bills yet!");
        for(Bill bill : billVector) {
            for(Products products: prodVector)
                System.out.println(bill.getProducts(products));
           // printAllProducts(billVector);


        }
    }

    void printAllDeliveryC(){
        ArrayList<DeliveryCompany> compVector = deliveryService.getAllDeliverycomp();
        ArrayList<Courier> couriersVector = courierService.getAllCouriers();
        if(compVector.size() == 0)
            System.out.println("No company yet!");
        for(DeliveryCompany delcomp : compVector) {
            for(Courier courier: couriersVector)
            System.out.println(delcomp);



        }
    }


    void printAllCouriers(){
        ArrayList<Courier> couriers = courierService.getAllCouriers();
        if(couriers.size() == 0)
            System.out.println("No couriers yet!");
        for(Courier courier : couriers) {
            System.out.println(courier);
        }
    }

    void printAllOrders(){
        ArrayList<Order> orders = orderService.getAllOrders();
        if(orders.size() == 0)
            System.out.println("No orders yet!");
        for(Order order : orders) {
            System.out.println(order);
        }
    }

    void printAllClients(){
        ArrayList<Client> clients = clientService.getAllClients();
        if(clients.size() == 0)
            System.out.println("No clients yet!");
        for(Client client : clients) {
            System.out.println(client);
        }
    }

    void printAllProducts(){
        List<Products> products = productService.getAllProducts();
        if(products.size() == 0)
            System.out.println("No products yet!");
        for(Products product : products) {
            System.out.println(product);
        }
    }

    void updateOrder() {
        System.out.print("Order index:");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Update client :");
        int clIndex = scanner.nextInt();

        orderService.updateClient(index, clIndex);

    }

//    private void searchAllOrders(String email, List<Order> comenzi){
//        boolean OK = false;
//        for (OrderService comanda : comenzi) {
//            if(comanda.getClientRepository()..equalsIgnoreCase(email)) {
//                System.out.println(comanda);
//                OK = true;
//            }
//        }
//        if(!OK)
//            System.out.println("Client with email " + email + " didn't make any order");
//
//        return bankAccounts.stream()
//                .filter(bankAccount -> bankAccount.getClient() != null &&
//                        bankAccount.getClient().getEmail() != null &&
//                        bankAccount.getClient().getEmail().equals(clientEmail))
//                .count();
//    }







}
