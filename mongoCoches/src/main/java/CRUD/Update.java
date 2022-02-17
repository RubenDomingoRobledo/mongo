package CRUD;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Updates.set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

public class Update {

	public static void main(String[] args) {
		JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase reparacion = client.getDatabase("Reparacion");
		MongoCollection<Document> coches = reparacion.getCollection("Coche");
		
		//Actualizar coche
		Bson filter = eq("Matricula", "84123006H");
		Bson updateOperation = set("Airbag", false);
		UpdateResult updateResult = coches.updateOne(filter, updateOperation);
		System.out.println("Actualizamos coche con matricula => 84123006H: ");
		System.out.println(coches.find(filter).first().toJson(prettyPrint));
		System.out.println(updateResult);

		//Upsert
		filter = and(eq("Marca", "Porche"), eq("Modelo", "BHA"));
		updateOperation = push("Descripcion", "Coche Modificado");
		UpdateOptions options = new UpdateOptions().upsert(true);
		updateResult = coches.updateOne(filter, updateOperation, options);
		System.out.println(
				"\nActualizamos coche a partir de la marca y el modelo: ");
		System.out.println(updateResult);
		System.out.println(coches.find(filter).first().toJson(prettyPrint));

		//Actualizamos varios coches
		filter = eq("Airbag", true);
		updateResult = coches.updateMany(filter, updateOperation);
		System.out.println("Actualizamos todos los coches: ");
		System.out.println(updateResult);
	}
}
