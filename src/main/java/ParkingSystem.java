import dao.*;
import redis.clients.jedis.Jedis;
import util.AppConfig;
import util.Menu;

import java.util.Scanner;

class ParkingSystem {

    public static void run(){


        Scanner sc = new Scanner(System.in);
        AppConfig appConfig= new AppConfig();
        boolean exit = false;


        if(appConfig.getClient().equals("elasticsearch")){
            BaseClient dao=new ElasticSearchBaseClient();
            System.out.println("in elasticsearch");
            try  {
                // dao inst
                while (!exit) {
                    try {
                        switch (Menu.getChoice()) {
                            case 1: // fetch details db
                                dao.carList();
                                break;

                            case 2: // insert
                                dao.carEntry();
                                break;
                            case 3:
                                dao.carExit();
                                break;

                            case 4:
                                dao.getRegistrationNumberByColour();
                                break;

                            case 5:
                                dao.getSlotNumberByRegistrationNumber();
                                break;

                            case 10:
                                exit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error " + e);
                        e.printStackTrace();
                        System.out.println("Pls retry....");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(appConfig.getClient().equals("redis")){
            RedisBaseClient dao=new RedisBaseClient();
            try  {
                // dao inst
                while (!exit) {
                    try {
                        switch (Menu.getChoice()) {
                            case 1: // fetch details db
                                dao.carList();
                                break;

                            case 2: // insert
                                dao.carEntry();
                                break;
                            case 3:
                                dao.carExit();
                                break;

                            case 4:
                                dao.getRegistrationNumberByColour();
                                break;

                            case 5:
                                dao.getSlotNumberByRegistrationNumber();
                                break;

                            case 10:
                                exit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error " + e);
                        e.printStackTrace();
                        System.out.println("Pls retry....");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(appConfig.getClient().equals("mongodb")){

            try  {
                // dao inst
                BaseClient dao = new MongoDbBaseClient();
                while (!exit) {
                    try {
                        switch (Menu.getChoice()) {
                            case 1: // fetch details db
                                dao.carList();
                                break;

                            case 2: // insert
                                dao.carEntry();
                                break;

                            case 3:
                                dao.carExit();
                                break;

                            case 4:
                                dao.getRegistrationNumberByColour();
                                break;

                            case 5:
                                dao.getSlotNumberByRegistrationNumber();
                                break;

                            case 10:
                                exit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error " + e);
                        e.printStackTrace();
                        System.out.println("Pls retry....");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(appConfig.getClient().equals("mysql")) {
            try  {

                // dao inst
                BaseClient dao = new MySqlBaseClient();
                while (!exit) {
                    try {
                        switch (Menu.getChoice()) {
                            case 1: // fetch details db
                                dao.carList();

                                break;

                            case 2: // insert
                                dao.carEntry();
                                break;

                            case 3:
                                dao.carExit();
                                break;

                            case 4:
                                dao.getRegistrationNumberByColour();
                                break;

                            case 5:
                                dao.getSlotNumberByRegistrationNumber();
                                break;

                            case 10:
                                exit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error " + e);
                        e.printStackTrace();
                        System.out.println("Pls retry....");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    }




