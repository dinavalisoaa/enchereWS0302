package model;

import BddObject.Connexion;
import BddObject.Ignore;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dina
 */
@InfoDAO(table = "Categorie")
public class Categorie extends ObjectBDD {

    private int id = -1;
    private String nom;
    @Ignore
    private int persInteresser;
    @Ignore
    double chiffreAffaire;

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
    public int getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Categorie getCategorie() throws Exception {
//    ArrayList<Users>vao=()
Categorie cat=new Categorie();
cat.setId(this.id);
        return ((Categorie) cat.select(null).get(0));
    }public Categorie getCategorie(Connection con) throws Exception {
//    ArrayList<Users>vao=()
Categorie cat=new Categorie();
cat.setId(this.id);
        return ((Categorie) cat.select(con).get(0));
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPersInteresser() {
        return persInteresser;
    }

//select count(*) as isa,categorieid from enchere en join encheremove mo on mo.enchereid=en.id
//group by categorieid
//order by isa desc
    public void setPersInteresser(int persInteresser) {
        this.persInteresser = persInteresser;
    }

//    public Categorie getUserCompteLePlusRecharg() throws SQLException {
//        Categorie valiny = null;
//        Connection connection = null;
//        try {
//            connection = Connexion.getConn();
//            String sql = "";
//            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Categorie categorie = new Categorie();
//                categorie.setId(resultSet.getInt("categorieid"));
//                //System.out.println(resultSet.getInt("usersid"));
//                categorie.setPersInteresser(resultSet.getInt("isa"));
//                //System.out.println(resultSet.getInt("som"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            connection.close();
//            return valiny;
//        }
//    }

 public  static Categorie[] categories() throws Exception {
        ArrayList lis = new Categorie().select(null);
        Categorie[] oo = new Categorie[lis.size()];
        for (int i = 0; i < oo.length; i++) {
            oo[i] = (Categorie) lis.get(i);
        }
        return oo;
    }
    

}
