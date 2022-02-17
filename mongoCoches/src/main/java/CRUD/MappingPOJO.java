package CRUD;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import POJOS.Coche;
import POJOS.DetallesTecnicos;

public class MappingPOJO {
	public static void main(String[] args) {
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();
		
		MongoClient mongoClient = MongoClients.create(clientSettings);
		MongoDatabase db = mongoClient.getDatabase("Reparacion");
		MongoCollection<Coche> coches = db.getCollection("Coche", Coche.class);

		Coche nuevoCoche = new Coche().setIdCoche(1).setMatricula("5468263P").setMarca("Audi").setModelo("q3").setAltura(2.42).setAnchura(1.01).setDetallesTecnicos(singletonList(new DetallesTecnicos().setPotenciaMaxima(220).setDescripcion("Coche rapido").setNumeroAsientos(5))).setAirbag(false);
		coches.insertOne(nuevoCoche);
		System.out.println("Coche insertado\n");
		
		Coche coche = coches.find(eq("idCoche", nuevoCoche.getIdCoche())).first();
		System.out.println("Coche encontrado:\t" + coche);
        
        List<DetallesTecnicos> nuevosDetalles = new ArrayList<>(coche.getDetallesTecnicos());
        nuevosDetalles.add(new DetallesTecnicos().setPotenciaMaxima(220).setDescripcion("Coche actualizado").setNumeroAsientos(5));
        coche.setDetallesTecnicos(nuevosDetalles);
        Document filterById = new Document("idCoche", coche.getIdCoche());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        Coche cocheActualizado = coches.findOneAndReplace(filterById, coche, returnDocAfterReplace);
        System.out.println("\nCoche actualizado:\t" + cocheActualizado);
        
        // delete this grade
        System.out.println(coches.deleteOne(filterById));
        System.out.println("\nCoche borrado");
	}

}
