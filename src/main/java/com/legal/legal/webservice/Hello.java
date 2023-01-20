//package com.legal.legal.webservice;
//
//import com.google.gson.Gson;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.HashMap;
//import model.*;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import utils.*;
//
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@RestController
//@CrossOrigin
//public class Hello {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Hello.class, args);
//        
//        
//    }
//
//    @GetMapping("test")
//    String Create() throws Exception {
//        Gson gson = new Gson();
//        String texte = "\n";// gson.toJson(new Message(new Success(idKilo, "Success")));
//       Enchere vin=new Enchere();
//            vin.setId(1);
//            System.out.println("FIN::"+vin.getEnchere().getFin());
//        return Boolean.toString(vin.isExpirer()) +texte+vin.getEnchere().getFin();
//    }
//}
