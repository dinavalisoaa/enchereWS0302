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
public class Stat {

    public String htmlStatUser() throws Exception {
        Connection con = Connexion.getConn();
        ArrayList<Users> ccat = Stat.getParticipantLePlusRentable(con);
        String str = "<table style=\"width:80%\" border=\"1\" class=\"table table-striped table-responsive-md\">\n"
                + "                                            <thead>\n"
                + "                                                <tr>\n"
                + "                                                    <th>Categorie</th>          \n"
                + "                                                    <th>Chiffre</th>\n"
                + "                                                    <th>Enchere Effectuer</th>\n"
                + "                                                    <th>Enchere Gagner</th>\n"
                + "                                                    <th>Efficacite</th>\n"
                + "                                                </tr>\n"
                + "                                            </thead>\n"
                + "                                            <tbody>\n" + "";
        for (int i = 0; i < ccat.size(); i++) {
            Users d = (Users) ccat.get(i);
            str += "<td>" + ccat.get(i).getNom() + "</td>";
            str += "<td>" + ccat.get(i).getRentabilite() + "Ar</td>";
            str += "<td>" + ccat.get(i).getEnchereEffectuer(con) + "</td>";
            str += "<td>" + ccat.get(i).getNbEnchereGagner(con) + "</td>";
            str += "<td>" + ccat.get(i).getEfficacite(con) + "5</td>";
            str += "</tr>";

        }
        str
                += "       </tbody>\n"
                + "   </table>\n"
                + "";
        con.close();;
        return str;

    }
//
//    public String htmlStatUser() throws Exception {
//        
//        Connection con = Connexion.getConn();
//        ArrayList<Users> ccat = Stat.getParticipantLePlusRentable(con);
//        String html = "";
//        String cat = "";
//        try {
//            html = "<table style=\"width:80%\" border=\"1\" class=\"table table-striped table-responsive-md\">\n"
//                    + "                                            <thead>\n"
//                    + "                                                <tr>\n"
//                    + "                                                    <th>Categorie</th>          \n"
//                    + "                                                    <th>Chiffre</th>\n"
//                    + "                                                    <th>Enchere Effectuer</th>\n"
//                    + "                                                    <th>Enchere Gagner</th>\n"
//                    + "                                                    <th>Efficacite</th>\n"
//                    + "                                                </tr>\n"
//                    + "                                            </thead>\n"
//                    + "                                            <tbody>\n" + "";
//
//            for (int i = 0; i < ccat.size(); i++) {
//                cat += "<tr>";
//                cat += "<td>" + ccat.get(i).getNom() + "</td>";
//                cat += "<td>" + ccat.get(i).getRentabilite() + "</td>";
//                cat += "<td>" + ccat.get(i).getEnchereEffectuer(con) + "</td>";
//                cat += "<td>" + ccat.get(i).getNbEnchereGagner(con) + "</td>";
//                cat += "<td>" + ccat.get(i).getEfficacite(con) + "</td>";
//                cat+= "</tr>\n";
//            }
//        } catch (Exception ex) {
//        } finally {
//          
//        }
//
//        return html
//                + cat
//                + " </tbody></table>";
//    }

    public static double getChiffreAffaireJour(Connection con) throws SQLException, Exception {
        Categorie valiny = null;
        Connection connection = null;
        double retu = 0;
        try {

            String sql = "select avg(sum) as avg from (select sum(commission),jour From chiffreEnchere group by jour) as foo;\n"
                    + "";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retu = resultSet.getDouble("avg");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

        }
        return retu;
    }

    public static double getChiffreAffaireMois(Connection con) throws SQLException, Exception {
        Categorie valiny = null;
        Connection connection = null;
        double retu = 0;
        try {

            String sql = "select avg(sum) as avg from (select sum(commission),mois From chiffreEnchere group by mois) as foo;\n"
                    + "";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retu = resultSet.getDouble("avg");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
//            resultSe

        }
        return retu;
    }

    public static double getChiffreAffaireAnnuel(Connection con) throws SQLException, Exception {
        Categorie valiny = null;
        double retu = 0;

        try {

            String sql = "select avg(sum) as avg from (select sum(commission),annee From chiffreEnchere group by annee) as foo;\n"
                    + "";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                retu = resultSet.getDouble("avg");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
//            resultSe
        }
        return retu;
    }

