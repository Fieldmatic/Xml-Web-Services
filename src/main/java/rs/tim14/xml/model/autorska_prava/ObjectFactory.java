//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:12 PM CET 
//


package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.tim14.xml.autorska_prava package. 
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

    private final static QName _ZahtevZaAutorskaPravaPunomocnik_QNAME = new QName("http://www.xml.tim14.rs/autorska_prava", "punomocnik");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.tim14.xml.autorska_prava
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava }
     * 
     */
    public ZahtevZaAutorskaPrava createZahtevZaAutorskaPrava() {
        return new ZahtevZaAutorskaPrava();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.AutorskoDelo }
     * 
     */
    public ZahtevZaAutorskaPrava.AutorskoDelo createZahtevZaAutorskaPravaAutorskoDelo() {
        return new ZahtevZaAutorskaPrava.AutorskoDelo();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.Punomocnik }
     * 
     */
    public ZahtevZaAutorskaPrava.Punomocnik createZahtevZaAutorskaPravaPunomocnik() {
        return new ZahtevZaAutorskaPrava.Punomocnik();
    }

    /**
     * Create an instance of {@link TAutor }
     * 
     */
    public TAutor createTAutor() {
        return new TAutor();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.AutorskoDelo.PodaciOAutoru }
     * 
     */
    public ZahtevZaAutorskaPrava.AutorskoDelo.PodaciOAutoru createZahtevZaAutorskaPravaAutorskoDeloPodaciOAutoru() {
        return new ZahtevZaAutorskaPrava.AutorskoDelo.PodaciOAutoru();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.AutorskoDelo.IzvornoAutorskoDelo }
     * 
     */
    public ZahtevZaAutorskaPrava.AutorskoDelo.IzvornoAutorskoDelo createZahtevZaAutorskaPravaAutorskoDeloIzvornoAutorskoDelo() {
        return new ZahtevZaAutorskaPrava.AutorskoDelo.IzvornoAutorskoDelo();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.AutorskoDelo.PrimerAutorskogDela }
     * 
     */
    public ZahtevZaAutorskaPrava.AutorskoDelo.PrimerAutorskogDela createZahtevZaAutorskaPravaAutorskoDeloPrimerAutorskogDela() {
        return new ZahtevZaAutorskaPrava.AutorskoDelo.PrimerAutorskogDela();
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaPrava.AutorskoDelo.OpisAutorskogDela }
     * 
     */
    public ZahtevZaAutorskaPrava.AutorskoDelo.OpisAutorskogDela createZahtevZaAutorskaPravaAutorskoDeloOpisAutorskogDela() {
        return new ZahtevZaAutorskaPrava.AutorskoDelo.OpisAutorskogDela();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZahtevZaAutorskaPrava.Punomocnik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml.tim14.rs/autorska_prava", name = "punomocnik", scope = ZahtevZaAutorskaPrava.class)
    public JAXBElement<ZahtevZaAutorskaPrava.Punomocnik> createZahtevZaAutorskaPravaPunomocnik(ZahtevZaAutorskaPrava.Punomocnik value) {
        return new JAXBElement<ZahtevZaAutorskaPrava.Punomocnik>(_ZahtevZaAutorskaPravaPunomocnik_QNAME, ZahtevZaAutorskaPrava.Punomocnik.class, ZahtevZaAutorskaPrava.class, value);
    }

}
