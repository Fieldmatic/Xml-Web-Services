//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.tim14.xml.zahtev_za_priznanje_patenta package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ZahtevZaPriznanjePatentaOsnovnaPrijava_QNAME = new QName("http://www.xml.tim14.rs/zahtev_za_priznanje_patenta", "osnovna_prijava");

    public ObjectFactory() {
    }

    public ZahtevZaPriznanjePatenta createZahtevZaPriznanjePatenta() {
        return new ZahtevZaPriznanjePatenta();
    }

    public TPrijava createTPrijava() {
        return new TPrijava();
    }

    public Pronalazak createZahtevZaPriznanjePatentaPronalazak() {
        return new Pronalazak();
    }

    public PodaciOPodnosiocu createZahtevZaPriznanjePatentaPodaciOPodnosiocu() {
        return new PodaciOPodnosiocu();
    }

    public PodaciOPronalazacu createZahtevZaPriznanjePatentaPodaciOPronalazacu() {
        return new PodaciOPronalazacu();
    }

    public PodaciOPunomocniku createZahtevZaPriznanjePatentaPodaciOPunomocniku() {
        return new PodaciOPunomocniku();
    }

    public PodaciODostavljanju createZahtevZaPriznanjePatentaPodaciODostavljanju() {
        return new PodaciODostavljanju();
    }

    public RanijePrijave createZahtevZaPriznanjePatentaZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava() {
        return new RanijePrijave();
    }

    @XmlElementDecl(namespace = "http://www.xml.tim14.rs/zahtev_za_priznanje_patenta", name = "osnovna_prijava", scope = ZahtevZaPriznanjePatenta.class)
    public JAXBElement<TPrijava> createZahtevZaPriznanjePatentaOsnovnaPrijava(TPrijava value) {
        return new JAXBElement<TPrijava>(_ZahtevZaPriznanjePatentaOsnovnaPrijava_QNAME, TPrijava.class, ZahtevZaPriznanjePatenta.class, value);
    }

}
