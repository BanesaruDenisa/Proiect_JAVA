package Services;

import Courier.Courier;
import Courier.DeliveryCompany;
import Order.Bill;
import Order.Client;
import Product.Products;
import Repository.ProductsRepository;

import javax.management.InvalidAttributeValueException;
import javax.sound.midi.InvalidMidiDataException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Services {

    Scanner scanner = new Scanner(System.in);

    private BillService billService = new BillService();
    private CourierService courierService = new CourierService();
    private ClientService clientService = new ClientService();
    private ProductService productService = new ProductService();


    public Services(){}

    private void Menu() {

        System.out.println("*******Gestionare comenzi magazin*******");
        System.out.println("--- Alegeti o optiune ---");
        System.out.println();
        System.out.println("1. Citire ");
        System.out.println("2. Afisare ");
        System.out.println("3. Modificare ");
        System.out.println("4. Stergere ");
        System.out.println("5. Cautare ");
        System.out.println("6. Sortare produse dupa pret. (asc/desc)");
        System.out.println("7. Listarea comenzilor unui client.");
        System.out.println("8. Update factura");
        System.out.println("9. Delete comanda");
        System.out.println("10. Delete producator ");
        System.out.println("0. Iesire. ");


    }

    public int executeOption() throws InvalidMidiDataException {

        Scanner sc = new Scanner(System.in);
        int option = 0;
        Menu();
        System.out.println("Choose option: ");

        option = sc.nextInt();
        if(option >= 0 && option < 11) {

            switch (option) {
                case 1: {
                    String clasa;
                    System.out.println("Choose the class: (Courier/DeliveryCompany/Bill/Client/Order/Distributors/Products)");
                    clasa = sc.next();
                    switch (clasa) {
                        case "Bill": {
                            readBill();
                        }
                        case "Courier": {
                            readCourier();
                        }
                        case "Client":{
                            readClient();
                        }
                        case "Products":{
                            readProduct();
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
                        }
                        case "Courier": {
                            printAllCouriers();
                        }
                        case "Client":{
                            printAllClients();
                        }
                        case "Products": {
                            printAllProducts();
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



    public void readBill(){
        System.out.println("Id bill: ");
        int idbill = scanner.nextInt();
        System.out.println("Nr de produse: ");
        int nrprod = scanner.nextInt();
        List<Products> list = new ArrayList<>();
        Products prod = new Products();
        System.out.println("Lista de produse: ");
        for(int i=0; i< nrprod;i++) {
            System.out.println("NUme prod");
            String nume = scanner.next();
            prod.setName(nume);
            System.out.println("pret ");
            double pret = scanner.nextDouble();
            prod.setPrice(pret);
            System.out.println("Cate produ");
            int cantitate = scanner.nextInt();
            prod.setQuantity(cantitate);

            list.add(prod);

//            Vector<Products> products = new Vector<>();
//            for(int i = 0; i < productsRepository.getSize(); i++)
//                bills.add(billRepository.get(i));
//
//            return bills;

        }
        billService.addProducts(prod);
        double totalPrice = scanner.nextDouble();
        //totalPrice = setTotalPrice(list.calculatePrice(prod)) ;
        billService.addNewBill(idbill, list,totalPrice);
    }

    void readCourier() throws InvalidMidiDataException {
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

    void readClient() throws InvalidMidiDataException {
        System.out.println("First name: ");
        String fname = scanner.next();
        System.out.println("Last name: ");
        String lname = scanner.next();
        System.out.println("Phone number: ");
        String phno = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Address: ");
        String address = scanner.next();

        clientService.addNewClient(fname, lname, phno, email, address);
        System.out.println("Client added!");
    }

    void readProduct() throws InvalidMidiDataException {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Quantity: ");
        int quantity = scanner.nextInt();

        productService.addNewProduct(name, price, quantity);
        System.out.println("Product added!");
    }

    void printAllBills(){
        Vector<Bill> billVector = billService.getAllBills();
        if(billVector.size() == 0)
            System.out.println("No bills yet!");
        for(Bill bill : billVector) {
            System.out.println(bill);
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
        Vector<Client> clients = clientService.getAllClients();
        if(clients.size() == 0)
            System.out.println("No clinets yet!");
        for(Client client : clients) {
            System.out.println(client);
        }
    }

    void printAllProducts(){
        Vector<Products> products = productService.getAllProducts();
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
