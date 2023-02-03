package com.legal.legal.webservice;

import BddObject.Connexion;
import java.util.List;
import java.util.ArrayList;
import model.*;
import com.google.gson.*;
import com.legal.legal.mongo.MongoRepository;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
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
public class EnchereService {
//creer son propres en Encheres
//
//    @GetMapping("Autreencheres/{id}")
//    String getALl(@PathVariable int id) throws Exception {
//        Enchere am = new Enchere();
////        am.setUser/sId(id);
//        Gson gson = new Gson();
//        Connection con = Connexion.getConn();
//        HashMap _val_ = new HashMap<String, Object>();
//        ArrayList<Enchere> all = new ArrayList<>();
//        ArrayList<Enchere> alls = am.select(con);
//        for (int i = 0; i < alls.size(); i++) {
//            Enchere get = alls.get(i);
//            Users vo = new Users();
//            vo.setId(get.getUsersId());
//            if (get.getUsersId() != id) {
//                Categorie gorie = new Categorie();
//                gorie.setId(get.getCategorieId());
//                get.setCat(gorie.getCategorie(con));
//                get.setUser(vo.getUsers(con));
////            get.setPhoto(get.getPhoto());
//                all.add(get);
//            }
//        }
//        _val_.put("data", all);
//        con.close();
//        return gson.toJson(_val_);
////          /*Enchere.checkDepassement();*/  /*Enchere.checkDepassement();*/return texte;
//    }

    //  get tous les encheresz
    @GetMapping("encheres")
    String getALl() throws Exception {
        Enchere am = new Enchere();
        Gson gson = new Gson();
        Connection con = Connexion.getConn();

//        String texte = gson.toJson(am.select(con));
        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<Enchere> all = new ArrayList<>();
        ArrayList<Enchere> alls = am.select(con);
        for (int i = 0; i < alls.size(); i++) {
            Enchere get = alls.get(i);
            Users vo = new Users();
            vo.setId(get.getUsersId());

            get.setPhoto(get.getPhoto());
            get.setDateFarany(get.getDateFarany());
            get.setDepuis(get.getDepuis());
            all.add(get);
        }
        
        _val_.put("data", all);
        con.close();
          /*Enchere.checkDepassement();*/
        return gson.toJson(_val_);
//          /*Enchere.checkDepassement();*/  /*Enchere.checkDepassement();*/return texte;
    }
// select *From categorie where id in (select categorieid from enchere where usersid=2)
///dina

    @GetMapping("categories/{id}/encheres")
    String categeEchere(@PathVariable int id) throws Exception {
        Enchere am = new Enchere();
        am.setCategorieId(id);
        Gson gson = new Gson();
        Connection con = Connexion.getConn();

        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<Enchere> tous = am.selectBySQL("select *from enchere where categorieId=" + id + " ", null);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            if (uu.getExpiration() == false) {
                EncherePhoto g = new EncherePhoto();
                g.setEnchereId(uu.getId());
                uu.setDateFarany(uu.getDateFarany());
                uu.setDepuis(uu.getDepuis());
                Users u_ = new Users();
               u_.setId(uu.getUsersId());
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
    //ce        
        return gson.toJson(_val_);
    }

    @GetMapping("categories/users/{id}")
    String getcat(@PathVariable int id) throws Exception {
       
       try{ Enchere am = new Enchere();
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("data", new Categorie().selectBySQL(" select *From categorie where id in (select categorieid from enchere where usersid=" + id + ")", null));
        return gson.toJson(_val_);
       }catch(Exception en){
        return en.getMessage();
       }
    }

    @GetMapping("encheres/{id}")
    String getOnes(@PathVariable int id) throws Exception {
        
          /*Enchere.checkDepassement();*/
        Enchere am = new Enchere();
        am.setId(id);
        Gson gson = new Gson();
        Connection con = Connexion.getConn();
        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<Enchere> tous = am.select(null);
        ArrayList<Enchere> touss = new ArrayList<>();
        for (int i = 0; i < tous.size(); i++) {
            Enchere uu = tous.get(i);
            uu.setDateFarany(uu.getDateFarany());
            uu.setDepuis(uu.getDepuis());
            Users u_ = new Users();
           u_.setId(uu.getUsersId());
           uu.setUser(u_.getUsers(con));
            uu.setExpiration(uu.getExpiration());
            Categorie gorie = new Categorie();
           gorie.setId(uu.getCategorieId());
           uu.setCat(gorie.getCsategorie(con));
            touss.add(uu);
        }
        EncherePhoto g = new EncherePhoto();
        g.setEnchereId(id);
        _val_.put("data", touss);
        _val_.put("photo", g.select(con));
//        .put("photo"
        con.close();
//       _val_.put("data",am.select(null));       
        return gson.toJson(_val_);
    }

    @GetMapping("encheres/{id}/gagnant")
    String getGagnatn(@PathVariable int id) throws Exception {
        Enchere am = new Enchere();
        am.setId(id);
        am = am.getEnchere();
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        am.setUserGagnant(am.getGagnant());

        _val_.put("data", am);
        return gson.toJson(_val_);

    }

    @PostMapping("encheres/close")
    String allClose() throws Exception {
//        EnchereClose am = new EnchereClose();
//        am.setEnchereId(id);
//        am.setDateClose(LocalDate.now().toString());
//        MongoRepository mon = new MongoRepository();
//        mon.Create(am);
        Connection con = Connexion.getConn();
        ArrayList<Enchere> als = new Enchere().select(con);
        ArrayList<Enchere> al = new ArrayList<>();

        for (int i = 0; i < als.size(); i++) {
            Enchere get = als.get(i);
            if (get.getExpiration() == true && get.getState() == 0) {
                Enchere yy = new Enchere();
                yy.setId(get.getId());
                yy.setState(1);
                yy.update("Id", con);
                al.add(get);
            }
        }
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("data", al);
//       _val_.put("data",new Fail(Boolean.toString(am.isExpirer()), "200"));s
        return gson.toJson(_val_);
    }

    @PostMapping("encheres/{id}/close")
    String close(@PathVariable int id) throws Exception {
        EnchereClose am = new EnchereClose();
        am.setEnchereId(id);
        am.setDateClose(LocalDate.now().toString());
        MongoRepository mon = new MongoRepository();
        mon.Create(am);
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("val", mon.List());
//       _val_.put("data",new Fail(Boolean.toString(am.isExpirer()), "200"));
        return gson.toJson(_val_);
    }

    @GetMapping("encheres/{id}/etatexp")
    String getExp(@PathVariable int id) throws Exception {
        Enchere am = new Enchere();
        am.setId(id);
        am = am.getEnchere();
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("data", new Fail(Boolean.toString(am.isExpirer()), "200"));
        return gson.toJson(_val_);
    }

}
