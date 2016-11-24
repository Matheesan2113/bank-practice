/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
//OVERVIEW: CREATES, AND MODIFIES CHEQUING ACCOUNT
/**
 *
 * @author m2manoka
 */
public class CAccount implements Account {

    protected int value;

    public CAccount(int value) {
        if (value > 20) {
            this.value = value;
        } else {
            this.value = 20;
        }
    }

    @Override
    public void add(int x) {
        //REQUIRES: X BE POSITIVE INTEGER
        //EFFECTS: ADDS BALANCE BY X VALUE FROM OTHER ACCOUNT
        //MODIFIES BALANCE ON ACCOUNT
        this.value = this.getValue() + x;
    }

    @Override
    public void sub(int x) {
        //REQUIRES: X BE LESS THAN BALANCE IN RESPECTIVE ACCOUNT
        //EFFECTS: REDUCES BALANCE BY X VALUE FROM OTHER ACCOUNT
        //MODIFIES: BALANCE ON ACCOUNT
        this.value = this.getValue() - x;
    }

    @Override
    public String toString() {
        return getValue() + "";
    }

    @Override
    public int getValue() {
        //EFFECTS: RETURNS VALUE
        return value;
    }

}
