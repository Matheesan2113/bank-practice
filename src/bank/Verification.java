/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
//OVERVIEW: CHECKS WITH TEXT FILE IF INFORMATION USERNAME AND PASSWORD MATCH
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author matheesan
 */
public class Verification {
    // TODO code application logic here
//INITIALIZING VARIABLES
    String g = "admin";
    String morc = "";
    File file = new File("./logindata.txt");
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
    BufferedWriter bw;
    //Constructor

    public Verification() throws IOException {
        //CONSTRUCTOR
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());// COMMA TRUE TO APPEND MANAGER LOG IN DETAILS
            this.fw = fw;
            FileReader fr = new FileReader(file);
            this.fr = fr;
            BufferedWriter bw = new BufferedWriter(fw);
            this.bw = bw;
            BufferedReader br = new BufferedReader(new FileReader("./logindata.txt"));
            this.br = br;
            br.mark(0);
        } catch (IOException e) {
        }
    }
    public int login(String user, String pass) throws IOException {
        //EFFECTS: CHECKS IF LOG IN EXISTS AND IS CORRECT OR NOT
        //REQUIRES: FOR EXITING LOOP, REQUIRES CORRECT USER AND PASS, REQUIRES FILE STORING INFORMATION
        int r = 0;
        String hold;
        BufferedReader br = new BufferedReader(new FileReader("./logindata.txt"));
        br.readLine();
        while (!((hold=br.readLine()).isEmpty())/*br.ready() == true*/) {
            if (user.equals(hold)) {
                if (pass.equals(br.readLine())) {
                    System.out.println("YOU logged in : " + user);//+" Pass is: "+br.readLine()+" Role is: "+br.readLine());
                    String morc;
                    if ((morc=br.readLine()).equals("Manager")) {
                        r = 1;
                        System.out.println("Your role is: " + morc);
                        break;
                    } else {
                        r = 2;
                        System.out.println("Your role is: " + morc);
                        break;
                    }
                } else {
                    //System.out.println("pass, incorrect, try again\n" + br.readLine()+br.readLine());
                    br.readLine();
                    br.readLine();
                }
            } else {
                //System.out.println("user, incorrect, try again\n" + br.readLine() + br.readLine());
                br.readLine();
                    br.readLine();
            }
        }
        return r;
    }
}
