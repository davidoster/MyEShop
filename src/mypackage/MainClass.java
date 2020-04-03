/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import models.Customer;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author George.Pasparakis
 */
public class MainClass {
    private static final String DB_URL = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + "/eshop?zeroDateTimeBehavior=CONVERT_TO_NULL&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "Root1234!";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            
            Customer customer = new Customer();
            
            connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
            System.out.println("Is connection valid? " + connection.isValid(5));
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customers;");
            resultSet.last();
            System.out.println("Rows : " + resultSet.getRow());
            resultSet.first();
            System.out.println("Customer's Id: " + resultSet.getString(1) + 
                               ", First Name: "  + resultSet.getString(2) + 
                               ", Last Name: "   + resultSet.getString(3) + 
                               ", Email: "       + resultSet.getString(4));
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No luck!");
        }
    
    
    }
    
    
    
    
}
