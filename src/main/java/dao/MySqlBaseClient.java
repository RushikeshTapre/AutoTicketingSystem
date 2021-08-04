package dao;
import core.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static util.AppConfig.fetchMySqlDBConnection;
public class MySqlBaseClient extends BaseClient {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MySqlBaseClient() throws Exception{
        int noOfSlots=10;
        prepareDatabase(noOfSlots);
    }

    @Override
    public void carList(){
            try{

                List<Car> listOfCars=new ArrayList<>();
                preparedStatement = connection.prepareStatement("select * from parking_system");
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {

                    if (resultSet.getInt(2) != 0 && resultSet.getString(3) != null) {
                        listOfCars.add(new Car(resultSet.getInt(2), resultSet.getString(3)));
                    }
                }
                if(listOfCars==null){
                    System.out.println("Car list empty");
                }else
                    for(Car c : listOfCars)
                        System.out.println(c);
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    @Override
    public void carEntry() {
            try{
                preparedStatement = connection.prepareStatement("select * from parking_system");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getInt(2) == 0 && resultSet.getString(3) == null) {
                            PreparedStatement pst1 = connection.prepareStatement("UPDATE parking_system " + " SET plate_number= ?,Color=? WHERE slot_number = ?");
                            pst1.setString(2, getColor());
                            pst1.setInt(1, getRegistrationNumber());
                            pst1.setInt(3, resultSet.getInt(1));
                            pst1.executeUpdate();
                            break;
                        }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
    }


    @Override
    public void carExit(){
            try{
                preparedStatement = connection.prepareStatement("UPDATE parking_system SET  plate_number=? ,color=? where plate_number=?");
                preparedStatement.setInt(1,0);
                preparedStatement.setString(2,null);
                preparedStatement.setInt(3,getRegistrationNumber());
                int i= preparedStatement.executeUpdate();
                if(i!=0){
                    System.out.println("updated...");
                }else
                    System.out.println("not updated...");
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
    @Override
    public void getRegistrationNumberByColour(){
        try{
            List<Car> list=new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from parking_system where color=?");
            preparedStatement.setString(1,getColor());
            resultSet = preparedStatement.executeQuery();
            System.out.println("Car List:");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void getSlotNumberByRegistrationNumber(){
        try{
            preparedStatement = connection.prepareStatement("select * from parking_System where plate_number=?");
            preparedStatement.setInt(1,getRegistrationNumber());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void prepareDatabase(int noOfSlots) {
        try{
            PreparedStatement preparedStatement2;
            connection = fetchMySqlDBConnection();
            preparedStatement = connection.prepareStatement("drop table if exists parking_system;");
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS parking_system" +
                            "(slot_number int primary key ,plate_number int ,color varchar(10));");
            preparedStatement.execute();
            for(int  i=1;i<=noOfSlots;i++){
                preparedStatement2 = connection.prepareStatement("insert into parking_system values(?,?,?);");
                preparedStatement2.setInt(1,i);
                preparedStatement2.setInt(2,0);
                preparedStatement2.setString(3,null);
                preparedStatement2.execute();
            }
            System.out.println("database created");
            System.out.println("dao created....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
