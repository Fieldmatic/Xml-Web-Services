package rs.tim14.xml.rdf;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import rs.tim14.xml.util.AuthenticationUtilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class FusekiReader {

	private static final String GRAPH_URI = "zahtevi_za_priznanje_ziga";

	public static void main(String[] args) throws Exception {
		run(AuthenticationUtilities.loadProperties());
	}

	public static void run(AuthenticationUtilities.ConnectionProperties conn) throws IOException {
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String varName;
		RDFNode varValue;
		
		while(results.hasNext()) {
			QuerySolution querySolution = results.next();
			Iterator<String> variableBindings = querySolution.varNames();
			while (variableBindings.hasNext()) {

				varName = variableBindings.next();
				varValue = querySolution.get(varName);

				System.out.println(varName + ": " + varValue);
			}
			System.out.println();
		}
		results = query.execSelect();

		ResultSetFormatter.outputAsXML(System.out, results);

		query.close();

	}

	public static String readMetadata(String sparqlQueryCondition, String type) throws Exception {
		AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		System.out.println("[INFO] Selecting the triples from the named graph \"" + GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.constructData(conn.dataEndpoint + "/" + GRAPH_URI, sparqlQueryCondition);
		System.out.println(sparqlQuery);
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		Model model = query.execConstruct();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, type);
		return out.toString();
	}

}
