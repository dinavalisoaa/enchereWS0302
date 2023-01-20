/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import BddObject.Connexion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author dina
 */
public class UFunction {
//  

    public final String current_timestamp = Timestamp.valueOf(LocalDateTime.now()).toString();
    public final String current_time = Date.valueOf(LocalDate.now()).toString();

    public static int getSequence(String str) {
        Connection connect = null;
        Statement stmt = null;
        ResultSet res = null;
        int date = 0;
        ResultSetMetaData resultMeta = null;
        PreparedStatement pst = null;
        try {
            connect = BddObject.Connexion.getConn();
            pst = connect.prepareStatement("select next value for " + str + "_id_seq as date");

//              pst=connect.prepareStatement("Select nextval('personne_id_seq') as date");
            res = pst.executeQuery();
            resultMeta = res.getMetaData();
            int b = 0;
            while (res.next()) {
                date = res.getInt("date");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (res != null) {
                try {
                    res.close();
                    pst.close();
                    connect.close();
                } catch (SQLException ex) {
                }
            }
        }

        return date;
    }

    public static int getSequence_Sync() {
        Connection connect = null;
        Statement stmt = null;
        ResultSet res = null;
        int date = 0;
        ResultSetMetaData resultMeta = null;
        PreparedStatement pst = null;
        try {
            connect = BddObject.Connexion.getConn();
//              pst=connect.prepareStatement("Select nextval('tablesync_id_seq') as date");          
            pst = connect.prepareStatement("select next value for tablesync_id_seq as date");

//              pst=connect.prepareStatement("Select nextval('tablesync_id_seq') as date");
            res = pst.executeQuery();
            resultMeta = res.getMetaData();
            int b = 0;
            while (res.next()) {
                date = res.getInt("date");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (res != null) {
                try {
                    res.close();
                    pst.close();
                    connect.close();
                } catch (SQLException ex) {
                }
            }
        }

        return date;
    }

    public static Date[] mois_a_venir() {
        Date next = Date.valueOf("2022-12-12");
//        next.setDate(2);
        Date[] mois = new Date[12];
        for (int i = 0; i < mois.length; i++) {
            next.setMonth(next.getMonth() + 1);
            Date vao = new Date(next.getYear(), next.getMonth(), 1);
            mois[i] = vao;
        }
        return mois;
    }

    public static Date get_farany(Date next) {
        int mois = next.getMonth() + 1;
        int i = 0;
//        System.out.println("EEEE"+next.getMonth());
        while (i < 50) {
            next.setDate(next.getDate() + 1);
//           System.out.println(next.toString());
            if (mois == next.getMonth()) {
                break;
            }
            i++;
        }
//       next.setDate(next.getDate()-1);
        Date vao = new Date(next.getYear(), next.getMonth(), 1);
        vao.setDate(vao.getDate() - 1);
        return vao;
//       return
    }

    public static Date[] mois_a_venir_ext() {
        Date next = Date.valueOf("2022-12-12");
//        next.setDate(2);
        Date[] mois = new Date[12];
        for (int i = 0; i < mois.length; i++) {
            next.setMonth(next.getMonth() + 1);
            Date vao = new Date(next.getYear(), next.getMonth(), next.getDate());
            mois[i] = get_farany(vao);
        }
        return mois;
    }

    public static int count_isTsyNiasa(String date1, String date2) throws Exception {
        int d = utils.UFunction.dateDiff(date2, date1);
        System.err.println("DIFF" + d);
        java.util.Date inc = java.sql.Date.valueOf(date1);
        inc.setDate(inc.getDate() - 1);

        int cpt = 0;
        int g = 0;
        while (cpt < d) {
            inc.setDate(inc.getDate() + 1);

            if (getJour(inc.toString()).equals("Thu") || getJour(inc.toString()).equals("Tue")) {
//                System.err.println("__" + inc.toString());
                g++;

            }

            cpt++;
        }
//                System.out.println(date1 + "  <="+g+"=>  " + date2);

        return g;
    }

    public static int count_niasa(int si) throws Exception {
        Date[] fin = mois_a_venir_ext();
        Date[] deb = mois_a_venir();
        int f = 0;
        int re = 0;
        for (int i = 0; i < fin.length; i++) {
//            Date moi = mois[i];
//            if (si == i+1)
            if (i < si) {
                int nbr = utils.UFunction.dateDiff(fin[i].toString(), deb[i].toString());
                int tsy = count_isTsyNiasa(deb[i].toString(), fin[i].toString());
                re = (nbr) - tsy;
                f += re;
            }
        }

        return f;
    }

    public static int dateDiff(String sfixer, String soriginal) throws Exception {
        Date fixer = Date.valueOf(sfixer);
//        Date original=Date.valueOf(original);
        Date original = Date.valueOf(soriginal);

        try {
            long diff = fixer.getTime() - original.getTime();
//            System.out.println("utils.UFunction.dateDiff()" + diff);
            TimeUnit time = TimeUnit.DAYS;
            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

            return (int) diffrence;
        } catch (Exception e) {
            throw new Exception("Date invalid");

        }
    }

    public static int getMois(String s) {
        Date daty = java.sql.Date.valueOf(s);
        return daty.getMonth() + 1;
    }

    public static String getJour(String ds) {
        java.util.Date d = java.sql.Date.valueOf(ds);
        String[] jours = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        int day = d.getDay();
        String jour = new String();
        for (int i = 0; i < jours.length; i++) {
            if (day == i) {
                jour = jours[i];
            }
        }
        return jour;
    }

    public static int timeDiff(String sfixer, String soriginal) throws Exception {
        Timestamp fixer = Timestamp.valueOf(sfixer);
//        Date original=Date.valueOf(original);
        Timestamp original = Timestamp.valueOf(soriginal);

        try {
            long diff = fixer.getTime() - original.getTime();
            System.out.println("utils.UFunction.dateDiff()" + diff);
            TimeUnit time = TimeUnit.HOURS;
            long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);

            return (int) diffrence;
        } catch (Exception e) {
            throw new Exception("Date invalid");

        }
    }

    public static String timestampAdd(String datetime, int val) {

        String str = datetime;
        str = str.split("\\.")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        LocalDateTime d = dateTime.plusHours(val);
        String fats = d.toString();
        String m1 = fats.split("T")[0];
        String m2 = fats.split("T")[1];

        return m1 + " " + m2;
    }

    public static boolean depasser(String datetime, int val) {
        String str = datetime;
        str = str.split("\\.")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        LocalDateTime d = dateTime.plusHours(val);
        return  d.isBefore(LocalDateTime.now());
    }

    public static double timestampDiff(String start_date,
            String end_date) {

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

    public static String getCss() {
        return "<head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "           <link rel=\"stylesheet\" href=\"bootstrap/css/bootstrap.min.css\"/>\n"
                + "        <link rel=\"stylesheet\" href=\"bootstrap/css/styles.min.css\"/>\n"
                + "\n";

    }

    public static String getCurrentTimestamp() {
        Connection connect = null;
        Statement stmt = null;
        ResultSet res = null;
        String date = "";
        ResultSetMetaData resultMeta = null;
        PreparedStatement pst = null;
        try {
            connect = Connexion.getConn();
            pst = connect.prepareStatement("Select current_timestamp as date");
            res = pst.executeQuery();
            resultMeta = res.getMetaData();
            int b = 0;
            while (res.next()) {
                date = res.getString("date");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (res != null) {
                try {
                    res.close();
                    pst.close();
                    connect.close();
                } catch (SQLException ex) {
                }
            }
        }

        return date;
    }
}
