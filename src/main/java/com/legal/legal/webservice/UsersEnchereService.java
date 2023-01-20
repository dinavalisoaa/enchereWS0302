package com.legal.legal.webservice;

import BddObject.Connexion;
import java.util.List;
import java.util.ArrayList;
import model.*;
import com.google.gson.*;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import utils.Fail;
import utils.Message;
import utils.Success;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@CrossOrigin
public class UsersEnchereService {
//creer son propres en Encheres

    @PostMapping("/users/{id}/encheres")
    String Create(HttpServletRequest request,
            @PathVariable int id, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();
//        Enchere
        double prixMin = Double.parseDouble(request.getParameter("prixMin"));
        String descriProduit = (request.getParameter("descriProduit"));

        double durer = Double.parseDouble(request.getParameter("durer"));

        int categorieId = Integer.parseInt(request.getParameter("categorieId"));

        Gson gson = new Gson();
        try {
         ///   TokenHandler token//s = new TokenHandler().ToToken(token);
            int usersId = 1;//tokens.getUtilisateur();
            System.err.println(usersId);
        } catch (Exception d) {
            _val_.put("error", new Fail(d.getMessage(), "404"));
            return gson.toJson(_val_);
        }
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        Connection con = null;
        try {
            Enchere enchere = new Enchere();
            enchere.setCategorieId(categorieId);
//            enchere.setDateDebut(dateDebut);
            enchere.setPrixMin(prixMin);
            enchere.setUsersId(id);
            enchere.setDescriProduit(descriProduit);
            Parametrage test = new Parametrage();
            test.setId(2);
            con = Connexion.getConn();
            Parametrage trage = (Parametrage) test.select(con).get(0);//getLast(con);
            if (durer > Integer.parseInt(trage.getValue())) {
                throw new Exception("Durer trop long");
            }
            test = new Parametrage();
            test.setId(4);
            trage = (Parametrage) test.select(con).get(0);
            if (durer < Integer.parseInt(trage.getValue())) {
                throw new Exception("Durer trop court");
            }
            enchere.setDurer(durer);
            enchere.insert(con);
            texte = gson.toJson(new Message(new Success(enchere.getLastID(), "Success")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(), "500")));

        } finally {
            con.close();;

        }
        /*Enchere.checkDepassement();*/
          /*Enchere.checkDepassement();*/return texte;
    }

    @PutMapping("/users/{id}/encheres/{ide}/addpictures")
    String change(
            @PathVariable int id,
            @PathVariable int ide, @RequestHeader String body) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        try {
            EncherePhoto enp = new EncherePhoto();
            enp.setEnchereId(ide);
            enp.setPhoto(body);
            enp.insert(null);
            texte = gson.toJson(new Message(new Success(200, "Success")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(), "500")));
//            
        }
          /*Enchere.checkDepassement();*/return texte;
    }

    //update un encheres adjuger
    @PutMapping("/users/{id}/encheres/{idc}/ajd")
    String updateAdj(@RequestParam double prixMin,
            @PathVariable int idc,
            @PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            Enchere enchere = new Enchere();
//            enchere.setCategorieId(categorieId);
//            enchere.setDateDebut(dateDebut);
//            enchere.setPrixMin(prixMin);
            enchere.setUsersId(id);
//            enchere.setDescriProduit(descriProduit);
//            enchere.setDurer(durer);
            enchere.setId(idc);
            enchere.setUsersId(id);
            enchere.setState(1);

            enchere.update("id", null);
            texte = gson.toJson(new Message(new Success(id, "Update Ok!!")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(), "500")));

        }
            /*Enchere.checkDepassement();*/return texte;
    }
