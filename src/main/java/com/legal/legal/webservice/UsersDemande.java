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
public class UsersDemande {
//creer son propres en Encheres

    @GetMapping("users/{idusers}/demandes")
    String Create(
            @PathVariable int idusers) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        DemandeRechargement move = new DemandeRechargement();
        move.setUsersId(idusers);
//        move.setEnchereId(id);
        HashMap _val_ = new HashMap<String, Object>();
          ArrayList<DemandeRechargement> all = new ArrayList<>();
            ArrayList<DemandeRechargement> alls = move.select(null);

            for (int i = 0; i < alls.size(); i++) {
                DemandeRechargement get = alls.get(i);
                Users vo = new Users();
                vo.setId(get.getUsersId());
                get.setUser(vo.getUsers());
                all.add(get);
            }
            
            _val_.put("data",all);
        return gson.toJson(_val_);
//          return texte;
    }
 @GetMapping("users/{idusers}/demandes/{id}")
    String Creates(@PathVariable int id,
            @PathVariable int idusers) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        DemandeRechargement move = new DemandeRechargement();
        move.setUsersId(idusers);
        move.setId(id);
//        move.setEnchereId(id);
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("data", move.select(null));
        return gson.toJson(_val_);
//          return texte;
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
