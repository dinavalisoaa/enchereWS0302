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
public class EnchereMoveCoteUtilisateur {
//creer son propres en Encheres

    @PostMapping("encheres/{id}/enchereMoves")
    String Create(@RequestParam double prixMise,
            @PathVariable int id, @RequestHeader String token) throws Exception {
        Gson gson = new Gson();

        Connection con = null;
        String texte = "";// gson.toJson(new Message(new Success(idKilo, "Success")));

//        TokenHandler tokens = new TokenHandler();
        try {
//            tokens = tokens.ToToken(token);

        } catch (Exception e) {
            texte = gson.toJson(new Message(new Fail( e.getMessage(),"500")));

            return texte;
        }
        int usersId =1;/// tokens.getUtilisateur();
        EnchereMove moves = new EnchereMove();
        moves.setEnchereId(id);
        Enchere enc = new Enchere();
        enc.setId(id);
        enc = enc.getEnchere();
        if (enc.getUsersId() == usersId) {
            texte = gson.toJson(new Message(new Fail("Vous ne pouvez pas participer a votre propre enchere","500")));

            return texte;
//            throw new Exception("Vous ne pouvez pas participer a votre propre enchere");
        }
        try {

            moves.setUsersId(usersId);
            moves.setPrixMise(prixMise);
            EnchereMove.setTransaction(moves, id);
            texte = gson.toJson(new Message(new Success(moves.lastMove(null).getId(), "Success")));
        } catch (Exception ex) {
            texte = gson.toJson(new Message(new Fail(ex.getMessage(),"500")));
//            throw ex;
        }
        return texte;
    }

    //  get tous les encheresz
    @GetMapping("encheres/{id}/enchereMoves/{idm}")
    String gethOne(@PathVariable int id, @PathVariable int idm) throws Exception {
        EnchereMove am = new EnchereMove();
        am.setId(idm);
        am.setEnchereId(id);
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
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
        _val_.put("data", all);
        return gson.toJson(_val_);
//        String texte = gson.toJson();
//        return t;
    }

    @GetMapping("encheres/{id}/enchereMoves")
    String getAl(@PathVariable int id) throws Exception {
        EnchereMove am = new EnchereMove();
//        am.setId(idm);
        am.setEnchereId(id);
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        ArrayList<EnchereMove> all = new ArrayList<>();
        ArrayList<EnchereMove> alls = am.select(null);
        for (int i = 0; i < alls.size(); i++) {
            EnchereMove get = alls.get(i);
            Users vo = new Users();
            Enchere enchere = new Enchere();
            enchere.setId(get.getEnchereId());
            vo.setId(get.getUsersId());
            get.setUser(vo.getUsers());
//                get.se
            Commission com = new Commission();
            com.setId(get.getCommissionId());
            get.setCommision(com);
            get.setEnchere(enchere.getEnchere());
            all.add(get);
        }
        _val_.put("data", all);
        return gson.toJson(_val_);
//        String texte = gson.toJson();
//        return t;
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
