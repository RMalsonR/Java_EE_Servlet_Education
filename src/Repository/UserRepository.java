package Repository;

import java.sql.*;
import java.util.HashMap;

public class UserRepository {
    private Connection connection;
    private static UserRepository repository;

    public static UserRepository getRepository(){
        if (repository == null){
           repository = new UserRepository();
           try {
               repository.createConnection();
           } catch (SQLException e){
               e.printStackTrace();
           }
        }
        return repository;
    }

    private void createConnection() throws SQLException{
        if (connection == null){
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/servlets",
                    "postgres",
                    "wasd"
            );
        }
    }

    public HashMap<String, String> getUserIdNameByLogin(String login, String password){
        if (login == null || password == null)
            return null;
        try {
            // Language = SQL
            PreparedStatement statement = connection.prepareStatement(
                    "select id, name from \"user\" where login = ? and password = ?"
            );
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                HashMap<String, String> result = new HashMap<String, String>();
                result.put("id", Integer.toString(resultSet.getInt("id")));
                result.put("name", (resultSet.getString("name")));
                return result;
            } else return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
