package Repository;

import Entity.User;

import java.sql.*;
import java.util.*;

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
    public List<User> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, \"name\", avatar FROM \"user\" "
            );
            ResultSet set = statement.executeQuery();
            List<User> a = new ArrayList<>();
            User tmp;
            while (set.next()) {
                tmp = new User(set.getInt("id"), set.getString("name"));
                tmp.setAvatar(set.getString("surname"));
                a.add(tmp);
            }
            statement.close();
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserInfoById(int id) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT id, \"name\", avatar FROM \"user\" WHERE id = ?"
            );
            System.out.println(id);
            st.setInt(1, id);
            ResultSet set = st.executeQuery();
            set.next();
            User user = new User(set.getInt("id"), set.getString("name"));
            user.setAvatar(set.getString("avatar"));
            st.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteStudentById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM \"user\" WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudentById(int id, Map<String, Object> context) {
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    "UPDATE \"user\" " +
                    "SET name = ?, avatar = ?" +
                    "WHERE id = ?"
            );
            st.setString(1, (String) context.get("name"));
            st.setString(2, (String) context.get("avatar"));
            st.setInt(3, id);
            st.executeUpdate();
            return true;
        } catch (SQLException | IllegalFormatException e) {
            e.printStackTrace();
            return false;
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
