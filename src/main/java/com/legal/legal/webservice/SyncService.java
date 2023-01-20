package com.legal.legal.webservice;

//import DAO.ObjectBDD;
import BddObject.ObjectBDD;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.client.RestTemplate;
import utils.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
@CrossOrigin
public class SyncService {

// @RequestMapping("/user")
// @ResponseBody
// private String getUser() {
//  String uri = "http://192.168.43.221:8080/test";
//  RestTemplate restTemplate = new RestTemplate();
//  Users min=restTemplate.getForObject(uri, Users.class);
//  System.out.println("User: "+ min.getLargeur() );
//  return min.getNom();
// }
//    @PutMapping("/update/{id}")
//    void up(@PathVariable int id) throws Exception {
//        ObjectBDD.run("update tablesync where id=" + id);
//    }
//
//    @PostMapping("/sync")
//    String all(@RequestParam String pers, @RequestParam String ids) throws Exception {
//
//        Gson gson = new Gson();
//        String vaovao = pers.replace("`", "'");
//        HashMap _val_ = new HashMap<String, Object>();
//        System.out.println(ids);
//        ArrayList lists2 = new ArrayList();
////        
//        for (String string : ids.split(";;;")) {
////            System.out.println("****"+string);
//            lists2.add(string);
//        }
//        for (String string : vaovao.split(";;;")) {
////            System.out.println("SQL SQL():=>"+string);
////        
////        
//// ObjectBDD.run("");
//
//            ObjectBDD.run(string);
//        }
//        ArrayList lists = new ArrayList();
////        lists.add(tables);
//        _val_.put("datas", lists2);
//        _val_.put("list", lists);
//
//        System.out.println(gson.toJson(_val_));
//        return gson.toJson(_val_);
//    }
//
//    @RequestMapping(
//            value = "/test",
//            method = RequestMethod.GET,
//            headers = "Accept=application/json")
//    @ResponseBody
//    //    //HashMap<String, Object> alls() throws Exception {
//    String alls() throws Exception {
//        Gson gson = new Gson();
//        HashMap _val_ = new HashMap<String, Object>();
//        TableSync[] tables = TableSync.TableSyncs();
//
//        ArrayList lists2 = new ArrayList();
////        
//// for (String string : ids.split(";;")) {
////            System.out.println("****"+string);
////            lists2.add(string);
////        }
//
////        for (String string : vaovao.split(";;")) {
////            System.out.println("****"+string);
////        
////            ObjectBDD.run(string.split(";,")[0]);
////        }s
//        ArrayList lists = new ArrayList();
////        for (TableSync ins : tables)
////        {
////            lists.add(ins);
////        }
//        lists.add(tables);
////        Data dag=new Data();
//        // gson.fromJson
//        // dag.setData();
//        _val_.put("datas", lists2);
//        _val_.put("list", lists);
//
////        System.out.println("tp.SyncService.alls()" + lists);
////          _val_.put("list",lists.size());
//        for (TableSync ins : tables) {
//            TableSync vaovaos = new TableSync();
////            vaovaos.setId(ins.getId());
////            vaovaos.setEtat(1);
////            vaovaos.update("Id",null);
//        }
//        return gson.toJson(_val_);
//    }

//     @PutMapping("/vehicules/{id}/kilometrages/{idKilo}")
//     String upKilo(@PathVariable int id, @PathVariable int idKilo, @RequestParam double debut, @RequestParam double fin,
//             @RequestParam String daty) throws Exception {
//         Gson gson = new Gson();
//         String texte = "";
//         try {
//             Kilometrage one = new Kilometrage();
//             one.setVehiculeId(id);
//             one.setId(idKilo);
//             one.setFin(fin);
//             one.setDebut(debut);
//             one.setDaty(daty);
//             one.update("id", null);
//             texte = gson.toJson(new Message(new Success(idKilo, "Success")));
//         } catch (Exception ex) {
//             texte = gson.toJson(new Message(new Fail("500", "Error")));
//         }
//         return texte;
// //        one.
//     }
//     @PostMapping("/vehicules/{id}/kilometrages")
//     String newKilo(@PathVariable int id, @RequestParam double debut, @RequestParam double fin,
//             @RequestParam String daty) throws Exception {
//         Gson gson = new Gson();
//         String texte = "";
//         try {
//             Kilometrage one = new Kilometrage();
//             one.setVehiculeId(id);
//             one.setFin(fin);
//             one.setDebut(debut);
//             one.setDaty(daty);
//             one.insert(null);
//             texte = gson.toJson(new Message(new Success(((Kilometrage) one.getLastObject()).getId(), "Success")));
//         } catch (Exception ex) {
//             texte = gson.toJson(new Message(new Fail("500", "Error")));
//         }
//         return texte;
//     }
//     @DeleteMapping("/vehicules/{id}/kilometrages/{idkilo}")
//     void delKilo(@PathVariable int idkilo, @PathVariable int id) throws Exception {
//         Kilometrage one = new Kilometrage();
//         one.setId(idkilo);
//         one.setVehiculeId(id);
//         one.delete("id", null);
//     }
}
