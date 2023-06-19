package rs.tim14.xml.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.stereotype.Repository;

import rs.tim14.xml.util.AuthenticationUtilities;

@Repository
public class MetadataRepository {
	String namespace = "http://www.ftn.uns.ac.rs/rdf/patent/";

	AuthenticationUtilities.ConnectionPropertiesFuseki conn;

	public MetadataRepository() {
		conn = AuthenticationUtilities.setUpPropertiesFuseki();
	}

	public List<String> executeSparqlQuery(String sparqlQuery) {

		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();

		String varName;
		RDFNode varValue;
		List<String> values = new ArrayList<>();

		while (results.hasNext()) {

			// A single answer from a SELECT query
			QuerySolution querySolution = results.next();
			Iterator<String> variableBindings = querySolution.varNames();

			// Retrieve variable bindings
			while (variableBindings.hasNext()) {

				varName = variableBindings.next();
				varValue = querySolution.get(varName);
				values.add(varValue.toString().replace(namespace, ""));
			}
		}

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		results = query.execSelect();
		ResultSetFormatter.out(System.out, results);

		query.close();
		return values;
	}
}
