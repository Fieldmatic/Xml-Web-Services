package rs.tim14.xml;

import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Objects;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.TFormaZapisa;
import rs.tim14.xml.model.autorska_prava.TVrstaAutorskogDela;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava.AutorskoDelo;
import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.util.MyDatatypeConverter;

@SpringBootApplication
public class Tim14Application {

	public static void main(String[] args) throws JAXBException, SAXException {
		SpringApplication.run(Tim14Application.class, args);
		try {

			JaxbParser jaxbParser = new JaxbParser();
			ZahtevZaAutorskaPrava zahtev_a1 = jaxbParser.unmarshall("./data/a1-primer1.xml", "rs.tim14.xml.model.autorska_prava", "./data/a-1.xsd");
			ZahtevZaPriznanjePatenta zahtev_p1 = jaxbParser.unmarshall("./data/p1-primer1.xml", "rs.tim14.xml.model.zahtev_za_priznanje_patenta", "./data/p-1.xsd");
			ZahtevZaPriznanjeZiga zahtev_z1 = jaxbParser.unmarshall("./data/z1-primer1.xml", "rs.tim14.xml.model.zahtev_za_priznanje_ziga", "./data/z-1.xsd");

			System.out.println(zahtev_p1);
			jaxbParser.marshall(kreirajA1Obrazac(), "rs.tim14.xml.model.autorska_prava");
			jaxbParser.marshall(zahtev_z1, "rs.tim14.xml.model.zahtev_za_priznanje_ziga");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static ZahtevZaAutorskaPrava kreirajA1Obrazac() throws DatatypeConfigurationException {
		ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = new ZahtevZaAutorskaPrava();

		TPravnoLice pravnoLice = new TPravnoLice();
		Adresa adresa = new Adresa();
		adresa.setMesto("Novi Sad");
		adresa.setBroj("10");
		adresa.setUlica("Dr Ivana Ribara");
		adresa.setPostanskiBroj("21000");
		pravnoLice.setAdresa(adresa);
		pravnoLice.setPoslovnoIme("REMIX");
		zahtevZaAutorskaPrava.setPodnosilac(pravnoLice);

		AutorskoDelo autorskoDelo = new AutorskoDelo();
		autorskoDelo.setFormaZapisa(TFormaZapisa.MUZICKA_PARTITURA);
		autorskoDelo.setNacinKoriscenjaAutorskogDela("dobro je");
		autorskoDelo.setVrstaAutorskogDela(TVrstaAutorskogDela.PATENTI);
		zahtevZaAutorskaPrava.setAutorskoDelo(autorskoDelo);

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(Objects.requireNonNull(MyDatatypeConverter.parseDate("1991-12-21")));
		zahtevZaAutorskaPrava.setDatumPodnosenja(DatatypeFactory.newInstance()
			.newXMLGregorianCalendar(gc));

		zahtevZaAutorskaPrava.setBrojPrijave(BigInteger.valueOf(100));

		return zahtevZaAutorskaPrava;
	}

}
