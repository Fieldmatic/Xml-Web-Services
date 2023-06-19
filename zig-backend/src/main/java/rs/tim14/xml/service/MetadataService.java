package rs.tim14.xml.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import lombok.RequiredArgsConstructor;
import rs.tim14.xml.dto.requests.NaprednaPretragaRequest;
import rs.tim14.xml.dto.requests.NaprednaPretragaTriplet;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.repository.MetadataRepository;
import rs.tim14.xml.repository.ZigRepository;

@Service
@RequiredArgsConstructor
public class MetadataService {
	public final MetadataRepository metadataRepository;

	public final ZigRepository zigRepository;

	String graphName = "<http://localhost:3030/XmlDataSet/data/zahtevi_za_priznanje_ziga>";
	String namespace = "http://www.ftn.uns.ac.rs/predicate/";

	public List<ZahtevZaPriznanjeZiga> dobaviPoMetapodacima(NaprednaPretragaRequest request) throws Exception {
		String query = this.buildMetaSearchQuery(request, graphName, "select");
		List<String> ids = metadataRepository.executeSparqlQuery(query);
		return zigRepository.getByIds(ids);
	}

	public String buildMetaSearchQuery(NaprednaPretragaRequest request, String graphName, String queryType) {
		StringBuilder query = new StringBuilder();

		query.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n");
		query.append(queryType + " ?subject FROM ").append(graphName).append(" \nWHERE {  \n");
		query.append("\t?subject ?predicate ?object . \n");
		for (NaprednaPretragaTriplet triplet : request.getMetadataTripleti()) {
			query.append("\t?subject <").append(namespace).append(triplet.getPredikat()).append("> ?").append(triplet.getPredikat()).append(" . \n");
		}
		query.append("\tFILTER (");

		List<NaprednaPretragaTriplet> parametri = request.getMetadataTripleti();
		for (int i = 0; i < parametri.size(); i++) {
			NaprednaPretragaTriplet triplet = parametri.get(i);
			String naziv = triplet.getPredikat();
			String vrednost = triplet.getObjekat();
			String operator = dobaviOperator(triplet.getOperator());

			if(operator.equals("!")){
				if (i != 0) query.append(" && ");
				query.append(String.format("!CONTAINS(?%s,'%s')", naziv, vrednost));
			}
			else if(operator.equals("&&") || operator.equals("||")){
				query.append(String.format(" %s CONTAINS(?%s,'%s')", operator, naziv, vrednost));
			}
			else{
				query.append(String.format("CONTAINS(?%s,'%s')", naziv, vrednost));
			}
		}
		query.append(") \n}\n");
		query.append("GROUP BY ?subject");
		return query.toString();
	}

	private String dobaviOperator(String operator){
		switch (operator) {
		case "I":
			return "&&";
		case "ILI":
			return  "||";
		case "NE":
			return "!";
		default:
			return "?";
		}
	}
}
