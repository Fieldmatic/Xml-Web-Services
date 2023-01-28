package rs.tim14.xml.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Service;
import rs.tim14.xml.util.AuthenticationUtilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FusekiWriter {
	private AuthenticationUtilities.ConnectionProperties conn;

	public FusekiWriter() throws IOException {
	 this.conn = AuthenticationUtilities.loadProperties();
	}

	public void saveRdf(ByteArrayInputStream rdfTriples, String GRAPH_URI) throws IOException {

		Model model = ModelFactory.createDefaultModel();
		model.read(rdfTriples, "");

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);
		model.write(System.out, SparqlUtil.RDF_XML);

		UpdateProcessor processor;
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + GRAPH_URI, out.toString());
		System.out.println(sparqlUpdate);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);
		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}

}
