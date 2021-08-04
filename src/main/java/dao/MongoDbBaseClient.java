package dao;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import core.Car;
import core.Slot;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import util.AppConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoDbBaseClient extends BaseClient{
    public MongoDbBaseClient() {
        AppConfig.getMongoDBConnection();
        prepareDatabase();
    }
    public void prepareDatabase(){
        try {
            MongoCollection<Document> collection = new AppConfig().getCollection();
            Document document = new Document();
            for (int i = 1; i <= Slot.noOfSlots; i++) {
                document.append("_id", i);
                document.append("plateNumber",0);
                document.append("colour", null);
                collection.insertOne(document);
            }
        }catch (Exception ex){
            ex.getMessage();
        }
    }


    @Override
    public void carList() {
        try {
            List<Document> carInfo = AppConfig.getCollection().find().into(new ArrayList<>());
            boolean flag=false;
            for (Document info : carInfo) {
                int slotNumber = info.getInteger("_id");
                int plateNumber = info.getInteger("plateNumber");
                String colour = info.getString("colour");
                if (plateNumber != 0 && colour != null) {
                    System.out.println(slotNumber + "  " + plateNumber + "  " + colour);
                    flag=false;
                }else{
                    flag=true;
                }
            }
            if(!flag){
                System.out.println("car list empty");
                return ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void carEntry() {
        try {
            Car newCar=registerVehicle();
                Bson filter = eq("colour", null);
                Bson updateOperationFirst = set("plateNumber", newCar.getPlateNumber());
                Bson updateOperationSecond = set("colour", newCar.getColour());
              AppConfig.getCollection().updateOne(filter,
                        updateOperationFirst);
                AppConfig.getCollection().updateOne(filter,
                        updateOperationSecond);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void carExit() {
        try {
            List<Document> slotInfo = AppConfig.getCollection().find().into(new ArrayList<>());
            boolean flag=false;
            System.out.println("Enter Slot Number:");
            int slotNumber=sc.nextInt();
            for (Document info : slotInfo) {
                int id = info.getInteger("_id");
                if (id==slotNumber) {
                    AppConfig.getCollection().updateOne(eq("_id", info.getInteger("_id")), set("plateNumber",0));
                    AppConfig.getCollection().updateOne(eq("_id", info.getInteger("_id")), set("colour", null));
                    flag=false;
                    break;
                }else{
                    flag=true;
                }
            }
            if(!flag){
                System.out.println("Successful..");
                return ;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getSlotNumberByRegistrationNumber()  {
        try {
            System.out.println("Enter Plate Number:");
            int plateNumber=sc.nextInt();
            List<Document> carInfo = AppConfig.getCollection().find(Filters.eq("plateNumber", plateNumber)).into(new ArrayList<>());
            if(carInfo.isEmpty()){
                System.out.println("Car not found..!!");
                return ;
            }
            for (Document info : carInfo) {
                    System.out.println("Slot Number:"+info.getInteger("_id"));
                }
            }
        catch (Exception e) {
            e.printStackTrace();
            }
       }

    @Override
    public void getRegistrationNumberByColour() {
        try {
            List<Document> slotInfo = AppConfig.getCollection().find().into(new ArrayList<>());
            System.out.println("Enter Colour:");
            String clr=sc.next();
            ArrayList<Car> list=new ArrayList<>();
            for (Document info : slotInfo) {
                String colour = info.getString("colour");
                int plateNumber=info.getInteger("plateNumber");
                if (clr.equals(colour)) {
                    list.add(new Car(plateNumber,colour));
                }
            }
            if(list.isEmpty()){
                System.out.println("car not found");
                return ;
            }else{
                for(Car c : list){
                    System.out.println(c);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
