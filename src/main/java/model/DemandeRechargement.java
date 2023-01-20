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

/**
 *
 * @author dina
 */
@InfoDAO(table = "DemandeRechargement")
public class DemandeRechargement extends ObjectBDD {

    int id = -1;//             SERIAL NOT NULL, 
    String dateDemande;//    date DEFAULT current_date, 
    int state = -1;//          int4, 
    int usersId = -1;//        int4 NOT NULL, 
    double montant = -1;//        float8, 
    String dateValidation;
      @Ignore
    Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
   public Users getUsers() throws Exception{
   Users i=new Users();
   i.setId(this.usersId);
   return i.getUsers();
   }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
    public String getValidation(){
    if(state==1)return "Valider";
     if(state==-2)return "Refuser";
    return "Attente";
    }
      public  DemandeRechargement[] getDemandes() throws Exception {
        ArrayList lis =this.select(null);
        DemandeRechargement[] oo = new DemandeRechargement[lis.size()];
        for (int i = 0; i < oo.length; i++) {
            oo[i] = (DemandeRechargement) lis.get(i);
        }
        return oo;
    }

   
    public DemandeRechargement getDemandeRechargement() throws Exception {
        DemandeRechargement us = new DemandeRechargement();
        us.setId(this.id);
        return ((DemandeRechargement) us.select(null).get(0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDemandeRechargementId() {
        return usersId;
    }

    public void setDemandeRechargementId(int usersId) {
        this.usersId = usersId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) throws Exception {
//        if (montant < this.getDemandeRechargement().getCurrentMoney()) {
//            throw new Exception("Solde inferieur");
//        } else {
            this.montant = montant;
//        }
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }

}
