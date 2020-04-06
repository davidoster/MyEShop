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
            
            // read customers
            ResultSet rs = db.getResults("SELECT * FROM Customers");
//            printCustomerResults(rs); //<--- returns void
            
            // read products
            rs = db.getResults("SELECT * FROM Sales");
//            printSalesResults(rs); //<--- returns void
           
            // read one product
            ResultSet rs2 = db.getOneResult("Customers", 2);
//            printCustomerResults(rs2); //<--- returns void
            
            // read one result with variable field
            rs2 = db.getOneResultByField("Sales", "products_id", "1");
//            printSalesResults(rs);
            
            // insert at customers
            // public static int insertRecordToCustomers(String first_name,String last_name, String email)
            // public static int insertRecordToCustomers(Customer customer)
            Customer customer = new Customer(0,"George", "Pasparakis", "paspa@hotmail.com");
            //System.out.println("Records inserted: " + insertRecordToCustomers(customer, db));
            rs = db.getResults("SELECT * FROM Customers");
//            printCustomerResults(rs);
            testdbMethods(db);

    }
    
    public static void testdbMethods(Database db) {
        try {
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Customers");
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static int insertRecordToCustomers(Customer customer, Database db) {
        int result = 0;
        String customer_data = "'"+ customer.getFirst_name() + "','" + customer.getLast_name() + "','" + customer.getEmail() + "'";
        String sql = "INSERT INTO `Customers` (`first_name`,`last_name`,`email`) VALUES ("+  customer_data + ");";
        db.setStatementNonStatic();
        Statement st = db.getStatementNonStatic();
        try {
            result = st.executeUpdate(sql);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        
    }
    
    public static int deleteRecordFromCustomersWithid(int id, Database db){
        int result = 0;
        String sql = String.format("DELETE FROM `customers` WHERE `id` = '%s'", id);
        db.setStatementNonStatic();
        Statement st = db.getStatementNonStatic();
        try {
            result = st.executeUpdate(sql);
            return result;
        }catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static int deleteRecordFromCustomersWithFirstName(Customer customer, Database db) {
        int result = 0;       
        String sql = "DELETE FROM `customers` WHERE `first_name` ="
                + "'" + customer.getFirst_name() + "';";
        db.setStatementNonStatic();
        Statement st = db.getStatementNonStatic();
        try {
            result = st.executeUpdate(sql);
            System.out.println(result  + " records deleted.");
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
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
//            rs.last();
//            System.out.println("Rows : " + rs.getRow());
//            rs.first();
            while(rs.next()) {
                System.out.println(
                    "Sales's Id: "                  + rs.getString(1) +
                    ", Customer's Id: "               + rs.getString(2) +
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




















