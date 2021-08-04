package core;

import java.util.ArrayList;
import java.util.ListIterator;
import exception.ParkingSlotHandlingException;
public class Slot {

    private int slotNumber;
    private String floor;
    public static int noOfSlots;
    public static ArrayList<Car> parkingSlot=new ArrayList<>(100);

    static{
        noOfSlots=10;
    }


    public Slot(int slotNumber, String floor) {
        this.slotNumber = slotNumber;
        this.floor = floor;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public static ArrayList<Car> getParkingSlot() {
        return parkingSlot;
    }

    public static void setParkingSlot(ArrayList<Car> parkingSlot) {
        Slot.parkingSlot = parkingSlot;
    }
}


//======================================================================


//    public Slot(int slotNumber, String floor, boolean isSlotAvailable) {
//        this.slotNumber = slotNumber;
//        this.floor = floor;
//        // this.noOfSlots = noOfSlots;
//        //if (isSlotAvailable) this.isSlotAvailable = true;
//        this.isSlotAvailable = false;
//    }
//
//    public static void prepareSlots(){
//        for(int i=0;i<100;i++)
//        {
//            slots.add(new Slot(-1,"NA",false));
//        }
//    }
//
//    public static Slot getEmptySlot() throws ParkingSlotHandlingException{
//        Slot tmpSlot=null;
//        if(slots.isEmpty()){
//            throw new ParkingSlotHandlingException("Slots not prepared..!!");
//        }
//        else{
//            int i=0;
//            ListIterator<Slot> itr=slots.listIterator();
//            for(;itr.hasNext();){
//                if(i>100)
//                    throw new ParkingSlotHandlingException("Slot Not Available");
//                i++;
//                tmpSlot= itr.next();
//                if(tmpSlot.isSlotAvailable()==false ){
//
//                    tmpSlot.setSlotNumber(i);
//                    tmpSlot.setFloor("Floor A");
//                    tmpSlot.setSlotAvailable(true);
//                    return tmpSlot;
//                }
//            }
//        }
//        return tmpSlot;
//    }
//
//    public void removeSlot(Slot slot) throws ParkingSlotHandlingException{
//        if(slots.isEmpty()){
//            throw new ParkingSlotHandlingException("All Slots are availble!!!");
//        }
//        slots.remove(slot);
//    }
//
//    public int getSlotNumber() {
//        return slotNumber;
//    }
//
//    public boolean isSlotAvailable() {
//        return isSlotAvailable;
//    }
//
//    //    public static void  slotInitializer() {
////
////        for(int i=0;i<100;i++){
////            slots[i]=new Slot(-1,"NA",false);
////        }
////
////    }
//
//    @Override
//    public String toString() {
//        return "Slot{" +
//                "slotNumber=" + slotNumber +
//                ", floor='" + floor + '\'' +
//                '}';
//    }
//
//    public void setSlotNumber(int slotNumber) {
//        this.slotNumber = slotNumber;
//    }
//
//    public void setFloor(String floor) {
//        this.floor = floor;
//    }
//
//    public void setSlotAvailable(boolean slotAvailable) {
//        isSlotAvailable = slotAvailable;
//    }
//
//



//====================================================

//       int i;
//        if(slots[0]){
//
//            slots[0].setSlotNumber(0);
//            slots[0].setSlotAvailable(true);
//            slots[0].setFloor("A");
//            tmpSlot= slots[0];
//                return tmpSlot;
//        }
//        {
//            for( i =0;i<100;i++){

//                if(slots[i].isSlotAvailable()==false){
//
//                    slots[i].setSlotAvailable(true);
//                    slots[i].setSlotNumber(i);
//                    if(i>=0 && i<=40)
//                        slots[i].setFloor("Floor: A");
//                    else if(i>=41 && i<=80)
//                        slots[i].setFloor("Floor: B");
//                    else if(i>=81 && i<=100)
//                        slots[i].setFloor("Floor: C");
//                    tmpSlot=slots[i];
//                }
