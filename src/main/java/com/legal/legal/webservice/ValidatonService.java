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
public class ValidatonService {
//creer son propres en Encheres

    @GetMapping("demandesAttente")
    String Creatse() throws Exception {
        Gson gson = new Gson();

        HashMap _val_ = new HashMap<String, Object>();

        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        try {
            DemandeRechargement demn = new DemandeRechargement();
            demn.setState(0);
//            demn.setMontant(montant);
//            demn.setUsersId(id);
//            demn.insert(null
            ArrayList<DemandeRechargement> all = new ArrayList<>();
            ArrayList<DemandeRechargement> alls = demn.select(null);

            for (int i = 0; i < alls.size(); i++) {
                DemandeRechargement get = alls.get(i);
                Users vo = new Users();
                vo.setId(get.getUsersId());
                get.setUser(vo.getUsers());
                all.add(get);
            }
            _val_.put("data", all);
//            texte = gson.toJson(demn.select(null));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            throw ex;
        }
        return gson.toJson(_val_);

        //demadnde valider
    }

    @PutMapping("demandesAttente/{id}")
    String up(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        try {
            DemandeRechargement demn = new DemandeRechargement();
//            demn.setState(0);
            demn.setId(id);
            demn.setState(1);
            demn.update("Id", null);
//            demn.setMontant(montant);
//            demn.setUsersId(id);
//            demn.insert(null);
            texte = gson.toJson(new Success(id, "Update Ok!!"));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            throw ex;
        }
        return texte;
        //demadnde valider
    }

    @GetMapping("demandesAttente/{id}")
    String att(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            DemandeRechargement demn = new DemandeRechargement();
            demn.setState(0);
            demn.setId(id);
//            demn.setMontant(montant);
//            demn.setUsersId(id);
//            demn.insert(null);
            texte = gson.toJson(demn.select(null));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            throw ex;
        }
        return texte;
        //demadnde valider
    }

    @GetMapping("demandesValide")
    String Create() throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        HashMap _val_ = new HashMap<String, Object>();

        try {
            DemandeRechargement demn = new DemandeRechargement();
            demn.setState(1);
  ArrayList<DemandeRechargement> all = new ArrayList<>();
            ArrayList<DemandeRechargement> alls = demn.select(null);

            for (int i = 0; i < alls.size(); i++) {
                DemandeRechargement get = alls.get(i);
                Users vo = new Users();
                vo.setId(get.getUsersId());
                get.setUser(vo.getUsers());
                all.add(get);
            }
            _val_.put("data", all);
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
        }
        return gson.toJson(_val_);
        //demadnde valider
    }

    @GetMapping("demandesValide/{id}")
    String Creates(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
        HashMap _val_ = new HashMap<String, Object>();

        try {
            DemandeRechargement demn = new DemandeRechargement();
            demn.setState(1);
            demn.setId(id);
            
  ArrayList<DemandeRechargement> all = new ArrayList<>();
            ArrayList<DemandeRechargement> alls = demn.select(null);

            for (int i = 0; i < alls.size(); i++) {
                DemandeRechargement get = alls.get(i);
                Users vo = new Users();
                vo.setId(get.getUsersId());
                get.setUser(vo.getUsers());
                all.add(get);
            }
            _val_.put("data", all);
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
        }
              return gson.toJson(_val_);

    }

}
