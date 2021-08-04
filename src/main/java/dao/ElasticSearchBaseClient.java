package dao;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import core.Car;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import util.AppConfig;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

public class ElasticSearchBaseClient extends BaseClient {
    Scanner sc = new Scanner(System.in);
    @Override
    public void carExit() {
        System.out.print("Enter Slot No :");
        String slotNumber = sc.nextLine();
        try {
            DeleteRequest deleteRequest = new DeleteRequest("parking_system");
            deleteRequest.id(slotNumber);
            DeleteResponse deleteResponse = AppConfig.getElasticClient().delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                System.out.println("car not found!!");
            }else
                System.out.println("data deleted!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void getSlotNumberByRegistrationNumber() {
        System.out.print("Enter Registration No :");
        String regNo = sc.nextLine();
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery("RegistrationNumber", regNo));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(sourceBuilder);
            SearchResponse response = AppConfig.getElasticClient().search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(response.toString());
            for (SearchHit hit : response.getHits()) {
                Map<String, Object> map = hit.getSourceAsMap();
                Object registrationNumber = map.get("RegistrationNumber");
                if (registrationNumber.equals(regNo)) {
                    System.out.println("slot No:" + map.get("Slot"));
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getRegistrationNumberByColour() {
        System.out.print("Enter vehicle colour :");
        String colour = sc.nextLine();
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(QueryBuilders.matchQuery("Colour", colour));
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(sourceBuilder);
            SearchResponse response = AppConfig.getElasticClient().search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()) {
                Map<String, Object> map = hit.getSourceAsMap();
                Object vehicleColour = map.get("Colour");
                if (vehicleColour.equals(colour)) {
                    System.out.println("Plate Number :" + map.get("RegistrationNumber"));
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void carList() {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(matchAllQuery());
            SearchRequest searchRequest = new SearchRequest("parking_system");
            searchRequest.source(sourceBuilder);

            SearchResponse response=AppConfig.getElasticClient().search(searchRequest,RequestOptions.DEFAULT);
            for (SearchHit hit : response.getHits()){
                Map<String, Object> map = hit.getSourceAsMap();
                Object colour=String.valueOf(map.get("Colour"));
                //System.out.println(colour+"++");
                if(!colour.equals("NULL")){
                    System.out.println("slot No :"+map.get("Slot"));
                    System.out.println("Platte No :"+map.get("RegistrationNumber"));
                    System.out.println("Colour :"+map.get("Colour"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void carEntry() {
        boolean isParkingFull = true;
        try {
            Car vehicle = registerVehicle();
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            SearchRequest searchRequest = new SearchRequest("parking_system");
            sourceBuilder.query(QueryBuilders.matchQuery("RegistrationNumber", "NULL" ));
            searchRequest.source(sourceBuilder);
            SearchResponse response = AppConfig.getElasticClient().search(searchRequest, RequestOptions.DEFAULT);
            Map<String, Object> CarDetails ;
            int id=1;
            for (SearchHit hit : response.getHits()) {
                CarDetails = hit.getSourceAsMap();
                if (CarDetails.get("Colour").equals("NULL")) {
                    id = Integer.parseInt(String.valueOf(CarDetails.get("Slot")));
                    isParkingFull = false;
                    break;
                }
            }
            if (!isParkingFull) {
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("RegistrationNumber", String.valueOf(vehicle.getPlateNumber()));
                updateMap.put("Colour", vehicle.getColour());
                updateMap.put("Slot", id);
                IndexRequest request = new IndexRequest("parking_system");
                request.id(String.valueOf(id));
                request.source(updateMap);
                IndexResponse indexResponseUpdate = AppConfig.getElasticClient().index(request, RequestOptions.DEFAULT);
                System.out.println("Plate No:" + vehicle.getPlateNumber());
                System.out.println("Colour:" + vehicle.getColour());
                System.out.println("Slot:" + indexResponseUpdate.getId());
            } else {
                System.out.println(" full Parking...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


