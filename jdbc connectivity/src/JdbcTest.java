import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


public class JdbcTest {
    private final static String URL= "jdbc:mysql://localhost:3306/jdbcjava";
    private final static String USERNAME= "root";
    private final static String PASSWORD= "Sri@7822";


    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("class loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
        }


        try{
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("connection successfully");


          // insertQuery(connection);
            updateQuery(connection);
           //deleteQuery(connection);
            //selectQuery(connection);
        }catch(SQLException e){
            System.out.println("connection not happend");
            throw new RuntimeException(e);
        }


    }
    public static void insertQuery(Connection connection){
        try{
            String query = "INSERT INTO person VALUE(?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,4);
            ps.setString(2,"ravi");
            ps.execute();
            System.out.println("record inserted successfully");
        } catch (SQLException e) {
            System.out.println("record not inserted ");
        }
    }
    public static void selectQuery(Connection connection){
        try{
            String query = "SELECT * FROM person";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println("id = "+ id+"name ="+name);

            }
            System.out.println("recort printed successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void updateQuery(Connection connection){
        try{
            String query ="update person set name=? where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,"raja");
            ps.setInt(2,3);
            ps.executeUpdate();
            System.out.println("record update successfully");

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }
    public static void deleteQuery(Connection connection){
        try{
            String query="delete from person where id=?";
            PreparedStatement ps= connection.prepareStatement(query);
            ps.setInt(1,2);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("delet");
        }
    }
}