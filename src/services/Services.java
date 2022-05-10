package services;

import courier.Courier;
import courier.DeliveryCompany;
import exception.InvalidDataException;
import order.Bill;
import order.Client;
import product.Products;
import services.csv.Audit;
import services.csv.ClientCSVService;

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


    public Services(){}

    private void audit(String action){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime timeNow = LocalDateTime.now();
        Audit auditServices = new Audit();
        auditServices.write(action, dtf.format(timeNow));
    }

    public void loadCSVFILES() {
        clientService.getFromCSVFile();
        listCSV();

    }
    public void listCSV(){
        ClientCSVService clientCSV = ClientCSVService.getInstance();
        ClientService clientService = new ClientService();
        ArrayList<Client> clListCsv = new ArrayList<>(clientService.getAllClients());
        for(Client client : clListCsv){
            clientCSV.write(client);
        }
    }

    private void Menu() {

        System.out.println("******* Managing the orders of a store *******");
        System.out.println("--- What do you want to do? ---");
        System.out.println();
        System.out.println("1. Read ");
        System.out.println("2. Print ");
        System.out.println("3. Update order");
        System.out.println("4. Update bill ");
        System.out.println("5. Update client's address ");
        System.out.println("6. Delete order");
        System.out.println("7. Delete distributor");
        System.out.println("8. Sortare produse dupa pret. (asc/desc)");
        System.out.println("9. Listarea comenzilor unui client.");
        System.out.println("10.  ");
        System.out.println("0. Iesire. ");


    }

    public int executeOption() throws InvalidDataException {

        Scanner sc = new Scanner(System.in);
        int option = 0;
        Menu();
        System.out.println(" Choose an option: ");

        option = sc.nextInt();
        if(option >= 0 && option < 11) {

            switch (option) {
                case 1: {
                    String clasa;
                    System.out.println("Choose the class: (Courier/Delivery/Bill/Client/Order/Distributors/Products)");
                    clasa = sc.next();
                    switch (clasa) {
                        case "Bill": {
                            readBill();
                            audit("Added new bill!");
                            break;
                        }
                        case "Courier": {
                            readCourier();
                            audit("Added new bill!");
                            break;
                        }
                        case "Client":{
                            readClient();
                            audit("Added new client!");
                            break;
                        }
                        case "Products":{
                            readProduct();
                            break;
                        }
                        case "Delivery":{
                            readDelivery();
                            break;
                        }


                    }
                }
                break;
                case 2: {
                    String clasa;
                    System.out.println("Choose the class: (Courier/DeliveryCompany/Bill/Client/Order/Distributors/Products)");
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
                        case "Client":{
                            printAllClients();
                            break;
                        }
                        case "Products": {
                            printAllProducts();
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


            }
        }
        else
        System.out.println("Invalid Option! Try again.");
        return executeOption();
        }

    public void readDelivery() throws InvalidDataException {

        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Id courier: ");
        int idcour = scanner.nextInt();
        System.out.println("Number of couriers: ");
        int noCouriers = scanner.nextInt();
        SortedSet<Courier> couriers = new TreeSet<Courier>();

        for(int i=0; i< noCouriers; i++){

            readCourier();
        }
        deliveryService.addNewDelivery(name, idcour, couriers);
    }


    public void readBill() throws InvalidDataException {
        System.out.println("Id bill: ");
        int idbill = scanner.nextInt();
        System.out.println("Number of products: ");
        int noprod = scanner.nextInt();
       // Products list = new Products();
        ArrayList<Products> list = new ArrayList<>();

        for(int i=0; i < noprod; i++)
        {
          // list.readProduct();
        }

//        System.out.println("Total price: ");
//        double totalPrice = scanner.nextDouble();
        //totalPrice = setTotalPrice(list.calculatePrice(prod)) ;
        double totalPrice = calculatePrice(list,noprod );
        billService.addNewBill(idbill, list, totalPrice);


    }

    public double calculatePrice(ArrayList<Products> prod, int noprod){
        double totalPrice=0;
        for(int i=0; i < noprod; i++){
          //  totalPrice = totalPrice + prod. * prod.getQuantity();
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
        System.out.println("Product added!");


    }
    public ArrayList<Products> readArrProduct() throws InvalidDataException {
        ArrayList<Products> arrayList = new ArrayList<>();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Quantity: ");
        int quantity = scanner.nextInt();

//        arrayList.addNewProduct(name, price, quantity);
//        System.out.println("Product added!");
//        return new ArrayList<Products>(name, price, quantity);   ????
        return  null;

    }

    void printAllBills(){
        ArrayList<Bill> billVector = billService.getAllBills();
        ArrayList<Products> prodVector = productService.getAllProducts();
        if(billVector.size() == 0)
            System.out.println("No bills yet!");
        for(Bill bill : billVector) {
            for(Products products: prodVector)
            System.out.println(bill);
           // printAllProducts(billVector);


        }
    }


    void printAllCouriers(){
        Vector<Courier> couriers = courierService.getAllCouriers();
        if(couriers.size() == 0)
            System.out.println("No couriers yet!");
        for(Courier courier : couriers) {
            System.out.println(courier);
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


//    private void addCourier(DeliveryCompany company) {
//        Scanner scn = new Scanner(System.in);
//        System.out.println("First name: ");
//        String fName = scn.next();
//        System.out.println("Last name: ");
//        String lName = scn.next();
//        System.out.println("Phone number (10 digits, start with 07): ");
//        String phoneNr;
//        int cond = 0;
//        while (cond == 0) {
//            phoneNr = scn.next();
//            cond = 1;
//            if (isValid(phoneNr)) {
//            } else {
//                System.out.println("Invalid number!");
//                cond = 0;
//            }

//        int condition = 0;
//        while(condition == 0){
//            String phoneNr = scn.next();
//            condition = 1;
//            try{
//            if(!phoneNr.matches("(07)[0-9]{8}" )) {
//                throw phoneNr;
//            }} catch(...)
//            System.out.println("Invalid phone number!");
//            condition = 0;
//        }
//
//            Courier newCourier = new Courier(fName, lName, phoneNr);
//            newCourier.setIdCourier(company.getIdCourier());
//            company.addCourier(newCourier);
//            System.out.println(newCourier);
//        }
//    }
//    private void afis(DeliveryCompany del){
//        SortedSet<Courier> curier = del.getCouriers();
//        for(Courier cour : curier)
//            System.out.println(curier);
//    }
//
//    public boolean isValid(String number){
//
//        if(number.matches("(07)[0-9]{8}" ))
//            return true;
//            else
//            {
//                return false;
//            }
//        }

}
