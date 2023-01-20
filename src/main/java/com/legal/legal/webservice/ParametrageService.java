package com.legal.legal.webservice;

//import wservice.*;
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
public class ParametrageService {
//creer son propres en Encheres

    @PostMapping("parametrages")
    String Create(@RequestParam String nom,
            @RequestParam String value) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            Parametrage demn=new Parametrage();
            demn.setNom(nom);
demn.setValue(value);
            demn.insert(null);
            texte = gson.toJson(new Message(new Success(demn.getLastID(), "Success")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            
        }
        return texte;
    }

    @PutMapping("parametrages/{id}")
    String put(@PathVariable int id,@RequestParam String nom,@RequestParam String value) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

        try {
            Parametrage demn=new Parametrage();
            demn.setId(id);
            demn.setValue(value);
            demn.setNom(nom);
            demn.update("Id",null);
            texte = gson.toJson(new Message(new Success(id, "Update OK!!")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            
        }
        return texte;
    }
   @GetMapping("commissions")
    String pradaf() throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
             HashMap _val_ = new HashMap<String, Object>();
             _val_.put("commission",new Commission().getCurrentId().getTaux());
        try {
            texte = gson.toJson(_val_);
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            
        }
        return texte;
    }
  @GetMapping("categories")
   String getOnes() throws Exception {
        Enchere am = new Enchere();
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
       
        _val_.put("data", new Categorie().select(null));
        return gson.toJson(_val_);
    }

   @GetMapping("parametrages/{id}")
    String alll(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
             HashMap _val_ = new HashMap<String, Object>();
        try {
            Parametrage demn=new Parametrage();
            demn.setId(id);
        _val_.put("data", demn.select(null));
            texte = gson.toJson(new Message(new Success(id, "Update OK!!")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            
        }
        return gson.toJson(_val_);
    }
   @GetMapping("parametrages")
    String allls() throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
             HashMap _val_ = new HashMap<String, Object>();

        try {
            Parametrage demn=new Parametrage();
        _val_.put("data", demn.select(null));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
            
        }
        return gson.toJson(_val_);
    }
}
