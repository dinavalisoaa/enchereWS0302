/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import BddObject.Connexion;
import BddObject.Ignore;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dina
 */
@InfoDAO(table = "Commission")
public class Commission extends ObjectBDD {   
    int id=-1;
    double taux=-1;
    String daty;
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTaux() {
        return taux;
    }
    
     public Commission getCommission() throws Exception {
//    ArrayList<Users>vao=()
Commission com=new Commission();
com.setId(this.id);
        return ((Commission) com.select(null).get(0));
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getDaty() {
        return daty;
    }
public Commission getCurrentId() throws Exception{
ArrayList<Commission>com=new Commission().selectBySQL("select *From commission order by daty desc",null);
return ((Commission)com.get(0));
}

    public void setDaty(String daty) {
        this.daty = daty;
    }
    
}
