/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
//OVERVIEW: USER INTERFACE IS MOSTLY HERE ALONG WITH SOME FILE COMMANDS
import java.io.*;
import java.util.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static javafx.application.Platform.exit;

/**
 *
 * @author m2manoka
 */
public class BANK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Initializing all used variables
        boolean managerlogin = false;
        Manager manager = new Manager();
        int login = 0;
        Verification q = new Verification();
        String  d = "", f = "", g, h = "";
        File file = new File("./logindata.txt");
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);
        FileWriter fw = new FileWriter(file.getAbsoluteFile());// COMMA TRUE TO APPEND MANAGER LOG IN DETAILS
        BufferedWriter bw = new BufferedWriter(fw);
        //INITIALIZE FILE BY HAVING ADMIN LOGIN INFO WRITTEN
        bw.newLine();
        bw.write("admin");
        bw.newLine();
        bw.write("admin");
        bw.newLine();
        bw.write("Manager");
        bw.flush();
        CAccount CAcc1 = new CAccount(2);
        Customer man = new Customer("admin", "admin", CAcc1);
        manager.clients.add(man);
//REAL MENU
        while (!d.matches("exit")) {//WHILE LOOP USED TO RUN PROGRAM UNTIL USER WISHES TO EXIT
            Scanner innew = new Scanner(System.in);
            while (login == 0) {//WHILE LOOP FOR LOGGING IN PURPOSES
                try {
                    System.out.println("Enter Username");
                    f = innew.nextLine();
                    System.out.println("Enter Password");
                    g = innew.nextLine();
                    login = q.login(f, g);//LOGIN IN METHOD HANDLED BY VERIFICATION CLASS
                } catch (NullPointerException ex) {
                    System.out.println("User or password incorrect, try again.");
                }
            }
            switch (login) {//RETURNED VARIABLE DECIDES WHETHER MANAGER OR CUSTOMER MENU IS DISPLAYED
                case 1:
                    System.out.println("Welcome Manager");//MANAGER MENU
                    while (!h.matches("exit")) {
                        System.out.println("Please enter 1-3\n1.Create Account\n2.Delete Account\n3.LogOut\n");//MENU OPTIONS
                        int input1 = 0;
                        try {
                            input1 = in2.nextInt();
                        } catch (InputMismatchException iomismatch) {
                            System.out.println("Try again, enter number 1-3");
                        }
                        switch (input1) {
                            case 1://CREATE ACCOUNT
                                Scanner in = new Scanner(System.in);
                                String custname = "";
                                boolean fooh=false;
                                //Create Account method
                                while (fooh==false) {//METHOD TO CHECK FOR USERNAME DUPLICACY HANDLED BY MANAGER CLASS
                                    System.out.println("Enter  new user username");
                                    custname = in.nextLine();
                                    fooh=manager.usernameexists(custname);
                                }
                                System.out.println("Enter new user password");
                                String custpass = in.nextLine();
                                System.out.println("Enter chequing account $value");
                                int CAValue = in.nextInt();
                                System.out.println("Enter Saving account $value (IF THERE IS ONE, ELSE enter -1");
                                int SAValue = in.nextInt();
                                CAccount CAcc = new CAccount(CAValue);
                                SAccount SAcc = new SAccount(SAValue);
                                if (SAValue > 0) {//METHODS TO ADD CUSTOMER OBJECTS, WHICH WILL THEN BE RETURNED AND ADDED TO ARRAYLISTS ON MANAGER CLASS
                                    manager.clients.add(manager.CreateAccount(custname, custpass, CAcc, SAcc));
                                } else {
                                    Customer cust1 = new Customer(custname, custpass, CAcc);
                                    manager.clients.add(cust1);
                                }
                                bw.newLine();//WRITING TO TEXTFILE NEW LOGIN INFO
                                bw.write(custname);
                                bw.newLine();
                                bw.write(custpass);
                                bw.newLine();
                                bw.write("Customer");
                                bw.flush();
                                break;
                            case 2://DELETING ACCOUNT METHOD
                                break;
                            default://OPTION TO LOG OUT OF MANAGER MENU, RESETS TO ASK WHETHER OR NOT TO CONTINUE PROGRAM
                                System.out.println("You logged out Manager");
                                h = "exit";//Exit into Real forever menu
                                break;
                        }
                    }
                    break;
                case 2://Customer Menu              
                    System.out.println(
                            "Welcome Customer");
                    while (!h.matches("exit")) {
                        System.out.println("Please enter 1-5\n1.View chequing\n2.View savings\n3.transfer cheq to savings\n4.savings to chequing\n5.logout\n");
                        int input2 = in2.nextInt();//CHOOSING CUSTOMER MENU OPTOINS
                        int test1;
                        switch (input2) {
                            case 1:
                                //View chequing
                                for (Customer x : manager.getClients()) {
                                    if (f.equals(x.getName())) {
                                        System.out.println("Chequing account has $" + x.getA().toString());//RETRIEVES BALANCE FROM CUSTOMER ACCOUNT
                                    }
                                }
                                break;
                            case 2:
                                //view savings
                                for (Customer x : manager.getClients()) {
                                    if (f.equals(x.getName())) {
                                        if (x.getB() != null) {
                                            System.out.println("Savings account has $" + x.getB().toString());//RETRIEVES BALANCE FROM CUSTOMER ACCOUNT
                                        } else {
                                            System.out.println("Client doesn't have savings account");
                                        }
                                    }
                                }
                                break;
                            case 3:
                                //exchange cheq->sav
                                for (Customer x : manager.getClients()) {
                                    if (f.equals(x.getName())) {//FINDS CUSTOMER IN ARRAYLIST
                                        if (x.hasSavings() == true) {//CHECKS IF CUSTOMER HAS SAVINGS
                                            System.out.println("enter how much you want to subtract from chequing and into saving");
                                            test1 = innew.nextInt();
                                            if (test1 <= x.getA().getValue() && test1 > 0) {//PERFORMS TRANSACTION ON CUSTOMER ACCOUNT
                                                x.getA().sub(test1);
                                                x.getB().add(test1);
                                            } else {
                                                System.out.println("Your value exceeds your money in chequings account");
                                            }
                                        } else {
                                            System.out.println("Customer does not have savings account, so no transaction");
                                        }
                                    }
                                }
                                break;
                            case 4:
                                //exchange sav->cheq
                                for (Customer x : manager.getClients()) {
                                    if (f.equals(x.getName())) {//FINDS CUSTOMER IN ARRAYLIST
                                        if (x.hasSavings() == true) {//CHECKS IF CUSTOMER HAS SAVINGS
                                            System.out.println("enter how much you want to subtract from savings and into chequing");
                                            test1 = innew.nextInt();
                                            if (test1 <= x.getB().getValue() && test1 > 0) {//PERFORMS TRANSACTION ON CUSTOMER ACCOUNT
                                                x.getB().sub(test1);
                                                x.getA().add(test1);
                                            } else {
                                                System.out.println("Your value exceeds your money in savings account");
                                            }
                                        } else {
                                            System.out.println("Customer does not have savings account, so no transaction");
                                        }
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println(
                            "You entered random choice, you will now exit program.");
                    d = "exit";//Exit into program

                    break;
            }
        }

    }

}
