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

import rs.tim14.xml.model.autorska_prava.PodaciOAutorima.TAutor;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ZahtevZaAutorskaPravaPunomocnik_QNAME = new QName("http://www.xml.tim14.rs/autorska_prava", "punomocnik");

    public ObjectFactory() {
    }

    public ZahtevZaAutorskaPrava createZahtevZaAutorskaPrava() {
        return new ZahtevZaAutorskaPrava();
    }

    public AutorskoDelo createZahtevZaAutorskaPravaAutorskoDelo() {
        return new AutorskoDelo();
    }
    public Punomocnik createZahtevZaAutorskaPravaPunomocnik() {
        return new Punomocnik();
    }

    public TAutor createTAutor() {
        return new TAutor();
    }

    public PodaciOAutorima createZahtevZaAutorskaPravaAutorskoDeloPodaciOAutoru() {
        return new PodaciOAutorima();
    }

    public IzvornoAutorskoDelo createZahtevZaAutorskaPravaAutorskoDeloIzvornoAutorskoDelo() {
        return new IzvornoAutorskoDelo();
    }

    public PrimerAutorskogDela createZahtevZaAutorskaPravaAutorskoDeloPrimerAutorskogDela() {
        return new PrimerAutorskogDela();
    }

    public OpisAutorskogDela createZahtevZaAutorskaPravaAutorskoDeloOpisAutorskogDela() {
        return new OpisAutorskogDela();
    }

    @XmlElementDecl(namespace = "http://www.xml.tim14.rs/autorska_prava", name = "punomocnik", scope = ZahtevZaAutorskaPrava.class)
    public JAXBElement<Punomocnik> createZahtevZaAutorskaPravaPunomocnik(Punomocnik value) {
        return new JAXBElement<Punomocnik>(_ZahtevZaAutorskaPravaPunomocnik_QNAME, Punomocnik.class, ZahtevZaAutorskaPrava.class, value);
    }

}
