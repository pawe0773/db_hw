package com.example.db_hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDB {
    String username = "root";
    String password = DbHwApplication.password;
    String url = "jdbc:mysql://"+DbHwApplication.ip+":3306/";
//    String url = "jdbc:mysql://1.2.3.4:3306/?useSSL=false";
    String schemaName = "mydb";
    String tableName = "persons";
    List<String > persons = new ArrayList();

    public MyDB(){
        connectAndQuery();
    }

    private void connectAndQuery(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(url, username,password)){
            if(!conn.isClosed()){
                System.out.println("DB Conn ok ");
                initializeDatabase(conn);
//                // Get the rows:

                String sql = "SELECT * FROM " + tableName;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();
                persons.clear();
                while (resultSet.next()){
                    String firstName = resultSet.getString("name");
                    System.out.println("Name: " + firstName);
                    persons.add(firstName);
                }
            }
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }

    private void initializeDatabase(Connection conn) throws Exception{
        // 1. make sure there exists a schema, named mydb. If not, create one

        // 2. make sure there exists a table, named persons. If not, create one:
        //    Primary key: idpersons INT AUTO_INCREMENT
        //    Column: name VARCHAR(45)
        // 3. If there was no table named persons, then insert two rows into the new table: "Anna" and "Bent"



        String sql = "CREATE DATABASE IF NOT EXISTS " + schemaName+ ";";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();

        sql =   "USE " + schemaName + ";";
        ps = conn.prepareStatement(sql);
        ps.execute();

        sql =   "DROP TABLE IF EXISTS " + tableName + ";";
        ps = conn.prepareStatement(sql);
        ps.execute();

        sql = " CREATE TABLE IF NOT EXISTS "+  tableName +" (\n" +
                " idpersons INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                " name VARCHAR(45)\n" +
                ");";
        ps = conn.prepareStatement(sql);
        ps.execute();
        sql = "INSERT INTO " +tableName+ " (name) VALUES ('Anna'), ('Brnet');";
        ps = conn.prepareStatement(sql);
        ps.execute();

// today I learned how to execute sql statments inside of java programe.



    }

    public List<String> getPersons() {
        return persons;
    }

}
