package core;

import java.util.ArrayList;
import java.util.Scanner;

import exception.ParkingSlotHandlingException;
public class Car {
        int plateNumber;
        String colour;

    public Car() {
    }

    public Car(int plateNumber  , String colour) {
        this.plateNumber=plateNumber;
        this.colour=colour;
    }


    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setcolour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber=" + plateNumber +
                ", colour='" + colour + '\'' +
                '}';
    }
}

//====================================
//
//    public static Car getCarBySlotNumber(int slotNumber, ArrayList<Car> carList){
//
//        if(carList==null)
//            return null;
//
//        for(Car tmpCar:carList){
//            if(tmpCar.getSlot().equals(slotNumber))
//                return tmpCar;
//        }
//        return null;
//    }
//
//    public static void removeCarFromList(ArrayList<Car> carList)throws ParkingSlotHandlingException {
//
//        boolean flag;
//        int slotNumber;
//        Car tmpCar = null;
//        System.out.println("Enter Slot Number:");
//        slotNumber = getScanner().nextInt();
//
//        if (carList.isEmpty()) {
//            System.out.println("List is Empty..!!");
//        }
//        for (Car carTmp : carList) {
//            if (carTmp.getSlot().getSlotNumber() == slotNumber) {
//                tmpCar = carTmp;
//            }
//        }
//        if (tmpCar == null)
//            System.out.println("Car Details not Found..");
//        else {
//            System.out.println("Car & Slot Details:");
//            System.out.println(tmpCar + "\n" + tmpCar.getSlot());
//            tmpCar.removeSlot();
//            carList.remove(tmpCar);
//        }
//
//
//
//    }
//
//    public static void vehicleEntry(ArrayList<Car> carList) throws ParkingSlotHandlingException {
//        //Slot.slotInitializer();
//        Slot slot=Slot.getEmptySlot();
//        if(slot==null) {
//            System.out.println("Parking Not Available..!!");
//        } else {
//            Car newCar=new Car();
//            System.out.println("Enter Plate Number:");
//            newCar.setPlateNumber(getScanner().nextInt());
//            System.out.println("Enter Car colour:");
//            newCar.setcolour(getScanner().next());
//            newCar.setSlot(slot);
//            carList.add(newCar);
//            System.out.println("Parking Details:");
//            System.out.println(newCar.getSlot());
//        }
//    }
//
//    public void  removeSlot() throws ParkingSlotHandlingException{
//        this.slot.removeSlot(this.slot);
//    }
//
//    public static void getCarListBycolour(ArrayList<Car> carList){
//        ArrayList<Car> tmpList = new ArrayList<>();
//        System.out.println("Enter colour:");
//        String colour = getScanner().next();
//
//        if (carList.isEmpty()) {
//            System.out.println("List is Empty..!!");
//            return;
//        }
//        for (Car carTmp : carList) {
//            if (carTmp.getcolour().equals(colour)) {
//                tmpList.add(carTmp);
//            }
//        }
//        if (tmpList == null)
//            System.out.println("Car Details not Found..");
//        else {
//            int i = 0;
//            System.out.println("Car & Slot Details:");
//            for (Car carTmp : tmpList) {
//                System.out.println(++i + "\n" + carTmp + "\n" + carTmp.getSlot() + "\n");
//            }
//        }
//    }
//
//
//    public static void getCarByRegistrationNumber(ArrayList<Car> carList) throws ParkingSlotHandlingException{
//        Car tmpCar = null;
//        System.out.println("Enter Registration Number:");
//        int registrationNumber = getScanner().nextInt();
//
//        if (carList.isEmpty()) {
//            throw new ParkingSlotHandlingException("carListEmpty!!!");
//        }
//        for (Car carTmp : carList) {
//            if (carTmp.getPlateNumber() == registrationNumber) {
//                tmpCar = carTmp;
//            }
//        }
//        if (tmpCar == null)
//            throw new ParkingSlotHandlingException("Car Not Found !!!");
//        else {
//            System.out.println("Car & Slot Details:");
//            System.out.println(tmpCar + "\n" + tmpCar.getSlot());
//        }
//
//    }
//
//
//    public Car(Slot slot, int plateNumber, String colour) {
//        this.slot = slot;
//        this.plateNumber = plateNumber;
//        this.colour = colour;
//    }
//
//    private static Scanner getScanner(){
//        return new Scanner(System.in);
//    }
//
//    public Car() {
//    }
//
//    public Slot getSlot() {
//        return slot;
//    }
//
//    public void setSlot(Slot slot) {
//        this.slot = slot;
//    }
//
//    public int getPlateNumber() {
//        return plateNumber;
//    }
//
//    public void setPlateNumber(int plateNumber) {
//        this.plateNumber = plateNumber;
//    }
//
//    public String getcolour() {
//        return colour;
//    }
//
//    public void setcolour(String colour) {
//        this.colour = colour;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "plateNumber=" + plateNumber +
//                ", colour='" + colour + '\'' +
//                '}';
//    }