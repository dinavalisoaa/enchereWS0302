/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import BddObject.Connexion;
import BddObject.Ignore;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author dina
 */
@InfoDAO(table = "Users")
public class Users extends ObjectBDD {

    int id = -1;
    String nom;
    String login;
    String mdp;
    String prenom;
    String dtn;
    @Ignore
    private int nbEnchereFait;
    @Ignore
    private int enchereGagner;
    @Ignore
    private int nbrechargeCompte;
    @Ignore
    double miseGagnant;
    @Ignore
    double rentabilite;

    public double getRentabilite() {
        return rentabilite;
    }

    public String getDtn() {
        return dtn;
    }

    public void setDtn(String dtn) throws Exception {
        Date vao = Date.valueOf(dtn);//.getYear();
        Calendar cal = Calendar.getInstance();
        Date now = Date.valueOf(LocalDate.now());
        int ch = now.getYear() - vao.getYear();
//        Parametrage test = new Parametrage();
//        test.setId(3);
//        Parametrage trage = (Parametrage) test.select(null).get(0);
//        if(ch<Integer.parseInt(trage.getValue())){
//        throw new Exception("Age minimum non respecter");
//        }
        this.dtn = dtn;
    }

    public void setRentabilite(double rentabilite) {
        this.rentabilite = rentabilite;
    }

    public double getMiseGagnant() {
        return miseGagnant;
    }

    public void setMiseGagnant(double miseGagnant) {
        this.miseGagnant = miseGagnant;
    }

    public int getId() {
        return id;
    }

    public Users getUsers() throws Exception {
//    ArrayList<Users>vao=()
        Users tt = new Users();
        tt.setId(this.id);
        return ((Users) tt.select(null).get(0));
    }

    public Users getUsers(Connection con) throws Exception {
//    ArrayList<Users>vao=()
        Users tt = new Users();
        tt.setId(this.id);
        return ((Users) tt.select(con).get(0));
    }

    public double getCurrentMoney() throws Exception {
        Compte cpt = new Compte();
        cpt.setUsersId(id);
        double montant = 0;
        try {
            montant = ((Compte) cpt.getLast(null)).getMontant();
        } catch (Exception e) {
        }
        return montant;
    }

    public String getLogin() {
        return login;
    }

    public int getLoginId(Connection con) throws Exception {
        this.setNom(nom);
        this.setLogin(login);
        if (this.select(con).size() > 0) {
            return ((Users) this.select(con).get(0)).getId();
        }
        return -1;
    }

    public int getLoginId() throws Exception {
        this.setNom(nom);
        this.setLogin(login);
        if (this.select(null).size() > 0) {
            return ((Users) this.select(null).get(0)).getId();
        }
        return -1;
    }

    public Compte getCompte() throws Exception {
        Compte vaovao = new Compte();
        vaovao.setUsersId(this.id);
        ArrayList<Compte> cpt = vaovao.select(null);
        return cpt.get(0);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static Users[] minieres() throws Exception {
        ArrayList lis = new Users().select(null);
        Users[] oo = new Users[lis.size()];
        for (int i = 0; i < oo.length; i++) {
            oo[i] = (Users) lis.get(i);
        }
        return oo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbEnchereFait() {
        return nbEnchereFait;
    }

    public void setNbEnchereFait(int nbEnchereFait) {
        this.nbEnchereFait = nbEnchereFait;
    }

    public ArrayList<Users> listeEnchereGagner() throws SQLException {
        ArrayList<Users> valiny = new ArrayList();
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select count(*) as sum,usersid from encheremove where state=1\n"
                    + "group by usersid";
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("usersid"));
                //System.out.println(resultSet.getInt("usersid"));
                users.setEnchereGagner(resultSet.getInt("som"));
                //System.out.println(resultSet.getInt("som"));
                valiny.add(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return valiny;
        }
    }

    public double getNbEnchereGagner(Connection con) throws Exception {
        ArrayList<Users> valiny = new ArrayList();
        Connection connection = null;
        Users users = new Users();
        users.setId(this.id);
//                        users=users.getUsers();

        try {

            String sql = "select count(*) as sum from encheremove where state=1 and usersId=" + this.id + "\n";
//                    + "group by usersid";
            System.err.println("[[[" + sql);

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                users.setId(resultSet.getInt("usersid"));
                //System.out.println(resultSet.getInt("usersid"));
                users.setEnchereGagner(resultSet.getInt("sum"));
                //System.out.println(resultSet.getInt("som"));
//                valiny.add(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

            return users.getEnchereGagner();
        }
    }

    public double getEnchereEffectuer(Connection con) throws SQLException {
        ArrayList<Users> valiny = new ArrayList();
        Users users = new Users();
        users.setId(this.id);

        try {
            String sql = "select count(*) as sum from encheremove where  usersId=" + this.id + "\n";
//                    + "group by usersid";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                users.setId(resultSet.getInt("usersid"));
                //System.out.println(resultSet.getInt("usersid"));
                users.setNbEnchereFait(resultSet.getInt("sum"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            return users.getNbEnchereFait();
        }
    }

    public double getEfficacite(Connection con) throws Exception {
        double win = getNbEnchereGagner(con);
        double fait = getEnchereEffectuer(con);
////        if()
//        fait-->100
//         win   ?
        if (fait != 0) {
            return 100 * win / fait;
        }
        return 0;
    }

    public Users getUserCompteLePlusRecharg() throws SQLException {
        Users valiny = null;
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select count(*) as isa,usersid from compte\n"
                    + "group by usersid \n"
                    + "order by isa desc\n"
                    + "limit 1";
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("usersid"));
                //System.out.println(resultSet.getInt("usersid"));
                users.setNbrechargeCompte(resultSet.getInt("som"));
                //System.out.println(resultSet.getInt("som"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return valiny;
        }
    }

    public int getEnchereGagner() {
        return enchereGagner;
    }

    public void setEnchereGagner(int enchereGagner) {
        this.enchereGagner = enchereGagner;
    }

    public int getNbrechargeCompte() {
        return nbrechargeCompte;
    }

    public void setNbrechargeCompte(int nbrechargeCompte) {
        this.nbrechargeCompte = nbrechargeCompte;
    }

}
