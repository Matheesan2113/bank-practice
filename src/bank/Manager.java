/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
//oVERVIEW: CHECKS TO SEE IF ARRAY OF USERS CAN BE KEPT APPROPRIATE AND HELPS CREATE USERS
import java.util.*;
import java.io.*;

/**
 *
 * @author mathe
 */
public class Manager {

    protected ArrayList<Customer> clients = new ArrayList<Customer>();

    public Manager() {

    }

    public boolean usernameexists(String custname) {
        //REQUIRES: CUSTOMER'S NAME TO CHECK WITH CREATING A NEW USERNAME FOR NEW ACCOUNT
        //EFFECTS: RETURNS TRUE IF USERNAME DOESN'T EXIST
        boolean a = true;
        for (Customer profile : getClients()) {
            if (profile.getName().equals(custname)) {
                System.out.println("USERNAME ALREADY EXISTS!");
            }
        }
        return a;
    }
public Customer CreateAccount(String u, String p,CAccount ca,SAccount sa){
    //REQUIRES: USERNAME, PASSWORD, AND TWO ACCOUNTS, IF THEY BOTH EXIST
    //EFFECTS: CUSTOMER CAN BE CREATED HERE. 
    Customer cust1 = new Customer(u, p, ca, sa);
    return cust1;
}
    protected ArrayList<Customer> getClients() {
        //EFFECTS: RETURNS ARRAYLIST OF CUSTOMERS
        return clients;
    }
}
