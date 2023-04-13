package bank.management.system;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;

import java.sql.*;

public class Conne {

    Connection connection;
    Statement statement;

    public Conne(){

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem1","root","#@incorrect");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
