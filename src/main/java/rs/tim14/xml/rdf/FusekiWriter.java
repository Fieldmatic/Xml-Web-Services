package rs.tim14.xml.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import rs.tim14.xml.util.AuthenticationUtilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiWriter {

	private static final String PATENT_GRAPH_URI = "/metadata";

	
	public static void main(String[] args) throws Exception {
		run(AuthenticationUtilities.loadProperties());
	}

	public static void run(AuthenticationUtilities.ConnectionProperties conn) throws IOException {

		System.out.println("[INFO] Loading triples from an RDF/XML to a model...");

		String rdfFilePath = "data/rdf/p1.rdf";

		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		model.write(out, SparqlUtil.NTRIPLES);
		
		System.out.println("[INFO] Rendering model as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		UpdateRequest request = UpdateFactory.create() ;
        request.add(SparqlUtil.dropAll());

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        processor.execute();

     	System.out.println("[INFO] Writing the triples to a named graph \"" + PATENT_GRAPH_URI + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + PATENT_GRAPH_URI, out.toString());
		System.out.println(sparqlUpdate);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);
		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	
	    System.out.println("[INFO] End.");
	}

}
