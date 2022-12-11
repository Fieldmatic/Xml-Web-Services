package rs.tim14.xml;

import javax.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;

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
			jaxbParser.marshall(zahtev_p1, "rs.tim14.xml.model.zahtev_za_priznanje_patenta");
			jaxbParser.marshall(zahtev_z1, "rs.tim14.xml.model.zahtev_za_priznanje_ziga");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
