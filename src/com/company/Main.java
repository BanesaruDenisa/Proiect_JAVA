package com.company;

import config.DBConnection;
import config.DbRepository.ClientDbRepo;
import config.DbRepository.CourierDbRepo;
import config.DbRepository.OrderDbRepo;
import config.DbRepository.ProductDbRepo;
import courier.Courier;
import exception.InvalidDataException;
import order.Client;
import order.Order;
import product.Products;
import services.OrderService;
import services.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InvalidDataException {

        DBConnection db = new DBConnection("jdbc:mysql://localhost:3306/store", "root", "bananaverde.ro20");

        Services myServ = new Services();


        System.out.println("Choose terminal or database (enter t or d): ");
        Scanner sc = new Scanner(System.in);
        String opt;
        opt = sc.next();

        switch (opt){
            case "t" :
                myServ.executeOption();
                myServ.loadCSVFILES();
                myServ.listCSVFILES();

            case "d":
                myServ.executeOptionDB();
        }




       // crDB.deleteC(1);
       // System.out.println("courier deleted");
    }
}
