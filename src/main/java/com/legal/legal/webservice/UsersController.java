package com.legal.legal.webservice;

//import antlr.Token;
import BddObject.Connexion;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import utils.Data;
import utils.Fail;
import utils.Message;
import utils.Success;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@CrossOrigin
public class UsersController {


    @GetMapping("/usersencheres/{id}")
    String getOne(@PathVariable int id) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();
        Gson gson = new Gson();
        Enchere am = new Enchere();
        am.setUsersId(id);
        Connection con = Connexion.getConn();

        ArrayList<Enchere> tous = new ArrayList<>();
        ArrayList<Enchere> all = new ArrayList<>();
        ArrayList<Enchere> alls = am.select(con);

        for (int i = 0; i < alls.size(); i++) {
            Enchere get = alls.get(i);
            Users vo = new Users();

            EncherePhoto phd = new EncherePhoto();
            phd.setEnchereId(get.getId());
            ArrayList<EncherePhoto> hdas = phd.select(con);
            get.setPhoto(hdas);
            Categorie gorie = new Categorie();
            get.setDateFarany(get.getDateFarany());
            get.setDepuis(get.getDepuis());
            all.add(get);
        }
        _val_.put("data", all);
        con.close();
        return gson.toJson(_val_);
    }

    @GetMapping("/users")
    String users() throws Exception {
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        _val_.put("data", new Users().select(null));
        return gson.toJson(_val_);
    }

    @GetMapping("/users/{id}")
    String users1(@PathVariable int id) throws Exception {
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        Users vao = new Users();
        vao.setId(id);
        _val_.put("data", vao.select(null));
        return gson.toJson(_val_);
    }

    @PostMapping("/users")
    String getUsers(@RequestParam String nom, @RequestParam String prenom,
            @RequestParam String login, @RequestParam String mdp) throws Exception {
        Gson gson = new Gson();
        HashMap _val_ = new HashMap<String, Object>();
        Users vao = new Users();
        vao.setLogin(login);
        vao.setMdp(mdp);
        vao.setNom(nom);
        vao.setPrenom(prenom);
        vao.insert(null);
        _val_.put("data", new Message(new Success(vao.getLastID(), "Create Ok")));
        return gson.toJson(_val_);
    }

    @PostMapping("/login")
    public String login(@RequestParam String logins, @RequestParam String pwds) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();
        Users zateur = new Users();
        zateur.setLogin(logins);
        zateur.setMdp(pwds);
        Gson gson = new Gson();
        Connection con = Connexion.getConn();
        int id = zateur.getLoginId(con);
        con.close();
        System.err.println(id);
        String json = "";
        if (id == -1) {
            _val_.put("datas", (new Fail("Login Error", "500")));
            return gson.toJson(_val_);
        }
        zateur.setId(id);
        try {
            _val_.put("datas", new Success(id, "ww"));
        } catch (Exception xc) {
            _val_.put("datas", new Fail("", xc.getMessage()));

        } finally {
            con.close();
        }

        return gson.toJson(_val_);
    }

    @PostMapping("/inscription")
    String logon(@RequestParam String nom, @RequestParam String prenom, @RequestParam String dtn, @RequestParam String logins, @RequestParam String pwds) throws Exception {
        HashMap _val_ = new HashMap<String, Object>();
        Users zateur = new Users();
        Gson gson = new Gson();

        try {
            zateur.setNom(nom);
            zateur.setPrenom(prenom);
            zateur.setDtn(dtn);
            zateur.setLogin(logins);
            zateur.setMdp(pwds);
            zateur.insert(null);
            Compte com = new Compte();
            com.setUsersId(zateur.getLastID());
            com.insert(null);
            int id = zateur.getLastID();
            zateur.setId(id);
            _val_.put("datas", new Success(id, "ww"));
            return gson.toJson(_val_);
        } catch (Exception xc) {
            _val_.put("datas", new Fail(xc.getMessage(), "500"));

        }
        return gson.toJson(_val_);
    }

    @RequestMapping(value = "/checkTokens", method = RequestMethod.GET, produces = "application/json")
    String logins(@RequestHeader String token) throws Exception {
        TokenHandler tokens = new TokenHandler();
        String texte = "";
        Gson gson = new Gson();
        try {
            tokens = tokens.ToToken(token);

        } catch (Exception e) {
            texte = gson.toJson(new Message(new Fail("500", e.getMessage())));
        }
//        _val_.put("datas", new Success(200, "Ok"));
        texte = gson.toJson(new Message(new Success(200, "OK")));

        return texte;
    }

}
