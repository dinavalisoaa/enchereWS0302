package com.legal.legal.webservice;

import java.util.List;
import java.util.ArrayList;
import model.*;
import com.google.gson.*;
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
public class CompteService {
//creer son propres en Encheres

    @PostMapping("/users/{id}/comptes")
    String Create(@RequestParam double montant,
            @PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            Compte cpt = new Compte();
            cpt.setUsersId(id);
            cpt.setMontant(montant);
            cpt.insert(null);
            texte = gson.toJson(new Message(new Success(cpt.getLastID(), "Success")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            throw ex;
        }
        return texte;
    }
     @GetMapping("/users/{id}/comptes")
    String getUsers(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        Compte vao = new Compte();
//        vao.setLogin(login);
//        vao.setMdp(mdp);
//        vao.setNom(nom);
        vao.setUsersId(id);
//        vao.insert(null);
        _val_.put("data", vao.select(null));
//        String texte = gson.toJson(new Users().select(null));
        return gson.toJson(_val_);
    }
      @GetMapping("/users/{id}/comptes/currentsolde")
    String etat(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        Compte vao = new Compte();
        vao.setUsersId(id);
//        vao.set
        _val_.put("data",vao.getCurrentMoney());
        return gson.toJson(_val_);
    }
//
//    //update un encheres adjuger
//    @PutMapping("/users/{id}/encheres/{idc}/ajd")
//    String updateAdj(@RequestParam double prixMin,
//            @PathVariable int idc,
//            @PathVariable int id) throws Exception {
//        Gson gson = new Gson();
//        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
//
//        try {
//            Enchere enchere = new Enchere();
////            enchere.setCategorieId(categorieId);
////            enchere.setDateDebut(dateDebut);
////            enchere.setPrixMin(prixMin);
//            enchere.setUsersId(id);
////            enchere.setDescriProduit(descriProduit);
////            enchere.setDurer(durer);
//            enchere.setId(idc);
//            enchere.setUsersId(id);
//            enchere.setState(1);
//            
//            enchere.update("id", null);
//            texte = gson.toJson(new Message(new Success(id, "Update Ok!!")));
//        } catch (Exception ex) {
//            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
//        }
//        return texte;
//    }
//// update d'un enchere
//
//    @PutMapping("/users/{id}/encheres/{idc}")
//    String update(@RequestParam double prixMin,
//            @PathVariable int idc,
//            @PathVariable int id, @RequestParam int categorieId,
//            @RequestParam String descriProduit, @RequestParam double durer) throws Exception {
//        Gson gson = new Gson();
//        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
//
//        try {
//            Enchere enchere = new Enchere();
//            enchere.setCategorieId(categorieId);
////            enchere.setDateDebut(dateDebut);
//            enchere.setPrixMin(prixMin);
//            enchere.setUsersId(id);
//            enchere.setDescriProduit(descriProduit);
//            enchere.setDurer(durer);
//            enchere.setId(id);
//            enchere.update("id", null);
//            texte = gson.toJson(new Message(new Success(id, "Update Ok!!")));
//        } catch (Exception ex) {
//            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
//        }
//        return texte;
//    }
//
//    //  get tous les encheresz
//    @GetMapping("/users/{id}/encheres")
//    String getOne(@PathVariable int id) throws Exception {
//        Enchere am = new Enchere();
//        am.setUsersId(id);
//        Gson gson = new Gson();
//        String texte = gson.toJson(am.select(null));
//        return texte;
//    }
//    
//    @GetMapping("/users/{id}/encheres/{idc}")
//    String getOneEnc(@PathVariable int id, @PathVariable int idc) throws Exception {
//        Enchere am = new Enchere();
//        am.setUsersId(id);
//        am.setId(idc);
//        Gson gson = new Gson();
//        String texte = gson.toJson(am.select(null));
//        return texte;
//    }
}
