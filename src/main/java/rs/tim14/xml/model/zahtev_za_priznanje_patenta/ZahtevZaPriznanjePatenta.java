//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prijava" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TPrijava"/>
 *         &lt;element name="pronalazak">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="naziv_pronalaska_rs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="naziv_pronalaska_eng" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="podaci_o_podnosiocu">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="je_pronalazac" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="podnosilac" type="{http://www.xml.tim14.rs/korisnici}TLice"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="podaci_o_pronalazacu">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ne_zeli_da_bude_naveden" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="putanja_do_izjave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tip_izjave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="pronalazac" type="{http://www.xml.tim14.rs/korisnici}TFizicko_Lice" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="podaci_o_punomocniku">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tip_punomocnika" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TTip_punomocnika"/>
 *                   &lt;element name="punomocnik_za_prijem_pismena" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="punomocnik" type="{http://www.xml.tim14.rs/korisnici}TLice"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="podaci_o_dostavljanju">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nacin_dostavljanja" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TNacin_dostavljanja"/>
 *                   &lt;element ref="{http://www.xml.tim14.rs/korisnici}adresa" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="osnovna_prijava" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TPrijava" minOccurs="0"/>
 *         &lt;element name="zahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ranija_prijava" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TPrijava" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "prijava",
    "pronalazak",
    "podaciOPodnosiocu",
    "podaciOPronalazacu",
    "podaciOPunomocniku",
    "podaciODostavljanju",
    "osnovnaPrijava",
    "zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava"
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
    @XmlElementRef(name = "osnovna_prijava", namespace = "http://www.xml.tim14.rs/zahtev_za_priznanje_patenta", type = JAXBElement.class, required = false)
    protected JAXBElement<TPrijava> osnovnaPrijava;
    @XmlElement(name = "zahtev_za_priznanje_prava_prvenstva_iz_ranijih_prijava", required = true)
    protected ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava;

    /**
     * Gets the value of the prijava property.
     * 
     * @return
     *     possible object is
     *     {@link TPrijava }
     *     
     */
    public TPrijava getPrijava() {
        return prijava;
    }

    /**
     * Sets the value of the prijava property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrijava }
     *     
     */
    public void setPrijava(TPrijava value) {
        this.prijava = value;
    }

    /**
     * Gets the value of the pronalazak property.
     * 
     * @return
     *     possible object is
     *     {@link Pronalazak }
     *     
     */
    public Pronalazak getPronalazak() {
        return pronalazak;
    }

    /**
     * Sets the value of the pronalazak property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pronalazak }
     *     
     */
    public void setPronalazak(Pronalazak value) {
        this.pronalazak = value;
    }

    /**
     * Gets the value of the podaciOPodnosiocu property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciOPodnosiocu }
     *     
     */
    public PodaciOPodnosiocu getPodaciOPodnosiocu() {
        return podaciOPodnosiocu;
    }

    /**
     * Sets the value of the podaciOPodnosiocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciOPodnosiocu }
     *     
     */
    public void setPodaciOPodnosiocu(PodaciOPodnosiocu value) {
        this.podaciOPodnosiocu = value;
    }

    /**
     * Gets the value of the podaciOPronalazacu property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciOPronalazacu }
     *     
     */
    public PodaciOPronalazacu getPodaciOPronalazacu() {
        return podaciOPronalazacu;
    }

    /**
     * Sets the value of the podaciOPronalazacu property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciOPronalazacu }
     *     
     */
    public void setPodaciOPronalazacu(PodaciOPronalazacu value) {
        this.podaciOPronalazacu = value;
    }

    /**
     * Gets the value of the podaciOPunomocniku property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciOPunomocniku }
     *     
     */
    public PodaciOPunomocniku getPodaciOPunomocniku() {
        return podaciOPunomocniku;
    }

    /**
     * Sets the value of the podaciOPunomocniku property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciOPunomocniku }
     *     
     */
    public void setPodaciOPunomocniku(PodaciOPunomocniku value) {
        this.podaciOPunomocniku = value;
    }

    /**
     * Gets the value of the podaciODostavljanju property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciODostavljanju }
     *     
     */
    public PodaciODostavljanju getPodaciODostavljanju() {
        return podaciODostavljanju;
    }

    /**
     * Sets the value of the podaciODostavljanju property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciODostavljanju }
     *     
     */
    public void setPodaciODostavljanju(PodaciODostavljanju value) {
        this.podaciODostavljanju = value;
    }

    /**
     * Gets the value of the osnovnaPrijava property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TPrijava }{@code >}
     *     
     */
    public JAXBElement<TPrijava> getOsnovnaPrijava() {
        return osnovnaPrijava;
    }

    /**
     * Sets the value of the osnovnaPrijava property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TPrijava }{@code >}
     *     
     */
    public void setOsnovnaPrijava(JAXBElement<TPrijava> value) {
        this.osnovnaPrijava = value;
    }

    /**
     * Gets the value of the zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava property.
     * 
     * @return
     *     possible object is
     *     {@link ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava }
     *     
     */
    public ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava getZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava() {
        return zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava;
    }

    /**
     * Sets the value of the zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava }
     *     
     */
    public void setZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava(ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava value) {
        this.zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava = value;
    }

    @Override
    public String toString() {
        return "ZahtevZaPriznanjePatenta{" +
            "prijava=" + prijava +
            ", pronalazak=" + pronalazak +
            ", podaciOPodnosiocu=" + podaciOPodnosiocu +
            ", podaciOPronalazacu=" + podaciOPronalazacu +
            ", podaciOPunomocniku=" + podaciOPunomocniku +
            ", podaciODostavljanju=" + podaciODostavljanju +
            ", osnovnaPrijava=" + osnovnaPrijava +
            ", zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava=" + zahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava +
            '}';
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="nacin_dostavljanja" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TNacin_dostavljanja"/>
     *         &lt;element ref="{http://www.xml.tim14.rs/korisnici}adresa" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nacinDostavljanja",
        "adresa"
    })
    public static class PodaciODostavljanju {

        @XmlElement(name = "nacin_dostavljanja", required = true)
        protected TNacinDostavljanja nacinDostavljanja;
        @XmlElement(namespace = "http://www.xml.tim14.rs/korisnici", nillable = true)
        protected Adresa adresa;

        /**
         * Gets the value of the nacinDostavljanja property.
         * 
         * @return
         *     possible object is
         *     {@link TNacinDostavljanja }
         *     
         */
        public TNacinDostavljanja getNacinDostavljanja() {
            return nacinDostavljanja;
        }

        /**
         * Sets the value of the nacinDostavljanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link TNacinDostavljanja }
         *     
         */
        public void setNacinDostavljanja(TNacinDostavljanja value) {
            this.nacinDostavljanja = value;
        }

        /**
         * Gets the value of the adresa property.
         * 
         * @return
         *     possible object is
         *     {@link Adresa }
         *     
         */
        public Adresa getAdresa() {
            return adresa;
        }

        /**
         * Sets the value of the adresa property.
         * 
         * @param value
         *     allowed object is
         *     {@link Adresa }
         *     
         */
        public void setAdresa(Adresa value) {
            this.adresa = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="je_pronalazac" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="podnosilac" type="{http://www.xml.tim14.rs/korisnici}TLice"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "jePronalazac",
        "podnosilac"
    })
    public static class PodaciOPodnosiocu {

        @XmlElement(name = "je_pronalazac")
        protected boolean jePronalazac;
        @XmlElement(required = true)
        protected TLice podnosilac;

        /**
         * Gets the value of the jePronalazac property.
         * 
         */
        public boolean isJePronalazac() {
            return jePronalazac;
        }

        /**
         * Sets the value of the jePronalazac property.
         * 
         */
        public void setJePronalazac(boolean value) {
            this.jePronalazac = value;
        }

        /**
         * Gets the value of the podnosilac property.
         * 
         * @return
         *     possible object is
         *     {@link TLice }
         *     
         */
        public TLice getPodnosilac() {
            return podnosilac;
        }

        /**
         * Sets the value of the podnosilac property.
         * 
         * @param value
         *     allowed object is
         *     {@link TLice }
         *     
         */
        public void setPodnosilac(TLice value) {
            this.podnosilac = value;
        }

        @Override
        public String toString() {
            return "PodaciOPodnosiocu{" +
                "jePronalazac=" + jePronalazac +
                ", podnosilac=" + podnosilac +
                '}';
        }
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ne_zeli_da_bude_naveden" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="putanja_do_izjave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tip_izjave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="pronalazac" type="{http://www.xml.tim14.rs/korisnici}TFizicko_Lice" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "neZeliDaBudeNaveden",
        "putanjaDoIzjave",
        "tipIzjave",
        "pronalazac"
    })
    public static class PodaciOPronalazacu {

        @XmlElement(name = "ne_zeli_da_bude_naveden")
        protected boolean neZeliDaBudeNaveden;
        @XmlElement(name = "putanja_do_izjave")
        protected String putanjaDoIzjave;
        @XmlElement(name = "tip_izjave")
        protected String tipIzjave;
        protected TFizickoLice pronalazac;

        /**
         * Gets the value of the neZeliDaBudeNaveden property.
         * 
         */
        public boolean isNeZeliDaBudeNaveden() {
            return neZeliDaBudeNaveden;
        }

        /**
         * Sets the value of the neZeliDaBudeNaveden property.
         * 
         */
        public void setNeZeliDaBudeNaveden(boolean value) {
            this.neZeliDaBudeNaveden = value;
        }

        /**
         * Gets the value of the putanjaDoIzjave property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPutanjaDoIzjave() {
            return putanjaDoIzjave;
        }

        /**
         * Sets the value of the putanjaDoIzjave property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPutanjaDoIzjave(String value) {
            this.putanjaDoIzjave = value;
        }

        /**
         * Gets the value of the tipIzjave property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipIzjave() {
            return tipIzjave;
        }

        /**
         * Sets the value of the tipIzjave property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipIzjave(String value) {
            this.tipIzjave = value;
        }

        /**
         * Gets the value of the pronalazac property.
         * 
         * @return
         *     possible object is
         *     {@link TFizickoLice }
         *     
         */
        public TFizickoLice getPronalazac() {
            return pronalazac;
        }

        /**
         * Sets the value of the pronalazac property.
         * 
         * @param value
         *     allowed object is
         *     {@link TFizickoLice }
         *     
         */
        public void setPronalazac(TFizickoLice value) {
            this.pronalazac = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="tip_punomocnika" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TTip_punomocnika"/>
     *         &lt;element name="punomocnik_za_prijem_pismena" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="punomocnik" type="{http://www.xml.tim14.rs/korisnici}TLice"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tipPunomocnika",
        "punomocnikZaPrijemPismena",
        "punomocnik"
    })
    public static class PodaciOPunomocniku {

        @XmlElement(name = "tip_punomocnika", required = true)
        protected TTipPunomocnika tipPunomocnika;
        @XmlElement(name = "punomocnik_za_prijem_pismena")
        protected boolean punomocnikZaPrijemPismena;
        @XmlElement(required = true)
        protected TLice punomocnik;

        /**
         * Gets the value of the tipPunomocnika property.
         * 
         * @return
         *     possible object is
         *     {@link TTipPunomocnika }
         *     
         */
        public TTipPunomocnika getTipPunomocnika() {
            return tipPunomocnika;
        }

        /**
         * Sets the value of the tipPunomocnika property.
         * 
         * @param value
         *     allowed object is
         *     {@link TTipPunomocnika }
         *     
         */
        public void setTipPunomocnika(TTipPunomocnika value) {
            this.tipPunomocnika = value;
        }

        /**
         * Gets the value of the punomocnikZaPrijemPismena property.
         * 
         */
        public boolean isPunomocnikZaPrijemPismena() {
            return punomocnikZaPrijemPismena;
        }

        /**
         * Sets the value of the punomocnikZaPrijemPismena property.
         * 
         */
        public void setPunomocnikZaPrijemPismena(boolean value) {
            this.punomocnikZaPrijemPismena = value;
        }

        /**
         * Gets the value of the punomocnik property.
         * 
         * @return
         *     possible object is
         *     {@link TLice }
         *     
         */
        public TLice getPunomocnik() {
            return punomocnik;
        }

        /**
         * Sets the value of the punomocnik property.
         * 
         * @param value
         *     allowed object is
         *     {@link TLice }
         *     
         */
        public void setPunomocnik(TLice value) {
            this.punomocnik = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="naziv_pronalaska_rs" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="naziv_pronalaska_eng" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nazivPronalaskaRs",
        "nazivPronalaskaEng"
    })
    public static class Pronalazak {

        @XmlElement(name = "naziv_pronalaska_rs", required = true)
        protected String nazivPronalaskaRs;
        @XmlElement(name = "naziv_pronalaska_eng", required = true)
        protected String nazivPronalaskaEng;

        /**
         * Gets the value of the nazivPronalaskaRs property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivPronalaskaRs() {
            return nazivPronalaskaRs;
        }

        /**
         * Sets the value of the nazivPronalaskaRs property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivPronalaskaRs(String value) {
            this.nazivPronalaskaRs = value;
        }

        /**
         * Gets the value of the nazivPronalaskaEng property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivPronalaskaEng() {
            return nazivPronalaskaEng;
        }

        /**
         * Sets the value of the nazivPronalaskaEng property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivPronalaskaEng(String value) {
            this.nazivPronalaskaEng = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ranija_prijava" type="{http://www.xml.tim14.rs/zahtev_za_priznanje_patenta}TPrijava" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ranijaPrijava"
    })
    public static class ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava {

        @XmlElement(name = "ranija_prijava")
        protected List<TPrijava> ranijaPrijava;

        /**
         * Gets the value of the ranijaPrijava property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ranijaPrijava property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRanijaPrijava().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TPrijava }
         * 
         * 
         */
        public List<TPrijava> getRanijaPrijava() {
            if (ranijaPrijava == null) {
                ranijaPrijava = new ArrayList<TPrijava>();
            }
            return this.ranijaPrijava;
        }

    }

}
