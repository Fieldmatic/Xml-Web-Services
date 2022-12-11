package rs.tim14.xml.test;

import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Objects;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.xml.sax.SAXException;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.PunoIme;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TVrstaZiga;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TVrstaZnaka;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.util.MyDatatypeConverter;

public class Z1Test {
	public void test() {
		try {
			JaxbParser jaxbParser = new JaxbParser();
			ZahtevZaPriznanjeZiga zahtev_z1 = jaxbParser.unmarshall("./data/z1-primer1.xml", "rs.tim14.xml.model.zahtev_za_priznanje_ziga", "./data/z-1.xsd");
			System.out.println(zahtev_z1);
			jaxbParser.marshall(kreirajZ1Obrazac(), "rs.tim14.xml.model.zahtev_za_priznanje_ziga");
		} catch (JAXBException | DatatypeConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	private static ZahtevZaPriznanjeZiga kreirajZ1Obrazac() throws DatatypeConfigurationException {
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

	public static void main(String[] args) {
		Z1Test test = new Z1Test();
		test.test();
	}
}
