//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prijava",
    "pronalazak",
    "podaciOPodnosiocu",
    "podaciOPronalazacu",
    "podaciOPunomocniku",
    "podaciODostavljanju",
    "osnovnaPrijava",
    "ranijePrijave"
})
@XmlRootElement(name = "zahtev_za_priznanje_patenta")
public class ZahtevZaPriznanjePatenta {

    @XmlElement(required = true)
    protected TPrijava prijava;
    @XmlElement(required = true)
    protected Pronalazak pronalazak;
    @XmlElement(name = "podaci_o_podnosiocu", required = true)
    protected PodaciOPodnosiocu podaciOPodnosiocu;
    @XmlElement(name = "podaci_o_pronalazacu", required = true)
    protected PodaciOPronalazacu podaciOPronalazacu;
    @XmlElement(name = "podaci_o_punomocniku", required = true)
    protected PodaciOPunomocniku podaciOPunomocniku;
    @XmlElement(name = "podaci_o_dostavljanju", required = true)
    protected PodaciODostavljanju podaciODostavljanju;
    @XmlElement(name = "osnovna_prijava", required = false)
    protected TPrijava osnovnaPrijava;
    @XmlElement(name = "ranije_prijave", required = true)
    protected RanijePrijave ranijePrijave;

    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;

    @XmlAnyAttribute
    private Map<QName, String> references = new HashMap<QName, String>();

    public Map<QName, String> getReferences() {
        return references;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public TPrijava getPrijava() {
        return prijava;
    }

    public void setPrijava(TPrijava value) {
        this.prijava = value;
    }

    public Pronalazak getPronalazak() {
        return pronalazak;
    }
    public void setPronalazak(Pronalazak value) {
        this.pronalazak = value;
    }
    public PodaciOPodnosiocu getPodaciOPodnosiocu() {
        return podaciOPodnosiocu;
    }
    public void setPodaciOPodnosiocu(PodaciOPodnosiocu value) {
        this.podaciOPodnosiocu = value;
    }
    public PodaciOPronalazacu getPodaciOPronalazacu() {
        return podaciOPronalazacu;
    }
    public void setPodaciOPronalazacu(PodaciOPronalazacu value) {
        this.podaciOPronalazacu = value;
    }
    public PodaciOPunomocniku getPodaciOPunomocniku() {
        return podaciOPunomocniku;
    }
    public void setPodaciOPunomocniku(PodaciOPunomocniku value) {
        this.podaciOPunomocniku = value;
    }
    public PodaciODostavljanju getPodaciODostavljanju() {
        return podaciODostavljanju;
    }
    public void setPodaciODostavljanju(PodaciODostavljanju value) {
        this.podaciODostavljanju = value;
    }
    public TPrijava getOsnovnaPrijava() {
        return osnovnaPrijava;
    }
    public void setOsnovnaPrijava(TPrijava value) {
        this.osnovnaPrijava = value;
    }
    public RanijePrijave getRanijePrijave() {
        return ranijePrijave;
    }
    public void setRanijePrijave(RanijePrijave value) {
        this.ranijePrijave = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ZahtevZaPriznanjePatenta: ");
        sb.append("\n\tPrijava: ").append(prijava);
        sb.append("\n\tPronalazak: ").append(pronalazak);
        sb.append("\n\tPodaci o podnosiocu: ").append(podaciOPodnosiocu);
        sb.append("\n\tPodaci o pronalazacu: ").append(podaciOPronalazacu);
        sb.append("\n\tPodaci o punomocniku: ").append(podaciOPunomocniku);
        sb.append("\n\tPodaci o dostavljanju: ").append(podaciODostavljanju);
        sb.append("\n\tOsnovna prijava: ").append(osnovnaPrijava);
        sb.append("\n\tZahtev za priznanje prava prvenstva iz ranijih prijava:").append(ranijePrijave);
        sb.append('}');
        return sb.toString();
    }
}
