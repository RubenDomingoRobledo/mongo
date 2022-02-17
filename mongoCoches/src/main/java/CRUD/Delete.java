package CRUD;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class Delete {

	public static void main(String[] args) {
		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase reparacion = client.getDatabase("Reparacion");
		MongoCollection<Document> coches = reparacion.getCollection("Coche");

		//Borramos coche a partir de matricula
		Bson filter = eq("Matricula", "17789654X");
		DeleteResult result = coches.deleteOne(filter);
		System.out.println(result);
		System.out.println("Coche borrado\n");

		//Mostramos el coche y borramos
		filter = eq("Matricula", "84123006H");
		Document doc = coches.findOneAndDelete(filter);
		System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));

		//Borramos todos los coches que cumplan el filtro
		filter = gte("Altura", 2.50);
		result = coches.deleteMany(filter);
		System.out.println("\n"+result);
		System.out.println("Coches borrados\n");

		//Borramos todos los coches
		coches.drop();
		System.out.println("Todos los coches han sido borrados");
	}
}
