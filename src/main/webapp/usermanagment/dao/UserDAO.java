package src.main.webapp.usermanagment.dao;

import src.main.webapp.usermanagment.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    // This DAO (Data- Accsess - Object) class provides CRUD (Creat-Read-Update-Delete) DB operations for the table in the DB

    private String jdbcURL = "jdbc:mysql://127.0.0.1:1208/demo";
    private String jdbcusername = "root";
    private String jdbcpassword = "qwerty123";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";


    private static final String SELECT_USER_BY_ID = "select id , name , email, country from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users ";
    private static final String DELETE_USERS_SQL= "update users set Deleted = 1 where id=?";
    private static final String UPDATE_USERS_SQL = "update users set name = ? , email = ?, country = ? where id = ? ";



    protected Connection getConnection(){
        Connection connection = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection  = DriverManager.getConnection(jdbcURL,jdbcusername,jdbcpassword);

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;


    }

    //Create or insert user

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //Update user
    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection();
                   PreparedStatement statment = connection.prepareStatement(UPDATE_USERS_SQL)){
                statment.setString(1,user.getName());
                statment.setString(2,user.getEmail());
                statment.setString(3,user.getCountry());
                statment.setInt(4,user.getId());

                rowUpdated = statment.executeUpdate()  > 0;






        }
        return rowUpdated;
    }


    //select users by id
    public User selectUserById(int id) {
        User user = null;
        // Step 1: Establishing  a Connection
        try(Connection connection = getConnection();
            // Step 2 : Create a statement using connection object
              PreparedStatement preparedStatment = connection.prepareStatement(SELECT_USER_BY_ID);){

            preparedStatment.setInt(1,id);
            System.out.println(preparedStatment);

            //Step 3: Execute the query or update query
            ResultSet rs = preparedStatment.executeQuery();

            //Step 4: Process the ResultSet object

            while(rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                  user = new User(id,name,email,country);
            }



        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;

    }



    //select users

    public List<User> selectAllUser(){
        List<User> users = new ArrayList<>();
        //Step 1: Establishing a Connection
        try(Connection connection = getConnection();
            //Step 2: Create a Statement using connection object
                PreparedStatement  preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){

            System.out.println(preparedStatement);
            //Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            //Step 4: Process the ResultSet object.
            while (rs.next()) {
                if(rs.getInt("Deleted")!=1){
                    int id = rs.getInt("id");
                    String  name = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    users.add(new User(id , name , email, country));
                }

            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    //delete user

public boolean deleteUser(int id) throws  SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);){
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() >0;

        }

return rowDeleted;


}

}
