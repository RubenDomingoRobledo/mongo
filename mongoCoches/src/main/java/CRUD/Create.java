package CRUD;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

public class Create {

	public static void main(String[] args) {
		MongoClient client = MongoClients.create("mongodb://localhost:27017");

		MongoDatabase reparacion = client.getDatabase("Reparacion");
		MongoCollection<Document> coches = reparacion.getCollection("Coche");

		insertOneDocument(coches);
		insertManyDocuments(coches);
	}

	//Metodo para insertar un coche
	private static void insertOneDocument(MongoCollection<Document> coches) {
		coches.insertOne(generateNewCoche("57542698O", "Ferrari", "K47", 2.12, 1.36, 627, "Veloz como el viento", 7, true));
		System.out.println("Coche insertado");
	}

	//Metodo para insertar varios coches
	private static void insertManyDocuments(MongoCollection<Document> coches) {
		List<Document> listacoches = new ArrayList<>();
		listacoches.add(generateNewCoche("74596312A", "Wolswagen", "R500", 2.73, 2.00, 548, "Mejora la conduccion", 6, false));
		listacoches.add(generateNewCoche("84123006H", "Seat", "Toledo", 1.93, 1.85, 280, "Coche de Alquiler", 5, true));
		listacoches.add(generateNewCoche("17789654X", "Porche", "BHA", 2.36, 1.72, 427, "Marca la diferencia", 7, true));

		coches.insertMany(listacoches, new InsertManyOptions().ordered(true));
		System.out.println("Coches insertados");
	}

	//Metodo para generar coche nuevo
	private static Document generateNewCoche(String Matricula, String Marca, String Modelo, Double Altura, Double Anchura, int PotenciaMax, String Descripcion, int NumeroAsientos, Boolean Airbag) {
		List<Document> detallesTecnicos = asList(new Document("Potencia Maxima", PotenciaMax)
				.append("Descripcion", Descripcion)
				.append("Numero Asientos", NumeroAsientos));
		
		return new Document("Coche", new ObjectId()).append("Matricula", Matricula).append("Marca", Marca)
				.append("Modelo", Modelo).append("Altura", Altura)
				.append("Anchura", Anchura).append("Detalles Tecnicos", detallesTecnicos)
				.append("Airbag", Airbag);
	}
}
