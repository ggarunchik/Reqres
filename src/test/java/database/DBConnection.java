package database;

import models.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void connectToDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/aqa4?" +
                            "user=root&password=tms123456&useSSL=true&serverTimezone=UTC");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public ArrayList<Student> getData(String tableName) {
        // Result set get the result of the SQL query
        ArrayList<Student> studentsArrayList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from " + tableName);
            while (resultSet.next()){
                Student student = new Student(
                        resultSet.getString("id"),
                        resultSet.getString("name"));
                studentsArrayList.add(student);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return studentsArrayList;
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            System.out.println("ID: " + id);
            System.out.println("NAME: " + name);
        }
    }

    public void createStudent(String name) {
        try {
            PreparedStatement preparedStatement =
                    connect.prepareStatement("INSERT INTO aqa4.students (name) VALUES(?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    // You need to close the resultSet
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception ignored) {
        }
    }
}
