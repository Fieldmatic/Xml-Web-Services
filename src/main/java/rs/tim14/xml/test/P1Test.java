package rs.tim14.xml.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.xml.sax.SAXException;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.PodaciODostavljanju;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.PodaciOPodnosiocu;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.PodaciOPronalazacu;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.PodaciOPunomocniku;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.Pronalazak;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.TNacinDostavljanja;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.TPrijava;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.TTipPunomocnika;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.RanijePrijave;

public class P1Test {
//	public void test() {
//		try {
//			JaxbParser jaxbParser = new JaxbParser();
//			ZahtevZaPriznanjePatenta zahtev_p1 = jaxbParser.unmarshall("./data/p1-primer1.xml", "rs.tim14.xml.model.zahtev_za_priznanje_patenta", "./data/p-1.xsd");
//			System.out.println(zahtev_p1);
//			System.out.println();
//		//	jaxbParser.marshall(kreirajP1(), "rs.tim14.xml.model.zahtev_za_priznanje_patenta");
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		} catch (SAXException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private ZahtevZaPriznanjePatenta kreirajP1() {
//		ZahtevZaPriznanjePatenta p1 = new ZahtevZaPriznanjePatenta();
//
//		p1.setPrijava(kreirajPrijavu1());
//		p1.setPronalazak(kreirajPronalazak());
//		p1.setPodaciOPodnosiocu(kreirajPodatkeOPodnosiocu());
//		p1.setPodaciOPronalazacu(kreirajPodatkeOPronalazacu());
//		p1.setPodaciOPunomocniku(kreirajPodatkeOPunomocniku());
//		p1.setPodaciODostavljanju(kreirajPodatkeODostavljanju());
//		p1.setOsnovnaPrijava(kreirajOsnovnuPrijavu());
//		p1.setRanijePrijave(kreirajPrvenstvo());
//
//		return p1;
//	}
//
//	private Pronalazak kreirajPronalazak() {
//		Pronalazak pronalazak = new Pronalazak();
//		pronalazak.setNazivPronalaskaEng("eng_naziv");
//		pronalazak.setNazivPronalaskaRs("rs_naziv");
//		return pronalazak;
//	}
//
//	private PodaciOPunomocniku kreirajPodatkeOPunomocniku() {
//		PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
//		podaciOPunomocniku.setTipPunomocnika(TTipPunomocnika.ZAJEDNICKI_PREDSTAVNIK);
//		podaciOPunomocniku.setPunomocnikZaPrijemPismena(true);
//		podaciOPunomocniku.setPunomocnik(kreirajPravnoLice());
//		return podaciOPunomocniku;
//	}
//
//	private PodaciOPodnosiocu kreirajPodatkeOPodnosiocu() {
//		PodaciOPodnosiocu podaciOPodnosiocu = new PodaciOPodnosiocu();
//		podaciOPodnosiocu.setJePronalazac(false);
//		podaciOPodnosiocu.setPodnosilac(kreirajPravnoLice());
//		return podaciOPodnosiocu;
//	}
//
//	private PodaciOPronalazacu kreirajPodatkeOPronalazacu() {
//		PodaciOPronalazacu podaciOPronalazacu = new PodaciOPronalazacu();
//		podaciOPronalazacu.setNeZeliDaBudeNaveden(true);
//		podaciOPronalazacu.setPutanjaDoIzjave("../data/izjava-p1");
//		podaciOPronalazacu.setTipIzjave("slika");
//		return podaciOPronalazacu;
//	}
//
//	private PodaciODostavljanju kreirajPodatkeODostavljanju() {
//		PodaciODostavljanju podaciODostavljanju = new PodaciODostavljanju();
//		podaciODostavljanju.setAdresa(kreirajAdresu2());
//		podaciODostavljanju.setNacinDostavljanja(TNacinDostavljanja.U_PAPIRNOJ_FORMI);
//		return podaciODostavljanju;
//	}
//
////	private TPrijava kreirajPrijavu1() {
//////		TPrijava prijava = new TPrijava();
//////		prijava.setBrojPrijave(BigInteger.TEN);
//////		prijava.setDatumPrijema(new Date());
//////		prijava.setDatumPodnosenja(new Date());
//////		return prijava;
////	}
////
////	private TPrijava kreirajOsnovnuPrijavu() {
//////		TPrijava prijava = new TPrijava();
//////		prijava.setBrojPrijave(BigInteger.ONE);
//////		prijava.setDatumPodnosenja(new Date());
//////		return prijava;
////	}
//
//	private RanijePrijave kreirajPrvenstvo() {
//		RanijePrijave zahtev = new RanijePrijave();
//		List<TPrijava> ranijePrijave = new ArrayList<>();
//		TPrijava prijava = kreirajOsnovnuPrijavu();
//		prijava.setOznakaDrzave("RS");
//		ranijePrijave.add(kreirajOsnovnuPrijavu());
//		zahtev.setRanijePrijave(ranijePrijave);
//		return zahtev;
//	}
//
//	public static TPravnoLice kreirajPravnoLice() {
//		TPravnoLice pravnoLice = new TPravnoLice();
//		pravnoLice.setAdresa(kreirajAdresu1());
//		pravnoLice.setBrojMobilnogTelefona(new JAXBElement<>(new QName("http://www.xml.tim14.rs/korisnici", "broj_mobilnog_telefona", "ks"), String.class, "066684500"));
//		pravnoLice.setBrojFaksa(new JAXBElement<>(new QName("http://www.xml.tim14.rs/korisnici", "broj_faksa", "ks"), String.class, "055845000"));
//		pravnoLice.setEmail(new JAXBElement<>(new QName("http://www.xml.tim14.rs/korisnici", "email", "ks"), String.class, "podnosilac@gmail.com"));
//		pravnoLice.setPoslovnoIme("REMIX");
//		return pravnoLice;
//	}
//
//	public static Adresa kreirajAdresu1() {
//		Adresa adresa = new Adresa();
//		adresa.setMesto("Novi Sad");
//		adresa.setBroj("10");
//		adresa.setUlica("Dr Ivana Ribara");
//		adresa.setPostanskiBroj("21000");
//		return adresa;
//	}
//
//	public static Adresa kreirajAdresu2() {
//		Adresa adresa = new Adresa();
//		adresa.setMesto("Bijeljina");
//		adresa.setBroj("10");
//		adresa.setUlica("Nikole Tesle");
//		adresa.setPostanskiBroj("16 400");
//		return adresa;
//	}
//
//	public static void main(String[] args) {
//		P1Test test = new P1Test();
//		test.test();
//	}

}
