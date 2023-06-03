package rs.tim14.xml.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import lombok.RequiredArgsConstructor;
import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.PodaciOAutorima;
import rs.tim14.xml.model.autorska_prava.TStatusZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.rdf.FusekiReader;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.AutorskaPravaRepository;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xslfo.XSLFOTransformer;

@Service
@RequiredArgsConstructor
public class AutorskaPravaService {
	private final MetadataExtractor metadataExtractor;
	private final FusekiWriter fusekiWriter;

	private final AutorskaPravaRepository autorskaPravaRepository;

	private final JaxbParser jaxbParser;

	public ZahtevZaAutorskaPrava create(final ZahtevZaAutorskaPrava zahtev) throws Exception {
		String id = Util.getNextId();
		zahtev.setIdZahteva(id);
		zahtev.setStatusZahteva(TStatusZahteva.PODNET);
		zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
		zahtev.setAbout("http://www.ftn.uns.ac.rs/rdf/a1/" + id);

		id = id.concat(".xml");
		zahtev.getPrijava().getDatumPodnosenja().setValue(Util.getXMLGregorianCalendarCurrentDate());

		zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
		zahtev.getPrijava().getBrojPrijave().setDatatype("xs:string");

		zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
		zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:string");

		for (final PodaciOAutorima.TAutor autor : zahtev.getAutorskoDelo().getPodaciOAutoru().getAutor()) {
			autor.getLicniPodaci().getPunoIme().getIme().setProperty("pred:imeAutora");
			autor.getLicniPodaci().getPunoIme().getIme().setDatatype("xs:string");
			autor.getLicniPodaci().getPunoIme().getPrezime().setProperty("pred:prezimeAutora");
			autor.getLicniPodaci().getPunoIme().getPrezime().setDatatype("xs:string");
		}

		zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/predicate/");
		zahtev.getReferences().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

		final TLice podnosilac = zahtev.getPodnosilac();
		if (podnosilac instanceof TFizickoLice) {
			((TFizickoLice) podnosilac).getPunoIme().getIme().setProperty("pred:imePodnosioca");
			((TFizickoLice) podnosilac).getPunoIme().getIme().setDatatype("xs:string");
			((TFizickoLice) podnosilac).getPunoIme().getPrezime().setProperty("pred:prezimePodnosioca");
			((TFizickoLice) podnosilac).getPunoIme().getPrezime().setDatatype("xs:string");
		} else {
			((TPravnoLice) podnosilac).getPoslovnoIme().setProperty("pred:poslovnoImePodnosioca");
			((TPravnoLice) podnosilac).getPoslovnoIme().setDatatype("xs:string");
		}

		final OutputStream os = jaxbParser.marshall(zahtev, "./autorska-prava-backend/data/a-1.xsd");
		autorskaPravaRepository.save(id, os);

		final XMLResource resource = autorskaPravaRepository.load(id);
		final byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
		fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_autorska_prava");
		return zahtev;
	}

	public List<ZahtevZaAutorskaPrava> dobaviPoTekstu(final List<String> filteri)
		throws XMLDBException, IOException {
		return autorskaPravaRepository.dobaviPoTekstu(filteri);
	}

	public List<ZahtevZaAutorskaPrava> getAll() {
		return autorskaPravaRepository.getAll();
	}

	public ZahtevZaAutorskaPrava getById(final String id) throws XMLDBException {
		return autorskaPravaRepository.getById(id);
	}

	public byte[] getHTML(final String id) throws Exception {
		final String xmlPath = autorskaPravaRepository.getPath(id);
		final String resultPath = "./autorska-prava-backend/data/result/" + id + ".html";
		final HTMLTransformer htmlTransformer = new HTMLTransformer();
		htmlTransformer.generateHTML(xmlPath, "./autorska-prava-backend/data/xsl/a1.xsl", resultPath);
		return FileUtils.readFileToByteArray(new File(resultPath));
	}

	public byte[] getPDF(final String id) throws Exception {
		final String xmlPath = autorskaPravaRepository.getPath(id);
		final String resultPath = "./autorska-prava-backend/data/result/" + id + ".pdf";
		final XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
		xslfoTransformer.generatePDF(xmlPath, "./autorska-prava-backend/data/xsl_fo/a1_fo.xsl", resultPath);
		return FileUtils.readFileToByteArray(new File(resultPath));
	}

	public String getRDF(final String id) {
		try {
			final String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/a1/" + id + "> ?d ?s .";
			return FusekiReader.readMetadata(sparqlCondition, "N-TRIPLE");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getJSON(final String id) {
		try {
			final String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/a1/" + id + "> ?d ?s .";
			return FusekiReader.readMetadata(sparqlCondition, "RDF/JSON");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
