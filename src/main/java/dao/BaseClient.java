package dao;

import core.Car;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class BaseClient {

    Scanner sc=new Scanner(System.in);
    public Car registerVehicle() {
        System.out.print("Enter Vehicle No :");
        int regNo=sc.nextInt();
        System.out.print("Enter Vehicle Colour :");
        String colour=sc.next();
            Car car=new Car();
            car.setcolour(colour);
            car.setPlateNumber(regNo);
            return car;
    }

    public int getRegistrationNumber(){
        System.out.println("Enter Car Registration Number : ");
        int registrationNumber = sc.nextInt();
        return registrationNumber;
    }

    public String getColor(){
        System.out.println("Enter Car Color : ");
        String color = sc.next();
        return color;
    }

    public abstract void carList() ;
    public abstract void carEntry();
    public abstract void carExit() ;
    public abstract void getSlotNumberByRegistrationNumber();
    public abstract void getRegistrationNumberByColour();
}
