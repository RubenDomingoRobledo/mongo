package CRUD;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Connection {

    public static void main(String[] args) {
    	//Mostrar bases de datos
    	MongoClient client = MongoClients.create("mongodb://localhost:27017");
    	List<Document> databases = client.listDatabases().into(new ArrayList<>());
        databases.forEach(db -> System.out.println(db.toJson()));	
    }
}
