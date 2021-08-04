package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import core.Slot;
import org.apache.http.HttpHost;
import org.bson.Document;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import redis.clients.jedis.Jedis;


public class AppConfig {
    private static String client;
    private static MongoDatabase mongoDatabase;
    private static MongoCollection<Document> collection;
    private static Properties properties;
    private static MongoClient mongoClient;
    private static BasicDBObject updateFields;
    private static BasicDBObject setQuery;
    private static BasicDBObject whereQuery;
    private static RestHighLevelClient elasticClient;
    private static Jedis jedis;

    public AppConfig(){
        setProperties();
        setClient();

        if(client.equals("redis")){
            setJedis();
        }
        if(client.equals("elasticsearch")){
            getElasticsearchConnection();
            //createIndex();
        }
    }

    public static Connection fetchMySqlDBConnection() throws Exception {
        Class.forName(properties.getProperty("jdbc.driver"));
        return DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.userName"),
                properties.getProperty("jdbc.password"));
    }

    public static  void getMongoDBConnection(){
        mongoClient = new MongoClient(properties.getProperty("mongo.localhost"),
                Integer.parseInt(properties.getProperty("mongo.port")));

        mongoDatabase = mongoClient.getDatabase("parking_system");
        collection = mongoDatabase.getCollection("parking_system");
        setUpdateFields(new BasicDBObject());
        setSetQuery(new BasicDBObject());
        setWhereQuery(new BasicDBObject());
    }

    public void getElasticsearchConnection(){
        elasticClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost(properties.getProperty("elasticsearch.host"), Integer.parseInt(properties.getProperty("elasticsearch.port")), properties.getProperty("scheme"))));
        this.setElasticClient(elasticClient);
    }


    public void setProperties() {
        this.properties = new Properties();
        try (FileReader fin = new FileReader("src/main/resources/config.properties")) {
            properties.load(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void createIndex(){
        for (int id = 1; id <= Slot.noOfSlots; id++) {
            IndexRequest request = new IndexRequest("parking_system")
                    .id(String.valueOf(id))
                    .source("RegistrationNumber", "NULL",
                            "Colour", "NULL",
                            "Slot",id);
            try {
                AppConfig.getElasticClient().index(request, RequestOptions.DEFAULT);
            } catch(ElasticsearchException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static MongoCollection<Document> getCollection() {
        return collection;
    }

    public static BasicDBObject getUpdateFields() {
        return updateFields;
    }

    public static void setUpdateFields(BasicDBObject updateFields) {
        AppConfig.updateFields = updateFields;
    }

    public static BasicDBObject getSetQuery() {
        return setQuery;
    }

    public static void setSetQuery(BasicDBObject setQuery) {
        AppConfig.setQuery = setQuery;
    }

    public static BasicDBObject getWhereQuery() {
        return whereQuery;
    }

    public static void setWhereQuery(BasicDBObject whereQuery) {
        AppConfig.whereQuery = whereQuery;
    }

    public static RestHighLevelClient getElasticClient() {
        return elasticClient;
    }

    public static void setElasticClient(RestHighLevelClient elasticClient) {
        AppConfig.elasticClient = elasticClient;
    }

    public String getClient() {
        return client;
    }

    public void setClient() {
        this.client=properties.getProperty("client");
    }
    public static Jedis getJedis() {
        return jedis;
    }

    public static void setJedis() {
        AppConfig.jedis =new Jedis();
    }

}
