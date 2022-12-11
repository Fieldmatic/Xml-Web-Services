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
import rs.tim14.xml.model.korisnici.PunoIme;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TVrstaZiga;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TVrstaZnaka;
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
			jaxbParser.marshall(kreirajZ1Obrazac(), "rs.tim14.xml.model.zahtev_za_priznanje_ziga");
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

	public static ZahtevZaPriznanjeZiga kreirajZ1Obrazac() throws DatatypeConfigurationException {
		ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = new ZahtevZaPriznanjeZiga();

		TPravnoLice podnosilac = new TPravnoLice();
		Adresa adresa1 = new Adresa();
		adresa1.setDrzava("Srbija");
		adresa1.setMesto("Novi Sad");
		adresa1.setBroj("11");
		adresa1.setUlica("Bulevar cara Lazara");
		adresa1.setPostanskiBroj("21000");
		podnosilac.setAdresa(adresa1);
		podnosilac.setPoslovnoIme("Poslovno ime");
		zahtevZaPriznanjeZiga.setPodnosilac(podnosilac);

		TFizickoLice punomocnik = new TFizickoLice();
		Adresa adresa2 = new Adresa();
		adresa2.setDrzava("Srbija");
		adresa2.setMesto("Subotica");
		adresa2.setBroj("13");
		adresa2.setUlica("Praška");
		adresa2.setPostanskiBroj("24000");
		punomocnik.setAdresa(adresa2);
		PunoIme punoIme1 = new PunoIme();
		punoIme1.setIme("Uroš");
		punoIme1.setIme("Prijović");
		punomocnik.setPunoIme(punoIme1);
		zahtevZaPriznanjeZiga.setPunomocnik(punomocnik);

		TFizickoLice zajednickiPredstavnik = new TFizickoLice();
		Adresa adresa3 = new Adresa();
		adresa3.setDrzava("Srbija");
		adresa3.setMesto("Subotica");
		adresa3.setBroj("29");
		adresa3.setUlica("Blaška Rajića");
		adresa3.setPostanskiBroj("24000");
		zajednickiPredstavnik.setAdresa(adresa3);
		PunoIme punoIme2 = new PunoIme();
		punoIme2.setIme("Krstina");
		punoIme2.setIme("Karlaš");
		zajednickiPredstavnik.setPunoIme(punoIme2);
		zahtevZaPriznanjeZiga.setZajednickiPredstavnik(zajednickiPredstavnik);

		zahtevZaPriznanjeZiga.setVrstaZiga(TVrstaZiga.ZIG_GARANCIJE);

		ZahtevZaPriznanjeZiga.Znak znak = new ZahtevZaPriznanjeZiga.Znak();
		ZahtevZaPriznanjeZiga.Znak.VrstaZnaka vrstaZnaka = new ZahtevZaPriznanjeZiga.Znak.VrstaZnaka();
		vrstaZnaka.setVrstaZnaka(TVrstaZnaka.TRODIMENZIONALNI);
		znak.setVrstaZnaka(vrstaZnaka);

		znak.setIzgledZnaka("Putanja do izgleda znaka");
		znak.setPrevodZnaka("Prevod znaka");
		znak.setTransliteracijaZnaka("Transliteracija znaka");
		znak.setOpisZnaka("Opis znaka");
		znak.getBojeZnaka().add("crvena");
		znak.getBojeZnaka().add("plava");
		znak.getBojeZnaka().add("bela");

		zahtevZaPriznanjeZiga.setZnak(znak);

		zahtevZaPriznanjeZiga.getKlaseRobeIliUsluge().add(1);

		ZahtevZaPriznanjeZiga.PravoPrvenstva pravoPrvenstva = new ZahtevZaPriznanjeZiga.PravoPrvenstva();
		pravoPrvenstva.setZatrazenoPravoPrvenstva(false);
		zahtevZaPriznanjeZiga.setPravoPrvenstva(pravoPrvenstva);

		ZahtevZaPriznanjeZiga.Takse takse = new ZahtevZaPriznanjeZiga.Takse();
		takse.setOsnovnaTaksa(1000);
		takse.setTaksaZaGrafickoResenje(5000);
		takse.setTaksaZaKlase(500);
		takse.setUkupnaVrednost(6500);
		zahtevZaPriznanjeZiga.setTakse(takse);

		zahtevZaPriznanjeZiga.setBrojPrijave(BigInteger.valueOf(2));
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(Objects.requireNonNull(MyDatatypeConverter.parseDate("2022-12-13")));
		zahtevZaPriznanjeZiga.setDatumPodnosenja(DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gc));

		return zahtevZaPriznanjeZiga;
	}

}
