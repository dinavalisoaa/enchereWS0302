package com.legal.legal.webservice;

import java.util.List;
import java.util.ArrayList;
import model.*;
import com.google.gson.*;
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
public class DemandeRechargementService {
//creer son propres en Encheres

    @PostMapping("/users/{id}/demandes")
    String Create(@RequestParam double montant,
            @PathVariable int id,@RequestHeader String token) throws Exception {
        Gson gson = new Gson();
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));
       
        try {
         TokenHandler tokens = new TokenHandler().ToToken(token);;
            DemandeRechargement demn=new DemandeRechargement();
            demn.setState(0);
            demn.setMontant(montant);
            demn.setUsersId(id);
            demn.insert(null);
            texte = gson.toJson(new Message(new Success(demn.getLastID(), "Success")));
            return texte;
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
        }
        return texte;
    }

  
}
