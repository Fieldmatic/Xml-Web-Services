package rs.tim14.xml.rdf;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import rs.tim14.xml.util.AuthenticationUtilities;

import java.io.IOException;
import java.util.Iterator;

public class FusekiReader {

	private static final String PATENT_GRAPH_URI = "/metadata";

	
	public static void main(String[] args) throws Exception {
		run(AuthenticationUtilities.loadProperties());
	}

	public static void run(AuthenticationUtilities.ConnectionProperties conn) throws IOException {
		System.out.println("[INFO] Selecting the triples from the named graph \"" + PATENT_GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + PATENT_GRAPH_URI, "?s ?p ?o");

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String varName;
		RDFNode varValue;
		
		while(results.hasNext()) {
			QuerySolution querySolution = results.next() ;
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
		
		query.close() ;
		
		System.out.println("[INFO] End.");
	}

}
