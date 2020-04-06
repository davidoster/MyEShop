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
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            Database db = new Database();
            
            // customers
            ResultSet rs = db.getResults("SELECT * FROM Customers");
            printCustomerResults(rs); //<--- returns void
            
            // products
            rs = db.getResults("SELECT * FROM Sales");
            printSalesResults(rs); //<--- returns void
           
            

//               Statement statement = null;
//            ResultSet resultSet = null;
//            
//            Customer customer = new Customer();
//            
            
//            System.out.println("Is connection valid? " + connection.isValid(5));
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM `customers`;");
    }
    
    
    public static void printCustomerResults(ResultSet rs) {
        try {
//            rs.last();
//            System.out.println("Rows : " + rs.getRow());
//            rs.first();
            while(rs.next()) {
                System.out.println(
                    "Customer's Id: " + rs.getString(1) +
                    ", First Name: "  + rs.getString(2) +
                    ", Last Name: "   + rs.getString(3) +
                    ", Email: "       + rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void printSalesResults(ResultSet rs) {
        try {
            rs.last();
            System.out.println("Rows : " + rs.getRow());
//            rs.first();
            while(rs.next()) {
                System.out.println(
                    "Sales's Id: "                  + rs.getString(1) +
                    "Customer's Id: "               + rs.getString(2) +
                    ", Product's Id: "              + rs.getString(3) +
                    ", Quantity: "                  + rs.getString(4) +
                    ", Unit Price: "                + rs.getString(5) +
                    ", Purchase Date & Time: "      + rs.getString(6));
            }
        } catch (SQLException ex) {
           Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
}




















