package dao;

import core.Car;
import core.Slot;
import util.AppConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RedisBaseClient extends BaseClient{

    @Override
    public void carList()  {
        for (int i = 0; i < Slot.noOfSlots; i++) {
            String index = String.valueOf(i);
             if (AppConfig.getJedis().exists(index)){
                System.out.println("Car Colour:" + AppConfig.getJedis().hget(index, "colour"));
                System.out.println("Plate Number:" + AppConfig.getJedis().hget(index, "plateNumber"));
                System.out.println("Slot Number:" + AppConfig.getJedis().hget(index, "slot"));
//                AppConfig.getJedis().del(index);
            }
        }
    }

    @Override
    public void carEntry()  {

        Car vehicle =registerVehicle();
            for (int i = 1; i <= Slot.noOfSlots; i++) {
                String index = String.valueOf(i);
                Map<String,String> slotInfo=new HashMap<>();
                String availableSlotNumber=getAvailableSlotNumber();
                if(availableSlotNumber.equals(null)){
                    System.out.println("Slot Not Available...");
                    return;
                }

                slotInfo.put("slot",availableSlotNumber);
                Map<String, String> vehicleInfo = new HashMap<>();
                vehicleInfo.put("plateNumber", String.valueOf(vehicle.getPlateNumber()));
                vehicleInfo.put("colour", vehicle.getColour());

                if (!AppConfig.getJedis().hexists(index, "plateNumber")) {
                    AppConfig.getJedis().hmset(index, vehicleInfo);
                    AppConfig.getJedis().hmset(index,slotInfo);
                    break;
                }
            }
    }

    private String getAvailableSlotNumber() {
        for (int i = 1; i <= Slot.noOfSlots; i++) {
            String index = String.valueOf(i);

            if (!AppConfig.getJedis().hexists(index, "slot")) {
                System.out.println("tmp:" + index);
                return index;
            }
         }
        return null;
    }


    @Override
    public void carExit() {
        System.out.print("Enter vehicle Registration Number :");
        String regNo=sc.next();
        for (int i = 1; i <= Slot.noOfSlots; i++) {
            String index=String.valueOf(i);
            if(AppConfig.getJedis().hget(index,"plateNumber").equals(regNo)){
                AppConfig.getJedis().hdel(index,"plateNumber");
                AppConfig.getJedis().hdel(index,"colour");
                System.out.println("Slot "+AppConfig.getJedis().hget(index,"slot")+" is now available");
                AppConfig.getJedis().hdel(index,"slot");
                break;
            }
        }
    }

    @Override
    public void getSlotNumberByRegistrationNumber()  {
        boolean isVehicleAvailable=true;
        System.out.print("Enter Registration Number :");
        String plateNumber= sc.next();
        for (int i = 1; i <= Slot.noOfSlots; i++) {
            String index=String.valueOf(i);
            if( AppConfig.getJedis().hget(index,"plateNumber") !=null  &&
                AppConfig.getJedis().hget(index,"plateNumber").equals(plateNumber)
            ){
                System.out.println("Slot Number:" + AppConfig.getJedis().hget(index,"slot"));
                isVehicleAvailable=true;
                break;
            }
        }
        if(!isVehicleAvailable){
            System.out.println("No vehicle is available with "+plateNumber+" Number");
        }
    }

    @Override
    public void getRegistrationNumberByColour(){
        ArrayList<Car>carList=new ArrayList<>();
          System.out.print("Enter Colour:");
        String colr= sc.next();
        for (int i = 1; i <= Slot.noOfSlots; i++) {
            String index=String.valueOf(i);
            if(AppConfig.getJedis().hget(index,"colour")!=null &&
                    AppConfig.getJedis().hget(index,"colour").equals(colr) ){
                carList.add(
                        new Car(
                                Integer.parseInt(AppConfig.getJedis().hget(index,"plateNumber")),
                                AppConfig.getJedis().hget(index,"colour")
                        )
                );
            }
        }
        if(carList.isEmpty()){
            System.out.println("Car Not Found");
        }
        else
            for(Car car:carList)
            System.out.println(car);
    }

}
