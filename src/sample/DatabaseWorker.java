package sample;

import java.sql.*;

public class DatabaseWorker extends DatabaseConfig {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // http://www.cyberforum.ru/java-database/thread1967034.html - serverTimezone=UTC
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name +
                "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";

        // новый драйвер взамен устаревшего
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public void createUser(User user) {
        String query = "INSERT INTO " + DatabaseConstant.USER_TABLE + " (" +
                DatabaseConstant.USER_LOGIN + "," +
                DatabaseConstant.USER_PASS + "," +
                DatabaseConstant.USER_FIRST_NAME + "," +
                DatabaseConstant.USER_LAST_NAME + "," +
                DatabaseConstant.USER_LOCATION + "," +
                DatabaseConstant.USER_GENDER + ") " + "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = getConnection().prepareStatement(query);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getLocation());
            ps.setString(6, user.getGender());

            ps.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (ClassNotFoundException error) {
            error.printStackTrace();
        }
    }

    public ResultSet findUser(User user) {
        ResultSet data = null;

        String query = "SELECT * FROM " + DatabaseConstant.USER_TABLE + " WHERE " +
                DatabaseConstant.USER_LOGIN + "=? AND " +
                DatabaseConstant.USER_PASS + "=?";

        try {
            PreparedStatement ps = getConnection().prepareStatement(query);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            data = ps.executeQuery();
        } catch (SQLException error) {
            error.printStackTrace();
        } catch (ClassNotFoundException error) {
            error.printStackTrace();
        }

        return data;
    }
}
