package tests;

import database.DBConnection;
import models.Student;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DBTest {

    @Test
    public void getBD() throws Exception {
        DBConnection dbConnection = new DBConnection();
        dbConnection.connectToDB();
        dbConnection.createStudent("Helllllllooooooo");
        ArrayList<Student> students = dbConnection.getData("students");
        assertEquals(students.get(0).getName(), "Dmitriy");
        dbConnection.close();
    }
}
