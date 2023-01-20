//package com.legal.legal.webservice;
//
//import java.util.ArrayList;
//import java.util.List;
//import model.EnchereClose;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
////import com.enchere.enchere.model.EnchereClose;
//
//@Repository
//public class HistoriqueRepository {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public List<EnchereClose> getHistorique(int etatActuelle, int produitid, int utilisateurid) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("etatActuelle").is(etatActuelle).where("produitid").is(produitid)
//                .where("utilisateuridacheteur").is(utilisateurid));
//        return mongoTemplate.find(query, EnchereClose.class);
//    }
//
//    public void UpdateHisto(int idproduit) {
//        // mongoTemplate
//
//        Query query = new Query(Criteria.where("produitid").is(idproduit));
//        Update update = new Update().set("etatactuelle", 0);
//        mongoTemplate.updateFirst(query, update, HistoriqueRepository.class);
//    }
//
//    public EnchereClose getDernierHistorique(int idproduit) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("etatActuelle").is(1).where("produitid").is(idproduit));
//        return mongoTemplate.find(query, EnchereClose.class).get(0);
//    }
//
//    public List<EnchereClose> getHistoriqueByUtil(int idutilisateur) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("utilisateuridacheteur").is(idutilisateur));
//        return mongoTemplate.find(query, EnchereClose.class);
//    }
//
//    public void InsertHistorique(EnchereClose histo) {
//        mongoTemplate.insert(histo);
//    }
//
//    public ArrayList<EnchereClose> ToArrayList(List<EnchereClose> histo) {
//        ArrayList<EnchereClose> array = new ArrayList<>();
//        for (int i = 0; i < histo.size(); i++) {
//            array.add(histo.get(i));
//        }
//        return array;
//    }
//
//}
