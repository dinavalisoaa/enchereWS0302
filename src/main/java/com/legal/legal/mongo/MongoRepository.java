package com.legal.legal.mongo;

import BddObject.ObjectBDD;
import com.mongodb.client.FindIterable;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import java.sql.Timestamp;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import model.Enchere;
import model.EnchereClose;

public class MongoRepository extends ObjectBDD {
//    @Overrid/e

    public void Create(EnchereClose iray) throws Exception {
        try {
            MongoDatabase database = MongoConnexion.getMongoConnection();
            MongoCollection<Document> collection = database.getCollection("enchereclose");

            Document document = new Document();
//            document.append("enchere", iray.getEnchere());
            document.append("dateClose", iray.getDateClose());
//            document.append("gagnant", iray.getEnchere().getGagnant());
            collection.insertOne(document);

//filtre.append("datefin", Timestamp.valueOf(localDateTime).toString());
//            System.out.println(filtre.toJson());
//            filtre.append("prixdepart", this.getPrixDepart()).append("description", this.getDescription()).append("Nom",this.getNom());
//            System.out.println(filtre.toJson());
//            filtre.append("Categorie", ca.getDesignation());
            System.out.println(document.toJson());
//            this.setEncherir(new ArrayList<>());
//            filtre.append("encherir",this.getEncherir());
//            collection.insertOne(filtre);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int List() throws Exception {

        MongoDatabase database = MongoConnexion.getMongoConnection();
        MongoCollection<Document> collection = database.getCollection("enchereclose");
        FindIterable<Document> iterDoc = collection.find();
//        Array
//return collection.find().
Document vaova=new Document();
//ArrayL
        Iterator it = iterDoc.iterator();
//        it.
int o=0;
        while (it.hasNext()) {
            System.out.println(it.next());
//            vaova.put(it.next());
o++;
        }
return o; 
    }

    /*public Enchere[] getListeEnchere() throws Exception {
        MongoDatabase database = Connexion.getMongoConnection();
        MongoCollection<Document> collection = database.getCollection("Enchere");
        ArrayList<Enchere> le=new ArrayList<Enchere>();
        for(Document c:collection.find()){
            Enchere e=new Enchere();
            e.setDate();
        }
    }*/
}
