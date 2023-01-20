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
public class EnchereMoveUsersService {
//get Tout les mouvements d'enchere sur mes enchere

    @GetMapping("users/{idusers}/encheres/{id}/enchereMoves")
    String Create(
            @PathVariable int idusers, @PathVariable int id, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
//        try{
//        TokenHandler tokens = new TokenHandler().ToToken(token);
//        int usersId = tokens.getUtilisateur();
//        }catch(Exception d){
//        _val_.put("error",new Fail(d.getMessage(),"404"));
//        return gson.toJson(_val_);
//        }
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        EnchereMove move = new EnchereMove();
        move.setUsersId(idusers);
        move.setEnchereId(id);
        ArrayList<EnchereMove> all = new ArrayList<>();
        ArrayList<EnchereMove> alls = move.select(null);
        for (int i = 0; i < alls.size(); i++) {
            EnchereMove get = alls.get(i);
            Users vo = new Users();
            Enchere enchere = new Enchere();
            enchere.setId(get.getEnchereId());
            vo.setId(get.getUsersId());
            get.setUser(vo.getUsers());
            get.setEnchere(enchere.getEnchere());
            all.add(get);
        }
        _val_.put("data", all);
        return gson.toJson(_val_);
//          return texte;
    }


    @GetMapping("users/{idusers}/enchereMoves")
    String mymise(
            @PathVariable int idusers, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        try{
        TokenHandler tokens = new TokenHandler().ToToken(token);
        int usersId = tokens.getUtilisateur();
        }catch(Exception d){
        _val_.put("error",new Fail(d.getMessage(),"404"));
        return gson.toJson(_val_);
        }
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        EnchereMove move = new EnchereMove();
        move.setUsersId(idusers);
//        move.setEnchereId(id);
        ArrayList<EnchereMove> all = new ArrayList<>();
        ArrayList<EnchereMove> alls = move.select(null);
        for (int i = 0; i < alls.size(); i++) {
            EnchereMove get = alls.get(i);
            Users vo = new Users();
            Enchere enchere = new Enchere();
            enchere.setId(get.getEnchereId());
            vo.setId(get.getUsersId());
            get.setUser(vo.getUsers());
            get.setEnchere(enchere.getEnchere());
            all.add(get);
        }
        _val_.put("data", all);
        return gson.toJson(_val_);
//          return texte;
    }
//get un mouvements d'enchere sur mes enchere 
    @GetMapping("users/{idusers}/encheres/{id}/enchereMoves/{idm}")
    String gethOne(@PathVariable int id, @PathVariable int idusers, @PathVariable int idm, @RequestHeader String token) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();

        Gson gson = new Gson();
        try{
        TokenHandler tokens = new TokenHandler().ToToken(token);
        int usersId = tokens.getUtilisateur();
        }catch(Exception d){
        _val_.put("error",new Fail(d.getMessage(),"404"));
        return gson.toJson(_val_);
        }
        EnchereMove am = new EnchereMove();
        am.setId(idm);
        am.setEnchereId(id);
        am.setUsersId(id);
//        Gson gson = new Gson();
//        String texte = gson.toJson(am.select(null));
//        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<EnchereMove> all = new ArrayList<>();
        ArrayList<EnchereMove> alls = am.select(null);
        for (int i = 0; i < alls.size(); i++) {
            EnchereMove get = alls.get(i);
            Users vo = new Users();
            Enchere enchere = new Enchere();
            enchere.setId(get.getEnchereId());
            vo.setId(get.getUsersId());
            get.setUser(vo.getUsers());
            get.setEnchere(enchere.getEnchere());
            all.add(get);
        }
        return gson.toJson(_val_);
    }

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
