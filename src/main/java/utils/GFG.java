// Java program for the above approach
package utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Users;
//import model.Personne;
  
public class GFG {
  
    // Function to print difference in
    // time start_date and end_date
    static 
  double  findDifference(String start_date,
                   String end_date)
    {
  
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
            = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
    double finaly=0.0;
        // Try Block
        try {
  
            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
  
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
           finaly=(double) (difference_In_Years*24*365+difference_In_Days*24+difference_In_Hours+new Double(difference_In_Minutes)/60+new Double(difference_In_Seconds)/3600);
//            System.out.println("FINALY"+finaly);
         
//            System.out.println("utils.GFG.findDifference()"+new Double(difference_In_Minutes)/60);
        }
  
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
           return finaly;
    }
  
    // Driver Code
    public static void main(String[] args) throws Exception
    {
        // Given start Date
//        String start_date
//            = "insert into Personne(Nom,Prenom,Dtn,Adresse,Sexe) values('das','dsa','2022-11-28','das','homme')";
//        String end_date
//            = "2018-07-13 00:00:02";
//        Personne io=new Personne();
//        io.setDtn("2022-01-20");
//        io.setNom("Dina");
//        io.setId(12);
//        Sy.stem.err.println(io.update("id"));
        //System.err.println(start_date.replaceAll("'", "`"));
        model.Users users=new Users();
//        ArrayList<Users> LUser=users.gmetEnchereVitany();
    }
}

