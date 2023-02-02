package model;

import BddObject.Connexion;
import BddObject.Ignore;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dina
 */
@InfoDAO(table = "Enchere")
public class Enchere extends ObjectBDD {

    private int id = -1;
    private double prixMin = -1;
    private String dateDebut;
    private String dateExp;
    private int usersId = -1;
    private int categorieId = -1;
    int state = -1;
    private String descriProduit;
    private double durer = -1;
    @Ignore
    int nbTest=-1;
    @Ignore
    double prixMax=-1;
    @Ignore
    String dateFarany;
    @Ignore 
    String leraFarany;
    @Ignore
    String depuis;
    @Ignore
    Users userGagnant;
    @Ignore
    Categorie cat;
    @Ignore
    private int nbPersonne;
    @Ignore
    double pourcentageCommission = -1;
    @Ignore
    double prixMiseInitial = -1;
    @Ignore
    Users user;
    @Ignore
    double rentabilite;
    @Ignore
    ArrayList photo;

    @Ignore
    boolean expiration;

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }
    
    public String getDateFarany() {
//     LocalDateTime da=LocalDateTime.parse( );
        String g = utils.UFunction.timestampAdd(this.getDateDebut().split("\\.")[0], (int) this.durer);

        return g;
    }

    public boolean getExpiration() {
        return utils.UFunction.depasser(this.getDateDebut().split("\\.")[0], (int) this.durer);
    }

    public void setExpiration(boolean expiration) {
        this.expiration = expiration;
    }
    

    public void setDateFarany(String dateFarany) {
        this.dateFarany = dateFarany;
    }

    public String getLeraFarany() {
//        utils.UFunction.timestampAdd("2023-01-19 23:27:27", 24));

        LocalDateTime da = LocalDateTime.parse(utils.UFunction.timestampAdd(this.getDateDebut().split("\\.")[0], (int) this.durer));
        return da.toString();
    }

    public void setLeraFarany(String leraFarany) {
        this.leraFarany = leraFarany;
    }

    public String getDepuis() {
        double d = utils.UFunction.timestampDiff(this.getDateDebut(), LocalDateTime.now().toString().split("T")[0]+" "+LocalDateTime.now().toString().split("T")[1]);
        return Double.toString(d);
    }

    public void setDepuis(String depuis) {
        this.depuis = depuis;
    }

    public ArrayList getPhoto() throws Exception {
        EncherePhoto kk = new EncherePhoto();
        kk.setEnchereId(this.id);
        return kk.select(null);
    }

    public void setPhoto(ArrayList photo) {
        this.photo = photo;
    }
    public static void checkDepassement()throws Exception{
    ArrayList<Enchere> tous = new Enchere().select(null);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            if(uu.getExpiration()==true){
                Enchere ff=new Enchere();
                ff.setId(uu.getId());
                ff.setState(1);
                ff.update("Id",null);
            }
        }
    }
    public double getRentabilite() {
        return rentabilite;
    }

    public void setRentabilite(double rentabilite) {
        this.rentabilite = rentabilite;
    }

    public ArrayList<Enchere> advancedSearch(String cle) throws Exception {
        String sql = " select *from enchere join categorie cat on enchere.categorieid=cat.id where 1=1";
        if (categorieId !=-1) {
            sql += " and categorieId=" + categorieId;
        }
        if (cle != null && cle!="") {
            sql += " and (descriproduit like '%" + cle + "%' or nom like '%" + cle + "%') ";
        }
        if (dateDebut != null) {
            sql += " and datedebut >" + dateDebut + "";
        }
        if (prixMin != -1) {
            sql += " and prixMin >" + prixMin + "";
        }
        if (prixMax != -1) {
            sql += " and prixMin <" + prixMax + "";
        }

        if (state != -1) {
            sql += " and state =" + state + "";
        }
        if (dateExp != null) {
            sql += " and dateexp <" + dateExp + "";
        }

        ArrayList<Enchere> li = new Enchere().selectBySQL(sql, null);
        return li;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getPrixMiseInitial() {
        return prixMiseInitial;
    }

    public void setPrixMiseInitial(double prixMiseInitial) {
        this.prixMiseInitial = prixMiseInitial;
    }

//     public Users getUser() {
//        return user;
//    }
    public Enchere getEnchere() throws Exception {
//    ArrayList<Users>vao=()
        return ((Enchere) this.select(null).get(0));
    }

//    public void setUser(Users user) {
//        this.user = user;
//    }
    public Categorie getCat() throws Exception {
//    ArrayList<Users>vao=()
        Categorie vo = new Categorie();
        vo.setId(this.categorieId);
        return ((Categorie) vo.select(null).get(0));
    }

    public EnchereMove getEnchereMovesGagnantId(Connection con) throws SQLException, Exception {
        Users valiny = new Users();
        EnchereMove users = new EnchereMove();
        Connection connection = null;
        int id = 0;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
//            connection = Connexion.getConn();
            String sql = "select * from encheremove\n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "and encheremove.prixmise in(\n"
                    + "select max(prixmise) as maximum from encheremove  \n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "group by enchereid \n"
                    + ") ";
//            System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                users.setId(id);
            }
        } catch (Exception e) {
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
//            connection.close();
        }

        return users.getEnchereMove();

    }

    public boolean isExpirer() throws Exception {
//        String f = "2023-01-17 08:22:20";//
        String f = this.getFin();
        LocalDateTime now = LocalDateTime.now();
        String nows = now.toString().replace("T", " ");
        double kk = Enchere.timestampDiff(f, nows);
        System.err.println(">>>" + kk);
        if (kk >= new Double(0)) {
            return false;
        }
        return true;

    }