// update d'un enchere

    @PutMapping("/users/{id}/encheres/{idc}")
    String update(@PathVariable int id, @PathVariable int idc, HttpServletRequest rq, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        try {
//            TokenHandler tokens = new TokenHandler().ToToken(token);
            int usersId =1;// tokens.getUtilisateur();
        } catch (Exception d) {
            _val_.put("error", new Fail(d.getMessage(), "404"));
            return gson.toJson(_val_);
        }
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            Enchere enchere = new Enchere();
            if (rq.getParameter("categorieId") != null) {
                enchere.setCategorieId(Integer.parseInt(rq.getParameter("categorieId")));
            }
            if (rq.getParameter("prixMin") != null) {
                enchere.setPrixMin(Double.parseDouble((rq.getParameter("prixMin"))));
            }
            if (rq.getParameter("durer") != null) {
                enchere.setDurer(Double.parseDouble((rq.getParameter("durer"))));
            }

            if (rq.getParameter("descriProduit") != null) {
                enchere.setDescriProduit(rq.getParameter("descriProduit"));

            }
            enchere.setUsersId(id);
            enchere.setId(idc);
            enchere.update("id", null);
            texte = gson.toJson(new Message(new Success(id, "Update Ok!!")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(), "500")));
//            
        }
          /*Enchere.checkDepassement();*/return texte;
    }

    @PostMapping("/users/{id}/encheres/{idc}/addPic")
    @ResponseBody
    String updatePic(@PathVariable int id, @PathVariable int idc, @RequestBody String photo) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        Photo gg = gson.fromJson(photo, Photo.class);
        try {
            EncherePhoto o = new EncherePhoto();
            o.setEnchereId(idc);
            o.setPhoto(gg.getPhoto());
            o.insert(null);
            texte = gson.toJson(new Message(new Success(id, "Insert Ok!!")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(), "500")));
//         /   
        }
          /*Enchere.checkDepassement();*/return texte;
    }

    //  get tous les encheresz
