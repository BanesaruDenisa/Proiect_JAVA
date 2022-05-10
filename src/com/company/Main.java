package com.company;

import exception.InvalidDataException;
import services.Services;


public class Main {

    public static void main(String[] args) throws InvalidDataException {

        Services myServ = new Services();
        myServ.executeOption();
        myServ.loadCSVFILES();



    }
}
