//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:12 PM CET 
//


package rs.tim14.xml.model.autorska_prava;

import java.math.BigInteger;
import java.util.*;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.PunoIme;
import rs.tim14.xml.model.korisnici.TLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prijava",
    "podnosilac",
    "punomocnik",
    "autorskoDelo"
})
@XmlRootElement(name = "zahtev_za_autorska_prava")
public class ZahtevZaAutorskaPrava {

    @XmlElement(required = true)
    protected TPrijava prijava;

    @XmlElement(required = true)
    protected TLice podnosilac;

    @XmlElement(name = "punomocnik", required = false)
    protected Punomocnik punomocnik;
    @XmlElement(name = "autorsko_delo", required = true)
    protected AutorskoDelo autorskoDelo;
    @XmlAnyAttribute
    private Map<QName, String> references = new HashMap<QName, String>();

    public Map<QName, String> getReferences() {
        return references;
    }

    public TPrijava getPrijava() {
        return prijava;
    }
    public void setPrijava(TPrijava value) {
        this.prijava = value;
    }

    public TLice getPodnosilac() {
        return podnosilac;
    }

    public void setPodnosilac(TLice value) {
        this.podnosilac = value;
    }

    public Punomocnik getPunomocnik() {
        return punomocnik;
    }

    public void setPunomocnik(Punomocnik value) {
        this.punomocnik = value;
    }

    public AutorskoDelo getAutorskoDelo() {
        return autorskoDelo;
    }

    public void setAutorskoDelo(AutorskoDelo value) {
        this.autorskoDelo = value;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Zahtev za autorska prava:");
        sb.append("\n\tDatum podnosenja: ").append(prijava.datumPodnosenja);
        sb.append("\n\tBroj prijave: ").append(prijava.brojPrijave);
        sb.append("\n\tPodnosilac:").append(podnosilac.toString("\t\t"));
        sb.append("\n\tPunomocnik: ").append(punomocnik);
        sb.append("\n\tAutorsko delo: ").append(autorskoDelo);
        return sb.toString();
    }
}
