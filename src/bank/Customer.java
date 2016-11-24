/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
//oVERVIEW: CREATES DIFFERENT TYPES OF CUSTOMERS, AND RETURNS VARIABLES, AND CHECKS TO SEE IF THEY'RE DIFFERENT
/**
 *
 * @author m2manoka
 */
public class Customer {

    private String name;
    private String password;
    protected CAccount a;
    protected SAccount b;
//CONSTRUCTOR IF SAVINGS EXISTS
    public Customer(String name, String password, CAccount a, SAccount b) {
        this.name = name;
        this.password = password;
        this.a = a;
        this.b = b;
    }
//cONSTRUCTOR IF SAVINGS DOESN'T EXIST
    public Customer(String name, String password, CAccount a) {
        this.name = name;
        this.password = password;
        this.a = a;
    }

    /**
     * @return the name
     */
    public String getName() {
        //EFFECTS: RETURNS NAME
        return name;
    }
    
public boolean hasSavings(){
    //EFFECTS: RETURNS TRUE IF SAVINGS ACCOUNT EXISTS FOR SPECIFIC CUSTOMER
    if (Customer.this.getB()==null)
        return false;
    else
        return true;
}

    protected CAccount getA() {
        //EFFECTS: RETURNS CHEQUING ACCOUNT
        return a;
    }

    protected SAccount getB() {
        //EFFECTS: RETURNS SAVINGS ACCOUNT
        return b;
    }
}
