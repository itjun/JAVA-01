package pers.peixinyi.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Student {

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void getStudent(ResultSet resultSet) throws SQLException {
        System.out.println("--------------------------------------------------------------------------------------------");
        while (resultSet.next()) {
            System.out.println("Student{" +
                    "id=" + resultSet.getString(1) +
                    ", name='" + resultSet.getString(2) + '\'' +
                    ", age=" + resultSet.getString(3) +
                    '}');
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
