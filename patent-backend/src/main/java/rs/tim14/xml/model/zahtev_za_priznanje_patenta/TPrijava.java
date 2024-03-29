//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrijava", propOrder = {
        "brojPrijave",
        "datumPodnosenja",
        "datumPrijema",
        "oznakaDrzave"
})
public class TPrijava {

    @XmlElement(name = "broj_prijave", required = true)
    protected TPrijava.BrojPrijave brojPrijave;
    @XmlElement(name = "datum_podnosenja", required = false)
    protected TPrijava.DatumPodnosenja datumPodnosenja;
    @XmlElement(name = "datum_prijema")
    protected TPrijava.DatumPrijema datumPrijema;
    @XmlElement(name = "oznaka_drzave")
    protected String oznakaDrzave;

    /**
     * Gets the value of the brojPrijave property.
     *
     * @return
     *     possible object is
     *     {@link TPrijava.BrojPrijave }
     *
     */
    public TPrijava.BrojPrijave getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     *
     * @param value
     *     allowed object is
     *     {@link TPrijava.BrojPrijave }
     *
     */
    public void setBrojPrijave(TPrijava.BrojPrijave value) {
        this.brojPrijave = value;
    }

    /**
     * Gets the value of the datumPodnosenja property.
     *
     * @return
     *     possible object is
     *     {@link TPrijava.DatumPodnosenja }
     *
     */
    public TPrijava.DatumPodnosenja getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     *
     * @param value
     *     allowed object is
     *     {@link TPrijava.DatumPodnosenja }
     *
     */
    public void setDatumPodnosenja(TPrijava.DatumPodnosenja value) {
        this.datumPodnosenja = value;
    }

    /**
     * Gets the value of the datumPrijema property.
     *
     * @return
     *     possible object is
     *     {@link TPrijava.DatumPrijema }
     *
     */
    public TPrijava.DatumPrijema getDatumPrijema() {
        return datumPrijema;
    }

    /**
     * Sets the value of the datumPrijema property.
     *
     * @param value
     *     allowed object is
     *     {@link TPrijava.DatumPrijema }
     *
     */
    public void setDatumPrijema(TPrijava.DatumPrijema value) {
        this.datumPrijema = value;
    }

    /**
     * Gets the value of the oznakaDrzave property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOznakaDrzave() {
        return oznakaDrzave;
    }

    /**
     * Sets the value of the oznakaDrzave property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOznakaDrzave(String value) {
        this.oznakaDrzave = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>positiveInteger">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class BrojPrijave {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class DatumPodnosenja {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class DatumPrijema {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("broj prijave: ").append(brojPrijave);
        if (datumPodnosenja != null) sb.append(", datum podnosenja: ").append(datumPodnosenja);
        if (datumPrijema != null) sb.append(", datum prijema: ").append(datumPrijema);
        if (oznakaDrzave != null) sb.append(", oznaka drzave:'").append(oznakaDrzave).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
