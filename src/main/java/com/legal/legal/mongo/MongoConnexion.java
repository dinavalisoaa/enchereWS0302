/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legal.legal.mongo;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author karen
 */
public class MongoConnexion {
    public static Connection getConnection() throws Exception {
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/enchere",
                "postgres", "karen");
        return con;
    }

//    public static MongoDatabase getMongoConnection() throws Exception {
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://karen:karen@clusterka.agwfuv7.mongodb.net/?retryWrites=true&w=majority");
//        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
//        MongoClient mongoClient = MongoClients.create(settings);
//        MongoDatabase database = mongoClient.getDatabase("EnchereTest");
//        for (String collectionName : database.listCollectionNames()) {
//            System.out.println(collectionName);
//        }
//        return database;
//    }

     public static MongoDatabase getMongoConnection() throws Exception {
	    	MongoDatabase database= null;
	    	try {
			
//	    	ConnectionString connectionString = new ConnectionString("mongodb+srv://ericonomena:ericonomena@enchere.qf9jcj9.mongodb.net/?retryWrites=true&w=majority");
	    	
	        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017/?retryWrites=true&w=majority");
	        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
	        MongoClient mongoClient = MongoClients.create(settings);
	        System.out.println("mongo client before database");
	        database = mongoClient.getDatabase("enchere");
//	        database.createCollection("enchere");
//	        for (String collectionName : database.listCollectionNames()) {
//	            System.out.println(collectionName);
//	        }
	   	} catch (Exception e) {
	   		e.printStackTrace();
			// TODO: handle exception
		}
	        return database;
	    }
}
