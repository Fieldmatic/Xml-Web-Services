package rs.tim14.xml.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;

import org.xml.sax.SAXException;

import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.*;
import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.PunoIme;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.xslfo.XSLFOTransformer;

public class A1Test {

	public void test() {
		try {
			JaxbParser jaxbParser = new JaxbParser();
			ZahtevZaAutorskaPrava zahtev_a1 = jaxbParser.unmarshall("./data/a1-primer1.xml", "rs.tim14.xml.model.autorska_prava", "./data/a-1.xsd");
			System.out.println(zahtev_a1);
			System.out.println();
			jaxbParser.marshall(kreirajA1(), "rs.tim14.xml.model.autorska_prava");

			final String inputFilePath = "data/a1-primer1.xml";
			final String outputFilePath = "data/result/a1";
			HTMLTransformer htmlTransformer = new HTMLTransformer();
			final String xslFilePath = "data/xsl/a1.xsl";

			htmlTransformer.generateHTML(inputFilePath, xslFilePath, outputFilePath + ".html");

			XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
			final String xslfoFilePath = "data/xsl_fo/a1_fo.xsl";

			xslfoTransformer.generatePDF(inputFilePath, xslfoFilePath, outputFilePath + ".pdf");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ZahtevZaAutorskaPrava kreirajA1() throws DatatypeConfigurationException {
		ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = new ZahtevZaAutorskaPrava();

		zahtevZaAutorskaPrava.setDatumPodnosenja(new Date());
		zahtevZaAutorskaPrava.setBrojPrijave(BigInteger.valueOf(100));
		zahtevZaAutorskaPrava.setPodnosilac(kreirajPravnoLice());
		zahtevZaAutorskaPrava.setPunomocnik(kreirajPunomocnika());
		zahtevZaAutorskaPrava.setAutorskoDelo(kreirajAutorskoDelo());

		return zahtevZaAutorskaPrava;
	}

	public static TPravnoLice kreirajPravnoLice() {
		TPravnoLice pravnoLice = new TPravnoLice();
		pravnoLice.setAdresa(kreirajAdresu());
		pravnoLice.setBrojMobilnogTelefona(new JAXBElement<>(new QName("http://www.xml.tim14.rs/korisnici", "broj_mobilnog_telefona", "ks"), String.class, "065684500"));
		pravnoLice.setEmail(new JAXBElement<>(new QName("http://www.xml.tim14.rs/korisnici", "email", "ks"), String.class, "pravnoLice@gmail.com"));
		pravnoLice.setPoslovnoIme("REMIX");
		return pravnoLice;
	}

	public static Punomocnik kreirajPunomocnika() {
		Punomocnik punomocnik = new Punomocnik();
		punomocnik.setAdresa(kreirajAdresu());
		punomocnik.setPunoIme(kreirajPunoIme());
		return punomocnik;
	}

	public static AutorskoDelo kreirajAutorskoDelo() {
		AutorskoDelo autorskoDelo = new AutorskoDelo();
		autorskoDelo.setNaslovAutorskogDela("Sava i Dunav");
		autorskoDelo.setFormaZapisa(TFormaZapisa.MUZICKA_PARTITURA);
		autorskoDelo.setNacinKoriscenjaAutorskogDela("da se koristi");
		autorskoDelo.setVrstaAutorskogDela(TVrstaAutorskogDela.MUZICKO_DELO);
		autorskoDelo.setStvorenoURadnomOdnosu(true);
		autorskoDelo.setPrimerAutorskogDela(kreirajPrimerAutorskogDela());
		return autorskoDelo;
	}

	public static PrimerAutorskogDela kreirajPrimerAutorskogDela() {
		PrimerAutorskogDela primerAutorskogDela = new PrimerAutorskogDela();
		primerAutorskogDela.setPutanjaDoPrimera("../data/primer-a1");
		primerAutorskogDela.setTipPrimera(TTipPrimera.AUDIO_ZAPIS);
		return primerAutorskogDela;
	}

	public static PunoIme kreirajPunoIme() {
		PunoIme punoIme = new PunoIme();
		punoIme.setIme("Mile");
		punoIme.setPrezime("Stevanovic");
		return punoIme;
	}

	public static Adresa kreirajAdresu() {
		Adresa adresa = new Adresa();
		adresa.setMesto("Novi Sad");
		adresa.setBroj("10");
		adresa.setUlica("Dr Ivana Ribara");
		adresa.setPostanskiBroj("21000");
		return adresa;
	}

	public static void main(String[] args) {
		A1Test test = new A1Test();
		test.test();
	}
}