//    ArrayList<Enchere> advancedSearch(String cle)
    @GetMapping("/users/{id}/categories/{idc}/encheres")
    String getOned(@PathVariable int id, @PathVariable int idc) throws Exception {
        Enchere am = new Enchere();
        am.setCategorieId(idc);
        am.setUsersId(id);
        Gson gson = new Gson();
        Connection con = Connexion.getConn();
        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<Enchere> tous = am.select(con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            Users u_ = new Users();
            u_.setId(uu.getUsersId());
            uu.setUser(u_.getUsers(con));
            uu.setExpiration(uu.getExpiration());
            uu.setPhoto(g.select(con));
            Categorie gorie = new Categorie();
            gorie.setId(uu.getCategorieId());
            uu.setCat(gorie.getCategorie(con));
            touss.add(uu);
        }
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}/encheresRecherche")
    String getOneRECHERCHE(@PathVariable int id, @RequestHeader String key, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        try {
//            TokenHandler tokens = new TokenHandler().ToToken(token);
            int usersId = 1;//tokens.getUtilisateur();
            System.err.println(usersId);
        } catch (Exception d) {
            _val_.put("error", new Fail(d.getMessage(), "404"));
            return gson.toJson(_val_);
        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Connection con = Connexion.getConn();
//        String key = "";
//        if (rq.getParameter("key") != null) {
//            key = rq.getParameter("key");
//        }
        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere en join categorie cat on cat.id=en.categorieid where descriproduit like '%" + key + "%' or cat.nom like '%" + key + "%'  and usersId=" + id + " order by en.id desc", con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            Users u_ = new Users();
            u_.setId(uu.getUsersId());
            uu.setUser(u_.getUsers(con));
            uu.setExpiration(uu.getExpiration());
            uu.setPhoto(g.select(con));
            Categorie gorie = new Categorie();
            gorie.setId(uu.getCategorieId());
            uu.setCat(gorie.getCategorie(con));
            touss.add(uu);
        }

//        String texte = gson.toJson(am.select(con));
//          /*Enchere.checkDepassement();*/return texte;
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}/encheres")
    String getOne(@PathVariable int id, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        Enchere am = new Enchere();
        am.setUsersId(id);
//        Connection con=null
//        try{
        Connection con = Connexion.getConn();
//        con.
        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere where usersId=" + id + " order by id desc", con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            Users u_ = new Users();
            u_.setId(uu.getUsersId());
//            uu.setUser(u_.getUsers(con));
            uu.setExpiration(uu.getExpiration());
            uu.setPhoto(g.select(con));
//            Categorie gorie = new Categorie();
//            gorie.setId(uu.getCategorieId());
//            uu.setCat(gorie.getCategorie(con));
            touss.add(uu);
        }
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }

   
    @PostMapping("/users/{id}/encheres2")
    String getpost(@PathVariable int id, @RequestParam String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
//        try {
////            TokenHandler token//s = new TokenHandler().ToToken(token);
//            int usersId = 1;//tokens.getUtilisateur();
//            System.err.println(usersId);
//        } catch (Exception d) {
//            _val_.put("error", new Fail(d.getMessage(), "404"));
//            return gson.toJson(_val_);
//        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Connection con = Connexion.getConn();

        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere where usersId=" + id + " order by id desc", con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            Users u_ = new Users();
            u_.setId(uu.getUsersId());
            uu.setUser(u_.getUsers(con));
            uu.setExpiration(uu.getExpiration());
            uu.setPhoto(g.select(con));
            Categorie gorie = new Categorie();
            gorie.setId(uu.getCategorieId());
            uu.setCat(gorie.getCategorie(con));
            touss.add(uu);
        }
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }


    @GetMapping("/users/{id}/encheresExpirer")
    String encheresExpirer(@PathVariable int id, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
//        try {
//            TokenHandler tokens = new TokenHandler().ToToken(token);
//            int usersId = tokens.getUtilisateur();
//            System.err.println(usersId);
//        } catch (Exception d) {
//            _val_.put("error", new Fail(d.getMessage(), "404"));
//            return gson.toJson(_val_);
//        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Connection con = Connexion.getConn();

        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere where usersId=" + id + " order by id desc", con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            if (uu.getExpiration() == true) {
                EncherePhoto g = new EncherePhoto();
                g.setEnchereId(uu.getId());
                uu.setDateFarany(uu.getDateFarany());
                uu.setDepuis(uu.getDepuis());
                Users u_ = new Users();
//                u_.setId(uu.getUsersId());
//                uu.setUser(u_.getUsers(con));
                uu.setExpiration(uu.getExpiration());
                uu.setPhoto(g.select(con));
                Categorie gorie = new Categorie();
                gorie.setId(uu.getCategorieId());
                uu.setCat(gorie.getCategorie(con));
                touss.add(uu);
            }
        }
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}/encheresEncours")
    String enchereEncoure(@PathVariable int id, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
//        try {
//            TokenHandler tokens = new TokenHandler().ToToken(token);
//            int usersId = tokens.getUtilisateur();
//            System.err.println(usersId);
//        } catch (Exception d) {
//            _val_.put("error", new Fail(d.getMessage(), "404"));
//            return gson.toJson(_val_);
//        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Connection con = Connexion.getConn();

        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere where usersId=" + id + " order by id desc", con);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            if (uu.getExpiration() != true) {
                EncherePhoto g = new EncherePhoto();
                g.setEnchereId(uu.getId());
                uu.setDateFarany(uu.getDateFarany());
                uu.setDepuis(uu.getDepuis());
                Users u_ = new Users();
                u_.setId(uu.getUsersId());
                uu.setUser(u_.getUsers(con));
                uu.setExpiration(uu.getExpiration());
                uu.setPhoto(g.select(con));
                Categorie gorie = new Categorie();
                gorie.setId(uu.getCategorieId());
                uu.setCat(gorie.getCategorie(con));
                touss.add(uu);
            }
        }
        _val_.put("data", touss);
        con.close();
        return gson.toJson(_val_);
    }

    @PostMapping("/users/{id}/encheressearch")
    String recherhce2(@PathVariable int id, HttpServletRequest request, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();
        System.out.println("com.legal.legal.webservice.UsersEnchereService.recherhce()" + request.getParameter("categorieId"));

        Gson gson = new Gson();
        try {
//            TokenHandler tokens = new TokenHandler().ToToken(token);
            int usersId = 1;//tokens.getUtilisateur();
            System.err.println(usersId);
        } catch (Exception d) {
            _val_.put("error", new Fail(d.getMessage(), "404"));
            return gson.toJson(_val_);
        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Enchere enchere = new Enchere();
        if (request.getParameter("datedebut") != null) {
            enchere.setDateDebut(request.getParameter("datedebut"));
        }
        if (request.getParameter("dateexp") != null) {
            enchere.setDateDebut(request.getParameter("dateexp"));
        }
        if (request.getParameter("state") != null) {
            enchere.setState(Integer.valueOf(request.getParameter("state")));
        }
        if (request.getParameter("categorieId") != null) {
            enchere.setCategorieId(Integer.valueOf(request.getParameter("categorieId")));
        }
        if (request.getParameter("prixmin") != null) {
            enchere.setPrixMin(Double.valueOf(request.getParameter("prixmin")));
        }
        if (request.getParameter("prixmax") != null) {
            enchere.setPrixMin(Double.valueOf(request.getParameter("prixmin")));
        }
        String key = null;//"";
        if (request.getParameter("cle") != null) {
            key = request.getParameter("cle");
        }
        ArrayList<Enchere> tous = enchere.advancedSearch(key);
         ArrayList<Enchere> touss = new ArrayList<>();
         Connection con=Connexion.getConn();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
//            uu.setDateFarany(uu.getDateFarany());
//            uu.setDepuis(uu.getDepuis());
//            Users u_ = new Users();
//            u_.setId(uu.getUsersId());
//            uu.setUser(u_.getUsers(con));
//            uu.setExpiration(uu.getExpiration());
            uu.setPhoto(g.select(con));
//            Categorie gorie = new Categorie();
//            gorie.setId(uu.getCategorieId());
//            uu.setCat(gorie.getCategorie(con));
            touss.add(uu);
        }
        _val_.put("data", touss);
        
        con.close();
//        _val_.put("data", sera);
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}/encheres/search")
    String recherhce(@PathVariable int id, HttpServletRequest request, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        try {
            TokenHandler tokens = new TokenHandler().ToToken(token);
            int usersId = 1;//tokens.getUtilisateur();
            System.err.println(usersId);
        } catch (Exception d) {
            _val_.put("error", new Fail(d.getMessage(), "404"));
            return gson.toJson(_val_);
        }
        Enchere am = new Enchere();
        am.setUsersId(id);
        Enchere enchere = new Enchere();
        if (request.getParameter("datedebut") != null) {
            enchere.setDateDebut(request.getParameter("datedebut"));
        }
        if (request.getParameter("dateexp") != null) {
            enchere.setDateDebut(request.getParameter("dateexp"));
        }
        if (request.getParameter("state") != null) {
            enchere.setState(Integer.valueOf(request.getParameter("state")));
        }
        if (request.getParameter("prixmin") != null) {
            enchere.setPrixMin(Double.valueOf(request.getParameter("prixmin")));
        }

        String key = null;//"";
        if (request.getParameter("cle") != null) {
            key = request.getParameter("cle");
        }
        ArrayList<Enchere> sera = enchere.advancedSearch(key);
        _val_.put("data", sera);
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}/encheres/{idc}")
    String getOneEnc(@PathVariable int id, @PathVariable int idc) throws Exception {
        Enchere am = new Enchere();
        am.setUsersId(id);
        am.setId(idc);
        EncherePhoto ph = new EncherePhoto();
        ph.setEnchereId(idc);
        Gson gson = new Gson();
        Connection con=Connexion.getConn();
        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<Enchere> touss = new ArrayList<>();
        ArrayList<Enchere> tous = am.select(con);
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            EncherePhoto g = new EncherePhoto();
            g.setEnchereId(uu.getId());
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            uu.setExpiration(uu.getExpiration());
            uu.setUserGagnant(uu.getGagnant());

            Categorie gorie = new Categorie();
            gorie.setId(uu.getCategorieId());
            uu.setCat(gorie.getCategorie());
            uu.setPhoto(g.select(con));
            touss.add(uu);
        }
        _val_.put("data", touss);
        _val_.put("photo", ph.select(con));

        return gson.toJson(_val_);
    }
}
