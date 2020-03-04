/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 * Java basic MongoDB connection.
 * Conexión básica en Java a MongoDB.
 * @author xules
 */
public class JavaBasicMongodb {
    /**
     * Testing Java basic Mongodb connection.
     * Probando la conexión básica en Java a Mongodb.
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        MongoClientURI uri = new MongoClientURI(
    "mongodb+srv://admin:123@cluster0-4lfbe.mongodb.net/test?retryWrites=true&w=majority");

MongoClient mongoClient = new MongoClient(uri);
MongoDatabase database = mongoClient.getDatabase("test");
    }    
}