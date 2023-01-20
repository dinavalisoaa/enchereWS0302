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
@InfoDAO(table = "Compte")
public class Compte extends ObjectBDD {

    private int id = -1;
    private double montant = -1;
    private int usersId;
    private String dateReload;
    int state = -1;
    @Ignore
    Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getMontant() {
        return montant;
    }

    public double getSumMoney() throws Exception {
        Compte vao = new Compte();
        List<Compte> avo = vao.select(null);
        double ii = 0;
        if (avo.size() == 0) {
            return 0;
        } else {
            for (int i = 0; i < avo.size(); i++) {
                Compte get = avo.get(i);
                ii += get.getMontant();
            }
        }
        return ii;
    }

    public double getCurrentMoney() throws Exception {
//        Compte vao = new Compte();
        String sql = "select *from compte where state=1 and usersid=" + this.usersId + " order by id desc ";
        System.out.println(sql);
        List<Compte> avo = this.selectBySQL(sql, null);
        if (avo.isEmpty() == true) {
            return 0;
        }

        return avo.get(0).getMontant();
    }

    public void insert(Connection con) throws Exception {
        ObjectBDD bss = this.getLastObject();
        double montant = 0;
        ArrayList<Compte> li = selectBySQL(" select  *  from compte where state=1 and usersId=" + this.usersId + " order by id desc limit 1", null);
        if (li.size() > 0) {
            montant = (li.get(0)).getMontant();
        }
        this.setMontant(montant + this.montant);
        super.insert(null);
    }

    public void setMontant(double montant) {
        this.montant = montant;
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

    public double getNom() {
        return this.montant;
    }

    /**
     *
     * @param montant
     */
    /**
     *
     * @param usersId
     */
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getUsersId() {
        return usersId;
    }

    public String getDateReload() {
        return this.dateReload;
    }

    /**
     *
     * @param dateReload
     */
    public void setDateReload(String dateReload) {
        this.dateReload = dateReload;
    }

}