//
//    public void setExpirer(boolean expirer) {
//        this.expirer = expirer;
//    }
//

    public void setCat(Categorie cat) {
        this.cat = cat;
    }

    public Users getUserGagnant() {
        return userGagnant;
    }

    public void setUserGagnant(Users userGagnant) {
        this.userGagnant = userGagnant;
    }

//    public boolean isExp
    public static double timestampDiff(String start_date,
            String end_date) {
        System.err.println("" + start_date + " - " + end_date);
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
        double finaly = 0.0;
        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            java.util.Date d1 = sdf.parse(start_date);
            java.util.Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
//            System.out.println(
//                difference_In_Years
//                + " years, "
//                + difference_In_Days
//                + " days, "
//                + difference_In_Hours
//                + " hours, "
//                + difference_In_Minutes
//                + " minutes, "
//                + difference_In_Seconds
//                + " seconds");
            finaly = (double) (difference_In_Years * 24 * 365 + difference_In_Days * 24 + difference_In_Hours + new Double(difference_In_Minutes) / 60 + new Double(difference_In_Seconds) / 3600);
//            System.out.println("FINALY"+finaly);

//            System.out.println("utils.GFG.findDifference()"+new Double(difference_In_Minutes)/60);
        } // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
        return finaly;
    }

    public String getFin() throws Exception {
        Enchere vin = new Enchere();
        vin.setId(this.id);
        String strDate = vin.getEnchere().getDateDebut().replace(" ", "T");
        LocalDateTime date = LocalDateTime.parse(strDate);
        System.out.println("DateDEBUT : " + strDate);
        // Add 2 hours to the date
        LocalDateTime newDate = date.plusHours((long) vin.getEnchere().getDurer());
        // Display result
        System.err.println(" AMPINA" + vin.getEnchere().getDurer());
        System.out.println("New Date : " + newDate);
        return newDate.toString().replace("T", " ");
    }

    public int getId() {
        return this.id;
    }
//        public 

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    public double getPrixMin() {
        return this.prixMin;
    }

    /**
     *
     * @param prixMin
     */
    public void setPrixMin(double prixMin) {
//        if()
        this.prixMin = prixMin;
    }

    public String getDateDebut() {
        return this.dateDebut;
    }

    public int getEnchereMovesGagnantId() throws SQLException, Exception {
        Users valiny = new Users();
        EnchereMove users = new EnchereMove();
        Connection connection = null;
        int id = 0;
        try {
            connection = Connexion.getConn();
            String sql = "select * from encheremove\n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "and encheremove.prixmise in(\n"
                    + "select max(prixmise) as maximum from encheremove  \n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "group by enchereid\n"
                    + ") ";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
        }
        return id;

    }
    /**
     *
     * @param dateDebut
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateExp() {
        return this.dateExp;
    }

    /**
     *
     * @param dateExp
     */
    public void setDateExp(String dateExp) {
        this.dateExp = dateExp;
    }

    public int getUsersId() {
        return this.usersId;
    }

    /**
     *
     * @param usersId
     */
    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getCategorieId() {
        return this.categorieId;
    }

    /**
     *
     * @param categorieId
     */
    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public int getState() {
        return this.state;
    }

    /**
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public String getDescriProduit() {
        return this.descriProduit;
    }

    /**
     *
     * @param descProduit
     */
    public void setDescriProduit(String descProduit) {
        this.descriProduit = descProduit;
    }

    public double getDurer() {
        return this.durer;
    }

    /**
     *
     * @param durer
     */
    public void setDurer(double durer) throws Exception {

        this.durer = durer;
    }
//add gagna
    public int getNbUserVisit() throws SQLException, Exception {
        Users valiny = new Users();
        Users users = new Users();
        Connection connection = null;
        int j = 0;
        try {
            connection = Connexion.getConn();
            String sql = "select count(distinct(usersid)) as in from encheremove where enchereid=" + this.getId() + "";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                j = resultSet.getInt("in");
//                users.setId(resultSet.getInt("usersid"));
//                users.setMiseGagnant(resultSet.getDouble("prixmise"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
//            return users;
        }
        return j;
    }

    public Users getGagnant() throws SQLException, Exception {
        Users valiny = new Users();
        Users users = new Users();
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select * from encheremove\n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "and encheremove.prixmise in(\n"
                    + "select max(prixmise) as maximum from encheremove  \n"
                    + "where encheremove.enchereid=" + this.getId() + "\n"
                    + "group by enchereid\n"
                    + ")";
            System.out.println(sql);

            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                users.setId(resultSet.getInt("usersid"));
                users = users.getUsers();

                users.setMiseGagnant(resultSet.getDouble("prixmise"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return users;
        }
    }


    public int getEnchereVitany() throws SQLException {
        int valiny = 0;
        Connection connection = null;
        try {
            connection = Connexion.getConn();
            String sql = "select * from(select  count(distinct(usersid)),enchereid from encheremove\n"
                    + "group by enchereid)as tab_1 join enchere ON enchere.id = tab_1.enchereid"
                    + " where enchereid=" + this.getId();
            PreparedStatement preparedStatement = Connexion.getConn().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
            return valiny;
        }
    }

    public double getPourcentageCommission() {
        return pourcentageCommission;
    }

    public void setPourcentageCommission(double pourcentageCommission) {
        this.pourcentageCommission = pourcentageCommission;
    }
//
//    public int getNbPersonne() {
//        return nbPersonne;
//    }
//
//    public void setNbPersonne(int nbPersonne) {
//        this.nbPersonne = nbPersonne;
//    }

}