    public static ArrayList<Categorie> categorieLePlusAimes(Connection con) throws Exception {
        Categorie valiny = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Categorie> va = new ArrayList<Categorie>();
        try {

            String sql = "select count(en.categorieid) as isa ,cat.id from categorie cat full join encheremove emv join enchere en on en.id=emv.enchereid  \n"
                    + "  on cat.id=en.categorieid group by cat.id \n"
                    + " order by isa desc";
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("id"));
                categorie = categorie.getCategorie();
                //System.out.println(resultSet.getInt("usersid"));
                categorie.setPersInteresser(resultSet.getInt("isa"));
                //System.out.println(resultSet.getInt("som"));
                va.add(categorie);
            }
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
        return va;
    }
    public static Categorie getcategorieLePlusAimes(Connection con) throws Exception {
        Categorie valiny = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Categorie> va = new ArrayList<Categorie>();
        try {

            String sql = "select count(en.categorieid) as isa ,cat.id from categorie cat full join encheremove emv join enchere en on en.id=emv.enchereid  \n"
                    + "  on cat.id=en.categorieid group by cat.id \n"
                    + " order by isa desc limit 1";
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("id"));
                categorie = categorie.getCategorie();
                //System.out.println(resultSet.getInt("usersid"));
                categorie.setPersInteresser(resultSet.getInt("isa"));
                //System.out.println(resultSet.getInt("som"));
                va.add(categorie);
            }
        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        } finally {
            resultSet.close();
            preparedStatement.close();

        }
        return va.get(0);
    }

    public static ArrayList<Categorie> getChiffreAffaireParCategorie(Connection con) throws SQLException {
        Categorie valiny = null;
        Connection connection = null;
        ArrayList<Categorie> va = new ArrayList<Categorie>();
        try {

            String sql = " select case when sum(commission)>0 then sum(commission) else 0 end prixmise ,ct.id From categorie ct full join"
                    + " chiffreEnchere ce on ce.categorieid=ct.id group by ct.id order by prixmise desc";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("id"));
                categorie = categorie.getCategorie();
                categorie.setChiffreAffaire(resultSet.getDouble("prixmise"));
                va.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

            return va;
        }
    }

    public static ArrayList<Users> getParticipantLePlusRentable(Connection con) throws SQLException {
        Categorie valiny = null;
        ArrayList<Users> va = new ArrayList<Users>();
        try {

            String sql = " select sum(commission),id from "
                    + " (select us.id ,case when commission>0 then"
                    + " commission else 0 end commission From users "
                    + "us full join chiffreEnchere ce on us.id=participant)"
                    + " as foo group by id order by sum desc";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users categorie = new Users();
                categorie.setId(resultSet.getInt("id"));
                categorie = categorie.getUsers();
                categorie.setRentabilite(resultSet.getDouble("sum"));
                va.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            return va;
        }
    }

    public static ArrayList<Enchere> getEnchereLePlusRentable(Connection con) throws SQLException {
        Categorie valiny = null;
        ArrayList<Enchere> va = new ArrayList<Enchere>();
        try {

            String sql = " select sum(commission),id,categorieid from"
                    + "(select us.id ,us.categorieid,case when commission>0 then"
                    + "   commission else 0 end commission From Enchere "
                    + "us full join chiffreEnchere ce on us.id=ce.enchereid)"
                    + " as foo group by id,categorieid order by sum desc";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Enchere categorie = new Enchere();
                categorie.setId(resultSet.getInt("id"));
                categorie = categorie.getEnchere();
                Categorie jj = new Categorie();
                jj.
                        setId(resultSet.getInt("categorieid"));
                categorie.setCat(jj.getCategorie());
                categorie.setCategorieId(resultSet.getInt("categorieid"));
                categorie.setRentabilite(resultSet.getDouble("sum"));
                va.add(categorie);
//                categorie.getNbPersonne()
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            return va;
        }
    }

    public ArrayList<Users> lustedemanderechargement() throws SQLException {
        Users valiny = null;
        ArrayList<Users> val = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select count(dm.id) as isa,us.id from demanderechargement dm full "
                    + "join users us on us.id=dm.usersid group by us.id order by isa desc;";
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("id"));
                users = users.getUsers();
                //System.out.println(resultSet.getInt("usersid"));
                users.setNbrechargeCompte(resultSet.getInt("isa"));
                val.add(users);
                //System.out.println(resultSet.getInt("som"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return val;
        }
//         select count(*) as isa,users.id from demanderechargement as dm right join users on users.id=dm.usersid group by users.id;
    }

    public ArrayList<Users> activiteUsers() throws SQLException {
        ArrayList<Users> valiny = new ArrayList();
        Connection connection = null;
        try {

            connection = Connexion.getConn();
            String sql = "select sum(isa)as som,usersid from (\n"
                    + "select count(usersid) as isa,enchereid,usersid from encheremove  \n"
                    + "group by enchereid,usersid\n"
                    + ")as b\n"
                    + "group by usersid";
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("usersid"));
                users = users.getUsers();
                users.setNbEnchereFait(resultSet.getInt("som"));
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

    /* enchere be panao ndrindra--- vita */
    public ArrayList<Enchere> getTopEnchere() throws SQLException {
        Enchere valiny = null;
        Connection connection = null;
        ArrayList<Enchere> en = new ArrayList<>();
        try {
            connection = Connexion.getConn();
            String sql = "select  count(distinct(usersid)) as isa,enchereid from encheremove\n"
                    + "group by enchereid\n"
                    + "order by isa desc \n"
                    + "";
            System.err.println(sql);
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Enchere enchere = new Enchere();
                enchere.setId(resultSet.getInt("enchereid"));
//                enchere=enchere.getEnchere();
                enchere.setNbPersonne(resultSet.getInt("isa"));
                en.add(enchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return en;
        }
    }

}
