/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import BddObject.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dina
 */
public class Benifice {
   double total;
   ArrayList<Enchere>pourcentages;
//   int 
    public static double getTotal() throws SQLException {
        double t=0;
        ArrayList<Enchere> pourcentages=getPourcentages();
        for (int i = 0; i < pourcentages.size(); i++) {
            Enchere get = pourcentages.get(i);
            t+=get.getPourcentageCommission();
        }
        return t;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public static ArrayList<Enchere> getPourcentages() throws SQLException {
        int valiny = 0;
        ArrayList<Enchere> en = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select prixmise-prixmise*taux as prixmise,taux,prixmise*taux as commission,"
                    + "enchereid,usersid from encheremove join commission com on encheremove.commissionid=com.id where state=1;";
            System.err.println(sql);
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Enchere ens = new Enchere();
//                System.err.println(resultSet.getDouble("commission"));
                ens.setId(resultSet.getInt("enchereId"));
               
                ens = ens.getEnchere();
                ens.setPrixMiseInitial(resultSet.getDouble("prixmise"));
                ens.setPourcentageCommission(resultSet.getDouble("commission"));

                en.add(ens);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();

        }
        return en;
    }

    public void setPourcentages(ArrayList<Enchere> pourcentages) {
        this.pourcentages = pourcentages;
    }
   
}
