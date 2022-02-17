package CRUD;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.descending;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Read {

	public static void main(String[] args) {
		MongoClient client = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase reparacion = client.getDatabase("Reparacion");
		MongoCollection<Document> coches = reparacion.getCollection("Coche");

		//Mostrar coche a partir de matricula
		Document coche1 = coches.find(new Document("Matricula", "84123006H")).first();
		System.out.println("Coche 1: " + coche1.toJson());

		//Mostrar coche a partir de operador
		Document coche2 = coches.find(eq("Matricula", "74596312A")).first();
		System.out.println("\nCoche 2: " + coche2.toJson());

		//Mostrar lista de coches a partir de iterador
		FindIterable<Document> iterable = coches.find(gte("Altura", 2.20));
		MongoCursor<Document> cursor = iterable.iterator();
		System.out.println("\nLista de coches de mas de 2 metros de altura: ");
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}

		//Mostrar lista de coches a recorriendo arraylist
		List<Document> listaCoches = coches.find(gte("Altura", 2.20)).into(new ArrayList<>());
		System.out.println("\nLista de coches de mas de 2 metros de altura: ");
		for (Document coche : listaCoches) {
			System.out.println(coche.toJson());
		}

		//Mostrar lista de coches usando un consumer
		System.out.println("\nCoches uasndo un Consumer:");
		Consumer<Document> consumer = document -> System.out.println(document.toJson());
		coches.find(gte("Altura", 2.20)).forEach(consumer);

		//Coches con varios filtros
		List<Document> docs = coches.find(and(eq("Airbag", true), lte("Altura", 2.50)))
				.projection(fields(excludeId(), include("Altura", "Airbag"))).sort(descending("Altura")).skip(2)
				.limit(2).into(new ArrayList<>());
		System.out.println("\nCoche con filtros: ");
		for (Document coche : docs) {
			System.out.println(coche.toJson());
		}
	}
}
