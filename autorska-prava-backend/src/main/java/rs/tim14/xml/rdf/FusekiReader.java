package rs.tim14.xml.rdf;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import rs.tim14.xml.util.AuthenticationUtilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class FusekiReader {

	private static final String GRAPH_URI = "zahtevi_za_autorska_prava";
	private static final String SUBJECT_TRIPLET_PART = "<http://www.ftn.uns.ac.rs/rdf/a1/";



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

	public static String getJsonString(String id) throws Exception {
		ResultSet results = getRDFById(id);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(outputStream, results);

		return outputStream.toString();
	}

	private static ResultSet getRDFById(String id) throws IOException {
		String whereQueryById = createWhereQueryByIdPart(id);
		ResultSetRewindable results = select(whereQueryById);
		ResultSetFormatter.out(System.out, results);
		results.reset();

		return results;
	}

	private static String createWhereQueryByIdPart(String id) {
		String whereStr = "?s ?p ?o "; //.concat(createPredicateIdTripletQueryPart());
		String filterStr = "FILTER ( ?s = ".concat(SUBJECT_TRIPLET_PART).concat(id).concat("> )");
		whereStr = whereStr.concat(filterStr);

		return whereStr;
	}

	private static ResultSetRewindable select(String whereQueryPart) throws IOException {
		AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, whereQueryPart);
		System.out.println(sparqlQuery);
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet r = query.execSelect();
		ResultSetRewindable results = ResultSetFactory.copyResults(r);
		query.close();

		return results;
	}

}
